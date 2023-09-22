package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.WoodPeckerEntityModel;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class WoodpeckerRenderer extends MobRenderer<WoodpeckerEntity, WoodPeckerEntityModel> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoodEnding.MODID, "textures/entity/woodpecker/woodpecker.png");

    public WoodpeckerRenderer(EntityRendererProvider.Context context) {
        super(context, new WoodPeckerEntityModel(context.bakeLayer(GoodEndingModelLayers.WOODPECKER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(WoodpeckerEntity entity) {
        return TEXTURE;
    }
}
