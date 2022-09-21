package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record HalfWaterloggedDecorationConfig(BlockStateProvider placed_block, IntProvider radius) implements FeatureConfig {

    public static final Codec<HalfWaterloggedDecorationConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("placed_block").forGetter(config -> config.placed_block), IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(config -> config.radius)).apply(instance, HalfWaterloggedDecorationConfig::new));

}