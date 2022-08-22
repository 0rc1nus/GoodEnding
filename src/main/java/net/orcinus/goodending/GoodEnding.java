package net.orcinus.goodending;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingFeatures;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GoodEnding implements ModInitializer {
	public static final String MODID = "goodending";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, MODID)).icon(() -> new ItemStack(Items.LILY_PAD)).build();

	@Override
	public void onInitialize() {
		GoodEndingItems.init();
		GoodEndingBlocks.init();
		GoodEndingFeatures.init();

		Util.make(ImmutableMap.<RegistryEntry<PlacedFeature>, GenerationStep.Feature>builder(),map -> {
			map.put(GoodEndingWorldGen.DISK_MUD_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
			map.put(GoodEndingWorldGen.SHALLOW_WATER_MUD_PLACED, GenerationStep.Feature.UNDERGROUND_ORES);
			map.put(GoodEndingWorldGen.PURPLE_MUSHROOM_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.CATTAIL_PATCH_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.SWAMP_FALLEN_LOG_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.CYPRESS_TREE_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.SWAMP_VEGETATION_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
			map.put(GoodEndingWorldGen.DUCKWEED_PATCH_PLACED, GenerationStep.Feature.VEGETAL_DECORATION);
		}).build().forEach((placedFeatureRegistryEntry, feature) -> placedFeatureRegistryEntry.getKey().ifPresent(placedFeatureRegistryKey -> BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), feature, placedFeatureRegistryKey)));

		BiomeModifications.create(new Identifier(GoodEnding.MODID, "replace_swamp_trees")).add(ModificationPhase.REPLACEMENTS, BiomeSelectors.includeByKey(BiomeKeys.SWAMP), biomeModificationContext -> {
			Optional<RegistryKey<PlacedFeature>> key = GoodEndingWorldGen.SWAMP_TREE_PLACED.getKey();
			if (key.isPresent() && biomeModificationContext.getGenerationSettings().removeBuiltInFeature(VegetationPlacedFeatures.TREES_SWAMP.value())) {
				biomeModificationContext.getGenerationSettings().addFeature(GenerationStep.Feature.VEGETAL_DECORATION, key.get());
			}
		});

		BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.SWAMP), SpawnGroup.AMBIENT, GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8);

		StrippableBlockRegistry.register(GoodEndingBlocks.SWAMP_LOG, GoodEndingBlocks.STRIPPED_SWAMP_LOG);
		StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_LOG, GoodEndingBlocks.STRIPPED_CYPRESS_LOG);

		CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
		compostingChanceRegistry.add(GoodEndingBlocks.SWAMP_LEAVES, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.CYPRESS_LEAVES, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.DUCKWEED, 0.5F);
		compostingChanceRegistry.add(GoodEndingBlocks.PURPLE_MUSHROOM, 0.65F);
		compostingChanceRegistry.add(GoodEndingBlocks.CATTAIL, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.SWAMP_SAPLING, 0.3F);
		compostingChanceRegistry.add(GoodEndingBlocks.CYPRESS_SAPLING, 0.3F);
	}

}
