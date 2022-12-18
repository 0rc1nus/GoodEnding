package net.orcinus.goodending.events;

import com.google.common.collect.Lists;
import net.minecraft.Util;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingStatusEffects;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {
    //Fucking dumb forge making me convert every single thing to suppliers
    private static final LinkedList<Supplier<MobEffect>> IMMUNITY_HIERARCHY = Util.make(Lists.newLinkedList(), list -> {
        list.add(GoodEndingStatusEffects.STRONG_IMMUNITY);
        list.add(GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY);
        list.add(GoodEndingStatusEffects.SHATTERED_IMMUNITY);
    });

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        event.put(GoodEndingEntityTypes.MARSH.get(), MarshEntity.createMarshAttributes().build());
        event.put(GoodEndingEntityTypes.FIREFLY_SWARM.get(), FireflyEntity.createFireflyAttributes().build());
        event.put(GoodEndingEntityTypes.WOODPECKER.get(), WoodpeckerEntity.createWoodPeckerAttributes().build());
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        LivingEntity $this = event.getEntity();
        Entity entity = event.getSource().getDirectEntity();
        if (entity instanceof Player player) {
            ItemStack stack = player.getItemInHand($this.getUsedItemHand());
            if (stack.getTag() != null && (stack.getTag().contains("Infinite") || stack.getTag().contains("Potion")) && !(stack.getItem() instanceof PotionItem)) {
                PotionUtils.getPotion(stack.getTag()).getEffects().stream().filter(this::isHarmful).toList().forEach(statusEffectInstance -> {
                    if (stack.getTag().getInt("Amount") > 0) {
                        stack.getTag().putInt("Amount", stack.getTag().getInt("Amount") - 1);
                    }
                    $this.addEffect(new MobEffectInstance(statusEffectInstance.getEffect(), 200, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.isVisible(), statusEffectInstance.showIcon()));
                });
            }
        }
    }

    @SubscribeEvent
    public void onShieldBlock(ShieldBlockEvent event) {
        LivingEntity $this = event.getEntity();
        CompoundTag nbt = $this.getUseItem().getTag();
        if (nbt != null && (nbt.getBoolean("Infinite") || nbt.getInt("Amount") > 0) && nbt.contains("Potion") && $this.isBlocking()) {
            Potion potion = PotionUtils.getPotion($this.getUseItem());
            potion.getEffects().stream().filter(this::isBeneficial).toList().forEach(statusEffectInstance -> {
                if ($this.getUseItem().getTag().getInt("Amount") > 0) {
                    nbt.putInt("Amount", nbt.getInt("Amount") - 1);
                }
                MobEffect effectType = statusEffectInstance.getEffect();
                if (effectType == GoodEndingStatusEffects.STRONG_IMMUNITY.get() || effectType == GoodEndingStatusEffects.CONTEMPORARY_IMMUNITY.get()) {
                    effectType = GoodEndingStatusEffects.SHATTERED_IMMUNITY.get();
                }
                $this.addEffect(new MobEffectInstance(effectType, 200, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), statusEffectInstance.isVisible(), statusEffectInstance.showIcon()));
            });
        }
    }

    private boolean isBeneficial(MobEffectInstance instance) {
        return this.getCategory(instance) == MobEffectCategory.BENEFICIAL;
    }

    private boolean isHarmful(MobEffectInstance instance) {
        return this.getCategory(instance) == MobEffectCategory.HARMFUL;
    }

    @NotNull
    private MobEffectCategory getCategory(MobEffectInstance instance) {
        return instance.getEffect().getCategory();
    }

}
