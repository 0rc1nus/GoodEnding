package net.orcinus.goodending;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldEvents;
import net.orcinus.goodending.init.GoodEndingBiomeModifiers;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingFeatures;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.init.GoodEndingPotions;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import net.orcinus.goodending.init.GoodEndingStructurePieceTypes;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import net.orcinus.goodending.init.GoodEndingStructureTypes;
import net.orcinus.goodending.init.GoodEndingTreeDecorators;
import net.orcinus.goodending.init.GoodEndingVanillaIntegration;

import java.util.List;

public class GoodEnding implements ModInitializer {
	public static final String MODID = "goodending";
	public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, MODID)).icon(() -> new ItemStack(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD)).build();

	@SuppressWarnings("UnstableApiUsage")
	@Override
	public void onInitialize() {

		Reflection.initialize(
			GoodEndingSoundEvents.class,
			GoodEndingItems.class,
			GoodEndingBlocks.class,
			GoodEndingStructureTypes.class,
			GoodEndingStructurePieceTypes.class,
			GoodEndingStructureProcessors.class
		);

		GoodEndingBiomes.init();
		GoodEndingFeatures.init();
		GoodEndingStatusEffects.init();
		GoodEndingTreeDecorators.init();
		GoodEndingPotions.init();
		GoodEndingBiomeModifiers.init();
		GoodEndingVanillaIntegration.init();

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

}
