package net.orcinus.goodending.client.gui.tooltip;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.EffectRenderingInventoryScreen;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
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
    public int getWidth(Font textRenderer) {
        return 15;
    }

    /**
     * I want to fucking hang myself
     *
     * @author 0rc1nus
     */
    @Override
    public void renderImage(Font textRenderer, int x, int y, PoseStack matrices, ItemRenderer itemRenderer, int z) {
        MobEffectTextureManager statusEffectSpriteManager = Minecraft.getInstance().getMobEffectTextures();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        for (MobEffectInstance statusEffectInstance : this.data.getPotion().getEffects()) {
            MobEffect statusEffect = statusEffectInstance.getEffect();
            TextureAtlasSprite sprite = statusEffectSpriteManager.get(statusEffect);

            RenderSystem.setShaderTexture(0, sprite.atlas().getId());
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            EffectRenderingInventoryScreen.blit(matrices, x, y - 1, z, 12, 12, sprite);

        }
    }
}
