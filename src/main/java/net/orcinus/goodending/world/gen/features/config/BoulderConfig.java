package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;


public record BoulderConfig(Boolean coarseDirt, BlockStateProvider stone, BlockStateProvider secondaryStone, FloatProvider size) implements FeatureConfiguration {
    public static final Codec<BoulderConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.BOOL.fieldOf("coarse_dirt").forGetter(config -> config.coarseDirt),
            BlockStateProvider.CODEC.fieldOf("stone").forGetter(config -> config.stone),
            BlockStateProvider.CODEC.fieldOf("secondary_stone").forGetter(config -> config.secondaryStone),
            FloatProvider.codec(1, 64).fieldOf("size").forGetter(config -> config.size)
    ).apply(instance, BoulderConfig::new));

}