package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
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
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.LinkedList;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

//    @Shadow protected ItemStack useItem;
//    private static final LinkedList<MobEffect> IMMUNITY_HIERARCHY = Util.make(Lists.newLinkedList(), list -> {
//        list.add(GoodEndingStatusEffects.STRONG_IMMUNITY.get());
//        list.add(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY.get());
//        list.add(GoodEndingStatusEffects.SHATTERED_IMMUNITY.get());
//    });
//
//    @Inject(at = @At("HEAD"), method = "actuallyHurt")
//    private void GE$applyDamage(DamageSource source, float amount, CallbackInfo ci) {
//        LivingEntity $this = (LivingEntity) (Object) this;
//        Entity entity = source.getDirectEntity();
//        if (entity instanceof Player player) {
//            ItemStack stack = player.getItemInHand($this.getUsedItemHand());
//            if (stack.getTag() != null && (stack.getTag().contains("Infinite") || stack.getTag().contains("Potion")) && !(stack.getItem() instanceof PotionItem)) {
//                PotionUtils .getPotion(stack.getTag()).getEffects().stream().filter(statusEffectInstance -> statusEffectInstance.getEffect().getCategory() == MobEffectCategory.HARMFUL).toList().forEach(statusEffectInstance -> {
//                    if (stack.getTag().getInt("Amount") > 0) {
//                        stack.getTag().putInt("Amount", stack.getTag().getInt("Amount") - 1);
//                    }
//                    $this.addEffect(new MobEffectInstance(statusEffectInstance.getEffect(), 200, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.isVisible(), statusEffectInstance.showIcon()));
//                });
//            }
//        }
//    }
//
//    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraftforge/event/entity/living/ShieldBlockEvent;shieldTakesDamage()Z"), method = "hurt")
//    private void GE$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
//        LivingEntity $this = (LivingEntity) (Object) this;
//        CompoundTag nbt = this.useItem.getTag();
//        if (nbt != null && (nbt.getBoolean("Infinite") || nbt.getInt("Amount") > 0) && nbt.contains("Potion") && $this.isBlocking()) {
//            Potion potion = PotionUtils.getPotion(this.useItem);
//            potion.getEffects().stream().filter(statusEffectInstance -> statusEffectInstance.getEffect().getCategory() == MobEffectCategory.BENEFICIAL).toList().forEach(statusEffectInstance -> {
//                if (this.useItem.getTag().getInt("Amount") > 0) {
//                    nbt.putInt("Amount", nbt.getInt("Amount") - 1);
//                }
//                MobEffect effectType = statusEffectInstance.getEffect();
//                if (effectType == GoodEndingStatusEffects.STRONG_IMMUNITY.get() || effectType == GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY.get()) {
//                    effectType = GoodEndingStatusEffects.SHATTERED_IMMUNITY.get();
//                }
//                $this.addEffect(new MobEffectInstance(effectType, 200, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.isVisible(), statusEffectInstance.showIcon()));
//            });
//        }
//    }
//
//    @Inject(at = @At("HEAD"), method = "addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z", cancellable = true)
//    private void GE$addStatusEffect(MobEffectInstance effect, Entity source, CallbackInfoReturnable<Boolean> cir) {
//        LivingEntity $this = (LivingEntity) (Object) this;
//        MobEffect finalResult = this.hierarchyResult($this, null);
//        if (effect.getEffect().getCategory() == MobEffectCategory.HARMFUL && this.containsImmunity($this)) {
//            if (finalResult != null) {
//                IMMUNITY_HIERARCHY.stream().filter($this::hasEffect).toList().forEach(hierarchy -> {
//                    MobEffectInstance instance = $this.getEffect(hierarchy);
//                    $this.removeEffect(hierarchy);
//                    if (instance != null) {
//                        $this.addEffect(new MobEffectInstance(finalResult, instance.getDuration(), instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()));
//                    }
//                });
//            } else {
//                IMMUNITY_HIERARCHY.stream().filter($this::hasEffect).toList().forEach($this::removeEffect);
//            }
//            cir.setReturnValue(false);
//        }
//        if (effect.getEffect() == GoodEndingStatusEffects.STRONG_IMMUNITY.get()) {
//            if (!$this.hasEffect(GoodEndingStatusEffects.STRONG_IMMUNITY.get())) {
//                $this.playSound(SoundEvents.BEACON_ACTIVATE, 1.0F, 1.0F);
//            }
//            if (this.containsImmunity($this)) {
//                cir.setReturnValue(false);
//            }
//            $this.getActiveEffects().stream().filter(instance -> instance.getEffect().getCategory() == MobEffectCategory.HARMFUL).toList().forEach(statusEffectInstance -> {
//                $this.removeEffect(statusEffectInstance.getEffect());
//                $this.addEffect(new MobEffectInstance(this.hierarchyResult($this, effect.getEffect()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
//                cir.setReturnValue(true);
//            });
//        }
//    }
//
//    private MobEffect hierarchyResult(LivingEntity $this, MobEffect finalResult) {
//        for (int i = 0; i < IMMUNITY_HIERARCHY.size() - 1; i++) {
//            MobEffect statusEffect = IMMUNITY_HIERARCHY.get(i);
//            if ($this.hasEffect(statusEffect)) {
//                finalResult = IMMUNITY_HIERARCHY.get(i + 1);
//                break;
//            }
//        }
//        return finalResult;
//    }
//
//    private boolean containsImmunity(LivingEntity livingEntity) {
//        return livingEntity.hasEffect(GoodEndingStatusEffects.STRONG_IMMUNITY.get()) || livingEntity.hasEffect(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY.get()) || livingEntity.hasEffect(GoodEndingStatusEffects.SHATTERED_IMMUNITY.get());
//    }

}
