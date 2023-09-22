package net.orcinus.goodending.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {

    @Inject(at = @At("HEAD"), method = "appendHoverText")
    private void GE$appendTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context, CallbackInfo ci) {
        CompoundTag nbt = stack.getTag();
        if (nbt != null) {
            String toolEffect = nbt.getString("Potion");
            Potion potion = PotionUtils.getPotion(nbt);
            if (toolEffect != null) {
                potion.getEffects().forEach(statusEffectInstance -> {
                    MutableComponent mutableText = Component.translatable("item.goodending.retained_tool.status_effect").append(" ");
                    tooltip.add(mutableText.append(Component.translatable(statusEffectInstance.getEffect().getDescriptionId())).withStyle(nbt.getBoolean("Infinite") ? ChatFormatting.LIGHT_PURPLE : ChatFormatting.BLUE));
                });
            }
            if (nbt.getInt("Amount") > 0) {
                tooltip.add(Component.translatable("item.goodending.retained_count.status_effect").append(" ").append(Component.translatable("potion.goodending.level." + nbt.getInt("Amount"))).withStyle(ChatFormatting.GRAY));
            }
        }
    }

}
