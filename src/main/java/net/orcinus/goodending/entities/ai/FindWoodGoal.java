package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class FindWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private BlockPos pos = BlockPos.ORIGIN;

    public FindWoodGoal(WoodpeckerEntity woodpecker) {
        this.woodpecker = woodpecker;
    }

    @Override
    public boolean canStart() {
        return this.findNearestBlock();
    }

    @Override
    public void start() {
        this.woodpecker.setWoodPos(this.pos);
    }

    protected boolean findNearestBlock() {
        int i = 12;
        int j = 1;
        BlockPos blockpos = this.woodpecker.getBlockPos();
        BlockPos.Mutable blockpos$mutableblockpos = new BlockPos.Mutable();

        for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
            for(int l = 0; l < i; ++l) {
                for(int i1 = 0; i1 <= l; i1 = i1 > 0 ? -i1 : 1 - i1) {
                    for(int j1 = i1 < l && i1 > -l ? l : 0; j1 <= l; j1 = j1 > 0 ? -j1 : 1 - j1) {
                        blockpos$mutableblockpos.set(blockpos, i1, k - 1, j1);
                        if (this.woodpecker.isInWalkTargetRange(blockpos$mutableblockpos) && this.woodpecker.world.getBlockState(blockpos$mutableblockpos).isIn(BlockTags.LOGS)) {
                            this.pos = blockpos$mutableblockpos;
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

}
