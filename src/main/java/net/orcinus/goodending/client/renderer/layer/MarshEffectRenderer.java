package net.orcinus.goodending.client.renderer.layer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;

@Environment(EnvType.CLIENT)
public class MarshEffectRenderer extends FeatureRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final Identifier TEXTURE_BREWING = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing_overlay.png");
    private static final Identifier TEXTURE_READY = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_ready_overlay.png");

    public MarshEffectRenderer(FeatureRendererContext<MarshEntity, MarshEntityModel<MarshEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MarshEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (entity.getStoredPotion() != Potions.EMPTY) {
            int color = PotionUtil.getColor(entity.getStoredPotion());
            int d = ((color >> 16 & 0xFF) / 100);
            int e = ((color >> 8 & 0xFF) / 100);
            int f = ((color & 0xFF) / 100);
            int brewingTicks = entity.getBrewingTicks();
            Identifier texture = brewingTicks == 0 ? TEXTURE_READY : TEXTURE_BREWING;
            VertexConsumer vertexConsumer = vertexConsumers.getBuffer(brewingTicks == 0 ? RenderLayer.getEntityCutoutNoCull(texture) : RenderLayer.getEyes(texture));
            this.getContextModel().render(matrices, vertexConsumer, light, LivingEntityRenderer.getOverlay(entity, 0.0f), d, e, f, 1.0f);
        }
    }

}
