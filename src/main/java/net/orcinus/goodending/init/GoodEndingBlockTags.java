package net.orcinus.goodending.init;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingBlockTags {

    public static final TagKey<Block> CYPRESS_LOGS = registerBlockTag("cypress_logs");
    public static final TagKey<Block> CYPRESS_REPLACEABLES = registerBlockTag("cypress_replaceables");

    private static TagKey<Block> registerBlockTag(String key) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, key));
    }

}
