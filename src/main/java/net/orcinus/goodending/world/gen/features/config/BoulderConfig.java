package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;


public record BoulderConfig(Boolean coarseDirt, BlockStateProvider stone, BlockStateProvider secondaryStone, FloatProvider size) implements FeatureConfig {

    public static final Codec<BoulderConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.BOOL.fieldOf("coarse_dirt").forGetter(config -> config.coarseDirt),
        BlockStateProvider.TYPE_CODEC.fieldOf("stone").forGetter(config -> config.stone),
        BlockStateProvider.TYPE_CODEC.fieldOf("secondary_stone").forGetter(config -> config.secondaryStone),
        FloatProvider.createValidatedCodec(1, 64).fieldOf("size").forGetter(config -> config.size)
    ).apply(instance, BoulderConfig::new));

}