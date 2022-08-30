package net.orcinus.goodending.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.orcinus.goodending.entities.ai.FindWoodGoal;
import net.orcinus.goodending.entities.ai.MoveToWoodGoal;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

public class WoodpeckerEntity extends PathAwareEntity {
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0f;
    private float field_28640 = 1.0f;
    @Nullable
    public BlockPos woodPos;
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState standingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();

    public WoodpeckerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 20, true);
    }

    public static DefaultAttributeContainer.Builder createWoodPeckerAttributes() {
        return MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        this.setWoodPos(null);
        if (nbt.contains("WoodPos")) {
            this.setWoodPos(NbtHelper.toBlockPos(nbt.getCompound("WoodPos")));
        }
        super.readCustomDataFromNbt(nbt);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.hasWood()) {
            nbt.put("WoodPos", NbtHelper.fromBlockPos(this.getBlockPos()));
        }
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
        super.tick();
        if (!this.world.isClient) {

        }
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
        this.goalSelector.add(1, new FindWoodGoal(this));
        this.goalSelector.add(2, new MoveToWoodGoal(this));
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


}
