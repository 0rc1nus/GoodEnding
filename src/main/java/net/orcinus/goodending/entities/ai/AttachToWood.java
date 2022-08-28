package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.List;

public class AttachToWood extends Goal {
    private final WoodpeckerEntity woodpecker;

    public AttachToWood(WoodpeckerEntity woodpeckerEntity) {
        this.woodpecker = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        return !this.woodpecker.isAttachedWood() && this.findWood() != null;
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
        BlockPos woodPos = new BlockPos(this.findWood().getX() + 0.5F,this.findWood().getY() + 0.5F,this.findWood().getZ() + 0.5F);
        if (woodPos != null) {
            this.woodpecker.getNavigation().startMovingTo(woodPos.getX(), woodPos.getY(), woodPos.getZ(), 1.0F);
            this.woodpecker.getLookControl().lookAt(woodPos.getX(), woodPos.getY(), woodPos.getZ());
            double distance = Math.sqrt(this.woodpecker.squaredDistanceTo(woodPos.getX(), woodPos.getY(), woodPos.getZ()));
            if (distance < 0.75D) {
                this.woodpecker.setAttachedWood(true);

                //Direction direction = this.findDirection();
                //if (this.woodpeckerEntity.world.getBlockState(woodPos).isIn(BlockTags.LOGS) && direction != null) {
                //    BlockPos relativePos = woodPos.add(direction.getOffsetX(), direction.getOffsetY(), direction.getOffsetZ());
                //    this.woodpeckerEntity.setAttachedFace(direction);
                //    this.woodpeckerEntity.getNavigation().startMovingTo(relativePos.getX(), relativePos.getY(), relativePos.getZ(), 1.0F);
                //    this.woodpeckerEntity.getLookControl().lookAt(relativePos.getX(), relativePos.getY(), relativePos.getZ());
                //}
            }
        }
    }

    public Direction findDirection() {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (!this.woodpecker.world.getBlockState(this.findWood().offset(direction)).isAir()) {
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
                    BlockPos blockPos = new BlockPos(this.woodpecker.getX() + x, this.woodpecker.getY() + y, this.woodpecker.getZ() + z);
                    if (this.woodpecker.world.getBlockState(blockPos).isIn(BlockTags.LOGS)) {
                        poses.add(blockPos);
                    }
                }
            }
        }

        if (poses.isEmpty()) return null;

        return poses.get(0);
    }

}
