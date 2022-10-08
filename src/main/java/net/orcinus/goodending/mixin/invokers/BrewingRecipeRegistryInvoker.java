package net.orcinus.goodending.mixin.invokers;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PotionBrewing.class)
public interface BrewingRecipeRegistryInvoker {
    @Invoker
    static void callAddMix(Potion input, Item item, Potion output) {
        throw new UnsupportedOperationException();
    }
}
