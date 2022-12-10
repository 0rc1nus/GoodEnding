package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.orcinus.goodending.GoodEnding;

import java.util.Optional;

public class GoodEndingBiomeModifiers {

    public static void init() {
        addSwampVegetation();
        addMangroveSwampVegetation();
        addMarshySwampVegetation();
        addOakHammockVegetation();
        addOldGrowthBirchVegetation();
        addFallenLogs();
        addWildFlowers();
        addBoulders();
    }

    public static void addOldGrowthBirchVegetation() {
        BiomeModifications.create(new Identifier(GoodEnding.MODID, "remove_small_birch_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), biomeModificationContext -> {
            Optional<RegistryKey<PlacedFeature>> key = GoodEndingWorldGen.TALL_BIRCH_VEGETATION_PLACED.getKey();
            if (key.isPresent() && biomeModificationContext.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL.value())) {
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, key.get());
            }
        });
        addFeature(GoodEndingWorldGen.PATCH_TALL_GRASS_PLACED, BiomeKeys.OLD_GROWTH_BIRCH_FOREST, GenerationStep.Feature.VEGETAL_DECORATION);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), SpawnGroup.CREATURE, GoodEndingEntityTypes.WOODPECKER, 40, 1, 4);
    }

    public static void addOakHammockVegetation() {
        addFeature(GoodEndingWorldGen.OAK_HAMMOCK_TREES_PLACED, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_FERN_PLACED, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_LARGE_FERN_PLACED, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.OAK_HAMMOCK_BOULDERS, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), SpawnGroup.CREATURE, EntityType.RABBIT, 10, 2, 3);
    }

    public static void addSwampVegetation() {
        addFeature(GoodEndingWorldGen.SHALLOW_WATER_MUD_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.UNDERGROUND_ORES);
        addFeature(GoodEndingWorldGen.CATTAIL_PATCH_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.SWAMP_FALLEN_LOG_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.CYPRESS_TREE_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.SWAMP_VEGETATION_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.DUCKWEED_PATCH_PLACED, BiomeKeys.SWAMP, GenerationStep.Feature.VEGETAL_DECORATION);
        BiomeModifications.create(new Identifier(GoodEnding.MODID, "replace_swamp_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.SWAMP), biomeModificationContext -> {
            Optional<RegistryKey<PlacedFeature>> key = GoodEndingWorldGen.SWAMP_TREE_PLACED.getKey();
            if (key.isPresent() && biomeModificationContext.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_SWAMP.value())) {
                biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, key.get());
            }
        });
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), SpawnGroup.AMBIENT, GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8);
    }

    public static void addMangroveSwampVegetation() {
        addFeature(GoodEndingWorldGen.PATCH_PINK_FLOWERED_LILY_PLACED, BiomeKeys.MANGROVE_SWAMP, GenerationStep.Feature.VEGETAL_DECORATION);
    }

    public static void addMarshySwampVegetation() {
        addFeature(GoodEndingWorldGen.PATCH_ALGAE_PLACED, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.UNDERGROUND_ORES);
        addFeature(GoodEndingWorldGen.BIG_LILY_PADS_PLACED, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_FLOWERING_WATERLILY_PLACED, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.DISK_PODZOL_PLACED, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.UNDERGROUND_ORES);
        addFeature(GoodEndingWorldGen.MARSH_SWAMP_TREE_PLACED, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.MARSHY_SWAMP_VEGETATION, GoodEndingBiomes.MARSHY_SWAMP_KEY, GenerationStep.Feature.VEGETAL_DECORATION);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(GoodEndingBiomes.MARSHY_SWAMP_KEY), SpawnGroup.AMBIENT, GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(GoodEndingBiomes.MARSHY_SWAMP_KEY), SpawnGroup.CREATURE, EntityType.FROG, 10, 2, 5);
    }

    public static void addFallenLogs() {
        addFeature(GoodEndingWorldGen.OAK_FALLEN_LOG_PLACED, GoodEndingTags.OAK_FALLEN_LOG_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.SPRUCE_FALLEN_LOG_PLACED, GoodEndingTags.SPRUCE_FALLEN_LOG_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.ACACIA_FALLEN_LOG_PLACED, GoodEndingTags.ACACIA_FALLEN_LOG_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.BIRCH_FALLEN_LOG_PLACED, GoodEndingTags.BIRCH_FALLEN_LOG_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
    }

    public static void addWildFlowers() {
        addFeature(GoodEndingWorldGen.PATCH_PASTEL_WILDFLOWERS_PLACED, GoodEndingTags.PASTEL_WILDFLOWER_GENERATES, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_TWILIGHT_WILDFLOWERS_PLACED, GoodEndingTags.TWILIGHT_WILDFLOWER_GENERATES, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_SPICY_WILDFLOWERS_PLACED, GoodEndingTags.SPICY_WILDFLOWER_GENERATES, GenerationStep.Feature.VEGETAL_DECORATION);
        addFeature(GoodEndingWorldGen.PATCH_BALMY_WILDFLOWERS_PLACED, GoodEndingTags.BALMY_WILDFLOWER_GENERATES, GenerationStep.Feature.VEGETAL_DECORATION);
    }

    public static void addBoulders() {
        addFeature(GoodEndingWorldGen.GRANITE_BOULDER_PLACED, GoodEndingTags.LIGHT_GRAY_TERRACOTTA_GRANITE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.PLAINS_BOULDER_PLACED, GoodEndingTags.STONE_TUFF_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.DESERT_BOULDER_PLACED, GoodEndingTags.SANDSTONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.RED_SANDSTONE_BOULDER_PLACED, GoodEndingTags.RED_SANDSTONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.SMOOTH_BASALT_BOULDER_PLACED, GoodEndingTags.SMOOTH_BASALT_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.TUFF_BOULDER_PLACED, GoodEndingTags.TUFF_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.STONE_BOULDER_PLACED, GoodEndingTags.STONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.COBBLESTONE_BOULDER_PLACED, GoodEndingTags.COBBLESTONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.MOSSY_COBBLESTONE_BOULDER_PLACED, GoodEndingTags.MOSSY_COBBLESTONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingWorldGen.MOSSIER_COBBLESTONE_BOULDER_PLACED, GoodEndingTags.MOSSIER_COBBLESTONE_BOULDER_GENERATES, GenerationStep.Feature.LOCAL_MODIFICATIONS);
    }

    public static void addFeature(RegistryEntry<PlacedFeature> featureRegistryEntry, RegistryKey<Biome> biomeRegistryKey, GenerationStep.Feature step) {
        featureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomeRegistryKey), step, placedFeatureRegistryKey));
    }

    public static void addFeature(RegistryEntry<PlacedFeature> featureRegistryEntry, TagKey<Biome> biomeRegistryKey, GenerationStep.Feature step) {
        featureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.tag(biomeRegistryKey), step, placedFeatureRegistryKey));
    }

}
