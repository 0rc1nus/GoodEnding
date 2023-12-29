package net.orcinus.goodending.init;

import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.EnvironmentScanPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.RandomOffsetPlacement;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;
import net.orcinus.goodending.GoodEnding;

import java.util.List;

public class GoodEndingPlacedFeatures {

    public static final ResourceKey<PlacedFeature> SHALLOW_WATER_MUD_PLACED = createKey("shallow_water_mud");
    public static final ResourceKey<PlacedFeature> PATCH_ALGAE_PLACED = createKey("patch_algae");
    public static final ResourceKey<PlacedFeature> CYPRESS_TREE_CHECKED = createKey("cypress_tree_checked");
    public static final ResourceKey<PlacedFeature> CYPRESS_TREE_PLACED = createKey("cypress_tree_placed");
    public static final ResourceKey<PlacedFeature> SWAMP_TREE_PLACED = createKey("swamp_tree");
    public static final ResourceKey<PlacedFeature> MARSH_SWAMP_TREE_PLACED = createKey("marsh_swamp_tree");
    public static final ResourceKey<PlacedFeature> CATTAIL_PATCH_PLACED = createKey("cattail_patch");
    public static final ResourceKey<PlacedFeature> SWAMP_FALLEN_LOG_PLACED = createKey("swamp_fallen_log");
    public static final ResourceKey<PlacedFeature> BIRCH_FALLEN_LOG_PLACED = createKey("birch_fallen_log");
    public static final ResourceKey<PlacedFeature> OAK_FALLEN_LOG_PLACED = createKey("oak_fallen_log");
    public static final ResourceKey<PlacedFeature> SPRUCE_FALLEN_LOG_PLACED = createKey("spruce_fallen_log");
    public static final ResourceKey<PlacedFeature> ACACIA_FALLEN_LOG_PLACED = createKey("acacia_fallen_log");
    public static final ResourceKey<PlacedFeature> SWAMP_VEGETATION_PLACED = createKey("swamp_vegetation");
    public static final ResourceKey<PlacedFeature> DUCKWEED_PATCH_PLACED = createKey("duckweed_patch");
    public static final ResourceKey<PlacedFeature> PATCH_PASTEL_WILDFLOWERS_PLACED = createKey("patch_pastel_wildflowers");
    public static final ResourceKey<PlacedFeature> PATCH_TWILIGHT_WILDFLOWERS_PLACED = createKey("patch_twilight_wildflowers");
    public static final ResourceKey<PlacedFeature> PATCH_SPICY_WILDFLOWERS_PLACED = createKey("patch_spicy_wildflowers");
    public static final ResourceKey<PlacedFeature> PATCH_BALMY_WILDFLOWERS_PLACED = createKey("patch_balmy_wildflowers");
    public static final ResourceKey<PlacedFeature> TALL_BIRCH_TREE_FILTERED = createKey("tall_birch_tree_filtered");
    public static final ResourceKey<PlacedFeature> TALL_BIRCH_VEGETATION_PLACED = createKey("tall_birch_vegetation");
    public static final ResourceKey<PlacedFeature> MARSHY_SWAMP_VEGETATION = createKey("marshy_swamp_vegetation");
    public static final ResourceKey<PlacedFeature> PATCH_PINK_FLOWERED_LILY_PLACED = createKey("patch_pink_flowered_lily_placed");
    public static final ResourceKey<PlacedFeature> PATCH_TALL_GRASS_PLACED = createKey("patch_tall_grass_placed");
    public static final ResourceKey<PlacedFeature> PATCH_FERN_PLACED = createKey("patch_fern_placed");
    public static final ResourceKey<PlacedFeature> PATCH_LARGE_FERN_PLACED = createKey("patch_large_fern_placed");
    public static final ResourceKey<PlacedFeature> OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED = createKey("oak_hammock_patch_tall_grass_placed");
    public static final ResourceKey<PlacedFeature> BIG_LILY_PADS_PLACED = createKey("big_lily_pads_placed");
    public static final ResourceKey<PlacedFeature> PATCH_FLOWERING_WATERLILY_PLACED = createKey("patch_flowering_waterlily");
    public static final ResourceKey<PlacedFeature> DISK_PODZOL_PLACED = createKey("disk_podzol");
    public static final ResourceKey<PlacedFeature> OAK_HAMMOCK_TREES_PLACED = createKey("oak_hammock_trees_placed");
    public static final ResourceKey<PlacedFeature> GRANITE_BOULDER_PLACED = createKey("granite_boulder_placed");
    public static final ResourceKey<PlacedFeature> PLAINS_BOULDER_PLACED = createKey("plains_boulder_placed");
    public static final ResourceKey<PlacedFeature> DESERT_BOULDER_PLACED = createKey("desert_boulder_placed");
    public static final ResourceKey<PlacedFeature> RED_SANDSTONE_BOULDER_PLACED = createKey("red_sandstone_boulder_placed");
    public static final ResourceKey<PlacedFeature> SMOOTH_BASALT_BOULDER_PLACED = createKey("smooth_basalt_boulder_placed");
    public static final ResourceKey<PlacedFeature> STONE_BOULDER_PLACED = createKey("stone_boulder_placed");
    public static final ResourceKey<PlacedFeature> TUFF_BOULDER_PLACED = createKey("tuff_boulder_placed");
    public static final ResourceKey<PlacedFeature> COBBLESTONE_BOULDER_PLACED = createKey("cobblestone_boulder_placed");
    public static final ResourceKey<PlacedFeature> MOSSY_COBBLESTONE_BOULDER_PLACED = createKey("mossy_cobblestone_boulder_placed");
    public static final ResourceKey<PlacedFeature> MOSSIER_COBBLESTONE_BOULDER_PLACED = createKey("mossier_cobblestone_boulder_placed");
    public static final ResourceKey<PlacedFeature> OAK_HAMMOCK_BOULDERS = createKey("oak_hammock_boulders");

    public static void bootstrap(BootstapContext<PlacedFeature> ctx) {
        HolderGetter<ConfiguredFeature<?, ?>> holderGetter = ctx.lookup(Registries.CONFIGURED_FEATURE);
        PlacementUtils.register(ctx, SHALLOW_WATER_MUD_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.SHALLOW_WATER_MUD), CountPlacement.of(120), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(ctx, PATCH_ALGAE_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_ALGAE), CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
        PlacementUtils.register(ctx, CYPRESS_TREE_CHECKED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.CYPRESS_TREE), PlacementUtils.filteredByBlockSurvival(GoodEndingBlocks.CYPRESS_SAPLING));
        PlacementUtils.register(ctx, CYPRESS_TREE_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.CYPRESS_TREE_FILTERED), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(8), PlacementUtils.countExtra(4, 0.5F, 4), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, SWAMP_TREE_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.MUDDY_OAK_TREE), CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesTag(BlockTags.DIRT), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), BiomeFilter.biome());
        PlacementUtils.register(ctx, MARSH_SWAMP_TREE_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.MARSH_MUDDY_OAK_TREE), CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesTag(BlockTags.DIRT), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), BiomeFilter.biome());
        PlacementUtils.register(ctx, CATTAIL_PATCH_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.CATTAIL_PATCH), CountPlacement.of(16), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(60), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, SWAMP_FALLEN_LOG_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.SWAMP_FALLEN_LOG), GoodEndingPlacedFeatures.fallenLogModifier());
        PlacementUtils.register(ctx, BIRCH_FALLEN_LOG_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.BIRCH_FALLEN_LOG), GoodEndingPlacedFeatures.fallenLogModifier());
        PlacementUtils.register(ctx, OAK_FALLEN_LOG_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.OAK_FALLEN_LOG), GoodEndingPlacedFeatures.fallenLogModifier());
        PlacementUtils.register(ctx, SPRUCE_FALLEN_LOG_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.SPRUCE_FALLEN_LOG), GoodEndingPlacedFeatures.fallenLogModifier());
        PlacementUtils.register(ctx, ACACIA_FALLEN_LOG_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.ACACIA_FALLEN_LOG), GoodEndingPlacedFeatures.fallenLogModifier());
        PlacementUtils.register(ctx, SWAMP_VEGETATION_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.SWAMP_VEGETATION), CountPlacement.of(16), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(60), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, DUCKWEED_PATCH_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.DUCKWEED_PATCH), CountPlacement.of(16), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, PATCH_PASTEL_WILDFLOWERS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_PASTEL_WILDFLOWERS), GoodEndingPlacedFeatures.wildFlowersModifier());
        PlacementUtils.register(ctx, PATCH_TWILIGHT_WILDFLOWERS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_TWILIGHT_WILDFLOWERS), GoodEndingPlacedFeatures.wildFlowersModifier());
        PlacementUtils.register(ctx, PATCH_SPICY_WILDFLOWERS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_SPICY_WILDFLOWERS), GoodEndingPlacedFeatures.wildFlowersModifier());
        PlacementUtils.register(ctx, PATCH_BALMY_WILDFLOWERS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_BALMY_WILDFLOWERS), GoodEndingPlacedFeatures.wildFlowersModifier());
        PlacementUtils.register(ctx, TALL_BIRCH_TREE_FILTERED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.TALL_BIRCH_TREE),  PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
        PlacementUtils.register(ctx, TALL_BIRCH_VEGETATION_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.TALL_BIRCH_VEGETATION), CountPlacement.of(16), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, MARSHY_SWAMP_VEGETATION, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_TALL_GRASS_MARSH), CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(ctx, PATCH_PINK_FLOWERED_LILY_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_PINK_FLOWERED_LILY), VegetationPlacements.worldSurfaceSquaredWithCount(2));
        PlacementUtils.register(ctx, PATCH_TALL_GRASS_PLACED, holderGetter.getOrThrow(VegetationFeatures.PATCH_TALL_GRASS), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
        PlacementUtils.register(ctx, PATCH_FERN_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_FERN), VegetationPlacements.worldSurfaceSquaredWithCount(1));
        PlacementUtils.register(ctx, PATCH_LARGE_FERN_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_LARGE_FERN), VegetationPlacements.worldSurfaceSquaredWithCount(1));
        PlacementUtils.register(ctx, OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.OAK_HAMMOCK_PATCH_TALL_GRASS), VegetationPlacements.worldSurfaceSquaredWithCount(3));
        PlacementUtils.register(ctx, BIG_LILY_PADS_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.LARGE_LILY_PADS), VegetationPlacements.worldSurfaceSquaredWithCount(4));
        PlacementUtils.register(ctx, PATCH_FLOWERING_WATERLILY_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PATCH_FLOWERING_WATERLILY), VegetationPlacements.worldSurfaceSquaredWithCount(4));
        PlacementUtils.register(ctx, DISK_PODZOL_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.DISK_PODZOL), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.COARSE_DIRT)), BiomeFilter.biome());
        PlacementUtils.register(ctx, OAK_HAMMOCK_TREES_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.OAK_HAMMOCK_TREES), CountPlacement.of(8), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());
        PlacementUtils.register(ctx, GRANITE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.GRANITE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));
        PlacementUtils.register(ctx, PLAINS_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.PLAINS_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, DESERT_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.DESERT_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, RED_SANDSTONE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.RED_SANDSTONE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, SMOOTH_BASALT_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.SMOOTH_BASALT_BOULDER), boulderModifier(8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, STONE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.STONE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));
        PlacementUtils.register(ctx, TUFF_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.TUFF_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));
        PlacementUtils.register(ctx, COBBLESTONE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.COBBLESTONE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, MOSSY_COBBLESTONE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.MOSSY_COBBLESTONE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, MOSSIER_COBBLESTONE_BOULDER_PLACED, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.MOSSIER_COBBLESTONE_BOULDER), boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
        PlacementUtils.register(ctx, OAK_HAMMOCK_BOULDERS, holderGetter.getOrThrow(GoodEndingConfiguredFeatures.MOSSY_COBBLESTONE_BOULDER), boulderModifier(8, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));
    }

    public static List<PlacementModifier> wildFlowersModifier() {
        return List.of(RarityFilter.onAverageOnceEvery(30), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    }

    public static List<PlacementModifier> fallenLogModifier() {
        return List.of(RarityFilter.onAverageOnceEvery(20), CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static List<PlacementModifier> boulderModifier(int chance, PlacementModifier heightmap) {
        return List.of(RarityFilter.onAverageOnceEvery(chance), CountPlacement.of(2), InSquarePlacement.spread(), heightmap, BiomeFilter.biome());
    }

    public static ResourceKey<PlacedFeature> createKey(String string) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(GoodEnding.MODID, string));
    }


}
