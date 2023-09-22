package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.mixin.invokers.BrewingRecipeRegistryInvoker;

import java.util.Map;

public class GoodEndingPotions {
    public static final Map<ResourceLocation, Potion> POTIONS = Maps.newLinkedHashMap();

    public static final Potion IMMUNITY = registerPotion("immunity", new Potion(new MobEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY, 3600)));
    public static final Potion LONG_IMMUNITY = registerPotion("long_immunity", new Potion(new MobEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY, 9600)));

    public static Potion registerPotion(String name, Potion potion) {
        POTIONS.put(new ResourceLocation(GoodEnding.MODID, name), potion);
        return potion;
    }

    public static void init() {
        for (ResourceLocation id : POTIONS.keySet()) {
            Registry.register(BuiltInRegistries.POTION, id, POTIONS.get(id));
        }

        BrewingRecipeRegistryInvoker.callAddMix(Potions.AWKWARD, GoodEndingBlocks.BIRCH_MUSHROOM.asItem(), IMMUNITY);
        BrewingRecipeRegistryInvoker.callAddMix(IMMUNITY, Items.REDSTONE, LONG_IMMUNITY);
    }
}
    