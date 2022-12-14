package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.MuddyOakFeatureConfig;

import java.util.List;

public class MuddyOakTreeFeature extends Feature<MuddyOakFeatureConfig> {

    public MuddyOakTreeFeature(Codec<MuddyOakFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<MuddyOakFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        MuddyOakFeatureConfig config = context.getConfig();
        int treeHeight = ConstantIntProvider.create(8).get(random);
        List<BlockPos> leavePoses = Lists.newArrayList();
        for (int i = 0; i < treeHeight; i++) {
            Direction direction = Direction.Type.HORIZONTAL.random(random);
            if (config.generateBranch && random.nextInt(2) == 0 && i >= treeHeight / 2) {
                for (int branchLength = 0; branchLength <= MathHelper.nextInt(random, 1, 2); branchLength++) {
                    BlockPos branchPos = blockPos.up(i).offset(direction, branchLength);
                    if (world.testBlockState(branchPos.down(), DripstoneHelper::canGenerate) && world.testBlockState(branchPos, blockState -> blockState.isAir() || blockState.isIn(BlockTags.LEAVES) || blockState.isOf(Blocks.WATER))){
                        world.setBlockState(branchPos, config.trunkProvider.getBlockState(random, branchPos).with(PillarBlock.AXIS, direction.getAxis()), 19);
                    }
                }
                if (world.testBlockState(blockPos.up(i), blockState -> blockState.isAir() || blockState.isIn(BlockTags.LEAVES) || blockState.isOf(Blocks.WATER) || blockState.isIn(BlockTags.LOGS))){
                    world.setBlockState(blockPos.up(i), config.trunkProvider.getBlockState(random, blockPos.up(i)), 2);
                }
                continue;
            }
            if (world.testBlockState(blockPos.up(i), blockState -> blockState.isAir() || blockState.isIn(BlockTags.LEAVES) || blockState.isOf(Blocks.WATER))) {
                world.setBlockState(blockPos.up(i), config.trunkProvider.getBlockState(random, blockPos.up(i)), 19);
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
                            if (world.testBlockState(pos.down(), DripstoneHelper::canGenerate)) {
                                world.setBlockState(pos.down(), config.foliageProvider.getBlockState(random, pos.down()).with(Properties.DISTANCE_1_7, 1), 19);
                                leavePoses.add(pos.down());
                            }
                            if (x == 0 || z == 0) {
                                if (world.testBlockState(pos.down(2), DripstoneHelper::canGenerate)) {
                                    world.setBlockState(pos.down(2), config.foliageProvider.getBlockState(random, pos.down(2)).with(Properties.DISTANCE_1_7, 1), 19);
                                    leavePoses.add(pos.down(2));
                                }
                            }
                        }
                    }
                    if (y > 0) {
                        eliminateFactor = eliminateFactor / (y * 2);
                    }
                    if (x * x + z * z <= eliminateFactor) {
                        if (world.testBlockState(pos, DripstoneHelper::canGenerate)) {
                            world.setBlockState(pos, config.foliageProvider.getBlockState(random, pos).with(Properties.DISTANCE_1_7, 1), 19);
                            leavePoses.add(pos);
                        }
                    }
                }
            }
        }
        this.generateHangingVines(world, 0.25F, random, leavePoses);
        return true;
    }

    public void generateHangingVines(StructureWorldAccess world, float probability, Random random, List<BlockPos> leavePositions) {
        leavePositions.stream().filter(pos -> world.isAir(pos.down()) && world.getBlockState(pos).isOf(Blocks.OAK_LEAVES)).forEach((pos) -> {
            BlockPos.Mutable mutable = pos.mutableCopy();
            if (random.nextFloat() < probability) {
                int length = MathHelper.nextInt(random, 1, 2);
                for (int i = 0; i <= length; i++) {
                    if (world.isAir(mutable)) {
                        if (i == length || !world.isAir(mutable.down())) {
                            world.setBlockState(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES.getDefaultState().with(AbstractPlantStemBlock.AGE, MathHelper.nextInt(random, 17, 25)), Block.NOTIFY_LISTENERS);
                            break;
                        }
                        world.setBlockState(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.getDefaultState(), Block.NOTIFY_LISTENERS);
                    }
                    mutable.move(Direction.DOWN);
                }
            }
        });
    }

}