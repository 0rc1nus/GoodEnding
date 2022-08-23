package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.util.WorldGenUtil;

import java.util.List;

public class SwampTreeFeature extends Feature<TreeFeatureConfig> {

    public SwampTreeFeature(Codec<TreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        TreeFeatureConfig config = context.getConfig();
        int treeHeight = ConstantIntProvider.create(8).get(random);
        List<BlockPos> leavePoses = Lists.newArrayList();
        for (int i = 0; i < treeHeight; i++) {
            Direction direction = Direction.Type.HORIZONTAL.random(random);
            if (random.nextInt(2) == 0 && i >= treeHeight / 2) {
                for (int branchLength = 0; branchLength <= MathHelper.nextInt(random, 1, 2); branchLength++) {
                    world.setBlockState(blockPos.up(i).offset(direction, branchLength), config.trunkProvider.getBlockState(random, blockPos.up(i).offset(direction, branchLength)).with(PillarBlock.AXIS, direction.getAxis()), 19);
                }
                world.setBlockState(blockPos.up(i), config.trunkProvider.getBlockState(random, blockPos.up(i)), 19);
                continue;
            }
            world.setBlockState(blockPos.up(i), config.trunkProvider.getBlockState(random, blockPos.up(i)), 19);
        }
        for (int y = -1; y <= 2; y++) {
            for (int x = -4; x <= 4; x++) {
                for (int z = -4; z <= 4; z++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y + treeHeight, blockPos.getZ() + z);
                    int eliminateFactor = 16;
                    if (y == -1) {
                        if (((x <= 1 && x >= -1) && (z == 2 || z == -2)) || ((z <= 1 && z >= -1) && (x == 2 || x == -2))) {
                            world.setBlockState(pos.down(), config.foliageProvider.getBlockState(random, pos.down()).with(Properties.DISTANCE_1_7, 1), 19);
                            if (x == 0 || z == 0) {
                                world.setBlockState(pos.down(2), config.foliageProvider.getBlockState(random, pos.down(2)).with(Properties.DISTANCE_1_7, 1), 19);
                            }
                        }
                    }
                    if (y > 0) {
                        eliminateFactor = eliminateFactor / (y * 2);
                    }
                    if (x * x + z * z <= eliminateFactor) {
                        world.setBlockState(pos, config.foliageProvider.getBlockState(random, pos).with(Properties.DISTANCE_1_7, 1), 19);
                        leavePoses.add(pos);
                    }
                }
            }
        }
        WorldGenUtil.generateHangingVines(world, 0.25F, random, leavePoses);
        return true;
    }

}