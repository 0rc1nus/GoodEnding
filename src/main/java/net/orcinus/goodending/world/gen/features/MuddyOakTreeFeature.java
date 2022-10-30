package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.MuddyOakFeatureConfig;

import java.util.List;

public class MuddyOakTreeFeature extends Feature<MuddyOakFeatureConfig> {

    public MuddyOakTreeFeature(Codec<MuddyOakFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<MuddyOakFeatureConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        MuddyOakFeatureConfig config = context.config();
        int treeHeight = ConstantInt.of(8).sample(random);
        List<BlockPos> leavePoses = Lists.newArrayList();
        for (int i = 0; i < treeHeight; i++) {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            if (config.generateBranch && random.nextInt(2) == 0 && i >= treeHeight / 2) {
                for (int branchLength = 0; branchLength <= Mth.nextInt(random, 1, 2); branchLength++) {
                    BlockPos branchPos = blockPos.above(i).relative(direction, branchLength);
                    if (world.isStateAtPosition(branchPos.below(), DripstoneUtils::isEmptyOrWater) && world.isStateAtPosition(branchPos, blockState -> blockState.isAir() || blockState.is(BlockTags.LEAVES) || blockState.is(Blocks.WATER))){
                        world.setBlock(branchPos, config.trunkProvider.getState(random, branchPos).setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 19);
                    }
                }
                if (world.isStateAtPosition(blockPos.above(i), blockState -> blockState.isAir() || blockState.is(BlockTags.LEAVES) || blockState.is(Blocks.WATER) || blockState.is(BlockTags.LOGS))){
                    world.setBlock(blockPos.above(i), config.trunkProvider.getState(random, blockPos.above(i)), 2);
                }
                continue;
            }
            if (world.isStateAtPosition(blockPos.above(i), blockState -> blockState.isAir() || blockState.is(BlockTags.LEAVES) || blockState.is(Blocks.WATER))) {
                world.setBlock(blockPos.above(i), config.trunkProvider.getState(random, blockPos.above(i)), 19);
            }
        }
        for (int y = -1; y <= 2; y++) {
            int radius = 4;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y + treeHeight, blockPos.getZ() + z);
                    int eliminateFactor = 16;
                    if (y == -1) {
                        if (((x <= 1 && x >= -1) && (z == 2 || z == -2)) || ((z <= 1 && z >= -1) && (x == 2 || x == -2))) {
                            if (world.isStateAtPosition(pos.below(), DripstoneUtils::isEmptyOrWater)) {
                                world.setBlock(pos.below(), config.foliageProvider.getState(random, pos.below()).setValue(BlockStateProperties.DISTANCE, 1), 19);
                                leavePoses.add(pos.below());
                            }
                            if (x == 0 || z == 0) {
                                if (world.isStateAtPosition(pos.below(2), DripstoneUtils::isEmptyOrWater)) {
                                    world.setBlock(pos.below(2), config.foliageProvider.getState(random, pos.below(2)).setValue(BlockStateProperties.DISTANCE, 1), 19);
                                    leavePoses.add(pos.below(2));
                                }
                            }
                        }
                    }
                    if (y > 0) {
                        eliminateFactor = eliminateFactor / (y * 2);
                    }
                    if (x * x + z * z <= eliminateFactor) {
                        if (world.isStateAtPosition(pos, DripstoneUtils::isEmptyOrWater)) {
                            world.setBlock(pos, config.foliageProvider.getState(random, pos).setValue(BlockStateProperties.DISTANCE, 1), 19);
                            leavePoses.add(pos);
                        }
                    }
                }
            }
        }
        this.generateHangingVines(world, 0.25F, random, leavePoses);
        return true;
    }

    public void generateHangingVines(WorldGenLevel world, float probability, RandomSource random, List<BlockPos> leavePositions) {
        leavePositions.stream().filter(pos -> world.isEmptyBlock(pos.below()) && world.getBlockState(pos).is(Blocks.OAK_LEAVES)).forEach((pos) -> {
            BlockPos.MutableBlockPos mutable = pos.mutable();
            if (random.nextFloat() < probability) {
                int length = Mth.nextInt(random, 1, 2);
                for (int i = 0; i <= length; i++) {
                    if (world.isEmptyBlock(mutable)) {
                        if (i == length || !world.isEmptyBlock(mutable.below())) {
                            world.setBlock(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, 17, 25)), 2);
                            break;
                        }
                        world.setBlock(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.get().defaultBlockState(), 2);
                    }
                    mutable.move(Direction.DOWN);
                }
            }
        });
    }

}