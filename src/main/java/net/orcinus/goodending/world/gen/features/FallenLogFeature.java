package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;

import java.util.List;

public class FallenLogFeature extends Feature<FallenLogConfig> {

    public FallenLogFeature(Codec<FallenLogConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FallenLogConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int logLength = MathHelper.nextInt(random, 4, 8);
        BlockPos.Mutable mut = blockPos.mutableCopy();
        List<BlockPos> decorationPoses = Lists.newArrayList();
        FallenLogConfig config = context.getConfig();
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            BlockState checkState = world.getBlockState(blockPos.offset(direction));
            if (!(checkState.isOf(Blocks.WATER) || checkState.isAir())) {
                direction = direction.rotateYClockwise();
            }
            for (int i = 0; i <= logLength; i++) {
                boolean flag = world.getBlockState(mut).getMaterial().isReplaceable() || world.testBlockState(mut, state -> state.isAir() || state.isOf(Blocks.WATER) || state.isIn(BlockTags.FLOWERS));
                if (world.getBlockState(mut.down()).getMaterial().isReplaceable() || world.testBlockState(mut.down(), DripstoneHelper::canGenerate)) {
                    mut.move(Direction.DOWN);
                    world.setBlockState(mut, config.log.getBlockState(random, mut.toImmutable()).with(PillarBlock.AXIS, direction.getAxis()), 2);
                    decorationPoses.add(mut.toImmutable());
                }
                if (flag) {
                    world.setBlockState(mut, config.log.getBlockState(random, mut.toImmutable()).with(PillarBlock.AXIS, direction.getAxis()), 2);
                    decorationPoses.add(mut.toImmutable());
                }
                mut.move(direction);
            }
            for (BlockPos pos : decorationPoses) {
                if (world.getBlockState(pos.up()).isAir() && random.nextInt(5) == 0 && config.brown_mushroom) {
                    world.setBlockState(pos.up(), Blocks.BROWN_MUSHROOM.getDefaultState(), 2);
                }
                for (Direction directions : Direction.Type.HORIZONTAL) {
                    if (world.getBlockState(pos.offset(directions)).isAir() && random.nextInt(5) == 0 && config.vines) {
                        world.setBlockState(pos.offset(directions), Blocks.VINE.getDefaultState().with(VineBlock.getFacingProperty(directions.getOpposite()), true), 2);
                    }
                }
                Direction dir = Direction.Type.HORIZONTAL.random(random);
                if (world.isAir(pos.offset(dir)) && random.nextInt(3) == 0 && config.shelf_mushroom) {
                    world.setBlockState(pos.offset(dir), GoodEndingBlocks.BIRCH_MUSHROOM.getDefaultState().with(BirchMushroomPlantBlock.FACING, dir), 2);
                }
            }
        }
        return true;
    }

    private void extracted(StructureWorldAccess world, Random random, Direction direction, int logLength, BlockPos.Mutable mut, List<BlockPos> decorationPoses, FallenLogConfig config) {
    }
}