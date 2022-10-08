package net.orcinus.goodending.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.orcinus.goodending.entities.MarshEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FollowMobWithEffectGoal extends Goal {
    protected final MarshEntity marsh;
    @Nullable
    protected LivingEntity entity;

    public FollowMobWithEffectGoal(MarshEntity marsh) {
        this.marsh = marsh;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        this.entity = this.findMate();
        return this.entity != null;
    }

    @Override
    public boolean canContinueToUse() {
        return this.entity.isAlive() && !this.entity.getActiveEffectsMap().isEmpty();
    }

    @Override
    public void stop() {
        this.entity = null;
    }

    @Override
    public void tick() {
        this.marsh.getLookControl().setLookAt(this.entity, 10.0f, this.marsh.getMaxHeadXRot());
        this.marsh.getNavigation().moveTo(this.entity, 1.2D);
    }

    @Nullable
    private LivingEntity findMate() {
        double d = Double.MAX_VALUE;
        LivingEntity target = null;
        for (LivingEntity targets : this.marsh.level.getEntitiesOfClass(LivingEntity.class, this.marsh.getBoundingBox().inflate(8.0D), livingEntity -> livingEntity.getUUID() != this.marsh.getUUID() && !livingEntity.getActiveEffectsMap().isEmpty())) {
            if (!(this.marsh.distanceToSqr(targets) < d)) continue;
            target = targets;
            d = this.marsh.distanceToSqr(targets);
        }
        return target;
    }

}
