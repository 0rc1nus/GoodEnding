package net.orcinus.goodending.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;

@Environment(EnvType.CLIENT)
public class MarshEffectRenderer extends RenderLayer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final ResourceLocation TEXTURE_BREWING = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing_overlay.png");
    private static final ResourceLocation TEXTURE_READY = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_ready_overlay.png");

    public MarshEffectRenderer(RenderLayerParent<MarshEntity, MarshEntityModel<MarshEntity>> context) {
        super(context);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int i, MarshEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getStoredPotion() != Potions.EMPTY) {
            int color = PotionUtils.getColor(entity.getStoredPotion());
            int d = ((color >> 16 & 0xFF) / 100);
            int e = ((color >> 8 & 0xFF) / 100);
            int f = ((color & 0xFF) / 100);
            int brewingTicks = entity.getBrewingTicks();
            ResourceLocation texture = brewingTicks == 0 ? TEXTURE_READY : TEXTURE_BREWING;
            VertexConsumer vertexConsumer = multiBufferSource.getBuffer(brewingTicks == 0 ? RenderType.entityCutoutNoCull(texture) : RenderType.eyes(texture));
            this.getParentModel().renderToBuffer(poseStack, vertexConsumer, i, LivingEntityRenderer.getOverlayCoords(entity, 0.0f), d, e, f, 1.0f);
        }
    }

}
