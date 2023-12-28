package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.orcinus.goodending.init.GoodEndingBlocks;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class LargeLilyPadBlock extends BushBlock implements SimpleWaterloggedBlock, BonemealableBlock {
    protected static final VoxelShape SHAPE = box(0, 15, 0, 16, 16, 16);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public LargeLilyPadBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    public void entityInside(BlockState p_60495_, Level world, BlockPos pos, Entity entity) {
        super.entityInside(p_60495_, world, pos, entity);
        if (world instanceof ServerLevel && entity instanceof Boat) {
            world.destroyBlock(new BlockPos(pos), true, entity);
        }
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState stateFrom, Direction direction) {
        if (stateFrom.is(this)) return true;
        return super.skipRendering(state, stateFrom, direction);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos) {
        BlockPos blockpos = pos.below();
        return this.mayPlaceOn(world.getBlockState(blockpos), world, blockpos);
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return (world.getFluidState(pos.above()).is(Fluids.WATER) && world.getFluidState(pos.above(2)).isEmpty());
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos).getFluidState().is(Fluids.WATER)) {
            int j = 1;
            int l = 0;
            int m = pos.getX() - 2;
            int n = 0;

            for (int o = 0; o < 5; ++o) {
                for (int p = 0; p < j; ++p) {
                    int q = 2 + pos.getY();
                    for (int r = q - 2; r < q; ++r) {
                        BlockPos blockPos = new BlockPos(m + o, r, pos.getZ() - n + p);
                        if (blockPos == pos
                            || random.nextInt(4) != 0
                            || world.getBlockState(blockPos).getBlock().defaultBlockState().is(GoodEndingBlocks.LARGE_LILY_PAD.get())
                            || !world.getBlockState(pos).getFluidState().is(Fluids.WATER)
                            || !world.getBlockState(blockPos).getBlock().defaultBlockState().is(Blocks.WATER))
                            continue;

                        world.setBlock(blockPos, state, 3);
                    }
                }
                if (l < 2) {
                    j += 2;
                    ++n;
                } else {
                    j -= 2;
                    --n;
                }
                ++l;
            }
            world.setBlock(pos, state, 2);
        }
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(WATERLOGGED, ctx.getLevel().getFluidState(ctx.getClickedPos()).getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}
