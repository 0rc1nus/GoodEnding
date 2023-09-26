package net.orcinus.goodending.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.orcinus.goodending.init.GoodEndingBiomes;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class GoodEndingBiomeProvider extends FabricDynamicRegistryProvider {

    public GoodEndingBiomeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, GoodEndingBiomes.MARSHY_SWAMP_KEY);
        add(registries, entries, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<Biome> resourceKey) {
        final HolderLookup.RegistryLookup<Biome> configuredFeatureRegistryLookup = registries.lookupOrThrow(Registries.BIOME);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/biome";
    }
}
