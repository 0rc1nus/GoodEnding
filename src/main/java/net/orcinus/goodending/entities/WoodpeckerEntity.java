package net.orcinus.goodending.entities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

public class WoodpeckerEntity extends PathAwareEntity {
    public float flapProgress;
    public float maxWingDeviation;
    public float prevMaxWingDeviation;
    public float prevFlapProgress;
    private float flapSpeed = 1.0f;
    private float field_28640 = 1.0f;
    public final AnimationState peckingAnimationState = new AnimationState();
    public final AnimationState standingAnimationState = new AnimationState();
    public final AnimationState flyingAnimationState = new AnimationState();

    public WoodpeckerEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createWoodPeckerAttributes() {
        return MobEntity.createMobAttributes()
                        .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                        .add(EntityAttributes.GENERIC_FLYING_SPEED, 0.4f)
                        .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        this.flapWings();
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
