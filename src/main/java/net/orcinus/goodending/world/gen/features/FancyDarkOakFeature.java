package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.FancyDarkOakTreeConfig;

import java.util.List;
import java.util.Optional;

public class FancyDarkOakFeature extends Feature<FancyDarkOakTreeConfig> {

    public FancyDarkOakFeature(Codec<FancyDarkOakTreeConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FancyDarkOakTreeConfig> context) {
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        WorldGenLevel world = context.level();
        boolean huge = false;
        int height = 7;
        /*
         * If the dark oak tree is extra fancy, it will multiply the height by 2
         */
        if (context.config().isFancy) {
            height *= 2;
            huge = true;
        }
        int baseRadius = 2;
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        List<BlockPos> stemPoses = Lists.newArrayList();
        if (!world.getBlockState(blockPos.below()).is(BlockTags.DIRT)) {
            return false;
        } else {
            Optional<BlockPos> initialPos = this.getInitialPos(context.config(), world, blockPos, baseRadius, 1);
            if (initialPos.isEmpty()) {
                return false;
            } else {
                blockPos = initialPos.get();
                for (int x = -baseRadius; x <= baseRadius; x++) {
                    for (int z = -baseRadius; z <= baseRadius; z++) {
                        for (int y = 0; y < height; y++) {
                            /*
                             * Trunk Shape is done here
                             */
                            BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                            boolean flag = x == -baseRadius || x == baseRadius;
                            boolean flag1 = z == -baseRadius || z == baseRadius;
                            /*
                             * This condition is to make it so that the tree's trunk won't generate the corners
                             */
                            if (!(flag && flag1)) {
                                int corner = baseRadius - 1;
                                boolean logFlag = ((x == corner && flag1) || (x == -corner && flag1) || (z == corner && flag) || (z == -corner && flag)) && y > 1;
                                boolean logFlag1 = ((x == 0 && flag1) || (z == 0 && flag)) && y > 2;
                                boolean logFlag2 = (x == corner || x == -corner) && (z == corner || z == -corner) && y > 3;
                                /*
                                 * Starts carving the trunk as the height goes up
                                 * Same method as the first condition that is shown above
                                 */
                                if (logFlag || logFlag1 || logFlag2) continue;
                                /*
                                 * Another condition for checking the corners
                                 */
                                if (y > 5 && ((x == -corner && z == 0) || (x == corner && z == 0) || (x == 0 && z == -corner) || (x == 0 && z == corner))) {
                                    /*
                                     * Generates the 4 branches at the top of the tree
                                     */
                                    for (Direction direction : Direction.Plane.HORIZONTAL) {
                                        BlockPos branchPos = blockPos.above(height - 1).relative(direction);
                                        final int length = UniformInt.of(1, 3).sample(random);
                                        for (int i = 0; i < length; i++) {
                                            /*
                                             * Checks if the position is empty or if the material is replaceable
                                             * If true, then it'll place the block
                                             */
                                            if (world.isEmptyBlock(branchPos.relative(direction, i)) || world.getBlockState(branchPos.relative(direction, i)).getMaterial().isReplaceable()) {
                                                /*
                                                 * Generates the 4 branches
                                                 */
                                                world.setBlock(branchPos.relative(direction, i), Blocks.DARK_OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()), 2);
                                                for (int t = 0; t < length; t++) {
                                                    if (world.isEmptyBlock(branchPos.relative(direction, length).above(t)) || world.getBlockState(branchPos.relative(direction, length).above(t)).getMaterial().isReplaceable()) {
                                                        world.setBlock(branchPos.relative(direction, length).above(t), Blocks.DARK_OAK_LOG.defaultBlockState(), 19);
                                                        /*
                                                         * adding the branchPos to the list since it will be used to generate leaves later
                                                         */
                                                        list.add(branchPos.relative(direction, length).above(length));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                /*
                                 * Check if the trunk pos is air or if the material of the trunk pos is replaceable
                                 */
                                if (world.isEmptyBlock(pos) || world.getBlockState(pos).getMaterial().isReplaceable()) {
                                    /*
                                     * Generates the trunk
                                     */
                                    world.setBlock(pos, Blocks.DARK_OAK_LOG.defaultBlockState(), 19);
                                    /*
                                     * If the tree is extra fancy, it'll generate this some sort of meristem thing
                                     * Which isn't important if you were to make a normal tree
                                     */
                                    if (huge && y > height - 2) {
                                        /*
                                         * the stemposes list stores the pos which will be used later
                                         */
                                        stemPoses.add(pos);
                                    }
                                }
                            }
                        }
                    }
                }
                /*
                 * Generates the meristem
                 */
                for (BlockPos stemPos : stemPoses) {
                    for (int x = -2; x <= 2; x++) {
                        for (int z = -2; z <= 2; z++) {
                            int stemLength = Mth.nextInt(random, 1, 4);
                            for (int y = 0; y <= stemLength; y++) {
                                BlockPos pos = new BlockPos(stemPos.getX() + x, stemPos.getY() - y, stemPos.getZ() + z);
                                if (!(x * x + z * z <= 4)) continue;
                                if (world.isEmptyBlock(pos)) {
                                    world.setBlock(pos, Blocks.DARK_OAK_LOG.defaultBlockState(), 19);
                                }
                            }
                        }
                    }
                }
                /*
                 * Taking the poses from the arraylist and it'll proceed to generate leaves
                 */
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
                                if (world.isStateAtPosition(leavePos, DripstoneUtils::isEmptyOrWater)) {
                                    world.setBlock(leavePos, Blocks.DARK_OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 19);
                                }
                                leavePoses.add(leavePos);
                            }
                        }
                    }
                }
                /*
                 * Collected from the list that was added in the leave pos,
                 * Generates one dense dark oak leaves per tree (Leaves that emits leaves particle)
                 */
                for (BlockPos densePos : leavePoses) {
                    world.setBlock(densePos, GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get().defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 19);
                    break;
                }
                /*
                 * Generates hanging leaves
                 */
                for (BlockPos pos : leavePoses) {
                    if (random.nextFloat() < 0.15F && world.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir) && world.isStateAtPosition(pos, blockState -> blockState.is(Blocks.DARK_OAK_LEAVES))) {
                        int branchHeight = (int) Mth.nextFloat(random, height * 0.25F, height * 0.75F);
                        for (int i = 0; i <= branchHeight; i++) {
                            BlockPos placePos = pos.below(i);
                            if (i == branchHeight) {
                                if (world.isStateAtPosition(placePos, BlockBehaviour.BlockStateBase::isAir)) {
                                    world.setBlock(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get().defaultBlockState(), 19);
                                    break;
                                }
                            }
                            if (world.getBlockState(placePos.below()).is(BlockTags.LOGS)) {
                                world.setBlock(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get().defaultBlockState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, 22, 25)), 19);
                                break;
                            }
                            if (world.isStateAtPosition(placePos, BlockBehaviour.BlockStateBase::isAir)) {
                                world.setBlock(placePos, GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.get().defaultBlockState(), 19);
                            }
                        }
                    }
                }
                return true;
            }
        }
    }

    /**
     * Checks if there's any air below the tree. If there is, it'll move down by 1 block and it rechecks again.
     * It checks for 4 times. If it checks for 5 times, the tree will not generate.
     */
    public Optional<BlockPos> getInitialPos(FancyDarkOakTreeConfig config, WorldGenLevel world, BlockPos originPos, int baseRadius, int count) {
        if (config.planted) {
            return Optional.of(originPos);
        }
        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int z = -baseRadius; z <= baseRadius; z++) {
                BlockPos pos = new BlockPos(originPos.getX() + x, originPos.getY(), originPos.getZ() + z);
                if (world.isEmptyBlock(pos.below())) {
                    if (count > 4) {
                        return Optional.empty();
                    }
                    count++;
                    return this.getInitialPos(config, world, originPos.below(), baseRadius, count);
                }
            }
        }
        return Optional.of(originPos);
    }

}
