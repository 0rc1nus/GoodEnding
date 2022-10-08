package net.orcinus.goodending;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.orcinus.goodending.events.MiscEvents;
import net.orcinus.goodending.events.MobEvents;
import net.orcinus.goodending.init.GoodEndingBiomeModifiers;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.init.GoodEndingBlockEntityTypes;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingFeatures;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingParticleTypes;
import net.orcinus.goodending.init.GoodEndingPotions;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import net.orcinus.goodending.init.GoodEndingStructurePieceTypes;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import net.orcinus.goodending.init.GoodEndingStructureTypes;
import net.orcinus.goodending.init.GoodEndingTreeDecorators;
import net.orcinus.goodending.init.GoodEndingVanillaIntegration;
import net.orcinus.goodending.init.GoodEndingWorldGen;
import net.orcinus.goodending.mixin.invokers.BrewingRecipeRegistryInvoker;

@Mod(GoodEnding.MODID)
public class GoodEnding {
	public static final String MODID = "goodending";
	public static final CreativeModeTab TAB = new CreativeModeTab(MODID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD.get());
		}
	};

	public GoodEnding() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		IEventBus eventBus = MinecraftForge.EVENT_BUS;

		GoodEndingBlocks.BLOCKS.register(modEventBus);
		GoodEndingBlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
		GoodEndingBiomes.BIOMES.register(modEventBus);
		GoodEndingItems.ITEMS.register(modEventBus);
		GoodEndingEntityTypes.ENTITY_TYPES.register(modEventBus);
		GoodEndingFeatures.FEATURES.register(modEventBus);
		GoodEndingParticleTypes.PARTICLE_TYPES.register(modEventBus);
		GoodEndingSoundEvents.SOUND_EVENTS.register(modEventBus);
		GoodEndingStatusEffects.MOB_EFFECTS.register(modEventBus);
		GoodEndingPotions.POTIONS.register(modEventBus);
		GoodEndingTreeDecorators.TREE_DECORATOR_TYPES.register(modEventBus);

		modEventBus.addListener(this::commonSetup);
		eventBus.register(this);
		eventBus.register(new MobEvents());
		eventBus.register(new MiscEvents());
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		BrewingRecipeRegistryInvoker.callAddMix(Potions.AWKWARD, GoodEndingBlocks.BIRCH_MUSHROOM.get().asItem(), GoodEndingPotions.IMMUNITY.get());
		BrewingRecipeRegistryInvoker.callAddMix(GoodEndingPotions.IMMUNITY.get(), Items.REDSTONE, GoodEndingPotions.LONG_IMMUNITY.get());
		event.enqueueWork(() -> {
			GoodEndingStructureProcessors.init();
			GoodEndingStructurePieceTypes.init();
			GoodEndingStructureTypes.init();
			GoodEndingWorldGen.init();
			GoodEndingVanillaIntegration.init();
		});
	}

}
