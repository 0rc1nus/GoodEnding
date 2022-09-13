package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.orcinus.goodending.entities.MarshEntity;

public class AvoidStrangerDangerGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
    private final MarshEntity marshEntity;

    public AvoidStrangerDangerGoal(MarshEntity mob, Class<T> fleeFromType) {
        super(mob, fleeFromType, 6.0f, 1.2, 1.5);
        this.marshEntity = mob;
    }

    @Override
    public boolean canStart() {
        return !this.marshEntity.isTrusted() && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !this.marshEntity.isTrusted() && super.shouldContinue();
    }

    @Override
    public void tick() {
        super.tick();
        this.marshEntity.world.sendEntityStatus(this.marshEntity, EntityStatuses.ADD_SPLASH_PARTICLES);
    }
}
