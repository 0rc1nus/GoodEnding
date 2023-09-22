package net.orcinus.goodending.world.gen.features.generators;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class CypressSaplingGenerator extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource randomSource, boolean bl) {
        return GoodEndingConfiguredFeatures.CYPRESS_TREE_PLANTED;
    }
}