package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class BoulderConfig implements FeatureConfig {
    public static final Codec<BoulderConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("stone").forGetter(config -> config.stone), BlockStateProvider.TYPE_CODEC.fieldOf("secondary_stone").forGetter(config -> config.secondaryStone)).apply(instance, BoulderConfig::new));

    public final BlockStateProvider stone;
    public final BlockStateProvider secondaryStone;

    public BoulderConfig(BlockStateProvider stone, BlockStateProvider secondaryStone) {
        this.stone = stone;
        this.secondaryStone = secondaryStone;
    }

}
