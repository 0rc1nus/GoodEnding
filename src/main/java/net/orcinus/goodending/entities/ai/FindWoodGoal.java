package net.orcinus.goodending.entities.ai;

import com.google.common.collect.Lists;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
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
        List<BlockPos> list = Lists.newArrayList();
        int range = 5;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                for (int y = -range; y <= range; y++) {
                    BlockPos blockPos = new BlockPos(this.woodpecker.getX() + x, this.woodpecker.getY() + y, this.woodpecker.getZ() + z);
                    if (this.woodpecker.world.getBlockState(blockPos).isIn(BlockTags.LOGS)) {
                        list.add(blockPos);
                    }
                }
            }
        }
        if (!list.isEmpty()) {
            BlockPos listPos = list.get(this.woodpecker.world.getRandom().nextInt(list.size()));
            Direction direction = Direction.Type.HORIZONTAL.random(this.woodpecker.world.getRandom());
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(listPos.offset(direction)));
            if (woodpeckerEntities.size() == 0 && this.woodpecker.world.isAir(listPos.offset(direction))) {
                this.pos = listPos;
                this.woodpecker.setAttachedFace(direction);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean canFitIn(BlockPos blockPos, Direction direction) {
        return this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(blockPos.offset(direction))).size() == 0;
    }

}
