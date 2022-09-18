package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class GoodEndingVanillaIntegration {

    public static void init() {
        registerCompostables();
        registerFlammables();
    }

    public static void registerFlammables() {
        FlammableBlockRegistry defaultInstance = FlammableBlockRegistry.getDefaultInstance();
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_LOG, 5, 5);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_LOG, 5, 5);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_WOOD, 5, 5);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_WOOD, 5, 5);
        defaultInstance.add(GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG, 5, 5);
        defaultInstance.add(GoodEndingBlocks.STRIPPED_CYPRESS_LOG, 5, 5);
        defaultInstance.add(GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD, 5, 5);
        defaultInstance.add(GoodEndingBlocks.STRIPPED_CYPRESS_WOOD, 5, 5);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_PLANKS, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_PLANKS, 5, 20);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_SLAB, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_SLAB, 5, 20);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_STAIRS, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_STAIRS, 5, 20);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_FENCE, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_FENCE, 5, 20);
        defaultInstance.add(GoodEndingBlocks.MUDDY_OAK_FENCE_GATE, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_FENCE_GATE, 5, 20);
        defaultInstance.add(GoodEndingBlocks.CYPRESS_LEAVES, 30, 60);
        defaultInstance.add(GoodEndingBlocks.DENSE_BIRCH_LEAVES, 30, 60);
        defaultInstance.add(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES, 30, 60);
        defaultInstance.add(GoodEndingBlocks.CATTAIL, 60, 100);
        defaultInstance.add(GoodEndingBlocks.POLLENFLAKE, 60, 100);
        defaultInstance.add(GoodEndingBlocks.BALMY_WILDFLOWERS, 60, 100);
        defaultInstance.add(GoodEndingBlocks.PASTEL_WILDFLOWERS, 60, 100);
        defaultInstance.add(GoodEndingBlocks.SPICY_WILDFLOWERS, 60, 100);
        defaultInstance.add(GoodEndingBlocks.TWILIGHT_WILDFLOWERS, 60, 100);
        defaultInstance.add(GoodEndingBlocks.HANGING_OAK_LEAVES, 15, 60);
        defaultInstance.add(GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT, 15, 60);
        defaultInstance.add(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES, 15, 60);
        defaultInstance.add(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT, 15, 60);
    }

    public static void registerCompostables() {
        StrippableBlockRegistry.register(GoodEndingBlocks.MUDDY_OAK_LOG, GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG);
        StrippableBlockRegistry.register(GoodEndingBlocks.MUDDY_OAK_WOOD, GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD);
        StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_LOG, GoodEndingBlocks.STRIPPED_CYPRESS_LOG);
        StrippableBlockRegistry.register(GoodEndingBlocks.CYPRESS_WOOD, GoodEndingBlocks.STRIPPED_CYPRESS_WOOD);
    }

}
