package net.orcinus.goodending.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.orcinus.goodending.entities.ai.FlyAroundGoal;
import net.orcinus.goodending.init.GoodEndingCriteriaTriggers;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingParticleTypes;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class FireflyEntity extends PathfinderMob implements FlyingAnimal {
    private static final EntityDataAccessor<Integer> COUNT = SynchedEntityData.defineId(FireflyEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FROM_BOTTLE = SynchedEntityData.defineId(FireflyEntity.class, EntityDataSerializers.BOOLEAN);

    public FireflyEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, @Nullable SpawnGroupData entityData, @Nullable CompoundTag entityNbt) {
        this.setCount(this.getRandom().nextIntBetweenInclusive(1,3));
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(COUNT, 1);
        this.entityData.define(FROM_BOTTLE, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putInt("Count", this.getCount());
        nbt.putBoolean("FromBottle", this.isFromBottle());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setCount(nbt.getInt("Count"));
        this.setFromBottle(nbt.getBoolean("FromBottle"));
    }

    public boolean isFromBottle() {
        return this.entityData.get(FROM_BOTTLE);
    }

    public void setFromBottle(boolean fromBottle) {
        this.entityData.set(FROM_BOTTLE, fromBottle);
    }

    public void setCount(int count) {
        this.entityData.set(COUNT, count);
    }

    public int getCount() {
        return this.entityData.get(COUNT);
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world);
        birdNavigation.setCanOpenDoors(false);
        birdNavigation.setCanFloat(true);
        birdNavigation.setCanPassDoors(true);
        return birdNavigation;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FlyAroundGoal(this));
        this.goalSelector.addGoal(2, new FloatGoal(this));
    }

    @Override
    public boolean isFree(double p_20230_, double p_20231_, double p_20232_) {
        return true;
    }

    @Override
    protected void blockedByShield(LivingEntity target) {
        target.knockback(0, this.getX(), this.getZ());
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(Items.GLASS_BOTTLE) && this.getCount() > 0) {
            this.level().playSound(player, player.getX(), player.getY(), player.getZ(), GoodEndingSoundEvents.ITEM_FIREFLY_BOTTLE_FILL, SoundSource.NEUTRAL, 2.0f, 1.0f);
            if (!this.level().isClientSide()) {
                if (player instanceof ServerPlayer serverPlayer) {
                    GoodEndingCriteriaTriggers.CAPTURE_FIREFLY.trigger(serverPlayer);
                }
                this.setCount(this.getCount() - 1);
                this.gameEvent(GameEvent.ENTITY_INTERACT);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(GoodEndingItems.FIREFLY_BOTTLE)));
                if (this.getCount() == 0) this.discard();
            }
            return InteractionResult.SUCCESS;
        }
        if (stack.is(GoodEndingItems.FIREFLY_BOTTLE) && this.getCount() < 3 && !this.level().isClientSide()) {
            if (!this.level().isClientSide()) {
                this.setCount(this.getCount() + 1);
                this.gameEvent(GameEvent.ENTITY_INTERACT);
                this.setFromBottle(true);
                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(Items.GLASS_BOTTLE)));
            }
            this.level().playSound(null, this.blockPosition(), GoodEndingSoundEvents.ITEM_FIREFLY_BOTTLE_EMPTY, SoundSource.NEUTRAL, 2.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level().isDay() && !this.level().isClientSide) {
            this.level().getEntitiesOfClass(Player.class, new AABB(this.blockPosition()).inflate(16.0D)).stream().filter(Objects::isNull).forEach(player -> this.discard());
        }
    }

    @Override
    public void tick() {
        super.tick();

        int count = this.getCount();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        float width = 1 + (count - 1) * 0.5F;
        RandomSource random = this.level().random;
        double j = random.nextGaussian() * 0.025;
        double k = random.nextGaussian() * 0.025;
        double l = random.nextGaussian() * 0.025;

        if (this.isAlive()) {
            if (this.level().getDayTime() < 12000 && this.level().getDayTime() > 0) {
                mutable.set(this.getX() + Mth.randomBetween(random, -0.25f, 0.25f), this.blockPosition().getY(), this.getZ() + Mth.randomBetween(random, -0.25f, 0.25f));
                for (int i = 0; i < 0.5; i++) {
                    if (this.random.nextFloat() < 0.01F) this.addParticle(mutable, random, j, k, l);
                }
            }
            else {
                mutable.set(this.getX() + Mth.randomBetween(random, -width, width), this.blockPosition().getY(), this.getZ() + Mth.randomBetween(random, -width, width));
                for (int i = 0; i < count; i++) {
                    if (this.random.nextFloat() < 0.1F) {
                        this.addParticle(mutable, random, j, k, l);
                    }
                }
            }

        }
    }

    private void addParticle(BlockPos.MutableBlockPos mutable, RandomSource random, double j, double k, double l) {
        this.level().addParticle(GoodEndingParticleTypes.FIREFLY, mutable.getX() + random.nextDouble(), this.getY() + random.nextDouble(), mutable.getZ() + random.nextDouble(), j, k, l);
    }

    public static AttributeSupplier.Builder createFireflyAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0).add(Attributes.FLYING_SPEED, 0.1f).add(Attributes.MOVEMENT_SPEED, 0.1f).add(Attributes.FOLLOW_RANGE, 48.0);
    }

    public static boolean canSpawn(EntityType<FireflyEntity> entity, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getLevel().isNight() && world.getBrightness(LightLayer.BLOCK, pos) < UniformInt.of(0, 7).sample(random) && world.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
    }

    @Override
    protected void doPush(Entity p_20971_) {
    }

    @Override
    protected void pushEntities() {
    }

    @Override
    public boolean canBeLeashed(Player p_21418_) {
        return false;
    }

    @Override
    public boolean startRiding(Entity p_20330_) {
        return false;
    }

    @Override
    public boolean isAttackable() {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double p_21542_) {
        return !this.isFromBottle();
    }

    @Override
    public boolean requiresCustomPersistence() {
        return true;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.level().getDayTime() < 12000 && this.level().getDayTime() > 0) return null;
        return GoodEndingSoundEvents.ENTITY_FIREFLY_SWARM_IDLE;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 200;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    protected MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }
}