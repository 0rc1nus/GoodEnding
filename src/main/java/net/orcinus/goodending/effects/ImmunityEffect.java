package net.orcinus.goodending.effects;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ImmunityEffect extends StatusEffect {

    public ImmunityEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFFFFFFFF);
    }

}
