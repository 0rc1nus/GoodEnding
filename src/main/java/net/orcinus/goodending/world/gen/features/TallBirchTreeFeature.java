package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class TallBirchTreeFeature extends Feature<TreeFeatureConfig> {

    public TallBirchTreeFeature(Codec<TreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        return false;
    }
}
