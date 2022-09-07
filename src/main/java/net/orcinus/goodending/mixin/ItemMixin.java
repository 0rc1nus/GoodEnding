package net.orcinus.goodending.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectUtil;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin  {

    @Inject(at = @At("HEAD"), method = "inventoryTick")
    private void GE$inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        Item $this = (Item) (Object) this;
        if (entity instanceof LivingEntity livingEntity && selected && $this instanceof ToolItem) {
            NbtCompound nbtCompound = stack.getNbt();
            if (nbtCompound != null && nbtCompound.getString("Potion") != null) {
                Potion potion = PotionUtil.getPotion(nbtCompound);
                for (StatusEffectInstance statusEffectInstance : potion.getEffects()) {
                    if (statusEffectInstance.getEffectType().getCategory() == StatusEffectCategory.BENEFICIAL) {
                        livingEntity.addStatusEffect(new StatusEffectInstance(statusEffectInstance.getEffectType(), 240, statusEffectInstance.getAmplifier(), statusEffectInstance.isAmbient(), false, false));
                        if (statusEffectInstance.getDuration() == 0) {
                            nbtCompound.remove("Potion");
                        }
                        break;
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "appendTooltip")
    private void GE$appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        Item $this = (Item) (Object) this;
        if ($this instanceof ToolItem || $this instanceof ArmorItem) {
            NbtCompound nbt = stack.getNbt();
            if (nbt != null) {
                String toolEffect = nbt.getString("Potion");
                Potion potion = PotionUtil.getPotion(nbt);
                if (toolEffect != null) {
                    potion.getEffects().forEach(statusEffectInstance -> {
                        tooltip.add(Text.translatable("item.goodending.effect_tool.status_effect").append(" ").append(Text.translatable(statusEffectInstance.getEffectType().getTranslationKey())).formatted(Formatting.GRAY));
                    });
                }
            }
        }
    }

}
