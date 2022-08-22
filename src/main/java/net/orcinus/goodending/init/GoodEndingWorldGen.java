package net.orcinus.goodending.init;

import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.DiskFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.RandomBooleanFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureEntry;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
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
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;

import java.util.List;
import java.util.OptionalInt;

public class GoodEndingWorldGen {

    public static final RegistryEntry<ConfiguredFeature<DiskFeatureConfig, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskFeatureConfig(new PredicatedStateProvider(BlockStateProvider.of(Blocks.MUD), List.of(new PredicatedStateProvider.Rule(BlockPredicate.not(BlockPredicate.eitherOf(BlockPredicate.solid(Direction.UP.getVector()), BlockPredicate.matchingFluids(Direction.UP.getVector(), Fluids.WATER))), BlockStateProvider.of(Blocks.MUD)))), BlockPredicate.matchingBlocks(List.of(Blocks.GRASS_BLOCK, Blocks.DIRT)), UniformIntProvider.create(3, 5), 2));
    public static final RegistryEntry<PlacedFeature> DISK_MUD_PLACED = register("disk_mud", DISK_MUD, CountPlacementModifier.of(2), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, RandomOffsetPlacementModifier.vertically(ConstantIntProvider.create(-1)), BlockFilterPlacementModifier.of(BlockPredicate.matchingBlocks(Blocks.GRASS_BLOCK, Blocks.DIRT)), BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> SHALLOW_WATER_MUD = register("shallow_water_mud", GoodEndingFeatures.SHALLOW_WATER, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> SHALLOW_WATER_MUD_PLACED = register("shallow_water_mud", SHALLOW_WATER_MUD, CountPlacementModifier.of(120), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> PURPLE_MUSHROOM = register("lime_mushroom", GoodEndingFeatures.PURPLE_MUSHROOM, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> PURPLE_MUSHROOM_PLACED = register("lime_mushroom", PURPLE_MUSHROOM, CountPlacementModifier.of(20), SquarePlacementModifier.of(), RarityFilterPlacementModifier.of(120), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> CYPRESS_TREE = register("cypress_tree", GoodEndingFeatures.CYPRESS_TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(GoodEndingBlocks.SWAMP_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(GoodEndingBlocks.SWAMP_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).build());
    public static final RegistryEntry<PlacedFeature> CYPRESS_TREE_PLACED = register("cypress_tree", CYPRESS_TREE, SquarePlacementModifier.of(), SurfaceWaterDepthFilterPlacementModifier.of(3), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> SWAMP_TREE = register("swamp_tree", GoodEndingFeatures.SWAMP_TREE, new TreeFeatureConfig.Builder(BlockStateProvider.of(GoodEndingBlocks.SWAMP_LOG), new DarkOakTrunkPlacer(6, 2, 1), BlockStateProvider.of(GoodEndingBlocks.SWAMP_LEAVES), new DarkOakFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty())).build());
    public static final RegistryEntry<PlacedFeature> SWAMP_TREE_PLACED = register("semi_circle", SWAMP_TREE, CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, EnvironmentScanPlacementModifier.of(Direction.DOWN, BlockPredicate.matchingBlockTag(BlockTags.DIRT), BlockPredicate.IS_AIR, 12), BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<HalfWaterloggedDecorationConfig, ?>> CATTAIL_PATCH = register("cattail_patch", GoodEndingFeatures.HALF_WATERLOGGED_DECORATION, new HalfWaterloggedDecorationConfig(BlockStateProvider.of(GoodEndingBlocks.CATTAIL), UniformIntProvider.create(6, 8)));
    public static final RegistryEntry<PlacedFeature> CATTAIL_PATCH_PLACED = register("cattail_patch", CATTAIL_PATCH, CountPlacementModifier.of(25), SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<FallenLogConfig, ?>> SWAMP_FALLEN_LOG = register("swamp_fallen_log", GoodEndingFeatures.FALLEN_LOG, new FallenLogConfig(BlockStateProvider.of(GoodEndingBlocks.SWAMP_LOG), UniformIntProvider.create(6, 8)));
    public static final RegistryEntry<PlacedFeature> SWAMP_FALLEN_LOG_PLACED = register("swamp_fallen_log", SWAMP_FALLEN_LOG, CountPlacementModifier.of(1), SquarePlacementModifier.of(), RarityFilterPlacementModifier.of(20), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<RandomBooleanFeatureConfig, ?>> SWAMP_VEGETATION = register("swamp_vegetation", Feature.RANDOM_BOOLEAN_SELECTOR, new RandomBooleanFeatureConfig(PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_BROWN_MUSHROOM), PlacedFeatures.createEntry(TreeConfiguredFeatures.HUGE_RED_MUSHROOM)));
    public static final RegistryEntry<PlacedFeature> SWAMP_VEGETATION_PLACED = register("swamp_vegetation", SWAMP_VEGETATION, CountPlacementModifier.of(16), SquarePlacementModifier.of(), RarityFilterPlacementModifier.of(60), VegetationPlacedFeatures.NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<ConfiguredFeature<DefaultFeatureConfig, ?>> DUCKWEED_PATCH = register("duckweed_patch", GoodEndingFeatures.DUCKWEED_PATCH, new DefaultFeatureConfig());
    public static final RegistryEntry<PlacedFeature> DUCKWEED_PATCH_PLACED = register("duckweed_patch", DUCKWEED_PATCH, CountPlacementModifier.of(20), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());

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
