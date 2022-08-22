package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class HalfWaterloggedDecorationConfig implements FeatureConfig {

    public static final Codec<HalfWaterloggedDecorationConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("placed_block").forGetter(config -> config.placed_block), IntProvider.VALUE_CODEC.fieldOf("radius").forGetter(config -> config.radius)).apply(instance, HalfWaterloggedDecorationConfig::new));

    public final BlockStateProvider placed_block;
    public final IntProvider radius;

    public HalfWaterloggedDecorationConfig(BlockStateProvider placed_block, IntProvider radius) {
        this.placed_block = placed_block;
        this.radius = radius;
    }

}