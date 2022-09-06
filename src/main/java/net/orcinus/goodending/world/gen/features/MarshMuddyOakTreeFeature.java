package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.util.WorldGenUtil;

import java.util.List;

public class MarshMuddyOakTreeFeature extends Feature<TreeFeatureConfig> {

    public MarshMuddyOakTreeFeature(Codec<TreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        TreeFeatureConfig config = context.getConfig();
        int treeHeight = ConstantIntProvider.create(5).get(random);
        List<BlockPos> leavePoses = Lists.newArrayList();
        for (int i = 0; i < treeHeight; i++) {
            if (random.nextInt(2) == 0 && i >= treeHeight / 2) {
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
        WorldGenUtil.generateHangingVines(world, 0.25F, random, leavePoses);
        return true;
    }

}