package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingTags;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

import java.util.Arrays;
import java.util.List;

public class CypressTreeFeature extends Feature<WaterTreeFeatureConfig> {

    public CypressTreeFeature(Codec<WaterTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<WaterTreeFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        List<BlockPos> branchPoses = Lists.newArrayList();
        int height = Mth.nextInt(random, 10, 15);
        Block cypressLog = GoodEndingBlocks.CYPRESS_LOG.get();
        boolean isPlanted = context.config().isPlanted;
        boolean initialFlag = (!isPlanted && !world.getBlockState(blockPos).is(Blocks.WATER)) || !world.getBlockState(blockPos.below()).is(BlockTags.DIRT);
        boolean initialFlag1 = isPlanted && !this.canGrow(world, blockPos.below());
        if (initialFlag || initialFlag1) {
            return false;
        }
        BlockState cypressLogDefaultState = cypressLog.defaultBlockState();
        for (int i = 0; i <= height; i++) {
            if (world.getBlockState(blockPos.above(i)).getMaterial().isReplaceable() || world.getBlockState(blockPos.above(i)).is(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(blockPos.above(i)).is(Blocks.MUD)) {
                world.setBlock(blockPos.above(i), cypressLogDefaultState, 19);
                if (random.nextInt(3) == 0 && i < height - 3 && i > 3) {
                    branchPoses.add(blockPos.above(i));
                }
            }
        }
        Direction randomDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
        BlockState leaveState = GoodEndingBlocks.CYPRESS_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1);
        branchPoses.stream().map(position -> position.relative(randomDirection)).forEach(brancingPos -> {
            if (world.getBlockState(brancingPos).getMaterial().isReplaceable() || world.getBlockState(brancingPos).is(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                world.setBlock(brancingPos, cypressLogDefaultState.setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()), 2);
                Arrays.stream(Direction.values()).forEach(direction -> {
                    for (int i = 0; i <= Mth.nextInt(random, 1, 4); i++) {
                        BlockPos relativeOffset = brancingPos.relative(direction).below(i);
                        if (world.isStateAtPosition(relativeOffset, DripstoneUtils::isEmptyOrWater) || world.getBlockState(relativeOffset).getMaterial().isReplaceable() || world.getBlockState(brancingPos).is(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                            world.setBlock(relativeOffset, leaveState.setValue(LeavesBlock.WATERLOGGED, world.getBlockState(relativeOffset).is(Blocks.WATER)), 2);
                            leavePoses.add(relativeOffset);
                        }
                    }
                });
            }
        });
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            if (world.getBlockState(blockPos.relative(direction)).getMaterial().isReplaceable() || world.getBlockState(blockPos.relative(direction)).is(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                world.setBlock(blockPos.relative(direction), cypressLogDefaultState, 19);
            }
            this.branchingRoot(world, blockPos.relative(direction), cypressLog, random, direction, 0);
        }
        if (world.getBlockState(blockPos.below()).is(Blocks.WATER) && world.getBlockState(blockPos).isAir()) {
            DuckweedFeature.generateDuckweed(world, blockPos, random);
        }
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            for (int length = 0; length < Mth.nextInt(random, 2, 5); length++) {
                BlockPos dirPos = blockPos.above(height).relative(direction, length);
                if (world.getBlockState(dirPos).getMaterial().isReplaceable() || world.getBlockState(dirPos).is(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                    world.setBlock(dirPos, cypressLogDefaultState.setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 19);
                    list.add(blockPos.above(height));
                    list.add(dirPos);
                }
            }
        }
        list.forEach(pos -> {
            for (Direction direction : Direction.values()) {
                if (world.getBlockState(pos.relative(direction)).isAir()) {
                    for (int t = 0; t < Mth.nextInt(random, 2, 5); t++) {
                        BlockPos leavePose = pos.relative(direction).below(t);
                        if (world.getBlockState(leavePose).is(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(leavePose).getMaterial().isReplaceable() || world.getBlockState(leavePose).is(BlockTags.LEAVES)){
                            world.setBlock(leavePose, leaveState.setValue(LeavesBlock.WATERLOGGED, world.getBlockState(leavePose).is(Blocks.WATER)), 19);
                            leavePoses.add(leavePose);
                        }
                    }
                }
            }
        });
        this.generateVines(world, 0.25F, random, leavePoses);
        return true;
    }

    public boolean canGrow(WorldGenLevel world, BlockPos blockPos) {
        int range = 3;
        boolean flag = false;
        for (int x = -range; x <= range; x++) {
            for (int z = -range; z <= range; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (world.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWaterOrLava)) {
                    break;
                }
                flag = true;
            }
        }
        return flag;
    }

    public boolean branchingRoot(WorldGenLevel world, BlockPos blockPos, Block block, RandomSource random, Direction direction, int tries) {
        BlockPos belowPos = blockPos.below();
        if (tries == 2) return this.repeatPlace(world, blockPos, block);
        BlockPos finalPos = random.nextInt(3) != 0 ? belowPos : blockPos.relative(direction);
        if ((world.getBlockState(finalPos).is(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(finalPos).getMaterial().isReplaceable() || world.getBlockState(finalPos).is(Blocks.WATER)) && tries < 2) {
            world.setBlock(finalPos, block.defaultBlockState(), 19);
            tries++;
            return branchingRoot(world, finalPos, block, random, direction, tries);
        } else {
            return true;
        }
    }

    public boolean repeatPlace(WorldGenLevel world, BlockPos blockPos, Block block) {
        BlockPos belowPos = blockPos.below();
        if (world.getBlockState(belowPos).is(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(belowPos).is(Blocks.WATER) || world.getBlockState(belowPos).getMaterial().isReplaceable()) {
            world.setBlock(belowPos, block.defaultBlockState(), 19);
            return this.repeatPlace(world, belowPos, block);
        } else {
            return true;
        }
    }

    public void generateVines(WorldGenLevel world, float probability, RandomSource random, List<BlockPos> leavePositions) {
        leavePositions.forEach((pos) -> {
            BlockPos blockPos;
            if (random.nextFloat() < probability) {
                blockPos = pos.west();
                if (world.isEmptyBlock(blockPos)) {
                    placeVines(blockPos, VineBlock.EAST, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.east();
                if (world.isEmptyBlock(blockPos)) {
                    placeVines(blockPos, VineBlock.WEST, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.north();
                if (world.isEmptyBlock(blockPos)) {
                    placeVines(blockPos, VineBlock.SOUTH, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.south();
                if (world.isEmptyBlock(blockPos)) {
                    placeVines(blockPos, VineBlock.NORTH, world);
                }
            }

        });
    }

    private static void placeVines(BlockPos pos, BooleanProperty faceProperty, WorldGenLevel world) {
        world.setBlock(pos, Blocks.VINE.defaultBlockState().setValue(faceProperty, true), 19);
        int i = 4;

        for (pos = pos.below(); world.isEmptyBlock(pos) && i > 0; --i) {
            world.setBlock(pos, Blocks.VINE.defaultBlockState().setValue(faceProperty, true), 19);
            pos = pos.below();
        }

    }
}