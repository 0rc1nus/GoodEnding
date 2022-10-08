package net.orcinus.goodending.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.orcinus.goodending.client.gui.tooltip.PotionApplicationTooltipData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin  {

    @Inject(at = @At("HEAD"), method = "inventoryTick")
    private void GE$inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (stack.getItem() instanceof SwordItem || stack.getItem() instanceof ShieldItem) {
            if (stack.getTag() != null && stack.getTag().contains("Amount") && stack.getTag().getInt("Amount") == 0) {
                stack.getTag().remove("Amount");
            }
            if (!stack.getTag().contains("Amount") && !stack.getTag().contains("Infinite") && stack.getTag().contains("Potion")) {
                stack.getTag().remove("Potion");
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "appendHoverText")
    private void GE$appendTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context, CallbackInfo ci) {
        Item $this = (Item) (Object) this;
        if ($this instanceof SwordItem) {
            CompoundTag nbt = stack.getTag();
            if (nbt != null) {
                Potion potion = PotionUtils.getPotion(nbt);
                potion.getEffects().forEach(statusEffectInstance -> {
                    MutableComponent mutableText = Component.translatable("item.goodending.inflict_tool.status_effect").append(" ");
                    tooltip.add(mutableText.append(Component.translatable(statusEffectInstance.getEffect().getDescriptionId())).withStyle(nbt.getBoolean("Infinite") ? ChatFormatting.LIGHT_PURPLE : ChatFormatting.RED));
                });
                if (nbt.getInt("Amount") > 0) {
                    tooltip.add(Component.translatable("item.goodending.inflict_count.status_effect").append(" ").append(Component.translatable("potion.goodending.level." + nbt.getInt("Amount"))).withStyle(ChatFormatting.GRAY));
                }
            }
        }
    }

    @Inject(at = @At("TAIL"), method = "getTooltipImage", cancellable = true)
    private void GE$getTooltipData(ItemStack stack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        Item $this = (Item) (Object) this;
        if ($this instanceof ShieldItem || $this instanceof SwordItem) {
            CompoundTag nbt = stack.getTag();
            if (nbt != null) {
                Potion potion = PotionUtils.getPotion(nbt);
                cir.setReturnValue(potion.getEffects().isEmpty() ? Optional.empty() : Optional.of(new PotionApplicationTooltipData(potion)));
            }
        }
    }

}
