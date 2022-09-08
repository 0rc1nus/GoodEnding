package net.orcinus.goodending.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import static net.orcinus.goodending.GoodEnding.*;

@SuppressWarnings("unused")
public interface GoodEndingTags {

    //Item tags
    TagKey<Item> MUDDY_OAK_LOGS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(MODID, "muddy_oak_logs"));
    TagKey<Item> CYPRESS_LOGS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(MODID, "cypress_logs"));
    TagKey<Item> WILDFLOWERS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(MODID, "wildflowers"));
    TagKey<Item> FLOWERING_LILY_PADS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(MODID, "flowering_lily_pads"));
    TagKey<Item> MARSH_TRUSTED_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(MODID, "marsh_trusted_items"));

    //Block tags
    TagKey<Block> CYPRESS_LOGS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(MODID, "cypress_logs"));
    TagKey<Block> MUDDY_OAK_LOGS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(MODID, "muddy_oak_logs"));
    TagKey<Block> CYPRESS_REPLACEABLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(MODID, "cypress_replaceables"));
    TagKey<Block> WILDFLOWERS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(MODID, "wildflowers"));
    TagKey<Block> FLOWERING_LILY_PADS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(MODID, "flowering_lily_pads"));

    //Biome tags
    TagKey<Biome> PASTEL_WILDFLOWER_GENERATES = TagKey.of(Registry.BIOME_KEY, new Identifier(MODID, "pastel_wildflower_generates"));
    TagKey<Biome> TWILIGHT_WILDFLOWER_GENERATES = TagKey.of(Registry.BIOME_KEY, new Identifier(MODID, "twilight_wildflower_generates"));
    TagKey<Biome> SPICY_WILDFLOWER_GENERATES = TagKey.of(Registry.BIOME_KEY, new Identifier(MODID, "spicy_wildflower_generates"));
    TagKey<Biome> BALMY_WILDFLOWER_GENERATES = TagKey.of(Registry.BIOME_KEY, new Identifier(MODID, "balmy_wildflower_generates"));
    TagKey<Biome> REVAMPED_WITCH_HUT = TagKey.of(Registry.BIOME_KEY, new Identifier(MODID, "has_structure/revamped_witch_hut"));

}
