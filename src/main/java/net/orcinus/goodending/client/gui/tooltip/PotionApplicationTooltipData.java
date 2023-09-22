package net.orcinus.goodending.client.gui.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.alchemy.Potion;

public class PotionApplicationTooltipData implements TooltipComponent {
    private final Potion potion;

    public PotionApplicationTooltipData(Potion potion) {
        this.potion = potion;
    }

    public Potion getPotion() {
        return potion;
    }
}
