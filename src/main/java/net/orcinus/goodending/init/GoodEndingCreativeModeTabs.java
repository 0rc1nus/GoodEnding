package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.orcinus.goodending.GoodEnding;

import static net.orcinus.goodending.init.GoodEndingItems.*;

public class GoodEndingCreativeModeTabs {

    public static final ResourceKey<CreativeModeTab> GOOD_ENDING = createKey("good_ending");

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GOOD_ENDING, FabricItemGroup.builder()
                .icon(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD::getDefaultInstance)
                .title(Component.translatable("itemGroup.goodending.goodending"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(FIREFLY_SWARM_SPAWN_EGG);
                    output.accept(MARSH_SPAWN_EGG);
                    output.accept(WOODPECKER_SPAWN_EGG);
                    output.accept(MUDDY_OAK_PLANKS);
                    output.accept(MUDDY_OAK_LOG);
                    output.accept(STRIPPED_MUDDY_OAK_LOG);
                    output.accept(STRIPPED_MUDDY_OAK_WOOD);
                    output.accept(MUDDY_OAK_WOOD);
                    output.accept(MUDDY_OAK_SLAB);
                    output.accept(MUDDY_OAK_FENCE);
                    output.accept(MUDDY_OAK_STAIRS);
                    output.accept(MUDDY_OAK_BUTTON);
                    output.accept(MUDDY_OAK_PRESSURE_PLATE);
                    output.accept(MUDDY_OAK_DOOR);
                    output.accept(MUDDY_OAK_TRAPDOOR);
                    output.accept(MUDDY_OAK_FENCE_GATE);
                    output.accept(MUDDY_OAK_SIGN);
                    output.accept(MUDDY_OAK_HANGING_SIGN);
                    output.accept(MUDDY_OAK_BOAT);
                    output.accept(MUDDY_OAK_CHEST_BOAT);
                    output.accept(CYPRESS_PLANKS);
                    output.accept(CYPRESS_LOG);
                    output.accept(CYPRESS_SAPLING);
                    output.accept(STRIPPED_CYPRESS_LOG);
                    output.accept(STRIPPED_CYPRESS_WOOD);
                    output.accept(CYPRESS_WOOD);
                    output.accept(CYPRESS_LEAVES);
                    output.accept(CYPRESS_SLAB);
                    output.accept(CYPRESS_FENCE);
                    output.accept(CYPRESS_STAIRS);
                    output.accept(CYPRESS_BUTTON);
                    output.accept(CYPRESS_PRESSURE_PLATE);
                    output.accept(CYPRESS_DOOR);
                    output.accept(CYPRESS_TRAPDOOR);
                    output.accept(CYPRESS_FENCE_GATE);
                    output.accept(CYPRESS_SIGN);
                    output.accept(CYPRESS_HANGING_SIGN);
                    output.accept(CYPRESS_BOAT);
                    output.accept(CYPRESS_CHEST_BOAT);
                    output.accept(LARGE_LILY_PAD);
                    output.accept(PURPLE_FLOWERING_LILY_PAD);
                    output.accept(PINK_FLOWERING_LILY_PAD);
                    output.accept(YELLOW_FLOWERING_LILY_PAD);
                    output.accept(PASTEL_WILDFLOWERS);
                    output.accept(TWILIGHT_WILDFLOWERS);
                    output.accept(SPICY_WILDFLOWERS);
                    output.accept(BALMY_WILDFLOWERS);
                    output.accept(POLLENFLAKE);
                    output.accept(BIRCH_MUSHROOM);
                    output.accept(DENSE_BIRCH_LEAVES);
                    output.accept(DENSE_DARK_OAK_LEAVES);
                    output.accept(HANGING_OAK_LEAVES);
                    output.accept(HANGING_DARK_OAK_LEAVES);
                    output.accept(DUCKWEED);
                    output.accept(ALGAE_BUCKET);
                    output.accept(CATTAIL);
                    output.accept(FIREFLY_BOTTLE);
                    output.accept(FIREFLY_LANTERN);
                }).build());
    }

    static {

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.MANGROVE_BUTTON,
                    MUDDY_OAK_LOG,
                    MUDDY_OAK_WOOD,
                    STRIPPED_MUDDY_OAK_LOG,
                    STRIPPED_MUDDY_OAK_WOOD,
                    MUDDY_OAK_PLANKS,
                    MUDDY_OAK_STAIRS,
                    MUDDY_OAK_SLAB,
                    MUDDY_OAK_FENCE,
                    MUDDY_OAK_FENCE_GATE,
                    MUDDY_OAK_DOOR,
                    MUDDY_OAK_TRAPDOOR,
                    MUDDY_OAK_PRESSURE_PLATE,
                    MUDDY_OAK_BUTTON,
                    CYPRESS_LOG,
                    CYPRESS_WOOD,
                    STRIPPED_CYPRESS_LOG,
                    STRIPPED_CYPRESS_WOOD,
                    CYPRESS_PLANKS,
                    CYPRESS_STAIRS,
                    CYPRESS_SLAB,
                    CYPRESS_FENCE,
                    CYPRESS_FENCE_GATE,
                    CYPRESS_DOOR,
                    CYPRESS_TRAPDOOR,
                    CYPRESS_PRESSURE_PLATE,
                    CYPRESS_BUTTON
            );
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(entries -> {
            entries.addBefore(Items.PINK_PETALS,
                    PASTEL_WILDFLOWERS,
                    TWILIGHT_WILDFLOWERS,
                    SPICY_WILDFLOWERS,
                    BALMY_WILDFLOWERS
            );
            entries.addAfter(Items.LILY_PAD,
                    PURPLE_FLOWERING_LILY_PAD,
                    PINK_FLOWERING_LILY_PAD,
                    YELLOW_FLOWERING_LILY_PAD,
                    LARGE_LILY_PAD,
                    DUCKWEED,
                    ALGAE_BUCKET
            );
            entries.addAfter(Items.PEONY, POLLENFLAKE, CATTAIL);
            entries.addAfter(Items.OAK_LEAVES, HANGING_OAK_LEAVES);
            entries.addAfter(Items.DARK_OAK_LEAVES, DENSE_DARK_OAK_LEAVES, HANGING_DARK_OAK_LEAVES);
            entries.addAfter(Items.BIRCH_LEAVES, DENSE_BIRCH_LEAVES);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS).register(entries -> {
            entries.addAfter(Items.SOUL_LANTERN, FIREFLY_LANTERN);
            entries.addAfter(Items.MANGROVE_HANGING_SIGN,
                    MUDDY_OAK_SIGN,
                    MUDDY_OAK_HANGING_SIGN,
                    CYPRESS_SIGN,
                    CYPRESS_HANGING_SIGN
            );
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(entries -> {
            entries.addAfter(Items.MANGROVE_CHEST_BOAT,
                    MUDDY_OAK_BOAT,
                    MUDDY_OAK_CHEST_BOAT,
                    CYPRESS_BOAT,
                    CYPRESS_CHEST_BOAT
            );
            entries.addBefore(Items.BUCKET, Items.GLASS_BOTTLE, FIREFLY_BOTTLE);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
            entries.addAfter(Items.WOLF_SPAWN_EGG, WOODPECKER_SPAWN_EGG);
            entries.addAfter(Items.MAGMA_CUBE_SPAWN_EGG, MARSH_SPAWN_EGG);
            entries.addBefore(Items.FOX_SPAWN_EGG, FIREFLY_SWARM_SPAWN_EGG);
        });
    }


    private static ResourceKey<CreativeModeTab> createKey(String string) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(GoodEnding.MODID, string));
    }

}
