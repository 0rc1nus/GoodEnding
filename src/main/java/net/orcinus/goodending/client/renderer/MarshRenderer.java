package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class MarshRenderer extends MobEntityRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final Identifier TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh.png");
    private static final Identifier BURP_TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_open.png");

    public MarshRenderer(EntityRendererFactory.Context context) {
        super(context, new MarshEntityModel<>(context.getPart(GoodEndingModelLayers.MARSH)), 0.5F);
        //this.addFeature(new MarshEffectRenderer(this));
    }

    @Override
    protected void setupTransforms(MarshEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta) {
        super.setupTransforms(entity, matrices, animationProgress, bodyYaw, tickDelta);
        if (entity.isTouchingWater()) {
            matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(180.0F));
            matrices.multiply(Vec3f.NEGATIVE_X.getDegreesQuaternion(90.0F));
            matrices.translate(0.0D, -0.6D, -0.1D);
        }
    }

    @Override
    public Identifier getTexture(MarshEntity entity) {
        return entity.getStoredPotion() != Potions.EMPTY ? BURP_TEXTURE : TEXTURE;
    }
}
