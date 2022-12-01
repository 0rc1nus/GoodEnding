package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.Comparator;
import java.util.List;

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
        List<BlockPos> poses = Lists.newArrayList();
        int range = 8;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    BlockPos pos = new BlockPos(this.woodpecker.getX() + x, this.woodpecker.getY() + y, this.woodpecker.getZ() + z);
                    BlockState state = this.woodpecker.level.getBlockState(pos);
                    if (state.is(BlockTags.LOGS)) {
                        poses.add(pos);
                    }
                }
            }
        }
        if (!poses.isEmpty()) {
            poses.sort(Comparator.comparingDouble(this.woodpecker.blockPosition()::distSqr));
            for (BlockPos pos : poses) {
                double x = pos.getX() + 0.5F - this.woodpecker.getX();
                double z = pos.getZ() + 0.5F - this.woodpecker.getZ();
                double distance = x * x + z * z;
                Vec3 blockVec = Vec3.atCenterOf(pos);
                BlockHitResult result = this.woodpecker.level.clip(new ClipContext(this.woodpecker.getEyePosition(), blockVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this.woodpecker));
                List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.level.getEntitiesOfClass(WoodpeckerEntity.class, new AABB(result.getBlockPos().relative(result.getDirection())));
                if (this.woodpecker.level.getBlockState(result.getBlockPos()).is(BlockTags.LOGS) && this.woodpecker.level.getBlockState(result.getBlockPos().relative(result.getDirection())).isAir() && result.getType() != HitResult.Type.MISS && distance > 4 && result.getDirection().getAxis() != Direction.Axis.Y && woodpeckerEntities.size() == 0) {
                    this.pos = result.getBlockPos();
                    this.woodpecker.setAttachedFace(result.getDirection());
                    return true;
                }
            }
        }
        return false;
    }

}
