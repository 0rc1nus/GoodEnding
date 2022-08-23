package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class DuckweedFeature extends Feature<DefaultFeatureConfig> {

    public DuckweedFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        if (world.getBlockState(blockPos.down()).isOf(Blocks.WATER) && world.getBlockState(blockPos).getMaterial().isReplaceable()) {
            generateDuckweed(world, blockPos, random);
            return true;
        } else {
            return false;
        }
    }

    public static void generateDuckweed(StructureWorldAccess world, BlockPos blockPos, Random random) {
        BlockPos.Mutable spreadPos = new BlockPos.Mutable();
        int tries = UniformIntProvider.create(30, 80).get(random);
        List<Block> blocks = Util.make(Lists.newArrayList(), list -> {
            list.add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD);
            list.add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD);
            list.add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD);
        });
        for (int i = 0; i < tries; i++) {
            spreadPos.set(blockPos, random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            if (random.nextFloat() < 0.23242F && world.getBlockState(spreadPos).getMaterial().isReplaceable() && world.getBlockState(spreadPos.down()).isOf(Blocks.WATER)) {
                world.setBlockState(spreadPos, random.nextInt(30) == 0 ? blocks.get(random.nextInt(blocks.size())).getDefaultState() : GoodEndingBlocks.DUCKWEED.getDefaultState(), 2);
            }
        }
    }
}