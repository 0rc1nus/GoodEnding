package net.orcinus.goodending.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.init.GoodEndingConfiguredFeatures;
import net.orcinus.goodending.init.GoodEndingPlacedFeatures;

public class GoodEndingDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(GoodEndingConfiguredFeatureProvider::new);
        pack.addProvider(GoodEndingPlacedFeatureProvider::new);
        pack.addProvider(GoodEndingBiomeProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder
                .add(Registries.CONFIGURED_FEATURE, GoodEndingConfiguredFeatures::bootstrap)
                .add(Registries.PLACED_FEATURE, GoodEndingPlacedFeatures::bootstrap)
                .add(Registries.BIOME, GoodEndingBiomes::bootstrap);
    }
}
