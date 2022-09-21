package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record ShallowWaterConfig(BlockStateProvider state, boolean decorate, boolean water_surface, int maxTries, float chance) implements FeatureConfig {
    public static final Codec<ShallowWaterConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> config.state), Codec.BOOL.fieldOf("decorate").forGetter(config -> config.decorate), Codec.BOOL.fieldOf("water_surface").forGetter(config -> config.water_surface), Codec.INT.fieldOf("max_tries").forGetter(config -> config.maxTries), Codec.FLOAT.fieldOf("chance").forGetter(config -> config.chance)).apply(instance, ShallowWaterConfig::new));
}
