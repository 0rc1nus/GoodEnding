package net.orcinus.goodending.world.gen.features.generators;

import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.jetbrains.annotations.Nullable;

public class SwampSaplingGenerator extends AbstractTreeGrower {

    @Nullable
    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return GoodEndingWorldGen.MUDDY_OAK_TREE;
    }

}
