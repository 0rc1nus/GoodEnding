package net.orcinus.goodending.mixin;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
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

    @Inject(at = @At("HEAD"), method = "appendTooltip")
    private void GE$appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context, CallbackInfo ci) {
        NbtCompound nbt = stack.getNbt();
        Item $this = (Item) (Object) this;
        if ($this instanceof SwordItem && nbt != null) {
            String toolEffect = nbt.getString("Potion");
            Potion potion = PotionUtil.getPotion(nbt);
            if (toolEffect != null) {
                List<StatusEffectInstance> effects = potion.getEffects();
                for (StatusEffectInstance instances : effects) {
                    tooltip.add(Text.translatable("item.goodending.effect_tool.status_effect").append(" ").append(Text.translatable(instances.getEffectType().getTranslationKey())).formatted(Formatting.GRAY));
                }
            }
        }
    }

}
