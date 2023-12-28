package net.orcinus.goodending.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingBiomes {

    public static final ResourceKey<Biome> OAK_HAMMOCK_FOREST = create("oak_hammock_forest");
    public static final ResourceKey<Biome> MARSHY_SWAMP = create("marshy_swamp");

    private static ResourceKey<Biome> create(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(GoodEnding.MODID, name));
    }

}
