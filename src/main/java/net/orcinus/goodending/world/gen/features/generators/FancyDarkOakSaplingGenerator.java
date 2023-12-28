package net.orcinus.goodending.world.gen.features.generators;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class FancyDarkOakSaplingGenerator extends AbstractMegaTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return random.nextFloat() < 0.25F ? GoodEndingConfiguredFeatures.BIG_FANCY_DARK_OAK_PLANTED : GoodEndingConfiguredFeatures.FANCY_DARK_OAK_PLANTED;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean b) {
        return null;
    }
}
