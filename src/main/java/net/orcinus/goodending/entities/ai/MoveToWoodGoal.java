package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class MoveToWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private boolean hasReached = false;

    public MoveToWoodGoal(WoodpeckerEntity woodpecker) {
        this.woodpecker = woodpecker;
    }

    @Override
    public boolean canStart() {
        return this.woodpecker.getWoodPos() != null && this.woodpecker.world.getBlockState(this.woodpecker.getWoodPos()).isIn(BlockTags.LOGS);
    }

    //TODO: MAKE IT DISCONTINUE
    @Override
    public boolean shouldContinue() {
        return this.woodpecker.getWoodPos() != null && this.woodpecker.world.getBlockState(this.woodpecker.getWoodPos()).isIn(BlockTags.LOGS) && this.hasReached;
    }

    @Override
    public void start() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            Vec3d vec3d = Vec3d.ofBottomCenter(new Vec3i(woodPos.getX(), woodPos.getY(), woodPos.getZ()));
            this.woodpecker.getNavigation().startMovingTo(vec3d.getX(), vec3d.getY(), vec3d.getZ(), 1.2F);
        }
    }

    @Override
    public void tick() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            Vec3d center = Vec3d.ofBottomCenter(woodPos);
            this.woodpecker.getLookControl().lookAt(center);
            double squaredDistance = this.woodpecker.getBlockPos().getSquaredDistance(Vec3d.ofBottomCenter(woodPos));
            if (squaredDistance <= 3D) {
                this.hasReached = true;
            }
        }
    }

}
