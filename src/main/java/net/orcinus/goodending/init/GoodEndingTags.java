package net.orcinus.goodending.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.orcinus.goodending.GoodEnding;

@SuppressWarnings("unused")
public interface GoodEndingTags {

    //Item tags
    TagKey<Item> MUDDY_OAK_LOGS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, "muddy_oak_logs"));
    TagKey<Item> CYPRESS_LOGS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, "cypress_logs"));
    TagKey<Item> WILDFLOWERS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, "wildflowers"));
    TagKey<Item> FLOWERING_LILY_PADS_ITEM = TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, "flowering_lily_pads"));
    TagKey<Item> MARSH_TRUSTED_ITEMS = TagKey.of(Registry.ITEM_KEY, new Identifier(GoodEnding.MODID, "marsh_trusted_items"));

    //Block tags
    TagKey<Block> CYPRESS_LOGS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "cypress_logs"));
    TagKey<Block> MUDDY_OAK_LOGS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "muddy_oak_logs"));
    TagKey<Block> CYPRESS_REPLACEABLES = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "cypress_replaceables"));
    TagKey<Block> WILDFLOWERS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "wildflowers"));
    TagKey<Block> FLOWERING_LILY_PADS_BLOCK = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "flowering_lily_pads"));
    TagKey<Block> BASE_BOULDER = TagKey.of(Registry.BLOCK_KEY, new Identifier(GoodEnding.MODID, "base_boulder"));

    //Biome tags
    TagKey<Biome> PASTEL_WILDFLOWER_GENERATES = registerBiomeTag("pastel_wildflower_generates");
    TagKey<Biome> TWILIGHT_WILDFLOWER_GENERATES = registerBiomeTag("twilight_wildflower_generates");
    TagKey<Biome> SPICY_WILDFLOWER_GENERATES = registerBiomeTag("spicy_wildflower_generates");
    TagKey<Biome> BALMY_WILDFLOWER_GENERATES = registerBiomeTag("balmy_wildflower_generates");
    TagKey<Biome> LIGHT_GRAY_TERRACOTTA_GRANITE_BOULDER_GENERATES = registerBiomeTag("light_gray_terracotta_granite_boulder_generates");
    TagKey<Biome> STONE_TUFF_BOULDER_GENERATES = registerBiomeTag("stone_tuff_boulder_generates");
    TagKey<Biome> STONE_BOULDER_GENERATES = registerBiomeTag("stone_boulder_generates");
    TagKey<Biome> COBBLESTONE_BOULDER_GENERATES = registerBiomeTag("cobblestone_boulder_generates");
    TagKey<Biome> MOSSY_COBBLESTONE_BOULDER_GENERATES = registerBiomeTag("mossy_cobblestone_boulder_generates");
    TagKey<Biome> MOSSIER_COBBLESTONE_BOULDER_GENERATES = registerBiomeTag("mossier_cobblestone_boulder_generates");
    TagKey<Biome> TUFF_BOULDER_GENERATES = registerBiomeTag("tuff_boulder_generates");
    TagKey<Biome> SANDSTONE_BOULDER_GENERATES = registerBiomeTag("sandstone_boulder_generates");
    TagKey<Biome> SMOOTH_BASALT_BOULDER_GENERATES = registerBiomeTag("smooth_basalt_boulder_generates");
    TagKey<Biome> RED_SANDSTONE_BOULDER_GENERATES = registerBiomeTag("red_sandstone_boulder_generates");
    TagKey<Biome> OAK_FALLEN_LOG_GENERATES = registerBiomeTag("oak_fallen_log_generates");
    TagKey<Biome> BIRCH_FALLEN_LOG_GENERATES = registerBiomeTag("birch_fallen_log_generates");
    TagKey<Biome> SPRUCE_FALLEN_LOG_GENERATES = registerBiomeTag("spruce_fallen_log_generates");
    TagKey<Biome> ACACIA_FALLEN_LOG_GENERATES = registerBiomeTag("acacia_fallen_log_generates");
    TagKey<Biome> REVAMPED_WITCH_HUT = registerBiomeTag("has_structure/revamped_witch_hut");

    private static TagKey<Biome> registerBiomeTag(String name) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(GoodEnding.MODID, name));
    }

}
