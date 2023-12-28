package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.init.GoodEndingBlocks;

public class DuckweedFeature extends Feature<NoneFeatureConfiguration> {

    public DuckweedFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        if (world.getBlockState(blockPos.below()).is(Blocks.WATER) && world.getBlockState(blockPos).canBeReplaced()) {
            DuckweedFeature.generateDuckweed(world, blockPos, random);
            return true;
        } else {
            return false;
        }
    }

    public static void generateDuckweed(WorldGenLevel world, BlockPos blockPos, RandomSource random) {
        BlockPos.MutableBlockPos spreadPos = new BlockPos.MutableBlockPos();
        int tries = UniformInt.of(30, 80).sample(random);
        for (int i = 0; i < tries; i++) {
            spreadPos.setWithOffset(blockPos, random.nextInt(3) - random.nextInt(3), 0, random.nextInt(3) - random.nextInt(3));
            if (random.nextFloat() < 0.23242F && world.getBlockState(spreadPos).canBeReplaced() && world.getBlockState(spreadPos.below()).is(Blocks.WATER)) {
                world.setBlock(spreadPos, random.nextInt(50) == 0 ? GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get().defaultBlockState() : GoodEndingBlocks.DUCKWEED.get().defaultBlockState(), 2);
            }
        }
    }
}