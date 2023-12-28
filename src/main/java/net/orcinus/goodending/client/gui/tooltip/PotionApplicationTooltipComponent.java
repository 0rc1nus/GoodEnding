package net.orcinus.goodending.client.gui.tooltip;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.player.LocalPlayer;
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

    @Override
    public void renderImage(Font p_194048_, int x, int y, GuiGraphics guiGraphics) {
        MobEffectTextureManager manager = Minecraft.getInstance().getMobEffectTextures();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;
        for (MobEffectInstance mobEffectInstance : this.data.getPotion().getEffects()) {
            MobEffect mobEffect = mobEffectInstance.getEffect();
            TextureAtlasSprite textureAtlasSprite = manager.get(mobEffect);
            guiGraphics.blit(x, y - 1, 0, 12, 12, textureAtlasSprite);
        }
    }

}
