package net.orcinus.goodending.init;

import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingBiomeTags {

    public static final TagKey<Biome> PASTEL_WILDFLOWER_GENERATES = createBiomeTag("pastel_wildflower_generates");
    public static final TagKey<Biome> TWILIGHT_WILDFLOWER_GENERATES = createBiomeTag("twilight_wildflower_generates");
    public static final TagKey<Biome> SPICY_WILDFLOWER_GENERATES = createBiomeTag("spicy_wildflower_generates");
    public static final TagKey<Biome> BALMY_WILDFLOWER_GENERATES = createBiomeTag("balmy_wildflower_generates");
    public static final TagKey<Biome> REVAMPED_WITCH_HUT = createBiomeTag("has_structure/revamped_witch_hut");

    public static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.of(Registry.BIOME_KEY, new Identifier(GoodEnding.MODID, name));
    }

}
