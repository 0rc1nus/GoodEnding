package net.orcinus.goodending.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
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
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin  {

    @Inject(at = @At("HEAD"), method = "inventoryTick")
    private void GE$inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected, CallbackInfo ci) {
        if (stack.getItem() instanceof AxeItem || stack.getItem() instanceof SwordItem || stack.getItem() instanceof ShieldItem) {
            if (stack.getNbt() != null && stack.getNbt().contains("Amount") && stack.getNbt().getInt("Amount") == 0) {
                stack.getNbt().remove("Amount");
            }
            if (!stack.getNbt().contains("Amount") && stack.getNbt().contains("Potion")) {
                stack.getNbt().remove("Potion");
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "appendTooltip")
    private void GE$appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        Item $this = (Item) (Object) this;
        if ($this instanceof SwordItem || $this instanceof AxeItem) {
            NbtCompound nbt = stack.getNbt();
            if (nbt != null) {
                String toolEffect = nbt.getString("Potion");
                Potion potion = PotionUtil.getPotion(nbt);
                if (toolEffect != null) {
                    potion.getEffects().forEach(statusEffectInstance -> {
                        MutableText mutableText = Text.translatable("item.goodending.inflict_tool.status_effect").append(" ");
                        tooltip.add(mutableText.append(Text.translatable(statusEffectInstance.getEffectType().getTranslationKey())).formatted(Formatting.GRAY));
                    });
                }
                if (nbt.getInt("Amount") > 0) {
                    tooltip.add(Text.translatable("item.goodending.inflict_count.status_effect").append(" ").append(Text.translatable("" + nbt.getInt("Amount"))).formatted(Formatting.GRAY));
                }
            }
        }
    }

}
