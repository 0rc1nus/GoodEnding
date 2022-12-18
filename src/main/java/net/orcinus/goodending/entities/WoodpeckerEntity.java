package net.orcinus.goodending.entities;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.orcinus.goodending.entities.ai.FindWoodGoal;
import net.orcinus.goodending.entities.ai.FlyAroundGoal;
import net.orcinus.goodending.entities.ai.MoveToWoodGoal;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

public class WoodpeckerEntity extends PathfinderMob implements FlyingAnimal {
    protected static final EntityDataAccessor<Direction> ATTACHED_FACE = SynchedEntityData.defineId(WoodpeckerEntity.class, EntityDataSerializers.DIRECTION);
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0f;
    private float field_28640 = 1.0f;
    private int peckingWoodCooldown;
    @Nullable
    public BlockPos woodPos;
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState standingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();

    public WoodpeckerEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
        this.moveControl = new FlyingMoveControl(this, 20, true);
        this.setPose(Pose.FALL_FLYING);
    }

    public static AttributeSupplier.Builder createWoodPeckerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.FLYING_SPEED, 0.4f)
                .add(Attributes.MOVEMENT_SPEED, 0.2f);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACHED_FACE, Direction.DOWN);
    }

    public Direction getAttachedFace() {
        return this.entityData.get(ATTACHED_FACE);
    }

    public void setAttachedFace(Direction attachedFace) {
        this.entityData.set(ATTACHED_FACE, attachedFace);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        this.setWoodPos(null);
        if (nbt.contains("WoodPos")) {
            this.setWoodPos(NbtUtils.readBlockPos(nbt.getCompound("WoodPos")));
        }
        this.setAttachedFace(Direction.from3DDataValue(nbt.getByte("AttachFace")));
        this.setPeckingWoodCooldown(nbt.getInt("PeckingWoodCooldownTicks"));
        super.readAdditionalSaveData(nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        if (this.hasWood()) {
            nbt.put("WoodPos", NbtUtils.writeBlockPos(this.blockPosition()));
        }
        nbt.putByte("AttachFace", (byte) this.getAttachedFace().get3DDataValue());
        nbt.putInt("PeckingWoodCooldownTicks", this.getPeckingWoodCooldown());
    }

    public int getPeckingWoodCooldown() {
        return this.peckingWoodCooldown;
    }

    public void setPeckingWoodCooldown(int peckingWoodCooldown) {
        this.peckingWoodCooldown = peckingWoodCooldown;
    }

    public boolean hasWood() {
        return this.getWoodPos() != null;
    }

    public void setWoodPos(BlockPos blockPos) {
        this.woodPos = blockPos;
    }

    @Nullable
    public BlockPos getWoodPos() {
        return this.woodPos;
    }

    @Override
    public void tick() {
        if (this.level.isClientSide) {
            if (this.getPose() == Pose.FALL_FLYING) {
                this.flyingAnimationState.startIfStopped(this.tickCount);
            } else {
                this.flyingAnimationState.stop();
            }
            if (this.getPose() == Pose.STANDING) {
                this.standingAnimationState.startIfStopped(this.tickCount);
            } else {
                this.standingAnimationState.stop();
            }
            if (this.getPose() == Pose.DIGGING) {
                this.peckingAnimationState.startIfStopped(this.tickCount);
            } else {
                this.peckingAnimationState.stop();
            }
        } else {
            if (this.getPeckingWoodCooldown() > 0) {
                this.setPeckingWoodCooldown(this.getPeckingWoodCooldown() - 1);
            }
            if (this.getWoodPos() == null && this.getAttachedFace() != Direction.DOWN) {
                this.setAttachedFace(Direction.DOWN);
            }
            if (this.getWoodPos() != null && !this.level.getBlockState(this.getWoodPos()).is(BlockTags.LOGS)) {
                this.setWoodPos(null);
            }
        }
        super.tick();
    }

    @Override
    protected PathNavigation createNavigation(Level world) {
        FlyingPathNavigation birdNavigation = new FlyingPathNavigation(this, world);
        birdNavigation.setCanPassDoors(false);
        birdNavigation.setCanFloat(true);
        birdNavigation.setCanOpenDoors(true);
        return birdNavigation;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.flapWings();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new FlyAroundGoal(this));
        this.goalSelector.addGoal(3, new FloatGoal(this));
        this.goalSelector.addGoal(4, new FindWoodGoal(this));
        this.goalSelector.addGoal(5, new MoveToWoodGoal(this, random));
    }

    private void flapWings() {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(this.onGround || this.isPassenger() ? -1 : 4) * 0.3f;
        this.maxWingDeviation = Mth.clamp(this.maxWingDeviation, 0.0f, 1.0f);
        if (!this.onGround && this.flapSpeed < 1.0f) {
            this.flapSpeed = 1.0f;
        }
        this.flapSpeed *= 0.9f;
        Vec3 vec3d = this.getDeltaMovement();
        if (!this.onGround && vec3d.y < 0.0) {
            this.setDeltaMovement(vec3d.multiply(1.0, 0.6, 1.0));
        }
        this.flapProgress += this.flapSpeed * 2.0f;
    }

    @Override
    protected boolean isFlapping() {
        return this.flyDist > this.field_28640;
    }

    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.hasCustomName()) {
            String string = ChatFormatting.stripFormatting(this.getName().getString());
            if ("woody".equalsIgnoreCase(string)) return GoodEndingSoundEvents.ENTITY_WOODPECKER_WOODY_IDLE.get();
        }
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_IDLE.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.PARROT_STEP, 0.15f, 1.0f);
    }

    @Override
    protected void onFlap() {
        this.playSound(GoodEndingSoundEvents.ENTITY_WOODPECKER_FLY.get(), 0.15f, 1.0f);
        this.field_28640 = this.flyDist + this.flapSpeed / 2.0f;
    }

    @Override
    public boolean isFlying() {
        return !this.onGround;
    }

    @Override
    public boolean causeFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }

    public static boolean canSpawn(EntityType<WoodpeckerEntity> entityType, ServerLevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource randomSource) {
        return world.getBlockState(pos.below()).is(BlockTags.PARROTS_SPAWNABLE_ON);
    }

}
