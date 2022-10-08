package net.orcinus.goodending.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FlyAroundGoal extends Goal {
    private final PathfinderMob fireflyEntity;

    public FlyAroundGoal(PathfinderMob fireflyEntity) {
        this.fireflyEntity = fireflyEntity;
        this.setFlags(EnumSet.of(Flag.MOVE));
    }

    @Override
    public boolean canUse() {
        return this.fireflyEntity.getNavigation().isDone() && this.fireflyEntity.getRandom().nextInt(10) == 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.fireflyEntity.getNavigation().isInProgress();
    }

    @Override
    public void start() {
        Vec3 vec3d = this.getRandomLocation();
        if (vec3d != null) {
            this.fireflyEntity.getNavigation().moveTo(this.fireflyEntity.getNavigation().createPath(new BlockPos(vec3d), 1), 1.0);
        }
    }

    @Nullable
    private Vec3 getRandomLocation() {
        Vec3 vec3 = this.fireflyEntity.getViewVector(0.0f);
        return AirAndWaterRandomPos.getPos(this.fireflyEntity, 10, 7, -2, vec3.x, vec3.z, 1.5707963705062866);
    }
}
