package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class FancyDarkOakFeature extends Feature<DefaultFeatureConfig> {

    public FancyDarkOakFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        int baseRadius = 2;
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            for (int x = -baseRadius; x <= baseRadius; x++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    int height = 7;
                    if (random.nextInt(3) == 0) {
                        height *= 1.5;
                    }
                    for (int y = 0; y < height; y++) {
                        BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                        boolean flag = x == -baseRadius || x == baseRadius;
                        boolean flag1 = z == -baseRadius || z == baseRadius;
                        if (!(flag && flag1)) {
                            boolean logFlag = ((x == 1 && flag1) || (x == -1 && flag1) || (z == 1 && flag) || (z == -1 && flag)) && y > 1;
                            boolean logFlag1 = ((x == 0 && flag1) || (z == 0 && flag)) && y > 2;
                            boolean logFlag2 = (x == 1 || x == -1) && (z == 1 || z == -1) && y > 3;
                            if (logFlag || logFlag1 || logFlag2) continue;
                            if (y > 5 && ((x == -1 && z == 0) || (x == 1 && z == 0) || (x == 0 && z == -1) || (x == 0 && z == 1))) {
                                for (Direction direction : Direction.Type.HORIZONTAL) {
                                    BlockPos branchPos = blockPos.up(height - 1).offset(direction);
                                    final int length = UniformIntProvider.create(1, 3).get(random);
                                    for (int i = 0; i < length; i++) {
                                        if (world.isAir(branchPos.offset(direction, i))) {
                                            world.setBlockState(branchPos.offset(direction, i), Blocks.DARK_OAK_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 2);
                                            for (int t = 0; t < length; t++) {
                                                world.setBlockState(branchPos.offset(direction, length).up(t), Blocks.DARK_OAK_LOG.getDefaultState(), 2);
                                                list.add(branchPos.offset(direction, length).up(length));
                                            }
                                        }
                                    }
                                }
                            }
                            world.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState(), 19);
                        }
                    }
                }
            }
            for (BlockPos pos : list) {
                for (int x = -2; x <= 2; x++) {
                    for (int z = -2; z <= 2; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos leavePos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                            boolean flag = (x == -2 || x == 2) && (z == -2 || z == 2);
                            boolean flag1 = y == 1 && !(x > -2 && x < 2 && z > -2 && z < 2);
                            boolean flag2 = y == -1 && !(x * x + z * z <= 4);
                            if (flag || flag1 || flag2) continue;
                            if (world.testBlockState(leavePos, DripstoneHelper::canGenerate)) {
                                world.setBlockState(leavePos, Blocks.DARK_OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 2);
                            }
                            if (random.nextFloat() < 0.15F) {
                                leavePoses.add(leavePos);
                            }
                        }
                    }
                }
            }
            for (BlockPos pos : leavePoses) {
                if (random.nextFloat() < 0.5F && world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir) && world.testBlockState(pos, blockState -> blockState.isOf(Blocks.DARK_OAK_LEAVES))) {
                    int height = MathHelper.nextInt(random, 1, 6);
                    for (int i = 0; i <= height; i++) {
                        BlockPos placePos = pos.down(i);
                        if (i == height) {
                            if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                                world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.getDefaultState(), 2);
                                break;
                            }
                        }
                        if (world.getBlockState(placePos.down()).isIn(BlockTags.LOGS)) {
                            world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.getDefaultState(), 2);
                            break;
                        }
                        if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                            world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.getDefaultState(), 2);
                        }
                    }
                }
            }
            return true;
        }
    }
}
