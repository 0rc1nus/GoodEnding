package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.List;

public class AttachToWood extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;

    public AttachToWood(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        return !this.woodpeckerEntity.isAttachedWood() && this.findWood() != null;
    }

    @Override
    public boolean shouldContinue() {
        if (this.findWood() == null) {
            return false;
        }
        return super.shouldContinue();
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos blockPos = this.findWood();
        if (blockPos != null) {
            this.woodpeckerEntity.getMoveControl().moveTo(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0F);
            this.woodpeckerEntity.getLookControl().lookAt(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            double distance = Math.sqrt(this.woodpeckerEntity.squaredDistanceTo(blockPos.getX(), blockPos.getY(), blockPos.getZ()));
            if (distance < 1.01D) {
                this.woodpeckerEntity.setAttachedWood(true);
                Direction direction = this.findDirection();
                if (this.woodpeckerEntity.world.getBlockState(blockPos).isIn(BlockTags.LOGS) && direction != null) {
                    BlockPos relativePos = blockPos.add(direction.getOffsetX(), direction.getOffsetY(), direction.getOffsetZ());
                    this.woodpeckerEntity.setAttachedFace(direction);
                    this.woodpeckerEntity.getNavigation().startMovingTo(relativePos.getX(), relativePos.getY(), relativePos.getZ(), 1.0F);
                    this.woodpeckerEntity.getLookControl().lookAt(relativePos.getX(), relativePos.getY(), relativePos.getZ());
                }
            }
        }
    }

    public Direction findDirection() {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (!this.woodpeckerEntity.world.getBlockState(this.findWood().offset(direction)).isAir()) {
                continue;
            }
            return direction;
        }
        return null;
    }

    public BlockPos findWood() {
        int radius = 3;
        List<BlockPos> poses = Lists.newArrayList();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -radius; y <= radius; y++) {
                    BlockPos blockPos = new BlockPos(this.woodpeckerEntity.getX() + x, this.woodpeckerEntity.getY() + y, this.woodpeckerEntity.getZ() + z);
                    if (this.woodpeckerEntity.world.getBlockState(blockPos).isIn(BlockTags.LOGS)) {
                        poses.add(blockPos);
                    }
                }
            }
        }

        if (poses.isEmpty()) return null;

        return poses.get(0);
    }
    
}
