package net.orcinus.goodending.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.goodending.entities.ai.FlyAroundGoal;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingParticleTypes;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class FireflyEntity extends PathAwareEntity implements Flutterer {
    private static final TrackedData<Integer> COUNT = DataTracker.registerData(FireflyEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> FROM_BOTTLE = DataTracker.registerData(FireflyEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public FireflyEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt) {
        this.setCount(this.getRandom().nextBetween(1,3));
        return super.initialize(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COUNT, 1);
        this.dataTracker.startTracking(FROM_BOTTLE, false);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Count", this.getCount());
        nbt.putBoolean("FromBottle", this.isFromBottle());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setCount(nbt.getInt("Count"));
        this.setFromBottle(nbt.getBoolean("FromBottle"));
    }

    public boolean isFromBottle() {
        return this.dataTracker.get(FROM_BOTTLE);
    }

    public void setFromBottle(boolean fromBottle) {
        this.dataTracker.set(FROM_BOTTLE, fromBottle);
    }

    public void setCount(int count) {
        this.dataTracker.set(COUNT, count);
    }

    public int getCount() {
        return this.dataTracker.get(COUNT);
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyAroundGoal(this));
        this.goalSelector.add(2, new SwimGoal(this));
    }

    @Override
    public boolean doesNotCollide(double offsetX, double offsetY, double offsetZ) {
        return true;
    }

    @Override
    protected void knockback(LivingEntity target) {
        target.takeKnockback(0, this.getX(), this.getZ());
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.GLASS_BOTTLE) && this.getCount() > 0) {
            this.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            if (!this.world.isClient()) {
                this.setCount(this.getCount() - 1);
                this.emitGameEvent(GameEvent.ENTITY_INTERACT);
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(GoodEndingItems.FIREFLY_BOTTLE)));
                if (this.getCount() == 0) this.discard();
            }
            return ActionResult.SUCCESS;
        }
        if (stack.isOf(GoodEndingItems.FIREFLY_BOTTLE) && this.getCount() < 3 && !this.world.isClient()) {
            if (!this.world.isClient()) {
                this.setCount(this.getCount() + 1);
                this.emitGameEvent(GameEvent.ENTITY_INTERACT);
                this.setFromBottle(true);
                player.setStackInHand(hand, ItemUsage.exchangeStack(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            this.world.playSound(null, this.getBlockPos(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }

        return super.interactMob(player, hand);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.world.isDay() && !this.world.isClient()) {
            this.world.getNonSpectatingEntities(PlayerEntity.class, new Box(this.getBlockPos()).expand(16.0D)).stream().filter(Objects::isNull).forEach(player -> this.discard());
        }
    }

    @Override
    public void tick() {
        super.tick();

        int count = this.getCount();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        float width = 1 + (count - 1) * 0.5F;
        Random random = this.getWorld().random;
        double j = random.nextGaussian() * 0.025;
        double k = random.nextGaussian() * 0.025;
        double l = random.nextGaussian() * 0.025;

        if (this.isAlive()) {
            if (world.getTimeOfDay() < 12000 && world.getTimeOfDay() > 0) {
                mutable.set(this.getX() + MathHelper.nextBetween(random, -0.25f, 0.25f), this.getBlockPos().getY(), this.getZ() + MathHelper.nextBetween(random, -0.25f, 0.25f));
                for (int i = 0; i < 0.5; i++) if (this.random.nextFloat() < 0.01F) world.addParticle(GoodEndingParticleTypes.FIREFLY, mutable.getX() + random.nextDouble(), this.getY() + random.nextDouble(), mutable.getZ() + random.nextDouble(), j, k, l);
            }
            else {
                mutable.set(this.getX() + MathHelper.nextBetween(random, -width, width), this.getBlockPos().getY(), this.getZ() + MathHelper.nextBetween(random, -width, width));
                for (int i = 0; i < count; i++) if (this.random.nextFloat() < 0.1F) world.addParticle(GoodEndingParticleTypes.FIREFLY, mutable.getX() + random.nextDouble(), this.getY() + random.nextDouble(), mutable.getZ() + random.nextDouble(), j, k, l);
            }

        }
    }

    public static DefaultAttributeContainer.Builder createFireflyAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.1f).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1f).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 48.0);
    }

    @SuppressWarnings("unused")
    public static boolean canSpawn(EntityType<FireflyEntity> entity, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getLightLevel(pos) < UniformIntProvider.create(0, 7).get(random);
    }

    @Override
    protected void pushAway(Entity entity) {
    }

    @Override
    protected void tickCramming() {
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isFromBottle();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    public boolean isInAir() {
        return !this.onGround;
    }

    @Override
    public boolean occludeVibrationSignals() {
        return true;
    }
}
