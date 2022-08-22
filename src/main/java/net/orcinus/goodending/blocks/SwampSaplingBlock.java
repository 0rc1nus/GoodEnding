package net.orcinus.goodending.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.Waterloggable;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class SwampSaplingBlock  extends SaplingBlock implements Waterloggable {
    public static final IntProperty AGE = Properties.AGE_4;
    private static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    private final Supplier<RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>>> feature;
    private final boolean isLarge;

    public SwampSaplingBlock(Supplier<RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>>> supplier, Settings settings) {
        this(false, supplier, settings);
    }

    public SwampSaplingBlock(boolean isLarge, Supplier<RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>>> supplier, Settings settings) {
        super(null, settings);
        this.feature = supplier;
        this.isLarge = isLarge;
        this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false).with(AGE, 0));
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        boolean bl = fluidState.getFluid() == Fluids.WATER;
        return super.getPlacementState(ctx).with(WATERLOGGED, bl).with(AGE, 4);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(STAGE, AGE, WATERLOGGED);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        if (!this.isLarge) {
            return super.canGrow(world, random, pos, state) && state.get(WATERLOGGED) && world.getBlockState(pos.up()).isOf(Blocks.WATER);
        } else {
            return true;
        }
    }

    @Override
    public void generate(ServerWorld world, BlockPos pos, BlockState state, Random random) {
        if (state.get(AGE) == 0) {
            world.setBlockState(pos, state.cycle(AGE), Block.NO_REDRAW);
        } else {
            if (!this.isLarge) {
                world.setBlockState(pos, Blocks.WATER.getDefaultState(), 3);
                this.feature.get().value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos);
            } else {
                for (int i = 0; i >= -1; --i) {
                    for (int j = 0; j >= -1; --j) {
                        if (!LargeTreeSaplingGenerator.canGenerateLargeTree(state, world, pos, i, j)) continue;
                        this.feature.get().value().generate(world, world.getChunkManager().getChunkGenerator(), random, pos.add(i, 0, j));
                    }
                }
            }
        }
    }
}
