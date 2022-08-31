package net.orcinus.goodending.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Flutterer;
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
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.orcinus.goodending.entities.ai.FindWoodGoal;
import net.orcinus.goodending.entities.ai.FlyAroundGoal;
import net.orcinus.goodending.entities.ai.MoveToWoodGoal;
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
    private int peckingWoodCooldown;
    @Nullable
    public BlockPos woodPos;
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState standingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();

    public WoodpeckerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
        this.setPose(EntityPose.FALL_FLYING);
    }

    public static DefaultAttributeContainer.Builder createWoodPeckerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ATTACHED_FACE, Direction.DOWN);
    }

    public Direction getAttachedFace() {
        return this.dataTracker.get(ATTACHED_FACE);
    }

    public void setAttachedFace(Direction attachedFace) {
        this.dataTracker.set(ATTACHED_FACE, attachedFace);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setWoodPos(null);
        if (nbt.contains("WoodPos")) {
            this.setWoodPos(NbtHelper.toBlockPos(nbt.getCompound("WoodPos")));
        }
        this.setAttachedFace(Direction.byId(nbt.getByte("AttachFace")));
        this.setPeckingWoodCooldown(nbt.getInt("PeckingWoodCooldownTicks"));
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.hasWood()) {
            nbt.put("WoodPos", NbtHelper.fromBlockPos(this.getBlockPos()));
        }
        nbt.putByte("AttachFace", (byte) this.getAttachedFace().getId());
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
        if (this.world.isClient()) {
            if (this.getPose() == EntityPose.FALL_FLYING) {
                this.flyingAnimationState.startIfNotRunning(this.age);
            } else {
                this.flyingAnimationState.stop();
            }
            if (this.getPose() == EntityPose.STANDING) {
                this.standingAnimationState.startIfNotRunning(this.age);
            } else {
                this.standingAnimationState.stop();
            }
            if (this.getPose() == EntityPose.DIGGING) {
                this.peckingAnimationState.startIfNotRunning(this.age);
            } else {
                this.peckingAnimationState.stop();
            }
        } else {
            if (this.getPeckingWoodCooldown() > 0) {
                this.setPeckingWoodCooldown(this.getPeckingWoodCooldown() - 1);
            }
        }
        super.tick();
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
    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FlyAroundGoal(this));
        this.goalSelector.add(2, new SwimGoal(this));
        this.goalSelector.add(3, new FindWoodGoal(this));
        this.goalSelector.add(4, new MoveToWoodGoal(this));
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


    @Override
    public boolean isInAir() {
        return !this.onGround;
    }
}
