package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.orcinus.goodending.entities.MarshEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FollowMobWithEffectGoal extends Goal {
    protected final MarshEntity marsh;
    @Nullable
    protected LivingEntity entity;

    public FollowMobWithEffectGoal(MarshEntity marsh) {
        this.marsh = marsh;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        this.entity = this.findMate();
        return this.entity != null;
    }

    @Override
    public boolean shouldContinue() {
        return this.entity.isAlive() && !this.entity.getActiveStatusEffects().isEmpty();
    }

    @Override
    public void stop() {
        this.entity = null;
    }

    @Override
    public void tick() {
        this.marsh.getLookControl().lookAt(this.entity, 10.0f, this.marsh.getMaxLookPitchChange());
        this.marsh.getNavigation().startMovingTo(this.entity, 1.2D);
    }

    @Nullable
    private LivingEntity findMate() {
        double d = Double.MAX_VALUE;
        LivingEntity target = null;
        for (LivingEntity targets : this.marsh.world.getEntitiesByClass(LivingEntity.class, this.marsh.getBoundingBox().expand(8.0D), livingEntity -> livingEntity.getUuid() != this.marsh.getUuid() && !livingEntity.getActiveStatusEffects().isEmpty())) {
            if (!(this.marsh.squaredDistanceTo(targets) < d)) continue;
            target = targets;
            d = this.marsh.squaredDistanceTo(targets);
        }
        return target;
    }

}
