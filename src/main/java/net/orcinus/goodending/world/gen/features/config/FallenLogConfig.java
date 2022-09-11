package net.orcinus.goodending.world.gen.features.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class FallenLogConfig implements FeatureConfig {

    public static final Codec<FallenLogConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("log").forGetter(config -> config.log), IntProvider.VALUE_CODEC.fieldOf("length").forGetter(config -> config.length), Codec.BOOL.fieldOf("shelf_mushroom").forGetter(config -> config.shelf_mushroom), Codec.BOOL.fieldOf("brown_mushroom").forGetter(config -> config.brown_mushroom), Codec.BOOL.fieldOf("vines").forGetter(config -> config.vines)).apply(instance, FallenLogConfig::new));

    public final BlockStateProvider log;
    public final IntProvider length;
    public final boolean shelf_mushroom;
    public final boolean brown_mushroom;
    public final boolean vines;

    public FallenLogConfig(BlockStateProvider log, IntProvider length, boolean shelf_mushroom, boolean brown_mushroom) {
        this(log, length, shelf_mushroom, brown_mushroom, true);
    }

    public FallenLogConfig(BlockStateProvider log, IntProvider length, boolean shelf_mushroom, boolean brown_mushroom, boolean vines) {
        this.log = log;
        this.length = length;
        this.shelf_mushroom = shelf_mushroom;
        this.brown_mushroom = brown_mushroom;
        this.vines = vines;
    }
}