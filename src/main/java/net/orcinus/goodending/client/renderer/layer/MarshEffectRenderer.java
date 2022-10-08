package net.orcinus.goodending.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;

@OnlyIn(Dist.CLIENT)
public class MarshEffectRenderer extends RenderLayer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final ResourceLocation TEXTURE_BREWING = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing_overlay.png");
    private static final ResourceLocation TEXTURE_READY = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_ready_overlay.png");

    public MarshEffectRenderer(RenderLayerParent<MarshEntity, MarshEntityModel<MarshEntity>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, MarshEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getStoredPotion() != Potions.EMPTY) {
            int color = PotionUtils.getColor(entity.getStoredPotion());
            int d = ((color >> 16 & 0xFF) / 100);
            int e = ((color >> 8 & 0xFF) / 100);
            int f = ((color & 0xFF) / 100);
            if (entity.brewingTicks < 0) renderColoredCutoutModel(this.getParentModel(), TEXTURE_READY, matrices, vertexConsumers, light, entity, d, e, f);
            else renderColoredCutoutModel(this.getParentModel(), TEXTURE_BREWING, matrices, vertexConsumers, light, entity, d, e, f);
        }
    }
}
