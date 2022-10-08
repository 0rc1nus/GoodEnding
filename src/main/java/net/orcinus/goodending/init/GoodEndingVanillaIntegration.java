package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;

public class GoodEndingVanillaIntegration {

    public static void init() {
        registerStrippables();
        registerFlammables();
    }

    public static void registerFlammables() {
        FireBlock fireBlock = (FireBlock) Blocks.FIRE;
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_LOG.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_LOG.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_WOOD.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_WOOD.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.STRIPPED_CYPRESS_LOG.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.STRIPPED_CYPRESS_WOOD.get(), 5, 5);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_PLANKS.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_SLAB.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_SLAB.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_STAIRS.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_FENCE.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_FENCE.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.MUDDY_OAK_FENCE_GATE.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_FENCE_GATE.get(), 5, 20);
        fireBlock.setFlammable(GoodEndingBlocks.CYPRESS_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(GoodEndingBlocks.DENSE_BIRCH_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get(), 30, 60);
        fireBlock.setFlammable(GoodEndingBlocks.CATTAIL.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.POLLENFLAKE.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.BALMY_WILDFLOWERS.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.PASTEL_WILDFLOWERS.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.SPICY_WILDFLOWERS.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.TWILIGHT_WILDFLOWERS.get(), 60, 100);
        fireBlock.setFlammable(GoodEndingBlocks.HANGING_OAK_LEAVES.get(), 15, 60);
        fireBlock.setFlammable(GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.get(), 15, 60);
        fireBlock.setFlammable(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get(), 15, 60);
        fireBlock.setFlammable(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.get(), 15, 60);
    }

    public static void registerStrippables() {
        AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(GoodEndingBlocks.MUDDY_OAK_LOG.get(), GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG.get());
        AxeItem.STRIPPABLES.put(GoodEndingBlocks.MUDDY_OAK_WOOD.get(), GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD.get());
        AxeItem.STRIPPABLES.put(GoodEndingBlocks.CYPRESS_LOG.get(), GoodEndingBlocks.STRIPPED_CYPRESS_LOG.get());
        AxeItem.STRIPPABLES.put(GoodEndingBlocks.CYPRESS_WOOD.get(), GoodEndingBlocks.STRIPPED_CYPRESS_WOOD.get());
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.CYPRESS_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.DUCKWEED.get(), 0.5F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.CATTAIL.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.CYPRESS_SAPLING.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.PASTEL_WILDFLOWERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.TWILIGHT_WILDFLOWERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.SPICY_WILDFLOWERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.BALMY_WILDFLOWERS.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.BIRCH_MUSHROOM.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.LARGE_LILY_PAD.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.DENSE_BIRCH_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.HANGING_OAK_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get(), 0.65F);
        ComposterBlock.COMPOSTABLES.put(GoodEndingBlocks.POLLENFLAKE.get(), 0.65F);
    }

}
