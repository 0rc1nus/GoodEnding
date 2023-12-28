package net.orcinus.goodending.init;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingSignTypes {

    public static final WoodType MUDDY_OAK = WoodType.register(new WoodType(GoodEnding.MODID + ":muddy_oak", GoodEndingBlockSetTypes.MUDDY_OAK));
    public static final WoodType CYPRESS = WoodType.register(new WoodType(GoodEnding.MODID + ":cypress", GoodEndingBlockSetTypes.CYPRESS));

    @OnlyIn(Dist.CLIENT)
    public static void init() {
        Sheets.addWoodType(MUDDY_OAK);
        Sheets.addWoodType(CYPRESS);
    }

}
