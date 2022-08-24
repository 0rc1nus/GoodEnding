package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.util.Util;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    //TODO: CHANGE THE CURRENT EFFECT TO A WEAKER EFFECT ONCE A HARMFUL EFFECT IS ADDED
    @Inject(at = @At("HEAD"), method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", cancellable = true)
    private void GE$addStatusEffect(StatusEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity $this = (LivingEntity) (Object) this;
        List<StatusEffect> list = Util.make(Lists.newArrayList(), statusEffects -> {
            statusEffects.add(GoodEndingStatusEffects.STRONG_IMMUNITY);
            statusEffects.add(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY);
            statusEffects.add(GoodEndingStatusEffects.SHATTERED_IMMUNITY);
        });
        if (effect.getEffectType().getCategory() == StatusEffectCategory.HARMFUL && $this.hasStatusEffect(GoodEndingStatusEffects.STRONG_IMMUNITY)) {
            cir.setReturnValue(false);
        }
        if (effect.getEffectType() == GoodEndingStatusEffects.STRONG_IMMUNITY) {
            $this.getStatusEffects().stream().filter(instance -> instance.getEffectType().getCategory() == StatusEffectCategory.HARMFUL).toList().forEach(statusEffectInstance -> {
                $this.removeStatusEffect(statusEffectInstance.getEffectType());
                $this.addStatusEffect(effect);
                cir.setReturnValue(true);
            });
        }
    }

}
