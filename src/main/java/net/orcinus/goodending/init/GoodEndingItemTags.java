package net.orcinus.goodending.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingItemTags {

    public static final TagKey<Item> CYPRESS_LOGS = registerItemTag("cypress_logs");

    private static TagKey<Item> registerItemTag(String key) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, key));
    }

}
