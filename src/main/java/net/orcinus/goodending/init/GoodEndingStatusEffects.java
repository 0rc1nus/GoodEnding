package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.effects.ImmunityEffect;

import java.util.Map;

public class GoodEndingStatusEffects {
    public static final Map<Identifier, StatusEffect> STATUS_EFFECTS = Maps.newLinkedHashMap();

    public static final StatusEffect STRONG_IMMUNITY = registerStatusEffect("strong_immunity", new ImmunityEffect());
    public static final StatusEffect CONTEMPORARY_IMMUNITY = registerStatusEffect("contemporary_immunity", new ImmunityEffect());
    public static final StatusEffect SHATTERED_IMMUNITY = registerStatusEffect("shattered_immunity", new ImmunityEffect());

    public static <S extends StatusEffect> S registerStatusEffect(String name, S effect) {
        STATUS_EFFECTS.put(new Identifier(GoodEnding.MODID, name), effect);
        return effect;
    }

    public static void init() {
        for (Identifier id : STATUS_EFFECTS.keySet()) {
            Registry.register(Registry.STATUS_EFFECT, id, STATUS_EFFECTS.get(id));
        }
    }

}
