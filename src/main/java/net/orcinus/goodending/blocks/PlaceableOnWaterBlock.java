package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PlaceableOnWaterBlock extends BushBlock {
    protected static final VoxelShape AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 1.5D, 15.0D);

    public PlaceableOnWaterBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public VoxelShape getShape(BlockState p_58169_, BlockGetter p_58170_, BlockPos p_58171_, CollisionContext p_58172_) {
        return AABB;
    }

    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        FluidState fluidState = world.getFluidState(pos);
        FluidState fluidState2 = world.getFluidState(pos.above());
        return (fluidState.getType() == Fluids.WATER || floor.getMaterial() == Material.ICE) && fluidState2.getType() == Fluids.EMPTY;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockPos = pos.below();
        return this.mayPlaceOn(world.getBlockState(blockPos), world, blockPos);
    }
}
