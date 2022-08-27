package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import net.orcinus.goodending.util.EffectIntake;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    private static final LinkedList<StatusEffect> IMMUNITY_HIERARCHY = Util.make(Lists.newLinkedList(), list -> {
        list.add(GoodEndingStatusEffects.STRONG_IMMUNITY);
        list.add(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY);
        list.add(GoodEndingStatusEffects.SHATTERED_IMMUNITY);
    });
    //ARMOR : KEEP
    //SWORD / BOW / TRIDENT : EMIT

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void GE$tick(CallbackInfo ci) {
        LivingEntity $this = (LivingEntity) (Object) this;
        NbtCompound nbt = $this.getStackInHand($this.getActiveHand()).getNbt();
        EffectIntake effectIntake = EffectIntake.KEEP;
        for (TagKey<Item> tagKey : effectIntake.getItemTagKey()) {
            if (!$this.getStackInHand($this.getActiveHand()).isIn(tagKey)) {
                continue;
            }
            if (nbt != null && nbt.contains("Potion")) {
                Potion potion = PotionUtil.getPotion(nbt);
                List<StatusEffectInstance> effects = potion.getEffects();
                effects.forEach($this::addStatusEffect);
            }
        }

    }

    @Inject(at = @At("HEAD"), method = "applyDamage")
    private void GE$damage(DamageSource source, float amount, CallbackInfo ci) {
        LivingEntity $this = (LivingEntity) (Object) this;
        Entity entity = source.getSource();
        if (entity instanceof PlayerEntity player) {
            ItemStack stack = player.getStackInHand($this.getActiveHand());
            EffectIntake effectIntake = EffectIntake.EMIT;
            for (TagKey<Item> tagKey : effectIntake.getItemTagKey()) {
                if (stack.isIn(tagKey) && stack.getNbt() != null && stack.getNbt().contains("Potion")) {
                    PotionUtil.getPotion(stack.getNbt()).getEffects().forEach($this::addStatusEffect);
                    stack.getNbt().remove("Potion");
                    break;
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z", cancellable = true)
    private void GE$addStatusEffect(StatusEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity $this = (LivingEntity) (Object) this;
        StatusEffect finalResult = this.hierarchyResult($this, null);
        if (effect.getEffectType().getCategory() == StatusEffectCategory.HARMFUL && this.containsImmunity($this)) {
            if (finalResult != null) {
                IMMUNITY_HIERARCHY.stream().filter($this::hasStatusEffect).toList().forEach(hierarchy -> {
                    StatusEffectInstance instance = $this.getStatusEffect(hierarchy);
                    $this.removeStatusEffect(hierarchy);
                    if (instance != null) {
                        $this.addStatusEffect(new StatusEffectInstance(finalResult, instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.shouldShowParticles(), instance.shouldShowIcon()));
                    }
                });
            } else {
                IMMUNITY_HIERARCHY.stream().filter($this::hasStatusEffect).toList().forEach($this::removeStatusEffect);
            }
            cir.setReturnValue(false);
        }
        if (effect.getEffectType() == GoodEndingStatusEffects.STRONG_IMMUNITY) {
            if (!$this.hasStatusEffect(GoodEndingStatusEffects.STRONG_IMMUNITY)) {
                $this.playSound(SoundEvents.BLOCK_BEACON_ACTIVATE, 1.0F, 1.0F);
            }
            if (this.containsImmunity($this)) {
                cir.setReturnValue(false);
            }
            $this.getStatusEffects().stream().filter(instance -> instance.getEffectType().getCategory() == StatusEffectCategory.HARMFUL).toList().forEach(statusEffectInstance -> {
                $this.removeStatusEffect(statusEffectInstance.getEffectType());
                $this.addStatusEffect(new StatusEffectInstance(this.hierarchyResult($this, effect.getEffectType()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.shouldShowParticles(), effect.shouldShowIcon()));
                cir.setReturnValue(true);
            });
        }
    }

    private StatusEffect hierarchyResult(LivingEntity $this, StatusEffect finalResult) {
        for (int i = 0; i < IMMUNITY_HIERARCHY.size() - 1; i++) {
            StatusEffect statusEffect = IMMUNITY_HIERARCHY.get(i);
            if ($this.hasStatusEffect(statusEffect)) {
                finalResult = IMMUNITY_HIERARCHY.get(i + 1);
                break;
            }
        }
        return finalResult;
    }

    private boolean containsImmunity(LivingEntity livingEntity) {
        return livingEntity.hasStatusEffect(GoodEndingStatusEffects.STRONG_IMMUNITY) || livingEntity.hasStatusEffect(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY) || livingEntity.hasStatusEffect(GoodEndingStatusEffects.SHATTERED_IMMUNITY);
    }

}
