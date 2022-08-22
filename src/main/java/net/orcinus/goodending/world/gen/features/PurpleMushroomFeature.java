package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;

public class PurpleMushroomFeature extends Feature<DefaultFeatureConfig> {

    public PurpleMushroomFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        BlockState state = world.getBlockState(blockPos);
        if (world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT) && (state.isAir() || state.isOf(GoodEndingBlocks.PURPLE_MUSHROOM))) {
            int height = MathHelper.nextInt(random, 0, 2);
            for (int y = 0; y <= height; y++) {
                if (world.getBlockState(blockPos.up(y)).isAir() || world.getBlockState(blockPos.up(y)).isOf(GoodEndingBlocks.PURPLE_MUSHROOM) || world.getBlockState(blockPos.up(y)).getMaterial().isReplaceable()) {
                    world.setBlockState(blockPos.up(y), Blocks.MUSHROOM_STEM.getDefaultState(), 2);
                }
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + height, blockPos.getZ() + z);
                        BlockState blockState = world.getBlockState(pos);
                        if (blockState.getMaterial().isReplaceable() || blockState.isOf(Blocks.MUSHROOM_STEM)) {
                            world.setBlockState(pos, GoodEndingBlocks.PURPLE_MUSHROOM_BLOCK.getDefaultState(), 2);
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