package net.orcinus.goodending.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ImmunityEffect extends MobEffect {

    public ImmunityEffect() {
        super(MobEffectCategory.BENEFICIAL, 0xFFFFFFFF);
    }

}
