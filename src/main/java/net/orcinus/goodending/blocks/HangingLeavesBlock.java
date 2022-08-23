package net.orcinus.goodending.blocks;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.VineLogic;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.WorldView;

public class HangingLeavesBlock extends AbstractPlantStemBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0, 9.0, 4.0, 12.0, 16.0, 12.0);
    Block plant;

    public HangingLeavesBlock(Settings settings, Block plant) {
        super(settings, Direction.DOWN, SHAPE, false, 0.1);
        this.plant = plant;
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
    protected int getGrowthLength(Random random) {
        return VineLogic.getGrowthLength(random);
    }

    @Override
    protected Block getPlant() {
        return plant;
    }

    @Override
    protected boolean chooseStemState(BlockState state) {
        return VineLogic.isValidForWeepingStem(state);
    }
}
