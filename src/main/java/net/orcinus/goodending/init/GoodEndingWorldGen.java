package net.orcinus.goodending.init;

import java.util.List;
import java.util.OptionalInt;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryEntryList;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SimpleBlockFeatureConfig;
import net.minecraft.world.gen.feature.SimpleRandomFeatureConfig;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.TreePlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.DarkOakFoliagePlacer;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.BlockFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.EnvironmentScanPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.RandomOffsetPlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SurfaceWaterDepthFilterPlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.FancyDarkOakFeatureConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;
import net.orcinus.goodending.world.gen.features.config.MuddyOakFeatureConfig;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

public class GoodEndingWorldGen {

    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskFeatureConfig(new PredicatedStateProvider(BlockStateProvider.of(Blocks.MUD), List.of(new PredicatedStateProvider.Rule(BlockPredicate.not(BlockPredicate.eitherOf(BlockPredicate.solid(Direction.UP.getVector()), BlockPredicate.matchingFluids(Direction.UP.getVector(), Fluids.WATER))), BlockStateProvider.of(Blocks.MUD)))), BlockPredicate.matchingBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT)), UniformIntProvider.create(3, 5), 2));
    public static final RegistryEntry<PlacedFeature> DISK_MUD_PLACED = register("disk_mud", DISK_MUD, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT)), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<ShallowWaterConfig, ?>> SHALLOW_WATER_MUD = register("shallow_water_mud", GoodEndingFeatures.SHALLOW_WATER, new ShallowWaterConfig(BlockStateProvider.of(Blocks.MUD), true, false, 6, 0.25F));
    public static final RegistryEntry<PlacedFeature> SHALLOW_WATER_MUD_PLACED = register("shallow_water_mud", SHALLOW_WATER_MUD, CountPlacementModifier.of(120), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> PATCH_ALGAE = register("patch_algae", GoodEndingFeatures.ALGAE_PATCH, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> PATCH_ALGAE_PLACED = register("patch_algae", PATCH_ALGAE, CountPlacementModifier.of(80), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE = register("cypress_tree", GoodEndingFeatures.CYPRESS_TREE, new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.of(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), false).build());
    public static final RegistryEntry<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE_PLANTED = register("cypress_tree_planted", GoodEndingFeatures.CYPRESS_TREE, new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.of(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), true).build());
    public static final RegistryEntry<PlacedFeature> CYPRESS_TREE_CHECKED = register("cypress_tree_checked", CYPRESS_TREE, PlacedFeatures.wouldSurvive(GoodEndingBlocks.CYPRESS_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> CYPRESS_TREE_FILTERED = register("cypress_tree_filtered", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(CYPRESS_TREE_CHECKED, 0.8F)), CYPRESS_TREE_CHECKED));
    public static final RegistryEntry<PlacedFeature> CYPRESS_TREE_PLACED = register("cypress_tree_placed", CYPRESS_TREE_FILTERED, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(8), PlacedFeatures.createCountExtraModifier(4, 0.5F, 4), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<MuddyOakFeatureConfig, ?>> MUDDY_OAK_TREE = register("swamp_tree", GoodEndingFeatures.MUDDY_OAK_TREE_FEATURE, new MuddyOakFeatureConfig.MuddyOakFeatureBuilder(BlockStateProvider.of(GoodEndingBlocks.MUDDY_OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), true).build());
    public static final RegistryEntry<PlacedFeature> SWAMP_TREE_PLACED = register("swamp_tree", MUDDY_OAK_TREE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<MuddyOakFeatureConfig, ?>> MARSH_MUDDY_OAK_TREE = register("marsh_swamp_tree", GoodEndingFeatures.MUDDY_OAK_TREE_FEATURE, new MuddyOakFeatureConfig.MuddyOakFeatureBuilder(BlockStateProvider.of(GoodEndingBlocks.MUDDY_OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), false).build());
    public static final RegistryEntry<PlacedFeature> MARSH_SWAMP_TREE_PLACED = register("marsh_swamp_tree", MARSH_MUDDY_OAK_TREE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<HalfWaterloggedDecorationConfig, ?>> CATTAIL_PATCH = register("cattail_patch", GoodEndingFeatures.HALF_WATERLOGGED_DECORATION, new HalfWaterloggedDecorationConfig(BlockStateProvider.of(GoodEndingBlocks.CATTAIL), UniformIntProvider.create(6, 8)));
    public static final RegistryEntry<PlacedFeature> CATTAIL_PATCH_PLACED = register("cattail_patch", CATTAIL_PATCH, CountPlacementModifier.of(25), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> SWAMP_FALLEN_LOG = register("swamp_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(GoodEndingBlocks.MUDDY_OAK_LOG), UniformIntProvider.create(4, 6), false, true));
    public static final RegistryEntry<PlacedFeature> SWAMP_FALLEN_LOG_PLACED = register("swamp_fallen_log", SWAMP_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> BIRCH_FALLEN_LOG = register("birch_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(Blocks.BIRCH_LOG), UniformIntProvider.create(6, 8), true, true, false));
    public static final RegistryEntry<PlacedFeature> BIRCH_FALLEN_LOG_PLACED = register("birch_fallen_log", BIRCH_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> OAK_FALLEN_LOG = register("oak_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(Blocks.OAK_LOG), UniformIntProvider.create(6, 8), false, true, false));
    public static final RegistryEntry<PlacedFeature> OAK_FALLEN_LOG_PLACED = register("oak_fallen_log", OAK_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> SPRUCE_FALLEN_LOG = register("spruce_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(Blocks.SPRUCE_LOG), UniformIntProvider.create(6, 8), false, false, false));
    public static final RegistryEntry<PlacedFeature> SPRUCE_FALLEN_LOG_PLACED = register("spruce_fallen_log", SPRUCE_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> ACACIA_FALLEN_LOG = register("acacia_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(Blocks.ACACIA_LOG), UniformIntProvider.create(4, 6), false, false, false));
    public static final RegistryEntry<PlacedFeature> ACACIA_FALLEN_LOG_PLACED = register("acacia_fallen_log", ACACIA_FALLEN_LOG, GoodEndingWorldGen.fallenLogModifier());

    public static final RegistryEntry<ConfiguredFeature<RandomBooleanFeatureConfig, ?>> SWAMP_VEGETATION = register("swamp_vegetation", Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_RED_MUSHROOM)));
    public static final RegistryEntry<PlacedFeature> SWAMP_VEGETATION_PLACED = register("swamp_vegetation", SWAMP_VEGETATION, CountPlacementModifier.of(16), SquarePlacementModifier.of(), RarityFilterPlacementModifier.of(60), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> DUCKWEED_PATCH = register("duckweed_patch", GoodEndingFeatures.DUCKWEED_PATCH, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> DUCKWEED_PATCH_PLACED = register("duckweed_patch", DUCKWEED_PATCH, CountPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PASTEL_WILDFLOWERS = register("patch_pastel_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.PASTEL_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TWILIGHT_WILDFLOWERS = register("patch_twilight_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.TWILIGHT_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK, Blocks.MUD, Blocks.COARSE_DIRT, Blocks.PODZOL)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_SPICY_WILDFLOWERS = register("patch_spicy_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.SPICY_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, Blocks.PODZOL)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_BALMY_WILDFLOWERS = register("patch_balmy_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.BALMY_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK)));

    public static final RegistryEntry<PlacedFeature> PATCH_PASTEL_WILDFLOWERS_PLACED = register("patch_pastel_wildflowers", PATCH_PASTEL_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_TWILIGHT_WILDFLOWERS_PLACED = register("patch_twilight_wildflowers", PATCH_TWILIGHT_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_SPICY_WILDFLOWERS_PLACED = register("patch_spicy_wildflowers", PATCH_SPICY_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_BALMY_WILDFLOWERS_PLACED = register("patch_balmy_wildflowers", PATCH_BALMY_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TALL_GRASS = register("patch_tall_grass", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(32, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.GRASS.getDefaultState(), 10).add(GoodEndingBlocks.POLLENFLAKE.getDefaultState(), 7).add(Blocks.AZURE_BLUET.getDefaultState(), 10).add(Blocks.OXEYE_DAISY.getDefaultState(), 10))), BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.PODZOL))))));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TALL_GRASS_MARSH = register("patch_tall_grass_marsh", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(32, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.TALL_GRASS)))));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TALL_BIRCH_TREE = register("tall_birch_tree", GoodEndingFeatures.TALL_BIRCH_TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.BIRCH_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.of(Blocks.BIRCH_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(0,0,0)).build());
    public static final RegistryEntry<PlacedFeature> TALL_BIRCH_TREE_FILTERED = register("tall_birch_tree_filtered", TALL_BIRCH_TREE, PlacedFeatures.wouldSurvive(Blocks.BIRCH_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> TALL_BIRCH_VEGETATION = register("tall_birch_vegetation", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(TALL_BIRCH_TREE_FILTERED, 0.5F)), PlacedFeatures.createEntry(PATCH_TALL_GRASS)));
    public static final RegistryEntry<PlacedFeature> TALL_BIRCH_VEGETATION_PLACED = register("tall_birch_vegetation", TALL_BIRCH_VEGETATION, CountPlacementModifier.of(16), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<PlacedFeature> MARSHY_SWAMP_VEGETATION = register("marshy_swamp_vegetation", PATCH_TALL_GRASS_MARSH, CountPlacementModifier.of(16), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PINK_FLOWERED_LILY = register("patch_pink_flowered_lily", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(6, 2, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD)))));
    public static final RegistryEntry<PlacedFeature> PATCH_PINK_FLOWERED_LILY_PLACED = register("patch_pink_flowered_lily_placed", PATCH_PINK_FLOWERED_LILY, VegetationPlacedFeatures.modifiers(2));

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_FERN = register("patch_fern", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.FERN))));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_LARGE_FERN = register("patch_large_fern", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.LARGE_FERN)), List.of(), 24));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> OAK_HAMMOCK_PATCH_TALL_GRASS = register("oak_hammock_patch_tall_grass", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(Blocks.TALL_GRASS)), List.of(), 24));
    public static final RegistryEntry<PlacedFeature> PATCH_TALL_GRASS_PLACED = register("patch_tall_grass_placed", VegetationConfiguredFeatures.PATCH_TALL_GRASS, VegetationPlacedFeatures.modifiers(3));
    public static final RegistryEntry<PlacedFeature> PATCH_FERN_PLACED = register("patch_fern_placed", PATCH_FERN, VegetationPlacedFeatures.modifiers(2));
    public static final RegistryEntry<PlacedFeature> PATCH_LARGE_FERN_PLACED = register("patch_large_fern_placed", PATCH_LARGE_FERN, VegetationPlacedFeatures.modifiers(3));
    public static final RegistryEntry<PlacedFeature> OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED = register("oak_hammock_patch_tall_grass_placed", OAK_HAMMOCK_PATCH_TALL_GRASS, VegetationPlacedFeatures.modifiers(3));

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> LARGE_LILY_PADS = register("large_lily_pads", GoodEndingFeatures.LARGE_LILY_PADS, new DefaultFeatureConfig());

    public static final RegistryEntry<PlacedFeature> BIG_LILY_PADS_PLACED = register("big_lily_pads_placed", LARGE_LILY_PADS, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_FLOWERING_WATERLILY = register("patch_flowering_waterlily", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(10, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfig(RegistryEntryList.of(PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.getDefaultState(), 1).add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.getDefaultState(), 1).add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.getDefaultState(), 1))), new PlacementModifier[0]))))));
    public static final RegistryEntry<PlacedFeature> PATCH_FLOWERING_WATERLILY_PLACED = register("patch_flowering_waterlily", PATCH_FLOWERING_WATERLILY, VegetationPlacedFeatures.modifiers(4));

    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_PODZOL = register("disk_podzol", Feature.DISK, new DiskFeatureConfig(new PredicatedStateProvider(BlockStateProvider.of(Blocks.PODZOL), List.of(new PredicatedStateProvider.Rule(BlockPredicate.not(BlockPredicate.anyOf(BlockPredicate.matchingBlocks(Blocks.COARSE_DIRT), BlockPredicate.solid(Direction.UP.getVector()), BlockPredicate.matchingFluids(Direction.UP.getVector(), Fluids.WATER))), BlockStateProvider.of(Blocks.PODZOL)))), BlockPredicate.matchingBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT, Blocks.COARSE_DIRT)), UniformIntProvider.create(2, 6), 0));
    public static final RegistryEntry<PlacedFeature> DISK_PODZOL_PLACED = register("disk_podzol", DISK_PODZOL, CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.COARSE_DIRT)), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<FancyDarkOakFeatureConfig, ?>> FANCY_DARK_OAK = register("fancy_dark_oak", GoodEndingFeatures.FANCY_DARK_OAK, new FancyDarkOakFeatureConfig(false));
    public static final RegistryEntry<ConfiguredFeature<FancyDarkOakFeatureConfig, ?>> FANCY_DARK_OAK_PLANTED = register("fancy_dark_oak_planted", GoodEndingFeatures.FANCY_DARK_OAK, new FancyDarkOakFeatureConfig(true));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> OAK_HAMMOCK_TREES = register("oak_hammock_trees", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(TreePlacedFeatures.FANCY_OAK_CHECKED, 0.2F), new RandomFeatureEntry(PlacedFeatures.createEntry(FANCY_DARK_OAK), 0.8F)), PlacedFeatures.createEntry(FANCY_DARK_OAK)));
    public static final RegistryEntry<PlacedFeature> OAK_HAMMOCK_TREES_PLACED = register("oak_hammock_trees_placed", OAK_HAMMOCK_TREES, CountPlacementModifier.of(5), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> GRANITE_BOULDER = register("granite_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.LIGHT_GRAY_TERRACOTTA), BlockStateProvider.of(Blocks.GRANITE), UniformFloatProvider.create(2, 4.2F)));
    public static final RegistryEntry<PlacedFeature> GRANITE_BOULDER_PLACED = register("granite_boulder_placed", GRANITE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> PLAINS_BOULDER = register("plains_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.STONE), BlockStateProvider.of(Blocks.TUFF), UniformFloatProvider.create(2, 3.5F)));
    public static final RegistryEntry<PlacedFeature> PLAINS_BOULDER_PLACED = register("plains_boulder_placed", PLAINS_BOULDER, GoodEndingWorldGen.boulderModifier(25, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> DESERT_BOULDER = register("desert_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.SANDSTONE), BlockStateProvider.of(Blocks.SANDSTONE), UniformFloatProvider.create(2, 3.5F)));
    public static final RegistryEntry<PlacedFeature> DESERT_BOULDER_PLACED = register("desert_boulder_placed", DESERT_BOULDER, GoodEndingWorldGen.boulderModifier(20, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> RED_SANDSTONE_BOULDER = register("red_sandstone_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.RED_SANDSTONE), BlockStateProvider.of(Blocks.RED_SANDSTONE), UniformFloatProvider.create(2, 3.5F)));
    public static final RegistryEntry<PlacedFeature> RED_SANDSTONE_BOULDER_PLACED = register("red_sandstone_boulder_placed", RED_SANDSTONE_BOULDER, GoodEndingWorldGen.boulderModifier(25, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> SMOOTH_BASALT_BOULDER = register("smooth_basalt_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.SMOOTH_BASALT), BlockStateProvider.of(Blocks.SMOOTH_BASALT), UniformFloatProvider.create(2.3F, 5.4F)));
    public static final RegistryEntry<PlacedFeature> SMOOTH_BASALT_BOULDER_PLACED = register("smooth_basalt_boulder_placed", SMOOTH_BASALT_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> STONE_BOULDER = register("stone_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.STONE), BlockStateProvider.of(Blocks.STONE), UniformFloatProvider.create(2.1F, 4.8F)));
    public static final RegistryEntry<PlacedFeature> STONE_BOULDER_PLACED = register("stone_boulder_placed", STONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> TUFF_BOULDER = register("tuff_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.TUFF), BlockStateProvider.of(Blocks.TUFF), UniformFloatProvider.create(2.2F, 5.2F)));
    public static final RegistryEntry<PlacedFeature> TUFF_BOULDER_PLACED = register("tuff_boulder_placed", TUFF_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> COBBLESTONE_BOULDER = register("cobblestone_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.COBBLESTONE), BlockStateProvider.of(Blocks.COBBLESTONE), UniformFloatProvider.create(2F, 3F)));
    public static final RegistryEntry<PlacedFeature> COBBLESTONE_BOULDER_PLACED = register("cobblestone_boulder_placed", COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> MOSSY_COBBLESTONE_BOULDER = register("mossy_cobblestone_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(true, BlockStateProvider.of(Blocks.MOSSY_COBBLESTONE), BlockStateProvider.of(Blocks.COBBLESTONE), UniformFloatProvider.create(2F, 3F)));
    public static final RegistryEntry<PlacedFeature> MOSSY_COBBLESTONE_BOULDER_PLACED = register("mossy_cobblestone_boulder_placed", MOSSY_COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static final RegistryEntry<ConfiguredFeature<BoulderConfig, ?>> MOSSIER_COBBLESTONE_BOULDER = register("mossier_cobblestone_boulder", GoodEndingFeatures.BOULDER, new BoulderConfig(false, BlockStateProvider.of(Blocks.MOSS_BLOCK), BlockStateProvider.of(Blocks.MOSSY_COBBLESTONE), UniformFloatProvider.create(2F, 3F)));
    public static final RegistryEntry<PlacedFeature> MOSSIER_COBBLESTONE_BOULDER_PLACED = register("mossier_cobblestone_boulder_placed", MOSSIER_COBBLESTONE_BOULDER, GoodEndingWorldGen.boulderModifier(19, PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP));

    public static List<PlacementModifier> boulderModifier(int chance, PlacementModifier heightmap) {
        return List.of(RarityFilterPlacementModifier.of(chance), CountPlacementModifier.of(2), SquarePlacementModifier.of(), heightmap, BiomePlacementModifier.of());
    }

    public static List<PlacementModifier> fallenLogModifier() {
        return List.of(RarityFilterPlacementModifier.of(20), CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    }

    public static <FC extends FeatureConfig, F extends Feature<FC>> RegistryEntry<ConfiguredFeature<FC, ?>> register(String id, F feature, FC config) {
        return BuiltinRegistries.addCasted(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(GoodEnding.MODID, id).toString(), new ConfiguredFeature<>(feature, config));
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, PlacementModifier... modifiers) {
        return register(id, registryEntry, List.of(modifiers));
    }

    public static RegistryEntry<PlacedFeature> register(String id, RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry, List<PlacementModifier> modifiers) {
        return BuiltinRegistries.add(BuiltinRegistries.PLACED_FEATURE, new Identifier(GoodEnding.MODID, id).toString(), new PlacedFeature(RegistryEntry.upcast(registryEntry), List.copyOf(modifiers)));
    }

}
