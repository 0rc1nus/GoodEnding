package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.effects.ImmunityEffect;

import java.util.Map;

public class GoodEndingStatusEffects {
    public static final Map<ResourceLocation, MobEffect> STATUS_EFFECTS = Maps.newLinkedHashMap();

    public static final MobEffect STRONG_IMMUNITY = registerStatusEffect("strong_immunity", new ImmunityEffect());
    public static final MobEffect CONTEMPORARY_IMMUNITY = registerStatusEffect("contemporary_immunity", new ImmunityEffect());
    public static final MobEffect SHATTERED_IMMUNITY = registerStatusEffect("shattered_immunity", new ImmunityEffect());

    public static <S extends MobEffect> S registerStatusEffect(String name, S effect) {
        STATUS_EFFECTS.put(new ResourceLocation(GoodEnding.MODID, name), effect);
        return effect;
    }

    public static void init() {
        for (ResourceLocation id : STATUS_EFFECTS.keySet()) {
            Registry.register(BuiltInRegistries.MOB_EFFECT, id, STATUS_EFFECTS.get(id));
        }
    }

}
