package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractBlock;
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
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.FancyDarkOakTreeConfig;

import java.util.List;
import java.util.Optional;

public class FancyDarkOakFeature extends Feature<FancyDarkOakTreeConfig> {

    public FancyDarkOakFeature(Codec<FancyDarkOakTreeConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FancyDarkOakTreeConfig> context) {
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        boolean huge = false;
        int height = 7;
        if (context.getConfig().isFancy) {
            height *= 2;
            huge = true;
        }
        int baseRadius = 2;
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        List<BlockPos> stemPoses = Lists.newArrayList();
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            Optional<BlockPos> initialPos = this.getInitialPos(context.getConfig(), world, blockPos, baseRadius, 1);
            if (initialPos.isEmpty()) {
                return false;
            } else {
                blockPos = initialPos.get();
                for (int x = -baseRadius; x <= baseRadius; x++) {
                    for (int z = -baseRadius; z <= baseRadius; z++) {
                        for (int y = 0; y < height; y++) {
                            BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                            boolean flag = x == -baseRadius || x == baseRadius;
                            boolean flag1 = z == -baseRadius || z == baseRadius;
                            if (!(flag && flag1)) {
                                int corner = baseRadius - 1;
                                boolean logFlag = ((x == corner && flag1) || (x == -corner && flag1) || (z == corner && flag) || (z == -corner && flag)) && y > 1;
                                boolean logFlag1 = ((x == 0 && flag1) || (z == 0 && flag)) && y > 2;
                                boolean logFlag2 = (x == corner || x == -corner) && (z == corner || z == -corner) && y > 3;
                                if (logFlag || logFlag1 || logFlag2) continue;
                                if (y > 5 && ((x == -corner && z == 0) || (x == corner && z == 0) || (x == 0 && z == -corner) || (x == 0 && z == corner))) {
                                    for (Direction direction : Direction.Type.HORIZONTAL) {
                                        BlockPos branchPos = blockPos.up(height - 1).offset(direction);
                                        final int length = UniformIntProvider.create(1, 3).get(random);
                                        for (int i = 0; i < length; i++) {
                                            if (world.isAir(branchPos.offset(direction, i)) || world.getBlockState(branchPos.offset(direction, i)).getMaterial().isReplaceable()) {
                                                world.setBlockState(branchPos.offset(direction, i), Blocks.DARK_OAK_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 2);
                                                for (int t = 0; t < length; t++) {
                                                    if (world.isAir(branchPos.offset(direction, length).up(t)) || world.getBlockState(branchPos.offset(direction, length).up(t)).getMaterial().isReplaceable()) {
                                                        world.setBlockState(branchPos.offset(direction, length).up(t), Blocks.DARK_OAK_LOG.getDefaultState(), 19);
                                                        list.add(branchPos.offset(direction, length).up(length));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                if (world.isAir(pos) || world.getBlockState(pos).getMaterial().isReplaceable()) {
                                    world.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState(), 19);
                                    if (huge && y > height - 2) {
                                        stemPoses.add(pos);
                                    }
                                }
                            }
                        }
                    }
                }
                for (BlockPos stemPos : stemPoses) {
                    for (int x = -2; x <= 2; x++) {
                        for (int z = -2; z <= 2; z++) {
                            int stemLength = MathHelper.nextInt(random, 1, 4);
                            for (int y = 0; y <= stemLength; y++) {
                                BlockPos pos = new BlockPos(stemPos.getX() + x, stemPos.getY() - y, stemPos.getZ() + z);
                                if (!(x * x + z * z <= 4)) continue;
                                if (world.isAir(pos)) {
                                    world.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState(), 19);
                                }
                            }
                        }
                    }
                }
                for (BlockPos pos : list) {
                    int leaveRadius = 2;
                    for (int x = -leaveRadius; x <= leaveRadius; x++) {
                        for (int z = -leaveRadius; z <= leaveRadius; z++) {
                            for (int y = -1; y <= 1; y++) {
                                BlockPos leavePos = new BlockPos(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                                boolean flag = (x == -leaveRadius || x == leaveRadius) && (z == -leaveRadius || z == leaveRadius);
                                boolean flag1 = y == 1 && !(x > -leaveRadius + random.nextInt(2) && x < leaveRadius - random.nextInt(2) && z > -leaveRadius + random.nextInt(2) && z < leaveRadius - random.nextInt(2));
                                boolean flag2 = y == -1 && !(x * x + z * z <= 4);
                                if (flag || flag1 || flag2) continue;
                                if (world.testBlockState(leavePos, DripstoneHelper::canGenerate)) {
                                    world.setBlockState(leavePos, Blocks.DARK_OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19);
                                }
                                leavePoses.add(leavePos);
                            }
                        }
                    }
                }
                for (BlockPos densePos : leavePoses) {
                    world.setBlockState(densePos, GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19);
                    break;
                }
                for (BlockPos pos : leavePoses) {
                    if (random.nextFloat() < 0.5F && world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir) && world.testBlockState(pos, blockState -> blockState.isOf(Blocks.DARK_OAK_LEAVES))) {
                        int branchHeight = MathHelper.nextInt(random, 1, 6);
                        for (int i = 0; i <= branchHeight; i++) {
                            BlockPos placePos = pos.down(i);
                            if (i == branchHeight) {
                                if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                                    world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.getDefaultState().with(HangingLeavesBlock.AGE, 25), 19);
                                    break;
                                }
                            }
                            if (world.getBlockState(placePos.down()).isIn(BlockTags.LOGS)) {
                                world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.getDefaultState().with(HangingLeavesBlock.AGE, 25), 19);
                                break;
                            }
                            if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                                world.setBlockState(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.getDefaultState(), 19);
                            }
                        }
                    }
                }
                return true;
            }
        }
    }

    public Optional<BlockPos> getInitialPos(FancyDarkOakTreeConfig config, StructureWorldAccess world, BlockPos originPos, int baseRadius, int count) {
        if (config.planted) {
            return Optional.of(originPos);
        }
        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int z = -baseRadius; z <= baseRadius; z++) {
                BlockPos pos = new BlockPos(originPos.getX() + x, originPos.getY(), originPos.getZ() + z);
                if (world.isAir(pos.down())) {
                    if (count > 4) {
                        return Optional.empty();
                    }
                    count++;
                    return this.getInitialPos(config, world, originPos.down(), baseRadius, count);
                }
            }
        }
        return Optional.of(originPos);
    }

}
