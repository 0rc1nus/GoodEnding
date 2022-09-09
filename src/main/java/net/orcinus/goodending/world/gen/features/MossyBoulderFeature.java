package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.List;

public class MossyBoulderFeature extends Feature<DefaultFeatureConfig> {

    public MossyBoulderFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        List<BlockPos> list = Lists.newArrayList();
        if (world.isAir(blockPos) && world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            for (int i = -1; i < 1; i++) {
                for (int j = -1; j < 1; j++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + i, blockPos.getY(), blockPos.getZ() + j);
                    if (i == -1 && j == -1) {
                        list.add(pos);
                    }
                    if (world.testBlockState(pos, state -> state.isAir() || state.isOf(Blocks.WATER) || state.getMaterial().isReplaceable())) {
                        world.setBlockState(pos, Blocks.MOSSY_COBBLESTONE.getDefaultState(), 2);
                    }
                }
            }
            for (BlockPos pos : list) {
                if (random.nextInt(3) == 0) {
                    pos = pos.east(2);
                }
                else if (random.nextInt(3) == 0) {
                    pos = pos.east(2).south(2);
                }
                else if (random.nextInt(3) == 0) {
                    pos = pos.south(2);
                }
                for (int i = -1; i < 1; i++) {
                    for (int j = -1; j < 1; j++) {
                        BlockState blockState = Blocks.MOSSY_COBBLESTONE.getDefaultState();
                        BlockPos placePos = pos.add(i, 0, j);
                        if (world.testBlockState(placePos, state -> state.isAir() || state.isOf(Blocks.WATER) || state.getMaterial().isReplaceable()) && world.testBlockState(placePos.up(), state -> state.isAir() || state.isOf(Blocks.WATER) || state.getMaterial().isReplaceable())) {
                            world.setBlockState(placePos, blockState, 2);
                            world.setBlockState(placePos.up(), blockState, 2);
                        }
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
