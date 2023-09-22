package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingBiomeModifiers {

    public static void init() {
//        addSwampVegetation();
//        addMangroveSwampVegetation();
//        addOldGrowthBirchVegetation();
//        addFallenLogs();
//        addWildFlowers();
//        addBoulders();
    }

    public static void addOldGrowthBirchVegetation() {
        BiomeModifications.create(new ResourceLocation(GoodEnding.MODID, "remove_small_birch_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST), biomeModificationContext -> {
            biomeModificationContext.getGenerationSettings().removeFeature(VegetationPlacements.BIRCH_TALL);
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.TALL_BIRCH_VEGETATION_PLACED);
        });
        addFeature(GoodEndingPlacedFeatures.PATCH_TALL_GRASS_PLACED, Biomes.OLD_GROWTH_BIRCH_FOREST, GenerationStep.Decoration.VEGETAL_DECORATION);
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.OLD_GROWTH_BIRCH_FOREST), MobCategory.CREATURE, GoodEndingEntityTypes.WOODPECKER, 40, 1, 4);
    }

    public static void addSwampVegetation() {
        addFeature(GoodEndingPlacedFeatures.SHALLOW_WATER_MUD_PLACED, Biomes.SWAMP, GenerationStep.Decoration.UNDERGROUND_ORES);
        addFeature(GoodEndingPlacedFeatures.CATTAIL_PATCH_PLACED, Biomes.SWAMP, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.SWAMP_FALLEN_LOG_PLACED, Biomes.SWAMP, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.CYPRESS_TREE_PLACED, Biomes.SWAMP, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.SWAMP_VEGETATION_PLACED, Biomes.SWAMP, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.DUCKWEED_PATCH_PLACED, Biomes.SWAMP, GenerationStep.Decoration.VEGETAL_DECORATION);
        BiomeModifications.create(new ResourceLocation(GoodEnding.MODID, "replace_swamp_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(Biomes.SWAMP), biomeModificationContext -> {
            biomeModificationContext.getGenerationSettings().removeFeature(VegetationPlacements.TREES_SWAMP);
            biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.SWAMP_TREE_PLACED);
        });
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(Biomes.SWAMP), MobCategory.AMBIENT, GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8);
    }

    public static void addMangroveSwampVegetation() {
        addFeature(GoodEndingPlacedFeatures.PATCH_PINK_FLOWERED_LILY_PLACED, Biomes.MANGROVE_SWAMP, GenerationStep.Decoration.VEGETAL_DECORATION);
    }

    public static void addFallenLogs() {
        addFeature(GoodEndingPlacedFeatures.OAK_FALLEN_LOG_PLACED, GoodEndingTags.OAK_FALLEN_LOG_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.SPRUCE_FALLEN_LOG_PLACED, GoodEndingTags.SPRUCE_FALLEN_LOG_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.ACACIA_FALLEN_LOG_PLACED, GoodEndingTags.ACACIA_FALLEN_LOG_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.BIRCH_FALLEN_LOG_PLACED, GoodEndingTags.BIRCH_FALLEN_LOG_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
    }

    public static void addWildFlowers() {
        addFeature(GoodEndingPlacedFeatures.PATCH_PASTEL_WILDFLOWERS_PLACED, GoodEndingTags.PASTEL_WILDFLOWER_GENERATES, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.PATCH_TWILIGHT_WILDFLOWERS_PLACED, GoodEndingTags.TWILIGHT_WILDFLOWER_GENERATES, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.PATCH_SPICY_WILDFLOWERS_PLACED, GoodEndingTags.SPICY_WILDFLOWER_GENERATES, GenerationStep.Decoration.VEGETAL_DECORATION);
        addFeature(GoodEndingPlacedFeatures.PATCH_BALMY_WILDFLOWERS_PLACED, GoodEndingTags.BALMY_WILDFLOWER_GENERATES, GenerationStep.Decoration.VEGETAL_DECORATION);
    }

    public static void addBoulders() {
        addFeature(GoodEndingPlacedFeatures.GRANITE_BOULDER_PLACED, GoodEndingTags.LIGHT_GRAY_TERRACOTTA_GRANITE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.PLAINS_BOULDER_PLACED, GoodEndingTags.STONE_TUFF_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.DESERT_BOULDER_PLACED, GoodEndingTags.SANDSTONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.RED_SANDSTONE_BOULDER_PLACED, GoodEndingTags.RED_SANDSTONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.SMOOTH_BASALT_BOULDER_PLACED, GoodEndingTags.SMOOTH_BASALT_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.TUFF_BOULDER_PLACED, GoodEndingTags.TUFF_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.STONE_BOULDER_PLACED, GoodEndingTags.STONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.COBBLESTONE_BOULDER_PLACED, GoodEndingTags.COBBLESTONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.MOSSY_COBBLESTONE_BOULDER_PLACED, GoodEndingTags.MOSSY_COBBLESTONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
        addFeature(GoodEndingPlacedFeatures.MOSSIER_COBBLESTONE_BOULDER_PLACED, GoodEndingTags.MOSSIER_COBBLESTONE_BOULDER_GENERATES, GenerationStep.Decoration.LOCAL_MODIFICATIONS);
    }

    public static void addFeature(ResourceKey<PlacedFeature> featureRegistryEntry, ResourceKey<Biome> biomeRegistryKey, GenerationStep.Decoration step) {
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomeRegistryKey), step, featureRegistryEntry);
    }

    public static void addFeature(ResourceKey<PlacedFeature> featureRegistryEntry, TagKey<Biome> biomeRegistryKey, GenerationStep.Decoration step) {
        BiomeModifications.addFeature(BiomeSelectors.tag(biomeRegistryKey), step, featureRegistryEntry);
    }

}
