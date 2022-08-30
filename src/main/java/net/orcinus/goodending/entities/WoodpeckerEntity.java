package net.orcinus.goodending.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FlyGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.orcinus.goodending.entities.ai.FindWoodGoal;
import net.orcinus.goodending.entities.ai.FlyAroundGoal;
import net.orcinus.goodending.entities.ai.PeckingWoodGoal;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

public class WoodpeckerEntity extends PathAwareEntity implements Flutterer {
    protected static final TrackedData<Direction> ATTACHED_FACE = DataTracker.registerData(WoodpeckerEntity.class, TrackedDataHandlerRegistry.FACING);
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0f;
    private float field_28640 = 1.0f;
    @Nullable
    private BlockPos woodPos;
    public int woodAttachingCooldownTicks;
    public int peckingWoodCooldownTicks;
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState standingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();

    public WoodpeckerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 10, true);
        this.setPose(EntityPose.FALL_FLYING);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, -1.0f);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0f);
    }

    @Override
    public Vec3d getVelocity() {
        return this.getWoodPos() != null ? Vec3d.ZERO : super.getVelocity();
    }

    @Override
    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.woodAttachingCooldownTicks > 0) {
            this.woodAttachingCooldownTicks--;
        }
        if (!this.world.isClient()) {
            if (this.getWoodPos() != null) {
                BlockState state = this.world.getBlockState(this.getWoodPos());
                if (!state.isIn(BlockTags.LOGS)) {
                    this.setPose(EntityPose.FALL_FLYING);
                    if (this.getPose() == EntityPose.FALL_FLYING) {
                        this.setWoodPos(null);
                    }
                }
                else {
                    this.getLookControl().lookAt(Vec3d.ofBottomCenter(this.getWoodPos()));
                }
            }
        }
        if (this.world.isClient()) {
            boolean b = this.getPose() == EntityPose.STANDING;
            boolean fallFlying = this.getPose() == EntityPose.FALL_FLYING;
            boolean digging = this.getPose() == EntityPose.DIGGING;

            if (b) this.standingAnimationState.startIfNotRunning(this.age);
            else this.standingAnimationState.stop();

            if (fallFlying) this.flyingAnimationState.startIfNotRunning(this.age);
            else this.flyingAnimationState.stop();

            if (digging) {
                this.peckingAnimationState.startIfNotRunning(this.age);
            } else {
                this.peckingAnimationState.stop();
            }

        }
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (POSE.equals(data)) {
            if (this.getPose() == EntityPose.STANDING) {
                this.standingAnimationState.start(this.age);
            }
            if (this.getPose() == EntityPose.DIGGING) {
                this.peckingAnimationState.start(this.age);
            }
        }
        super.onTrackedDataSet(data);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACHED_FACE, Direction.DOWN);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(2, new FindWoodGoal(this));
        this.goalSelector.add(3, new PeckingWoodGoal(this));
        this.goalSelector.add(4, new WanderGoal(this));
//        this.goalSelector.add(4, new FlyOntoBranchGoal(this, 1.0f));
        this.goalSelector.add(5, new LookGoal(this, PlayerEntity.class, 8.0f));
    }

    public static DefaultAttributeContainer.Builder createWoodPeckerAttributes() {
        return MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
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
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        return super.getPathfindingFavor(pos, world);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setAttachedFace(Direction.byId(nbt.getByte("AttachFace")));
        this.woodAttachingCooldownTicks = nbt.getInt("WoodAttachingCooldownTicks");
        this.setPeckingWoodCooldownTicks(nbt.getInt("PeckingWoodCooldownTicks"));
        this.setWoodPos(null);
        if (nbt.contains("WoodPos")) this.setWoodPos(NbtHelper.toBlockPos(nbt.getCompound("WoodPos")));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("WoodAttachingCooldownTicks", this.woodAttachingCooldownTicks);
        nbt.putInt("PeckingWoodCooldownTicks", this.getPeckingWoodCooldownTicks());
        nbt.putByte("AttachFace", (byte)this.getAttachedFace().getId());
        if (this.getWoodPos() != null) nbt.put("WoodPos", NbtHelper.fromBlockPos(this.getWoodPos()));
    }

    public void setPeckingWoodCooldownTicks(int peckingWoodCooldownTicks) {
        this.peckingWoodCooldownTicks = peckingWoodCooldownTicks;
    }

    public int getPeckingWoodCooldownTicks() {
        return this.peckingWoodCooldownTicks;
    }

    public void setWoodPos(BlockPos blockPos) {
        this.woodPos = blockPos;
    }

    public BlockPos getWoodPos() {
        return this.woodPos;
    }

    public Direction getAttachedFace() {
        return this.dataTracker.get(ATTACHED_FACE);
    }

    public void setAttachedFace(Direction face) {
        this.dataTracker.set(ATTACHED_FACE, face);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
    }

    @Override
    public boolean isInAir() {
        return false;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
    }


    private void flapWings() {
        this.prevFlapProgress = this.flapProgress;
        this.prevMaxWingDeviation = this.maxWingDeviation;
        this.maxWingDeviation += (float)(this.onGround || this.hasVehicle() ? -1 : 4) * 0.3f;
        this.maxWingDeviation = MathHelper.clamp(this.maxWingDeviation, 0.0f, 1.0f);
        if (!this.onGround && this.flapSpeed < 1.0f) {
            this.flapSpeed = 1.0f;
        }
        this.flapSpeed *= 0.9f;
        Vec3d vec3d = this.getVelocity();
        if (!this.onGround && vec3d.y < 0.0) {
            this.setVelocity(vec3d.multiply(1.0, 0.6, 1.0));
        }
        this.flapProgress += this.flapSpeed * 2.0f;
    }

    @Override
    protected boolean hasWings() {
        return this.speed > this.field_28640;
    }


    @Override
    protected float getSoundVolume() {
        return 0.5f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.hasCustomName()) {
            String string = Formatting.strip(this.getName().getString());
            if ("woody".equalsIgnoreCase(string)) return GoodEndingSoundEvents.ENTITY_WOODPECKER_WOODY_IDLE;
        }
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GoodEndingSoundEvents.ENTITY_WOODPECKER_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15f, 1.0f);
    }

    @Override
    protected void addFlapEffects() {
        this.playSound(GoodEndingSoundEvents.ENTITY_WOODPECKER_FLY, 0.15f, 1.0f);
        this.field_28640 = this.speed + this.maxWingDeviation / 2.0f;
    }

    public boolean hasWoodPos() {
        return this.getWoodPos() != null;
    }

    static class FlyOntoBranchGoal extends FlyGoal {

        public FlyOntoBranchGoal(PathAwareEntity pathAwareEntity, double d) {
            super(pathAwareEntity, d);
        }

        @Override
        @Nullable
        protected Vec3d getWanderTarget() {
            Vec3d vec3d = null;
            if (this.mob.isTouchingWater()) {
                vec3d = FuzzyTargeting.find(this.mob, 15, 25);
            }
            if (this.mob.getRandom().nextFloat() >= this.probability) {
                vec3d = this.locateBranch();
            }
            return vec3d == null ? super.getWanderTarget() : vec3d;
        }

        @Nullable
        private Vec3d locateBranch() {
            BlockPos blockPos = this.mob.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            BlockPos.Mutable mutable2 = new BlockPos.Mutable();
            Iterable<BlockPos> iterable = BlockPos.iterate(MathHelper.floor(this.mob.getX() - 3.0), MathHelper.floor(this.mob.getY() - 6.0), MathHelper.floor(this.mob.getZ() - 3.0), MathHelper.floor(this.mob.getX() + 3.0), MathHelper.floor(this.mob.getY() + 6.0), MathHelper.floor(this.mob.getZ() + 3.0));
            for (BlockPos blockPos2 : iterable) {
                BlockPos blockPos3 = new BlockPos(blockPos2.getX() + 0.5D, blockPos2.getY() + 1, blockPos2.getZ() + 0.5D) ;
                if (blockPos.equals(blockPos2) || !this.mob.world.getBlockState(mutable2.set(blockPos2, Direction.DOWN)).isIn(BlockTags.LOGS) || !this.mob.world.isAir(blockPos2) || !this.mob.world.isAir(mutable.set(blockPos2, Direction.UP))) continue;
                return Vec3d.of(blockPos3);
            }
            return null;
        }
    }

    public static class LookGoal extends LookAtEntityGoal {
        private final WoodpeckerEntity woodpeckerEntity;

        public LookGoal(WoodpeckerEntity mob, Class<? extends LivingEntity> targetType, float range) {
            super(mob, targetType, range);
            this.woodpeckerEntity = mob;
        }

        @Override
        public boolean canStart() {
            return !this.woodpeckerEntity.hasWoodPos() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !this.woodpeckerEntity.hasWoodPos() && super.shouldContinue();
        }
    }

    public static class WanderGoal extends WanderAroundFarGoal {
        private final WoodpeckerEntity woodpeckerEntity;

        public WanderGoal(WoodpeckerEntity pathAwareEntity) {
            super(pathAwareEntity, 1.0D);
            this.woodpeckerEntity = pathAwareEntity;
        }

        @Override
        public boolean canStart() {
            return !this.woodpeckerEntity.hasWoodPos() && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            return !this.woodpeckerEntity.hasWoodPos() && super.shouldContinue();
        }
    }

}
