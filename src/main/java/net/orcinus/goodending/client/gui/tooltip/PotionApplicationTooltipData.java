package net.orcinus.goodending.client.gui.tooltip;

import net.minecraft.client.item.TooltipData;
import net.minecraft.potion.Potion;

public class PotionApplicationTooltipData implements TooltipData {
    private final Potion potion;

    public PotionApplicationTooltipData(Potion potion) {
        this.potion = potion;
    }

    public Potion getPotion() {
        return potion;
    }
}
