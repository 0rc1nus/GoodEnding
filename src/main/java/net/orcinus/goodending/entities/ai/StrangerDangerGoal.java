package net.orcinus.goodending.entities.ai;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.orcinus.goodending.entities.MarshEntity;

public class StrangerDangerGoal<T extends LivingEntity> extends AvoidEntityGoal<T> {
    private final MarshEntity marshEntity;

    public StrangerDangerGoal(MarshEntity mob, Class<T> fleeFromType) {
        super(mob, fleeFromType, 6.0f, 1.2, 1.5);
        this.marshEntity = mob;
    }

    @Override
    public boolean canUse() {
        return !this.marshEntity.isTrusted() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return !this.marshEntity.isTrusted() && super.canContinueToUse();
    }

    @Override
    public void tick() {
        super.tick();
        this.marshEntity.level.broadcastEntityEvent(this.marshEntity, (byte) 42);
    }
}
