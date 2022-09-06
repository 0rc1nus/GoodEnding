package net.orcinus.goodending;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingFeatures;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingPotions;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import net.orcinus.goodending.init.GoodEndingTags;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GoodEnding implements ModInitializer {
	public static final String MODID = "goodending";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, MODID)).icon(() -> new ItemStack(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD)).build();

	//-7697642187085846839
	//-5973 63 417692

	//2634269655997717243
	//-2121 72 -116

	//1519091418833411884
	//387 82 272

	//-866439392768082315
	//-2752 63 -3572

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {

		Reflection.initialize(
			GoodEndingSoundEvents.class,
			GoodEndingItems.class,
			GoodEndingBlocks.class
		);

		GoodEndingBiomes.init();
		GoodEndingFeatures.init();
		GoodEndingStatusEffects.init();
		GoodEndingPotions.init();

		Util.make(ImmutableMap.<RegistryEntry<PlacedFeature>, GenerationStep.Feature>builder(), map -> {
			map.put(GoodEndingWorldGen.BIG_LILY_PADS_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.PATCH_FLOWERING_WATERLILY_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.DISK_PODZOL_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
			map.put(GoodEndingWorldGen.MARSH_SWAMP_TREE_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
		}).build().forEach((placedFeatureRegistryEntry, feature) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(GoodEndingBiomes.MARSHY_SWAMP_KEY), feature, placedFeatureRegistryKey)));

		Util.make(ImmutableMap.<RegistryEntry<PlacedFeature>, GenerationStep.Feature>builder(), map -> {
			map.put(GoodEndingWorldGen.DISK_PODZOL_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
		}).build().forEach((placedFeatureRegistryEntry, feature) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), feature, placedFeatureRegistryKey)));

		Util.make(ImmutableMap.<RegistryEntry<PlacedFeature>, GenerationStep.Feature>builder(),map -> {
			map.put(GoodEndingWorldGen.DISK_MUD_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
			map.put(GoodEndingWorldGen.SHALLOW_WATER_MUD_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
			map.put(GoodEndingWorldGen.CATTAIL_PATCH_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.SWAMP_FALLEN_LOG_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.CYPRESS_TREE_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.SWAMP_VEGETATION_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.DUCKWEED_PATCH_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
		}).build().forEach((placedFeatureRegistryEntry, feature) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), feature, placedFeatureRegistryKey)));

		Util.make(ImmutableMap.<TagKey<Biome>, RegistryEntry<PlacedFeature>>builder(), map -> {
			map.put(GoodEndingTags.PASTEL_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_PASTEL_WILDFLOWERS_PLACED);
			map.put(GoodEndingTags.TWILIGHT_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_TWILIGHT_WILDFLOWERS_PLACED);
			map.put(GoodEndingTags.SPICY_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_SPICY_WILDFLOWERS_PLACED);
			map.put(GoodEndingTags.BALMY_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_BALMY_WILDFLOWERS_PLACED);
		}).build().forEach((biomeTagKey, placedFeatureRegistryEntry) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.tag(biomeTagKey), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey)));

		this.addFeatureToBiome(GoodEndingWorldGen.PATCH_PINK_FLOWERED_LILY_PLACED, BiomeKeys.MANGROVE_SWAMP);

		this.addFeatureToBiome(GoodEndingWorldGen.OAK_FALLEN_LOG_PLACED, BiomeKeys.FOREST);
		this.addFeatureToBiome(GoodEndingWorldGen.OAK_FALLEN_LOG_PLACED, BiomeKeys.WOODED_BADLANDS);
		this.addFeatureToBiome(GoodEndingWorldGen.SPRUCE_FALLEN_LOG_PLACED, BiomeKeys.TAIGA);
		this.addFeatureToBiome(GoodEndingWorldGen.SPRUCE_FALLEN_LOG_PLACED, BiomeKeys.SNOWY_TAIGA);
		this.addFeatureToBiome(GoodEndingWorldGen.SPRUCE_FALLEN_LOG_PLACED, BiomeKeys.WINDSWEPT_FOREST);
		this.addFeatureToBiome(GoodEndingWorldGen.SPRUCE_FALLEN_LOG_PLACED, BiomeKeys.GROVE);
		this.addFeatureToBiome(GoodEndingWorldGen.ACACIA_FALLEN_LOG_PLACED, BiomeKeys.SAVANNA);
		this.addFeatureToBiome(GoodEndingWorldGen.BIRCH_FALLEN_LOG_PLACED, BiomeKeys.FOREST);
		this.addFeatureToBiome(GoodEndingWorldGen.BIRCH_FALLEN_LOG_PLACED, BiomeKeys.BIRCH_FOREST);
		this.addFeatureToBiome(GoodEndingWorldGen.BIRCH_FALLEN_LOG_PLACED, BiomeKeys.OLD_GROWTH_BIRCH_FOREST);

		this.addFeatureToBiome(GoodEndingWorldGen.PATCH_TALL_GRASS_PLACED, BiomeKeys.OLD_GROWTH_BIRCH_FOREST);

		BiomeModifications.create(new Identifier(GoodEnding.MODID, "replace_swamp_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.SWAMP), biomeModificationContext -> {
			Optional<RegistryKey<PlacedFeature>> key = GoodEndingWorldGen.SWAMP_TREE_PLACED.getKey();
			if (key.isPresent() && biomeModificationContext.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_SWAMP.value())) {
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, key.get());
			}
		});

		BiomeModifications.create(new Identifier(GoodEnding.MODID, "remove_small_birch_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), biomeModificationContext -> {
			Optional<RegistryKey<PlacedFeature>> key = GoodEndingWorldGen.TALL_BIRCH_VEGETATION_PLACED.getKey();
			if (key.isPresent() && biomeModificationContext.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.BIRCH_TALL.value())) {
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, key.get());
			}
		});

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), SpawnGroup.AMBIENT, GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8);

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.OLD_GROWTH_BIRCH_FOREST), SpawnGroup.CREATURE, GoodEndingEntityTypes.WOODPECKER, 20, 1, 4);

		StrippableBlockRegistry.register(GoodEndingBlocks.MUDDY_OAK_LOG, GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG);
		StrippableBlockRegistry.register(GoodEndingBlocks.MUDDY_OAK_WOOD, GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD);
		StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_LOG, GoodEndingBlocks.STRIPPED_CYPRESS_LOG);
		StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_WOOD, GoodEndingBlocks.STRIPPED_CYPRESS_WOOD);

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockPos blockPos = hitResult.getBlockPos();
			ItemStack stack = player.getStackInHand(hand);
			if (world.getBlockState(blockPos).isOf(Blocks.LILY_PAD) && stack.isOf(Items.BONE_MEAL) && !world.isClient()) {
				if (!player.getAbilities().creativeMode) {
					stack.decrement(1);
				}
				List<Block> list = Util.make(Lists.newArrayList(), block -> {
					block.add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD);
					block.add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD);
					block.add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD);
				});
				world.setBlockState(blockPos, list.get(world.getRandom().nextInt(list.size())).getDefaultState(), 2);
				world.playSound(null, blockPos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.0F, 1.0F);
				world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 0);
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		});

	}

	private void addFeatureToBiome(RegistryEntry<PlacedFeature> placedFeatureRegistryEntry, RegistryKey<Biome> biomeRegistryKey) {
		placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomeRegistryKey), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey));
	}
}
