package net.orcinus.goodending.world.gen.features.generators;

import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.jetbrains.annotations.Nullable;

public class FancyDarkOakSaplingGenerator extends LargeTreeSaplingGenerator {

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return random.nextFloat() < 0.25F ? GoodEndingWorldGen.BIG_FANCY_DARK_OAK_PLANTED : GoodEndingWorldGen.FANCY_DARK_OAK_PLANTED;
    }

    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return null;
    }

}
