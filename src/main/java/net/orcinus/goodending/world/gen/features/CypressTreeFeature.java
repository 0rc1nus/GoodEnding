package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingTags;
import net.orcinus.goodending.util.WorldGenUtil;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

import java.util.Arrays;
import java.util.List;

public class CypressTreeFeature extends Feature<WaterTreeFeatureConfig> {

    public CypressTreeFeature(Codec<WaterTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    public BlockPos moveUpIfNecessary(StructureWorldAccess world, BlockPos blockPos) {
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (!world.getBlockState(blockPos.offset(direction)).isAir() && world.testBlockState(blockPos.up(), DripstoneHelper::canGenerate)) {
                return this.moveUpIfNecessary(world, blockPos.up());
            }
        }
        return blockPos;
    }

    @Override
    public boolean generate(FeatureContext<WaterTreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        List<BlockPos> branchPoses = Lists.newArrayList();
        int height = MathHelper.nextInt(random, 10, 15);
        Block cypressLog = GoodEndingBlocks.CYPRESS_LOG;
        boolean flag = !context.getConfig().isPlanted && !world.getBlockState(blockPos).isOf(Blocks.WATER);
        if (flag || !world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        }
        BlockState cypressLogDefaultState = cypressLog.getDefaultState();
        for (int i = 0; i <= height; i++) {
            if (world.getBlockState(blockPos.up(i)).getMaterial().isReplaceable() || world.getBlockState(blockPos.up(i)).isIn(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(blockPos.up(i)).isOf(Blocks.MUD)) {
                world.setBlockState(blockPos.up(i), cypressLogDefaultState, 19);
                if (random.nextInt(3) == 0 && i < height - 3 && i > 3) {
                    branchPoses.add(blockPos.up(i));
                }
            }
        }
        Direction randomDirection = Direction.Type.HORIZONTAL.random(random);
        BlockState leaveState = GoodEndingBlocks.CYPRESS_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1);
        branchPoses.stream().map(position -> position.offset(randomDirection)).forEach(brancingPos -> {
            if (world.getBlockState(brancingPos).getMaterial().isReplaceable() || world.getBlockState(brancingPos).isIn(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                world.setBlockState(brancingPos, cypressLogDefaultState.with(PillarBlock.AXIS, randomDirection.getAxis()), 2);
                Arrays.stream(Direction.values()).forEach(direction -> {
                    for (int i = 0; i <= MathHelper.nextInt(random, 1, 4); i++) {
                        BlockPos relativeOffset = brancingPos.offset(direction).down(i);
                        if (world.testBlockState(relativeOffset, DripstoneHelper::canGenerate) || world.getBlockState(relativeOffset).getMaterial().isReplaceable() || world.getBlockState(brancingPos).isIn(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                            world.setBlockState(relativeOffset, leaveState.with(LeavesBlock.WATERLOGGED, world.getBlockState(relativeOffset).isOf(Blocks.WATER)), 2);
                            leavePoses.add(relativeOffset);
                        }
                    }
                });
            }
        });
        for (Direction direction : Direction.Type.HORIZONTAL) {
            if (world.getBlockState(blockPos.offset(direction)).getMaterial().isReplaceable() || world.getBlockState(blockPos.offset(direction)).isIn(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                world.setBlockState(blockPos.offset(direction), cypressLogDefaultState, 19);
            }
            this.branchingRoot(world, blockPos.offset(direction), cypressLog, random, direction, 0);
        }
        if (world.getBlockState(blockPos.down()).isOf(Blocks.WATER) && world.getBlockState(blockPos).isAir()) {
            DuckweedFeature.generateDuckweed(world, blockPos, random);
        }
        for (Direction direction : Direction.Type.HORIZONTAL) {
            for (int length = 0; length < MathHelper.nextInt(random, 2, 5); length++) {
                BlockPos dirPos = blockPos.up(height).offset(direction, length);
                if (world.getBlockState(dirPos).getMaterial().isReplaceable() || world.getBlockState(dirPos).isIn(GoodEndingTags.CYPRESS_REPLACEABLES)) {
                    world.setBlockState(dirPos, cypressLogDefaultState.with(PillarBlock.AXIS, direction.getAxis()), 19);
                    list.add(blockPos.up(height));
                    list.add(dirPos);
                }
            }
        }
        list.forEach(pos -> {
            for (Direction direction : Direction.values()) {
                if (world.getBlockState(pos.offset(direction)).isAir()) {
                    for (int t = 0; t < MathHelper.nextInt(random, 2, 5); t++) {
                        BlockPos leavePose = pos.offset(direction).down(t);
                        if (world.getBlockState(leavePose).isIn(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(leavePose).getMaterial().isReplaceable() || world.getBlockState(leavePose).isIn(BlockTags.LEAVES)){
                            world.setBlockState(leavePose, leaveState.with(LeavesBlock.WATERLOGGED, world.getBlockState(leavePose).isOf(Blocks.WATER)), 19);
                            leavePoses.add(leavePose);
                        }
                    }
                }
            }
        });
        WorldGenUtil.generateVines(world, 0.25F, random, leavePoses);
        return true;
    }

    public boolean branchingRoot(StructureWorldAccess world, BlockPos blockPos, Block block, Random random, Direction direction, int tries) {
        BlockPos belowPos = blockPos.down();
        if (tries == 2) return repeatInnit(world, blockPos, block);
        BlockPos finalPos = random.nextInt(3) != 0 ? belowPos : blockPos.offset(direction);
        if ((world.getBlockState(finalPos).isIn(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(finalPos).getMaterial().isReplaceable() || world.getBlockState(finalPos).isOf(Blocks.WATER)) && tries < 2) {
            world.setBlockState(finalPos, block.getDefaultState(), 19);
            tries++;
            return branchingRoot(world, finalPos, block, random, direction, tries);
        } else {
            return true;
        }
    }

    public boolean repeatInnit(StructureWorldAccess world, BlockPos blockPos, Block block) {
        BlockPos belowPos = blockPos.down();
        if (world.getBlockState(belowPos).isIn(GoodEndingTags.CYPRESS_REPLACEABLES) || world.getBlockState(belowPos).isOf(Blocks.WATER) || world.getBlockState(belowPos).getMaterial().isReplaceable()) {
            world.setBlockState(belowPos, block.getDefaultState(), 19);
            return repeatInnit(world, belowPos, block);
        } else {
            return true;
        }
    }
}