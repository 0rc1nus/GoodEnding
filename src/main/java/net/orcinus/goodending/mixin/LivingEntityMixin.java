package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.orcinus.goodending.criterion.GoodEndingCriterion;
import net.orcinus.goodending.init.GoodEndingCriteriaTriggers;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;
import java.util.function.Supplier;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    private static final LinkedList<Supplier<MobEffect>> IMMUNITY_HIERARCHY = Util.make(Lists.newLinkedList(), list -> {
        list.add(GoodEndingStatusEffects.STRONG_IMMUNITY);
        list.add(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY);
        list.add(GoodEndingStatusEffects.SHATTERED_IMMUNITY);
    });

    @Inject(at = @At("HEAD"), method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", cancellable = true)
    private void GE$addStatusEffect(MobEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity $this = (LivingEntity) (Object) this;
        Supplier<MobEffect> finalResult = this.hierarchyResult($this, null);
        if (this.isHarmful(effect) && this.containsImmunity($this)) {
            if (finalResult != null) {
                IMMUNITY_HIERARCHY.stream().map(Supplier::get).filter($this::hasEffect).toList().forEach(hierarchy -> {
                    MobEffectInstance instance = $this.getEffect(hierarchy);
                    $this.removeEffect(hierarchy);
                    if (instance != null) {
                        if ($this instanceof ServerPlayer serverPlayer) {
                            GoodEndingCriteriaTriggers.IMMUNITY.trigger(serverPlayer);
                        }
                        $this.addEffect(new MobEffectInstance(finalResult.get(), instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()));
                    }
                });
            } else {
                IMMUNITY_HIERARCHY.stream().map(Supplier::get).filter($this::hasEffect).toList().forEach($this::removeEffect);
            }
            cir.setReturnValue(false);
        }
        if (effect.getEffect() == GoodEndingStatusEffects.STRONG_IMMUNITY.get()) {
            if (this.containsImmunity($this)) {
                cir.setReturnValue(false);
            }
            $this.getActiveEffects().stream().filter(this::isHarmful).toList().forEach(statusEffectInstance -> {
                $this.removeEffect(statusEffectInstance.getEffect());
                $this.addEffect(new MobEffectInstance(this.hierarchyResult($this, effect::getEffect).get(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
                if ($this instanceof ServerPlayer serverPlayer) {
                    GoodEndingCriteriaTriggers.IMMUNITY.trigger(serverPlayer);
                }
                cir.setReturnValue(true);
            });
        }
    }

    private boolean isHarmful(MobEffectInstance instance) {
        return instance.getEffect().getCategory() == MobEffectCategory.HARMFUL;
    }

    private Supplier<MobEffect> hierarchyResult(LivingEntity $this, Supplier<MobEffect> finalResult) {
        for (int i = 0; i < IMMUNITY_HIERARCHY.size() - 1; i++) {
            Supplier<MobEffect> statusEffect = IMMUNITY_HIERARCHY.get(i);
            if ($this.hasEffect(statusEffect.get())) {
                finalResult = IMMUNITY_HIERARCHY.get(i + 1);
                break;
            }
        }
        return finalResult;
    }

    private boolean containsImmunity(LivingEntity livingEntity) {
        return livingEntity.hasEffect(GoodEndingStatusEffects.STRONG_IMMUNITY.get()) || livingEntity.hasEffect(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY.get()) || livingEntity.hasEffect(GoodEndingStatusEffects.SHATTERED_IMMUNITY.get());
    }

}
