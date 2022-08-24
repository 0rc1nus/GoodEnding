package net.orcinus.goodending;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
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
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.orcinus.goodending.init.GoodEndingBiomeTags;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingFeatures;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class GoodEnding implements ModInitializer {
	public static final String MODID = "goodending";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, MODID)).icon(() -> new ItemStack(Items.LILY_PAD)).build();

	//-7697642187085846839
	//-5973 63 417692

	//2634269655997717243
	//-2121 72 -116

	//1519091418833411884
	//387 82 272

	@Override
	public void onInitialize() {
		GoodEndingItems.init();
		GoodEndingBlocks.init();
		GoodEndingFeatures.init();

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
			map.put(GoodEndingBiomeTags.PASTEL_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_PASTEL_WILDFLOWERS_PLACED);
			map.put(GoodEndingBiomeTags.TWILIGHT_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_TWILIGHT_WILDFLOWERS_PLACED);
			map.put(GoodEndingBiomeTags.SPICY_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_SPICY_WILDFLOWERS_PLACED);
			map.put(GoodEndingBiomeTags.BALMY_WILDFLOWER_GENERATES, GoodEndingWorldGen.PATCH_BALMY_WILDFLOWERS_PLACED);
		}).build().forEach((biomeTagKey, placedFeatureRegistryEntry) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> {
			BiomeModifications.addFeature(BiomeSelectors.tag(biomeTagKey), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey);
		}));

		GoodEndingWorldGen.PATCH_PINK_FLOWERED_LILY_PLACED.getKey().ifPresent(placedFeatureRegistryKey -> {
			BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MANGROVE_SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, placedFeatureRegistryKey);
		});

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

		StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_LOG, GoodEndingBlocks.STRIPPED_CYPRESS_LOG);

		CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
		compostingChanceRegistry.add(GoodEndingBlocks.CYPRESS_LEAVES, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.DUCKWEED, 0.5F);
		compostingChanceRegistry.add(GoodEndingBlocks.CATTAIL, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.CYPRESS_SAPLING, 0.3F);

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
				return ActionResult.SUCCESS;
			}
			return ActionResult.PASS;
		});

	}

}
