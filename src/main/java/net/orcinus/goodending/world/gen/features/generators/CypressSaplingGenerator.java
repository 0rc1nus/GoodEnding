package net.orcinus.goodending.world.gen.features.generators;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.jetbrains.annotations.Nullable;

public class CypressSaplingGenerator extends SaplingGenerator {
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return GoodEndingWorldGen.CYPRESS_TREE;
    }
}
