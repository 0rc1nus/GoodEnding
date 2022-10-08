package net.orcinus.goodending.init;

import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomBooleanFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleRandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
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
import net.minecraft.world.level.material.Fluids;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.FancyDarkOakTreeConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;
import net.orcinus.goodending.world.gen.features.config.MuddyOakFeatureConfig;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

import java.util.List;
import java.util.OptionalInt;

public class GoodEndingWorldGen {

    public static void init() { }

    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.MUD), List.of(new RuleBasedBlockStateProvider.Rule(BlockPredicate.not(BlockPredicate.anyOf(BlockPredicate.solid(Direction.UP.getNormal()), BlockPredicate.matchesFluids(Direction.UP.getNormal(), Fluids.WATER))), BlockStateProvider.simple(Blocks.MUD)))), BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT)), UniformInt.of(3, 5), 2));
    public static final Holder<PlacedFeature> DISK_MUD_PLACED = register("disk_mud", DISK_MUD, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT)), BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<ShallowWaterConfig, ?>> SHALLOW_WATER_MUD = register("shallow_water_mud", GoodEndingFeatures.SHALLOW_WATER.get(), new ShallowWaterConfig(BlockStateProvider.simple(Blocks.MUD), true, false, 6, 0.25F));
    public static final Holder<PlacedFeature> SHALLOW_WATER_MUD_PLACED = register("shallow_water_mud", SHALLOW_WATER_MUD, CountPlacement.of(120), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> PATCH_ALGAE = register("patch_algae", GoodEndingFeatures.ALGAE_PATCH.get(), new NoneFeatureConfiguration());
    public static final Holder<PlacedFeature> PATCH_ALGAE_PLACED = register("patch_algae", PATCH_ALGAE, CountPlacement.of(80), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE = register("cypress_tree", GoodEndingFeatures.CYPRESS_TREE.get(), new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), false).build());
    public static final Holder<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE_PLANTED = register("cypress_tree_planted", GoodEndingFeatures.CYPRESS_TREE.get(), new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.simple(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), true).build());
    public static final Holder<PlacedFeature> CYPRESS_TREE_CHECKED = register("cypress_tree_checked", CYPRESS_TREE, PlacementUtils.filteredByBlockSurvival(GoodEndingBlocks.CYPRESS_SAPLING.get()));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> CYPRESS_TREE_FILTERED = register("cypress_tree_filtered", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(CYPRESS_TREE_CHECKED, 0.8F)), CYPRESS_TREE_CHECKED));
    public static final Holder<PlacedFeature> CYPRESS_TREE_PLACED = register("cypress_tree_placed", CYPRESS_TREE_FILTERED, InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(8), PlacementUtils.countExtra(4, 0.5F, 4), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<MuddyOakFeatureConfig, ?>> MUDDY_OAK_TREE = register("swamp_tree", GoodEndingFeatures.MUDDY_OAK_TREE_FEATURE.get(), new MuddyOakFeatureConfig.MuddyOakFeatureBuilder(BlockStateProvider.simple(GoodEndingBlocks.MUDDY_OAK_LOG.get()), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), true).build());
    public static final Holder<PlacedFeature> SWAMP_TREE_PLACED = register("swamp_tree", MUDDY_OAK_TREE, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesTag(BlockTags.DIRT), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<MuddyOakFeatureConfig, ?>> MARSH_MUDDY_OAK_TREE = register("marsh_swamp_tree", GoodEndingFeatures.MUDDY_OAK_TREE_FEATURE.get(), new MuddyOakFeatureConfig.MuddyOakFeatureBuilder(BlockStateProvider.simple(GoodEndingBlocks.MUDDY_OAK_LOG.get()), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.simple(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), false).build());
    public static final Holder<PlacedFeature> MARSH_SWAMP_TREE_PLACED = register("marsh_swamp_tree", MARSH_MUDDY_OAK_TREE, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesTag(BlockTags.DIRT), BlockPredicate.ONLY_IN_AIR_PREDICATE, 12), BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<HalfWaterloggedDecorationConfig, ?>> CATTAIL_PATCH = register("cattail_patch", GoodEndingFeatures.HALF_WATERLOGGED_DECORATION.get(), new HalfWaterloggedDecorationConfig(BlockStateProvider.simple(GoodEndingBlocks.CATTAIL.get()), UniformInt.of(6, 8)));
    public static final Holder<PlacedFeature> CATTAIL_PATCH_PLACED = register("cattail_patch", CATTAIL_PATCH, CountPlacement.of(25), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>> SWAMP_FALLEN_LOG = register("swamp_fallen_log", GoodEndingFeatures.FALLEN_LOG.get(), new FallenLogConfig(BlockStateProvider.simple(GoodEndingBlocks.MUDDY_OAK_LOG.get()), UniformInt.of(4, 6), false, true));
    public static final Holder<PlacedFeature> SWAMP_FALLEN_LOG_PLACED = register("swamp_fallen_log", SWAMP_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>> BIRCH_FALLEN_LOG = register("birch_fallen_log", GoodEndingFeatures.FALLEN_LOG.get(), new FallenLogConfig(BlockStateProvider.simple(Blocks.BIRCH_LOG), UniformInt.of(6, 8), true, true, false));
    public static final Holder<PlacedFeature> BIRCH_FALLEN_LOG_PLACED = register("birch_fallen_log", BIRCH_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>> OAK_FALLEN_LOG = register("oak_fallen_log", GoodEndingFeatures.FALLEN_LOG.get(), new FallenLogConfig(BlockStateProvider.simple(Blocks.OAK_LOG), UniformInt.of(6, 8), false, true, false));
    public static final Holder<PlacedFeature> OAK_FALLEN_LOG_PLACED = register("oak_fallen_log", OAK_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>> SPRUCE_FALLEN_LOG = register("spruce_fallen_log", GoodEndingFeatures.FALLEN_LOG.get(), new FallenLogConfig(BlockStateProvider.simple(Blocks.SPRUCE_LOG), UniformInt.of(6, 8), false, false, false));
    public static final Holder<PlacedFeature> SPRUCE_FALLEN_LOG_PLACED = register("spruce_fallen_log", SPRUCE_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final Holder<ConfiguredFeature<FallenLogConfig, ?>> ACACIA_FALLEN_LOG = register("acacia_fallen_log", GoodEndingFeatures.FALLEN_LOG.get(), new FallenLogConfig(BlockStateProvider.simple(Blocks.ACACIA_LOG), UniformInt.of(4, 6), false, false, false));
    public static final Holder<PlacedFeature> ACACIA_FALLEN_LOG_PLACED = register("acacia_fallen_log", ACACIA_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final Holder<ConfiguredFeature<RandomBooleanFeatureConfiguration, ?>> SWAMP_VEGETATION = register("swamp_vegetation", Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfiguration(PlacementUtils.inlinePlaced(TreeFeatures.HUGE_BROWN_MUSHROOM), PlacementUtils.inlinePlaced(TreeFeatures.HUGE_RED_MUSHROOM)));
    public static final Holder<PlacedFeature> SWAMP_VEGETATION_PLACED = register("swamp_vegetation", SWAMP_VEGETATION, CountPlacement.of(16), InSquarePlacement.spread(), RarityFilter.onAverageOnceEvery(60), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> DUCKWEED_PATCH = register("duckweed_patch", GoodEndingFeatures.DUCKWEED_PATCH.get(), new NoneFeatureConfiguration());
    public static final Holder<PlacedFeature> DUCKWEED_PATCH_PLACED = register("duckweed_patch", DUCKWEED_PATCH, CountPlacement.of(20), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_PASTEL_WILDFLOWERS = register("patch_pastel_wildflowers", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GoodEndingBlocks.PASTEL_WILDFLOWERS.get())), List.of(Blocks.GRASS_BLOCK)));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TWILIGHT_WILDFLOWERS = register("patch_twilight_wildflowers", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GoodEndingBlocks.TWILIGHT_WILDFLOWERS.get())), List.of(Blocks.GRASS_BLOCK, Blocks.MUD, Blocks.COARSE_DIRT, Blocks.PODZOL)));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_SPICY_WILDFLOWERS = register("patch_spicy_wildflowers", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GoodEndingBlocks.SPICY_WILDFLOWERS.get())), List.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.PODZOL)));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_BALMY_WILDFLOWERS = register("patch_balmy_wildflowers", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GoodEndingBlocks.BALMY_WILDFLOWERS.get())), List.of(Blocks.GRASS_BLOCK)));

    public static final Holder<PlacedFeature> PATCH_PASTEL_WILDFLOWERS_PLACED = register("patch_pastel_wildflowers", PATCH_PASTEL_WILDFLOWERS, RarityFilter.onAverageOnceEvery(30), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> PATCH_TWILIGHT_WILDFLOWERS_PLACED = register("patch_twilight_wildflowers", PATCH_TWILIGHT_WILDFLOWERS, RarityFilter.onAverageOnceEvery(30), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> PATCH_SPICY_WILDFLOWERS_PLACED = register("patch_spicy_wildflowers", PATCH_SPICY_WILDFLOWERS, RarityFilter.onAverageOnceEvery(30), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> PATCH_BALMY_WILDFLOWERS_PLACED = register("patch_balmy_wildflowers", PATCH_BALMY_WILDFLOWERS, RarityFilter.onAverageOnceEvery(30), CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_GRASS = register("patch_tall_grass", Feature.RANDOM_PATCH, new RandomPatchConfiguration(32, 7, 3, PlacementUtils.filtered(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.GRASS.defaultBlockState(), 10).add(GoodEndingBlocks.POLLENFLAKE.get().defaultBlockState(), 7).add(Blocks.AZURE_BLUET.defaultBlockState(), 10).add(Blocks.OXEYE_DAISY.defaultBlockState(), 10))), BlockPredicate.allOf(BlockPredicate.ONLY_IN_AIR_PREDICATE, BlockPredicate.not(BlockPredicate.matchesBlocks(Direction.DOWN.getNormal(), Blocks.PODZOL))))));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_TALL_GRASS_MARSH = register("patch_tall_grass_marsh", Feature.RANDOM_PATCH, new RandomPatchConfiguration(32, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.TALL_GRASS)))));

    public static final Holder<ConfiguredFeature<TreeConfiguration, ?>> TALL_BIRCH_TREE = register("tall_birch_tree", GoodEndingFeatures.TALL_BIRCH_TREE.get(), new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.BIRCH_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.simple(Blocks.BIRCH_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(0, 0, 0)).build());
    public static final Holder<PlacedFeature> TALL_BIRCH_TREE_FILTERED = register("tall_birch_tree_filtered", TALL_BIRCH_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.BIRCH_SAPLING));
    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> TALL_BIRCH_VEGETATION = register("tall_birch_vegetation", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TALL_BIRCH_TREE_FILTERED, 0.5F)), PlacementUtils.inlinePlaced(PATCH_TALL_GRASS)));
    public static final Holder<PlacedFeature> TALL_BIRCH_VEGETATION_PLACED = register("tall_birch_vegetation", TALL_BIRCH_VEGETATION, CountPlacement.of(16), InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    public static final Holder<PlacedFeature> MARSHY_SWAMP_VEGETATION = register("marshy_swamp_vegetation", PATCH_TALL_GRASS_MARSH, CountPlacement.of(16), InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_PINK_FLOWERED_LILY = register("patch_pink_flowered_lily", Feature.RANDOM_PATCH, new RandomPatchConfiguration(6, 2, 3, PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get())))));
    public static final Holder<PlacedFeature> PATCH_PINK_FLOWERED_LILY_PLACED = register("patch_pink_flowered_lily_placed", PATCH_PINK_FLOWERED_LILY, VegetationPlacements.worldSurfaceSquaredWithCount(2));

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_FERN = register("patch_fern", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_LARGE_FERN = register("patch_large_fern", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LARGE_FERN)), List.of(), 24));
    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> OAK_HAMMOCK_PATCH_TALL_GRASS = register("oak_hammock_patch_tall_grass", Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.TALL_GRASS)), List.of(), 24));
    public static final Holder<PlacedFeature> PATCH_TALL_GRASS_PLACED = register("patch_tall_grass_placed", VegetationFeatures.PATCH_TALL_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(3));
    public static final Holder<PlacedFeature> PATCH_FERN_PLACED = register("patch_fern_placed", PATCH_FERN, VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final Holder<PlacedFeature> PATCH_LARGE_FERN_PLACED = register("patch_large_fern_placed", PATCH_LARGE_FERN, VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final Holder<PlacedFeature> OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED = register("oak_hammock_patch_tall_grass_placed", OAK_HAMMOCK_PATCH_TALL_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(3));

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> LARGE_LILY_PADS = register("large_lily_pads", GoodEndingFeatures.LARGE_LILY_PADS.get(), new NoneFeatureConfiguration());

    public static final Holder<PlacedFeature> BIG_LILY_PADS_PLACED = register("big_lily_pads", LARGE_LILY_PADS, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> PATCH_FLOWERING_WATERLILY = register("patch_flowering_waterlily", Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.inlinePlaced(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get().defaultBlockState(), 1).add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get().defaultBlockState(), 1).add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get().defaultBlockState(), 1)))))))));
    public static final Holder<PlacedFeature> PATCH_FLOWERING_WATERLILY_PLACED = register("patch_flowering_waterlily", PATCH_FLOWERING_WATERLILY, VegetationPlacements.worldSurfaceSquaredWithCount(4));

    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_PODZOL = register("disk_podzol", Feature.DISK, new DiskConfiguration(new RuleBasedBlockStateProvider(BlockStateProvider.simple(Blocks.PODZOL), List.of(new RuleBasedBlockStateProvider.Rule(BlockPredicate.not(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(Blocks.COARSE_DIRT), BlockPredicate.solid(Direction.UP.getNormal()), BlockPredicate.matchesFluids(Direction.UP.getNormal(), Fluids.WATER))), BlockStateProvider.simple(Blocks.PODZOL)))), BlockPredicate.matchesBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT)), UniformInt.of(2, 6), 0));
    public static final Holder<PlacedFeature> DISK_PODZOL_PLACED = register("disk_podzol", DISK_PODZOL, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, RandomOffsetPlacement.vertical(ConstantInt.of(-1)), BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(Blocks.COARSE_DIRT)), BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<FancyDarkOakTreeConfig, ?>> FANCY_DARK_OAK = register("fancy_dark_oak", GoodEndingFeatures.FANCY_DARK_OAK.get(), new FancyDarkOakTreeConfig.FancyDarkOakTreeConfigBuilder(false, false).build());
    public static final Holder<ConfiguredFeature<FancyDarkOakTreeConfig, ?>> FANCY_DARK_OAK_PLANTED = register("fancy_dark_oak_planted", GoodEndingFeatures.FANCY_DARK_OAK.get(), new FancyDarkOakTreeConfig.FancyDarkOakTreeConfigBuilder(false, true).build());

    public static final Holder<ConfiguredFeature<FancyDarkOakTreeConfig, ?>> BIG_FANCY_DARK_OAK = register("big_fancy_dark_oak", GoodEndingFeatures.FANCY_DARK_OAK.get(), new FancyDarkOakTreeConfig.FancyDarkOakTreeConfigBuilder(true, false).build());
    public static final Holder<ConfiguredFeature<FancyDarkOakTreeConfig, ?>> BIG_FANCY_DARK_OAK_PLANTED = register("big_fancy_dark_oak_planted", GoodEndingFeatures.FANCY_DARK_OAK.get(), new FancyDarkOakTreeConfig.FancyDarkOakTreeConfigBuilder(true, true).build());

    public static final Holder<ConfiguredFeature<RandomFeatureConfiguration, ?>> OAK_HAMMOCK_TREES = register("oak_hammock_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(TreePlacements.FANCY_OAK_CHECKED, 0.2F), new WeightedPlacedFeature(PlacementUtils.inlinePlaced(FANCY_DARK_OAK), 0.8F), new WeightedPlacedFeature(PlacementUtils.inlinePlaced(BIG_FANCY_DARK_OAK), 0.78F)), PlacementUtils.inlinePlaced(FANCY_DARK_OAK)));
    public static final Holder<PlacedFeature> OAK_HAMMOCK_TREES_PLACED = register("oak_hammock_trees_placed", OAK_HAMMOCK_TREES, CountPlacement.of(8), InSquarePlacement.spread(), VegetationPlacements.TREE_THRESHOLD, PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome());

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> GRANITE_BOULDER = register("granite_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.LIGHT_GRAY_TERRACOTTA), BlockStateProvider.simple(Blocks.GRANITE), UniformFloat.of(2, 4.2F)));
    public static final Holder<PlacedFeature> GRANITE_BOULDER_PLACED = register("granite_boulder", GRANITE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> PLAINS_BOULDER = register("plains_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.STONE), BlockStateProvider.simple(Blocks.TUFF), UniformFloat.of(2, 3.5F)));
    public static final Holder<PlacedFeature> PLAINS_BOULDER_PLACED = register("plains_boulder", PLAINS_BOULDER, GoodEndingWorldGen.boulderModifier(25, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> DESERT_BOULDER = register("desert_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.SANDSTONE), BlockStateProvider.simple(Blocks.SANDSTONE), UniformFloat.of(2, 3.5F)));
    public static final Holder<PlacedFeature> DESERT_BOULDER_PLACED = register("desert_boulder", DESERT_BOULDER, GoodEndingWorldGen.boulderModifier(20, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> RED_SANDSTONE_BOULDER = register("red_sandstone_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.RED_SANDSTONE), BlockStateProvider.simple(Blocks.RED_SANDSTONE), UniformFloat.of(2, 3.5F)));
    public static final Holder<PlacedFeature> RED_SANDSTONE_BOULDER_PLACED = register("red_sandstone_boulder", RED_SANDSTONE_BOULDER, GoodEndingWorldGen.boulderModifier(25, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> SMOOTH_BASALT_BOULDER = register("smooth_basalt_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.SMOOTH_BASALT), BlockStateProvider.simple(Blocks.SMOOTH_BASALT), UniformFloat.of(2.3F, 5.4F)));
    public static final Holder<PlacedFeature> SMOOTH_BASALT_BOULDER_PLACED = register("smooth_basalt_boulder", SMOOTH_BASALT_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> STONE_BOULDER = register("stone_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.STONE), BlockStateProvider.simple(Blocks.STONE), UniformFloat.of(2.1F, 4.8F)));
    public static final Holder<PlacedFeature> STONE_BOULDER_PLACED = register("stone_boulder", STONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> TUFF_BOULDER = register("tuff_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.TUFF), BlockStateProvider.simple(Blocks.TUFF), UniformFloat.of(2.2F, 5.2F)));
    public static final Holder<PlacedFeature> TUFF_BOULDER_PLACED = register("tuff_boulder", TUFF_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_TOP_SOLID));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> COBBLESTONE_BOULDER = register("cobblestone_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.COBBLESTONE), BlockStateProvider.simple(Blocks.COBBLESTONE), UniformFloat.of(2F, 3F)));
    public static final Holder<PlacedFeature> COBBLESTONE_BOULDER_PLACED = register("cobblestone_boulder", COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> MOSSY_COBBLESTONE_BOULDER = register("mossy_cobblestone_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(true, BlockStateProvider.simple(Blocks.MOSSY_COBBLESTONE), BlockStateProvider.simple(Blocks.COBBLESTONE), UniformFloat.of(2F, 3F)));
    public static final Holder<PlacedFeature> MOSSY_COBBLESTONE_BOULDER_PLACED = register("mossy_cobblestone_boulder", MOSSY_COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static final Holder<ConfiguredFeature<BoulderConfig, ?>> MOSSIER_COBBLESTONE_BOULDER = register("mossier_cobblestone_boulder", GoodEndingFeatures.BOULDER.get(), new BoulderConfig(false, BlockStateProvider.simple(Blocks.MOSS_BLOCK), BlockStateProvider.simple(Blocks.MOSSY_COBBLESTONE), UniformFloat.of(2, 4.2F)));
    public static final Holder<PlacedFeature> MOSSIER_COBBLESTONE_BOULDER_PLACED = register("mossier_cobblestone_boulder", MOSSIER_COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacementUtils.HEIGHTMAP_WORLD_SURFACE));

    public static List<PlacementModifier> boulderModifier(int chance, PlacementModifier heightmap) {
        return List.of(RarityFilter.onAverageOnceEvery(chance), CountPlacement.of(2), InSquarePlacement.spread(), heightmap, BiomeFilter.biome());
    }

    public static List<PlacementModifier> fallenLogModifier() {
        return List.of(RarityFilter.onAverageOnceEvery(20), CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    }

    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(GoodEnding.MODID, id).toString(), new ConfiguredFeature<>(feature, config));
    }

    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static Holder<PlacedFeature> register(String id, Holder<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(GoodEnding.MODID, id).toString(), new PlacedFeature(Holder.hackyErase(registryEntry), List.copyOf(modifiers)));
    }

}
