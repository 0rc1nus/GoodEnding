package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class BigLilyPadConfig implements FeatureConfig {
    public static final Codec<BigLilyPadConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.INT.fieldOf("size").forGetter(config -> config.size)).apply(instance, BigLilyPadConfig::new));

    public final int size;

    public BigLilyPadConfig(int size) {
        this.size = size;
    }

}
