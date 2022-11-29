package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
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
    public boolean canStart() {
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
                    BlockState state = this.woodpecker.world.getBlockState(pos);
                    if (state.isIn(BlockTags.LOGS)) {
                        poses.add(pos);
                    }
                }
            }
        }
        if (!poses.isEmpty()) {
            poses.sort(Comparator.comparingDouble(this.woodpecker.getBlockPos()::getSquaredDistance));
            for (BlockPos pos : poses) {
                double x = pos.getX() + 0.5F - this.woodpecker.getX();
                double z = pos.getZ() + 0.5F - this.woodpecker.getZ();
                double distance = x * x + z * z;
                Vec3d blockVec = Vec3d.ofCenter(pos);
                BlockHitResult result = this.woodpecker.world.raycast(new RaycastContext(this.woodpecker.getEyePos(), blockVec, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this.woodpecker));
                List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(result.getBlockPos().offset(result.getSide())));
                if (this.woodpecker.world.getBlockState(result.getBlockPos()).isIn(BlockTags.LOGS) && this.woodpecker.world.getBlockState(result.getBlockPos().offset(result.getSide())).isAir() && result.getType() != HitResult.Type.MISS && distance > 4 && result.getSide().getAxis() != Direction.Axis.Y && woodpeckerEntities.size() == 0) {
                    this.pos = result.getBlockPos();
                    this.woodpecker.setAttachedFace(result.getSide());
                    return true;
                }
            }
        }
        return false;
    }

}
