package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.PlaceableOnWaterItem;
import net.minecraft.item.SignItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.items.FireflyBottleItem;
import net.orcinus.goodending.items.PlaceableUnderWaterItem;

import java.util.Map;

public class GoodEndingItems {

    public static final Map<Identifier, Item> ITEMS = Maps.newLinkedHashMap();

    public static final Item DUCKWEED = registerItem("duckweed", new PlaceableOnWaterItem(GoodEndingBlocks.DUCKWEED, new Item.Settings().group(GoodEnding.TAB)));
    public static final Item LARGE_LILY_PAD = registerItem("large_lily_pad", new PlaceableUnderWaterItem(GoodEndingBlocks.LARGE_LILY_PAD, new Item.Settings().group(GoodEnding.TAB)));
    public static final Item PURPLE_FLOWERING_LILY_PAD = registerItem("purple_flowering_lily_pad", new PlaceableOnWaterItem(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD, new Item.Settings().group(GoodEnding.TAB)));
    public static final Item PINK_FLOWERING_LILY_PAD = registerItem("pink_flowering_lily_pad", new PlaceableOnWaterItem(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD, new Item.Settings().group(GoodEnding.TAB)));
    public static final Item YELLOW_FLOWERING_LILY_PAD = registerItem("yellow_flowering_lily_pad", new PlaceableOnWaterItem(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD, new Item.Settings().group(GoodEnding.TAB)));
//    public static final Item CYPRESS_BOAT = registerItem("cypress_boat", new GoodEndingBoatItem(false, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Settings().maxCount(1).group(GoodEnding.TAB)));
//    public static final Item CYPRESS_CHEST_BOAT = registerItem("cypress_chest_boat", new GoodEndingBoatItem(true, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Settings().maxCount(1).group(GoodEnding.TAB)));
    public static final Item CYPRESS_SIGN_ITEM = registerItem("cypress_sign", new SignItem(new Item.Settings().maxCount(16).group(GoodEnding.TAB), GoodEndingBlocks.CYPRESS_SIGN, GoodEndingBlocks.CYPRESS_WALL_SIGN));
    public static final Item FIREFLY_BOTTLE = registerItem("firefly_bottle", new FireflyBottleItem(new FabricItemSettings().maxCount(16).group(GoodEnding.TAB)));
//    public static final Item MARSH_SPAWN_EGG = registerItem("marsh_spawn_egg", new SpawnEggItem(GoodEndingEntityTypes.MARSH, 3423006, 6979129, new Item.Settings().group(GoodEnding.TAB)));

    public static <I extends Item> I registerItem(String name, I item) {
        ITEMS.put(new Identifier(GoodEnding.MODID, name), item);
        return item;
    }

    public static void init() {
        ITEMS.forEach((identifier, item) -> Registry.register(Registry.ITEM, identifier, item));
    }

}
