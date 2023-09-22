package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public record HalfWaterloggedDecorationConfig(BlockStateProvider placed_block, IntProvider radius) implements FeatureConfiguration {
    public static final Codec<HalfWaterloggedDecorationConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.CODEC.fieldOf("placed_block").forGetter(config -> config.placed_block), IntProvider.CODEC.fieldOf("radius").forGetter(config -> config.radius)).apply(instance, HalfWaterloggedDecorationConfig::new));
}