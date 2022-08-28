package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.WoodPeckerEntityModel;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class WoodpeckerRenderer extends MobEntityRenderer<WoodpeckerEntity, WoodPeckerEntityModel<WoodpeckerEntity>> {
    private static final Identifier TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/woodpecker/woodpecker.png");

    public WoodpeckerRenderer(EntityRendererFactory.Context context) {
        super(context, new WoodPeckerEntityModel<>(context.getPart(GoodEndingModelLayers.WOODPECKER)), 0.25F);
    }

    @Override
    protected boolean isShaking(WoodpeckerEntity entity) {
        return super.isShaking(entity);
    }

    @Override
    protected void setupTransforms(WoodpeckerEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        super.setupTransforms(entity, matrices, animationProgress, bodyYaw, tickDelta);
    }

    @Override
    public Identifier getTexture(WoodpeckerEntity entity) {
        return TEXTURE;
    }
}
