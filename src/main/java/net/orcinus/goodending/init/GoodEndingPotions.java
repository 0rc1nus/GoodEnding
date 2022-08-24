package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;

import java.util.Map;

public class GoodEndingPotions {
    public static final Map<Identifier, Potion> POTIONS = Maps.newLinkedHashMap();

    public static final Potion IMMUNITY = registerPotion("immunity", new Potion(new StatusEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY, 3600)));
    public static final Potion LONG_IMMUNITY = registerPotion("long_immunity", new Potion(new StatusEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY, 9600)));

    public static Potion registerPotion(String name, Potion potion) {
        POTIONS.put(new Identifier(GoodEnding.MODID, name), potion);
        return potion;
    }

    public static void init() {
        for (Identifier id : POTIONS.keySet()) {
            Registry.register(Registry.POTION, id, POTIONS.get(id));
        }
    }
}
