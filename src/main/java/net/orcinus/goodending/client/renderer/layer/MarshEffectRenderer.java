package net.orcinus.goodending.client.renderer.layer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;

@Environment(EnvType.CLIENT)
public class MarshEffectRenderer extends FeatureRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final Identifier EFFECT_OVERLAY = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_effect_overlay.png");

    public MarshEffectRenderer(FeatureRendererContext<MarshEntity, MarshEntityModel<MarshEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, MarshEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        int color = PotionUtil.getColor(entity.getStoredPotion());
        renderModel(this.getContextModel(), EFFECT_OVERLAY, matrices, vertexConsumers, light, entity, color, color, color);
    }
}
