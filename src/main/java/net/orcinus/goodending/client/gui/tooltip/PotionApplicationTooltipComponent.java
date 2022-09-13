package net.orcinus.goodending.client.gui.tooltip;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.screen.ingame.AbstractInventoryScreen;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;

@Environment(EnvType.CLIENT)
public class PotionApplicationTooltipComponent implements TooltipComponent {
    private final PotionApplicationTooltipData data;

    public PotionApplicationTooltipComponent(PotionApplicationTooltipData data) {
        this.data = data;
    }

    @Override
    public int getHeight() {
        return 15;
    }

    @Override
    public int getWidth(TextRenderer textRenderer) {
        return 15;
    }

    /**
     * I want to fucking hang myself
     *
     * @author 0rc1nus
     */
    @Override
    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
        StatusEffectSpriteManager statusEffectSpriteManager = MinecraftClient.getInstance().getStatusEffectSpriteManager();
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        for (StatusEffectInstance statusEffectInstance : this.data.getPotion().getEffects()) {
            StatusEffect statusEffect = statusEffectInstance.getEffectType();
            Sprite sprite = statusEffectSpriteManager.getSprite(statusEffect);

            RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            AbstractInventoryScreen.drawSprite(matrices, x, y - 1, z, 12, 12, sprite);

        }
    }
}
