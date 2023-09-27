package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingCreativeModeTabs {

    public static final ResourceKey<CreativeModeTab> GOOD_ENDING = createKey("good_ending");

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, GOOD_ENDING, FabricItemGroup.builder()
                .icon(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD::getDefaultInstance)
                .title(Component.translatable("itemGroup.goodending.goodending"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(GoodEndingItems.FIREFLY_SWARM_SPAWN_EGG);
                    output.accept(GoodEndingItems.MARSH_SPAWN_EGG);
                    output.accept(GoodEndingItems.WOODPECKER_SPAWN_EGG);
                    output.accept(GoodEndingItems.MUDDY_OAK_PLANKS);
                    output.accept(GoodEndingItems.MUDDY_OAK_LOG);
                    output.accept(GoodEndingItems.STRIPPED_MUDDY_OAK_LOG);
                    output.accept(GoodEndingItems.STRIPPED_MUDDY_OAK_WOOD);
                    output.accept(GoodEndingItems.MUDDY_OAK_WOOD);
                    output.accept(GoodEndingItems.MUDDY_OAK_SLAB);
                    output.accept(GoodEndingItems.MUDDY_OAK_FENCE);
                    output.accept(GoodEndingItems.MUDDY_OAK_STAIRS);
                    output.accept(GoodEndingItems.MUDDY_OAK_BUTTON);
                    output.accept(GoodEndingItems.MUDDY_OAK_PRESSURE_PLATE);
                    output.accept(GoodEndingItems.MUDDY_OAK_DOOR);
                    output.accept(GoodEndingItems.MUDDY_OAK_TRAPDOOR);
                    output.accept(GoodEndingItems.MUDDY_OAK_FENCE_GATE);
                    output.accept(GoodEndingItems.MUDDY_OAK_SIGN);
                    output.accept(GoodEndingItems.MUDDY_OAK_HANGING_SIGN);
                    output.accept(GoodEndingItems.MUDDY_OAK_BOAT);
                    output.accept(GoodEndingItems.MUDDY_OAK_CHEST_BOAT);
                    output.accept(GoodEndingItems.CYPRESS_PLANKS);
                    output.accept(GoodEndingItems.CYPRESS_LOG);
                    output.accept(GoodEndingItems.CYPRESS_SAPLING);
                    output.accept(GoodEndingItems.STRIPPED_CYPRESS_LOG);
                    output.accept(GoodEndingItems.STRIPPED_CYPRESS_WOOD);
                    output.accept(GoodEndingItems.CYPRESS_WOOD);
                    output.accept(GoodEndingItems.CYPRESS_LEAVES);
                    output.accept(GoodEndingItems.CYPRESS_SLAB);
                    output.accept(GoodEndingItems.CYPRESS_FENCE);
                    output.accept(GoodEndingItems.CYPRESS_STAIRS);
                    output.accept(GoodEndingItems.CYPRESS_BUTTON);
                    output.accept(GoodEndingItems.CYPRESS_PRESSURE_PLATE);
                    output.accept(GoodEndingItems.CYPRESS_DOOR);
                    output.accept(GoodEndingItems.CYPRESS_TRAPDOOR);
                    output.accept(GoodEndingItems.CYPRESS_FENCE_GATE);
                    output.accept(GoodEndingItems.CYPRESS_SIGN);
                    output.accept(GoodEndingItems.CYPRESS_HANGING_SIGN);
                    output.accept(GoodEndingItems.CYPRESS_BOAT);
                    output.accept(GoodEndingItems.CYPRESS_CHEST_BOAT);
                    output.accept(GoodEndingItems.LARGE_LILY_PAD);
                    output.accept(GoodEndingItems.PURPLE_FLOWERING_LILY_PAD);
                    output.accept(GoodEndingItems.PINK_FLOWERING_LILY_PAD);
                    output.accept(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD);
                    output.accept(GoodEndingItems.PASTEL_WILDFLOWERS);
                    output.accept(GoodEndingItems.TWILIGHT_WILDFLOWERS);
                    output.accept(GoodEndingItems.SPICY_WILDFLOWERS);
                    output.accept(GoodEndingItems.BALMY_WILDFLOWERS);
                    output.accept(GoodEndingItems.POLLENFLAKE);
                    output.accept(GoodEndingItems.BIRCH_MUSHROOM);
                    output.accept(GoodEndingItems.DENSE_BIRCH_LEAVES);
                    output.accept(GoodEndingItems.DENSE_DARK_OAK_LEAVES);
                    output.accept(GoodEndingItems.HANGING_OAK_LEAVES);
                    output.accept(GoodEndingItems.HANGING_DARK_OAK_LEAVES);
                    output.accept(GoodEndingItems.DUCKWEED);
                    output.accept(GoodEndingItems.ALGAE_BUCKET);
                    output.accept(GoodEndingItems.CATTAIL);
                    output.accept(GoodEndingItems.FIREFLY_BOTTLE);
                    output.accept(GoodEndingItems.FIREFLY_LANTERN);
                }).build());
    }

    private static ResourceKey<CreativeModeTab> createKey(String string) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(GoodEnding.MODID, string));
    }

}
