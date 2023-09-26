package net.orcinus.goodending.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.orcinus.goodending.init.GoodEndingPlacedFeatures;

import java.util.concurrent.CompletableFuture;

@SuppressWarnings("UnstableApiUsage")
public class GoodEndingPlacedFeatureProvider extends FabricDynamicRegistryProvider {

    public GoodEndingPlacedFeatureProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        add(registries, entries, GoodEndingPlacedFeatures.SHALLOW_WATER_MUD_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_ALGAE_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.CYPRESS_TREE_CHECKED);
        add(registries, entries, GoodEndingPlacedFeatures.CYPRESS_TREE_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.SWAMP_TREE_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.MARSH_SWAMP_TREE_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.CATTAIL_PATCH_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.SWAMP_FALLEN_LOG_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.BIRCH_FALLEN_LOG_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.OAK_FALLEN_LOG_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.SPRUCE_FALLEN_LOG_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.ACACIA_FALLEN_LOG_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.SWAMP_VEGETATION_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.DUCKWEED_PATCH_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_PASTEL_WILDFLOWERS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_TWILIGHT_WILDFLOWERS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_SPICY_WILDFLOWERS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_BALMY_WILDFLOWERS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.TALL_BIRCH_TREE_FILTERED);
        add(registries, entries, GoodEndingPlacedFeatures.TALL_BIRCH_VEGETATION_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.MARSHY_SWAMP_VEGETATION);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_PINK_FLOWERED_LILY_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_TALL_GRASS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_FERN_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_LARGE_FERN_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.BIG_LILY_PADS_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PATCH_FLOWERING_WATERLILY_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.DISK_PODZOL_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.OAK_HAMMOCK_TREES_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.GRANITE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.PLAINS_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.DESERT_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.RED_SANDSTONE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.SMOOTH_BASALT_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.STONE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.TUFF_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.COBBLESTONE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.MOSSY_COBBLESTONE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.MOSSIER_COBBLESTONE_BOULDER_PLACED);
        add(registries, entries, GoodEndingPlacedFeatures.OAK_HAMMOCK_BOULDERS);
    }

    private void add(HolderLookup.Provider registries, Entries entries, ResourceKey<PlacedFeature> resourceKey) {
        final HolderLookup.RegistryLookup<PlacedFeature> configuredFeatureRegistryLookup = registries.lookupOrThrow(Registries.PLACED_FEATURE);

        entries.add(resourceKey, configuredFeatureRegistryLookup.getOrThrow(resourceKey).value());
    }

    @Override
    public String getName() {
        return "worldgen/placed_features";
    }
}
