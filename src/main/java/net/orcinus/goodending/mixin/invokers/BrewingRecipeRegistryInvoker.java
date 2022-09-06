package net.orcinus.goodending.mixin.invokers;

import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryInvoker {
    @Invoker
    static void callRegisterPotionRecipe(Potion input, Item item, Potion output) {
        throw new UnsupportedOperationException();
    }
}
