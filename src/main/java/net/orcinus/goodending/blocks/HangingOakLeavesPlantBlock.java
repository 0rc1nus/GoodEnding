package net.orcinus.goodending.blocks;

import net.minecraft.block.AbstractPlantBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;
import net.orcinus.goodending.init.GoodEndingBlocks;

public class HangingOakLeavesPlantBlock extends AbstractPlantBlock {
    public static final VoxelShape SHAPE = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    public HangingOakLeavesPlantBlock(Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.offset(this.growthDirection.getOpposite());
        BlockState blockState = world.getBlockState(blockPos);

        if (!this.canAttachTo(blockState)) {
            return false;
        } else {
            return blockState.isOf(this.getStem()) || blockState.isOf(this.getPlant()) || blockState.isIn(BlockTags.LEAVES);
        }
    }

    @Override
    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) GoodEndingBlocks.HANGING_OAK_LEAVES;
    }
}
