package net.orcinus.goodending.init;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.dynamic.Range;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
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
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
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
import net.minecraft.world.gen.stateprovider.DualNoiseBlockStateProvider;
import net.minecraft.world.gen.stateprovider.PredicatedStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

import java.util.List;
import java.util.OptionalInt;

public class GoodEndingWorldGen {

    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskFeatureConfig(new PredicatedStateProvider(BlockStateProvider.of(Blocks.MUD), List.of(new PredicatedStateProvider.Rule(BlockPredicate.not(BlockPredicate.eitherOf(BlockPredicate.solid(Direction.UP.getVector()), BlockPredicate.matchingFluids(Direction.UP.getVector(), Fluids.WATER))), BlockStateProvider.of(Blocks.MUD)))), BlockPredicate.matchingBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT)), UniformIntProvider.create(3, 5), 2));
    public static final RegistryEntry<PlacedFeature> DISK_MUD_PLACED = register("disk_mud", DISK_MUD, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT)), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> SHALLOW_WATER_MUD = register("shallow_water_mud", GoodEndingFeatures.SHALLOW_WATER, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> SHALLOW_WATER_MUD_PLACED = register("shallow_water_mud", SHALLOW_WATER_MUD, CountPlacementModifier.of(120), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE = register("cypress_tree", GoodEndingFeatures.CYPRESS_TREE, new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.of(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), false).build());
    public static final RegistryEntry<ConfiguredFeature<WaterTreeFeatureConfig, ?>> CYPRESS_TREE_PLANTED = register("cypress_tree_planted", GoodEndingFeatures.CYPRESS_TREE, new WaterTreeFeatureConfig.WaterTreeFeatureBuilder(BlockStateProvider.of(Blocks.OAK_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()), true).build());
    public static final RegistryEntry<PlacedFeature> CYPRESS_TREE_CHECKED = register("cypress_tree_checked", CYPRESS_TREE, PlacedFeatures.wouldSurvive(GoodEndingBlocks.CYPRESS_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> CYPRESS_TREE_FILTERED = register("cypress_tree_filtered", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(CYPRESS_TREE_CHECKED, 0.8F)), CYPRESS_TREE_CHECKED));
    public static final RegistryEntry<PlacedFeature> CYPRESS_TREE_PLACED = register("cypress_tree_placed", CYPRESS_TREE_FILTERED, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(8), PlacedFeatures.createCountExtraModifier(4, 0.5F, 4), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SWAMP_TREE = register("swamp_tree", GoodEndingFeatures.SWAMP_TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(GoodEndingBlocks.SWAMP_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(Blocks.OAK_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).build());
    public static final RegistryEntry<PlacedFeature> SWAMP_TREE_PLACED = register("swamp_tree", SWAMP_TREE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<HalfWaterloggedDecorationConfig, ?>> CATTAIL_PATCH = register("cattail_patch", GoodEndingFeatures.HALF_WATERLOGGED_DECORATION, new HalfWaterloggedDecorationConfig(BlockStateProvider.of(GoodEndingBlocks.CATTAIL), UniformIntProvider.create(6, 8)));
    public static final RegistryEntry<PlacedFeature> CATTAIL_PATCH_PLACED = register("cattail_patch", CATTAIL_PATCH, CountPlacementModifier.of(25), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> SWAMP_FALLEN_LOG = register("swamp_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(GoodEndingBlocks.SWAMP_LOG), UniformIntProvider.create(6, 8), false));
    public static final RegistryEntry<PlacedFeature> SWAMP_FALLEN_LOG_PLACED = register("swamp_fallen_log", SWAMP_FALLEN_LOG, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.matchingBlocks(Blocks.AIR, Blocks.GRASS, Blocks.TALL_GRASS), 12), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> BIRCH_FALLEN_LOG = register("birch_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(Blocks.BIRCH_LOG), UniformIntProvider.create(6, 8), true));
    public static final RegistryEntry<PlacedFeature> BIRCH_FALLEN_LOG_PLACED = register("birch_fallen_log", BIRCH_FALLEN_LOG, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.matchingBlocks(Blocks.AIR, Blocks.GRASS, Blocks.TALL_GRASS), 12), BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomBooleanFeatureConfig, ?>> SWAMP_VEGETATION = register("swamp_vegetation", Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_RED_MUSHROOM)));
    public static final RegistryEntry<PlacedFeature> SWAMP_VEGETATION_PLACED = register("swamp_vegetation", SWAMP_VEGETATION, CountPlacementModifier.of(16), SquarePlacementModifier.of(), RarityFilterPlacementModifier.of(60), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> DUCKWEED_PATCH = register("duckweed_patch", GoodEndingFeatures.DUCKWEED_PATCH, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> DUCKWEED_PATCH_PLACED = register("duckweed_patch", DUCKWEED_PATCH, CountPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PASTEL_WILDFLOWERS = register("patch_pastel_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.PASTEL_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TWILIGHT_WILDFLOWERS = register("patch_twilight_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.TWILIGHT_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK, Blocks.MUD)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_SPICY_WILDFLOWERS = register("patch_spicy_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.SPICY_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK)));
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_BALMY_WILDFLOWERS = register("patch_balmy_wildflowers", Feature.RANDOM_PATCH, ConfiguredFeatures.createRandomPatchFeatureConfig(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.BALMY_WILDFLOWERS)), List.of(Blocks.GRASS_BLOCK)));

    public static final RegistryEntry<PlacedFeature> PATCH_PASTEL_WILDFLOWERS_PLACED = register("patch_pastel_wildflowers", PATCH_PASTEL_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_TWILIGHT_WILDFLOWERS_PLACED = register("patch_twilight_wildflowers", PATCH_TWILIGHT_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_SPICY_WILDFLOWERS_PLACED = register("patch_spicy_wildflowers", PATCH_SPICY_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_BALMY_WILDFLOWERS_PLACED = register("patch_balmy_wildflowers", PATCH_BALMY_WILDFLOWERS, RarityFilterPlacementModifier.of(30), CountPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_TALL_GRASS = register("patch_tall_grass", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(32, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder().add(Blocks.GRASS.getDefaultState(), 10).add(GoodEndingBlocks.POLLENFLAKE.getDefaultState(), 7).add(Blocks.AZURE_BLUET.getDefaultState(), 10).add(Blocks.OXEYE_DAISY.getDefaultState(), 10))), BlockPredicate.bothOf(BlockPredicate.IS_AIR, BlockPredicate.not(BlockPredicate.matchingBlocks(Direction.DOWN.getVector(), Blocks.PODZOL))))));

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TALL_BIRCH_TREE = register("tall_birch_tree", GoodEndingFeatures.TALL_BIRCH_TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(Blocks.BIRCH_LOG), new StraightTrunkPlacer(5, 2, 1), BlockStateProvider.of(Blocks.BIRCH_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(0,0,0)).build());
    public static final RegistryEntry<PlacedFeature> TALL_BIRCH_TREE_FILTERED = register("tall_birch_tree_filtered", TALL_BIRCH_TREE, PlacedFeatures.wouldSurvive(Blocks.BIRCH_SAPLING));
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> TALL_BIRCH_VEGETATION = register("tall_birch_vegetation", Feature.RANDOM_SELECTOR, new RandomFeatureConfig(List.of(new RandomFeatureEntry(TALL_BIRCH_TREE_FILTERED, 0.5F)), PlacedFeatures.createEntry(PATCH_TALL_GRASS)));
    public static final RegistryEntry<PlacedFeature> TALL_BIRCH_VEGETATION_PLACED = register("tall_birch_vegetation", TALL_BIRCH_VEGETATION, CountPlacementModifier.of(16), SquarePlacementModifier.of(), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> PATCH_PINK_FLOWERED_LILY = register("patch_pink_flowered_lily", Feature.RANDOM_PATCH, new RandomPatchFeatureConfig(10, 7, 3, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(BlockStateProvider.of(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD)))));
    public static final RegistryEntry<PlacedFeature> PATCH_PINK_FLOWERED_LILY_PLACED = register("patch_pink_flowered_lily_placed", PATCH_PINK_FLOWERED_LILY, VegetationPlacedFeatures.modifiers(4));

    public static final RegistryEntry<PlacedFeature> PATCH_TALL_GRASS_PLACED = register("patch_tall_grass_placed", VegetationConfiguredFeatures.PATCH_TALL_GRASS, VegetationPlacedFeatures.modifiers(3));

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
