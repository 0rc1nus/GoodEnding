package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;

import java.util.List;

public class FallenLogFeature extends Feature<FallenLogConfig> {

    public FallenLogFeature(Codec<FallenLogConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenLogConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        int logLength = Mth.nextInt(random, 4, 8);
        BlockPos.MutableBlockPos mut = blockPos.mutable();
        List<BlockPos> decorationPoses = Lists.newArrayList();
        FallenLogConfig config = context.config();
        if (world.getBlockState(blockPos.below()).is(BlockTags.DIRT) && world.isStateAtPosition(blockPos, DripstoneUtils::isEmptyOrWater)) {
            if (!world.isStateAtPosition(blockPos.relative(direction), DripstoneUtils::isEmptyOrWater)) {
                direction = direction.getClockWise();
            }
            for (int i = 0; i <= logLength; i++) {
                boolean flag = world.getBlockState(mut).getMaterial().isReplaceable() || world.isStateAtPosition(mut, state -> state.isAir() || state.is(Blocks.WATER) || state.is(BlockTags.FLOWERS));
                if (world.getBlockState(mut.below()).getMaterial().isReplaceable() || world.isStateAtPosition(mut.below(), DripstoneUtils::isEmptyOrWater)) {
                    mut.move(Direction.DOWN);
                    world.setBlock(mut, config.log.getState(random, mut.immutable()).setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
                    decorationPoses.add(mut.immutable());
                }
                if (flag) {
                    world.setBlock(mut, config.log.getState(random, mut.immutable()).setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
                    decorationPoses.add(mut.immutable());
                }
                mut.move(direction);
            }
            for (BlockPos pos : decorationPoses) {
                if (world.getBlockState(pos.above()).isAir() && random.nextInt(5) == 0 && config.brown_mushroom) {
                    world.setBlock(pos.above(), Blocks.BROWN_MUSHROOM.defaultBlockState(), 2);
                }
                for (Direction directions : Direction.Plane.HORIZONTAL) {
                    if (world.getBlockState(pos.relative(directions)).isAir() && random.nextInt(5) == 0 && config.vines) {
                        world.setBlock(pos.relative(directions), Blocks.VINE.defaultBlockState().setValue(VineBlock.getPropertyForFace(directions.getOpposite()), true), 2);
                    }
                }
                Direction dir = Direction.Plane.HORIZONTAL.getRandomDirection(random);
                if (world.isEmptyBlock(pos.relative(dir)) && random.nextInt(3) == 0 && config.shelf_mushroom) {
                    world.setBlock(pos.relative(dir), GoodEndingBlocks.BIRCH_MUSHROOM.get().defaultBlockState().setValue(BirchMushroomPlantBlock.FACING, dir), 2);
                }
            }
            return true;
        } else {
            return false;
        }
    }

}