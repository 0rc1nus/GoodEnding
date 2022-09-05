package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;

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
        BlockPos mobPos = this.woodpecker.getBlockPos();
        for (int i = 0; i < 8; i++) {
            BlockPos offset = mobPos.add(this.woodpecker.getRandom().nextInt(16) - 8, this.woodpecker.getRandom().nextInt(4) + 1, this.woodpecker.getRandom().nextInt(16) - 8);
            double x = offset.getX() + 0.5F - this.woodpecker.getX();
            double z = offset.getZ() + 0.5F - this.woodpecker.getZ();
            double distance = x * x + z * z;
            Vec3d blockVec = Vec3d.ofCenter(offset);
            BlockHitResult result = this.woodpecker.world.raycast(new RaycastContext(this.woodpecker.getEyePos(), blockVec, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this.woodpecker));
            if (this.woodpecker.world.getBlockState(result.getBlockPos()).isIn(BlockTags.LOGS) && this.woodpecker.world.getBlockState(result.getBlockPos().offset(result.getSide())).isAir() && result.getType() != HitResult.Type.MISS && distance > 4 && result.getSide().getAxis() != Direction.Axis.Y) {
                this.pos = result.getBlockPos();
                this.woodpecker.setAttachedFace(result.getSide());
                return true;
            }
        }
        return false;
    }

}
