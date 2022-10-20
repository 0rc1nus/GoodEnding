package net.orcinus.goodending.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingItems;

public class GoodEndingCreativeModeTab extends CreativeModeTab {

    public GoodEndingCreativeModeTab() {
        super(GoodEnding.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD.get());
    }

}
