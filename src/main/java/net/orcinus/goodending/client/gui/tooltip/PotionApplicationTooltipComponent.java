package net.orcinus.goodending.client.gui.tooltip;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

@Environment(EnvType.CLIENT)
public class PotionApplicationTooltipComponent implements ClientTooltipComponent {
    private final PotionApplicationTooltipData data;

    public PotionApplicationTooltipComponent(PotionApplicationTooltipData data) {
        this.data = data;
    }

    @Override
    public int getHeight() {
        return 15;
    }

    @Override
    public int getWidth(Font font) {
        return 15;
    }

    @Override
    public void renderImage(Font font, int x, int y, GuiGraphics guiGraphics) {
        MobEffectTextureManager manager = Minecraft.getInstance().getMobEffectTextures();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        for (MobEffectInstance mobEffectInstance : this.data.getPotion().getEffects()) {
            MobEffect mobEffect = mobEffectInstance.getEffect();
            TextureAtlasSprite textureAtlasSprite = manager.get(mobEffect);
            guiGraphics.blit(x, y - 1, 0, 12, 12, textureAtlasSprite);
        }
    }

//    @Override
//    public void drawItems(TextRenderer textRenderer, int x, int y, MatrixStack matrices, ItemRenderer itemRenderer, int z) {
//        StatusEffectSpriteManager statusEffectSpriteManager = MinecraftClient.getInstance().getStatusEffectSpriteManager();
//        ClientPlayerEntity player = MinecraftClient.getInstance().player;
//        if (player == null) return;
//        for (StatusEffectInstance statusEffectInstance : this.data.getPotion().getEffects()) {
//            StatusEffect statusEffect = statusEffectInstance.getEffectType();
//            Sprite sprite = statusEffectSpriteManager.getSprite(statusEffect);
//
//            RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
//            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//            AbstractInventoryScreen.drawSprite(matrices, x, y - 1, z, 12, 12, sprite);
//
//        }
//    }
}
