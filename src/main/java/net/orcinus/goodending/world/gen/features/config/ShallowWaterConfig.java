package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class ShallowWaterConfig implements FeatureConfig {
    public static final Codec<ShallowWaterConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter(config -> config.state), Codec.BOOL.fieldOf("decorate").forGetter(config -> config.decorate), Codec.BOOL.fieldOf("water_surface").forGetter(config -> config.water_surface), Codec.INT.fieldOf("max_tries").forGetter(config -> config.maxTries), Codec.FLOAT.fieldOf("chance").forGetter(config -> config.chance)).apply(instance, ShallowWaterConfig::new));

    public final BlockStateProvider state;
    public final boolean decorate;
    public final boolean water_surface;
    public final int maxTries;
    public final float chance;

    public ShallowWaterConfig(BlockStateProvider state, boolean decorate, boolean water_surface, int maxTries, float chance) {
        this.state = state;
        this.decorate = decorate;
        this.water_surface = water_surface;
        this.maxTries = maxTries;
        this.chance = chance;
    }

}
