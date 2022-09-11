package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class FancyDarkOakFeatureConfig implements FeatureConfig {
    public static final Codec<FancyDarkOakFeatureConfig> CODEC = RecordCodecBuilder.create(instnace -> instnace.group(Codec.BOOL.fieldOf("planted").forGetter(config -> config.planted)).apply(instnace, FancyDarkOakFeatureConfig::new));

    public final boolean planted;

    public FancyDarkOakFeatureConfig(boolean planted) {
        this.planted = planted;
    }

    public boolean isPlanted() {
        return this.planted;
    }
}
