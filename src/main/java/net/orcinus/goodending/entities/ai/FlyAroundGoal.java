package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ai.NoPenaltySolidTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.orcinus.goodending.entities.FireflyEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FlyAroundGoal extends Goal {
    private final PathAwareEntity fireflyEntity;

    public FlyAroundGoal(PathAwareEntity fireflyEntity) {
        this.fireflyEntity = fireflyEntity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        return this.fireflyEntity.getNavigation().isIdle() && this.fireflyEntity.getRandom().nextInt(10) == 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.fireflyEntity.getNavigation().isFollowingPath();
    }

    @Override
    public void start() {
        Vec3d vec3d = this.getRandomLocation();
        if (vec3d != null) {
            this.fireflyEntity.getNavigation().startMovingAlong(this.fireflyEntity.getNavigation().findPathTo(new BlockPos(vec3d), 1), 1.0);
        }
    }

    @Nullable
    private Vec3d getRandomLocation() {
        Vec3d vec3 = this.fireflyEntity.getRotationVec(0.0f);
        return NoPenaltySolidTargeting.find(this.fireflyEntity, 10, 7, -2, vec3.x, vec3.z, 1.5707963705062866);
    }
}
