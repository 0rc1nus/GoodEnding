package net.orcinus.goodending.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class FindWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private BlockPos pos;

    public FindWoodGoal(WoodpeckerEntity woodpecker) {
        this.woodpecker = woodpecker;
    }

    @Override
    public boolean canUse() {
        if (!(this.woodpecker.getAttachedFace() == Direction.UP || this.woodpecker.getAttachedFace() == Direction.DOWN)) {
            return false;
        }
        return this.findNearestBlock() && this.woodpecker.getWoodPos() == null;
    }

    @Override
    public void start() {
        if (this.pos != null) {
            this.woodpecker.setWoodPos(this.pos);
        }
    }

    protected boolean findNearestBlock() {
        BlockPos mobPos = this.woodpecker.blockPosition();
        for (int i = 0; i < 8; i++) {
            BlockPos offset = mobPos.offset(this.woodpecker.getRandom().nextInt(16) - 8, this.woodpecker.getRandom().nextInt(4) + 1, this.woodpecker.getRandom().nextInt(16) - 8);
            double x = offset.getX() + 0.5F - this.woodpecker.getX();
            double z = offset.getZ() + 0.5F - this.woodpecker.getZ();
            double distance = x * x + z * z;
            Vec3 blockVec = Vec3.atCenterOf(offset);
            BlockHitResult result = this.woodpecker.level.clip(new ClipContext(this.woodpecker.getEyePosition(), blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.woodpecker));
            if (this.woodpecker.level.getBlockState(result.getBlockPos()).is(BlockTags.LOGS) && this.woodpecker.level.getBlockState(result.getBlockPos().relative(result.getDirection())).isAir() && result.getType() != HitResult.Type.MISS && distance > 4 && result.getDirection().getAxis() != Direction.Axis.Y) {
                this.pos = result.getBlockPos();
                this.woodpecker.setAttachedFace(result.getDirection());
                return true;
            }
        }
        return false;
    }

}
