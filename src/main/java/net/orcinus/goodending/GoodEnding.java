package net.orcinus.goodending;

import com.google.common.collect.Lists;
import com.google.common.reflect.Reflection;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.orcinus.goodending.init.GoodEndingBiomeModifiers;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingCreativeModeTabs;
import net.orcinus.goodending.init.GoodEndingCriteriaTriggers;
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

import java.util.List;

public class GoodEnding implements ModInitializer {
	public static final String MODID = "goodending";
//	public static final ItemGroup TAB = FabricItemGroupBuilder.create(new Identifier(MODID, MODID)).icon(() -> new ItemStack(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD)).build();

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

		GoodEndingCriteriaTriggers.init();
		GoodEndingCreativeModeTabs.init();
		GoodEndingFeatures.init();
		GoodEndingStatusEffects.init();
		GoodEndingTreeDecorators.init();
		GoodEndingPotions.init();
		GoodEndingBiomeModifiers.init();
		GoodEndingVanillaIntegration.init();
		GoodEndingParticleTypes.init();

		UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
			BlockPos blockPos = hitResult.getBlockPos();
			ItemStack stack = player.getItemInHand(hand);
			if (world.getBlockState(blockPos).is(Blocks.LILY_PAD) && stack.is(Items.BONE_MEAL) && !world.isClientSide) {
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				List<Block> list = Util.make(Lists.newArrayList(), block -> {
					block.add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD);
					block.add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD);
					block.add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD);
				});
				world.setBlock(blockPos, list.get(world.getRandom().nextInt(list.size())).defaultBlockState(), 2);
				world.playSound(null, blockPos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
				world.levelEvent(1505, blockPos, 0);
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		});

	}

}
