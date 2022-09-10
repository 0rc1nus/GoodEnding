package net.orcinus.goodending.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ShieldItem.class)
public class ShieldItemMixin {

    @Inject(at = @At("HEAD"), method = "appendTooltip")
    private void GE$appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtCompound nbt = stack.getNbt();
        if (nbt != null) {
            String toolEffect = nbt.getString("Potion");
            Potion potion = PotionUtil.getPotion(nbt);
            if (toolEffect != null) {
                potion.getEffects().forEach(statusEffectInstance -> {
                    MutableText mutableText = Text.translatable("item.goodending.retained_tool.status_effect").append(" ");
                    tooltip.add(mutableText.append(Text.translatable(statusEffectInstance.getEffectType().getTranslationKey())).formatted(nbt.getBoolean("Infinite") ? Formatting.LIGHT_PURPLE : Formatting.BLUE));
                });
            }
            if (nbt.getInt("Amount") > 0) {
                tooltip.add(Text.translatable("item.goodending.retained_count.status_effect").append(" ").append(Text.translatable("" + nbt.getInt("Amount"))).formatted(Formatting.GRAY));
            }
        }
    }

}
