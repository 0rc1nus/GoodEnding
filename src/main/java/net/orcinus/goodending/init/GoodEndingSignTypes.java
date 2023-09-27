package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingSignTypes {

    public static final WoodType CYPRESS = WoodTypeRegistry.register(new ResourceLocation(GoodEnding.MODID, "cypress"), GoodEndingBlockSetTypes.MUDDY_OAK);
    public static final WoodType MUDDY_OAK = WoodTypeRegistry.register(new ResourceLocation(GoodEnding.MODID, "muddy_oak"), GoodEndingBlockSetTypes.CYPRESS);

}
