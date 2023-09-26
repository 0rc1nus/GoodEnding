package net.orcinus.goodending.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.orcinus.goodending.init.GoodEndingConfiguredFeatures;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class GoodEndingConfiguredFeatureProvider extends FabricDynamicRegistryProvider {

    public GoodEndingConfiguredFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, GoodEndingConfiguredFeatures.SHALLOW_WATER_MUD);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_ALGAE);
        add(registries, entries, GoodEndingConfiguredFeatures.CYPRESS_TREE);
        add(registries, entries, GoodEndingConfiguredFeatures.CYPRESS_TREE_PLANTED);
        add(registries, entries, GoodEndingConfiguredFeatures.CYPRESS_TREE_FILTERED);
        add(registries, entries, GoodEndingConfiguredFeatures.MUDDY_OAK_TREE);
        add(registries, entries, GoodEndingConfiguredFeatures.MARSH_MUDDY_OAK_TREE);
        add(registries, entries, GoodEndingConfiguredFeatures.CATTAIL_PATCH);
        add(registries, entries, GoodEndingConfiguredFeatures.SWAMP_FALLEN_LOG);
        add(registries, entries, GoodEndingConfiguredFeatures.BIRCH_FALLEN_LOG);
        add(registries, entries, GoodEndingConfiguredFeatures.OAK_FALLEN_LOG);
        add(registries, entries, GoodEndingConfiguredFeatures.SPRUCE_FALLEN_LOG);
        add(registries, entries, GoodEndingConfiguredFeatures.ACACIA_FALLEN_LOG);
        add(registries, entries, GoodEndingConfiguredFeatures.SWAMP_VEGETATION);
        add(registries, entries, GoodEndingConfiguredFeatures.DUCKWEED_PATCH);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_PASTEL_WILDFLOWERS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_TWILIGHT_WILDFLOWERS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_SPICY_WILDFLOWERS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_BALMY_WILDFLOWERS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_TALL_GRASS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_TALL_GRASS_MARSH);
        add(registries, entries, GoodEndingConfiguredFeatures.TALL_BIRCH_TREE);
        add(registries, entries, GoodEndingConfiguredFeatures.TALL_BIRCH_VEGETATION);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_PINK_FLOWERED_LILY);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_FERN);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_LARGE_FERN);
        add(registries, entries, GoodEndingConfiguredFeatures.OAK_HAMMOCK_PATCH_TALL_GRASS);
        add(registries, entries, GoodEndingConfiguredFeatures.LARGE_LILY_PADS);
        add(registries, entries, GoodEndingConfiguredFeatures.PATCH_FLOWERING_WATERLILY);
        add(registries, entries, GoodEndingConfiguredFeatures.DISK_PODZOL);
        add(registries, entries, GoodEndingConfiguredFeatures.FANCY_DARK_OAK);
        add(registries, entries, GoodEndingConfiguredFeatures.FANCY_DARK_OAK_PLANTED);
        add(registries, entries, GoodEndingConfiguredFeatures.BIG_FANCY_DARK_OAK);
        add(registries, entries, GoodEndingConfiguredFeatures.BIG_FANCY_DARK_OAK_PLANTED);
        add(registries, entries, GoodEndingConfiguredFeatures.OAK_HAMMOCK_TREES);
        add(registries, entries, GoodEndingConfiguredFeatures.GRANITE_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.PLAINS_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.DESERT_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.RED_SANDSTONE_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.SMOOTH_BASALT_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.STONE_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.TUFF_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.COBBLESTONE_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.MOSSY_COBBLESTONE_BOULDER);
        add(registries, entries, GoodEndingConfiguredFeatures.MOSSIER_COBBLESTONE_BOULDER);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<ConfiguredFeature<?, ?>> resourceKey) {
        final HolderLookup.RegistryLookup<ConfiguredFeature<?, ?>> configuredFeatureRegistryLookup = registries.lookupOrThrow(Registries.CONFIGURED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/configured_feature";
    }
}
