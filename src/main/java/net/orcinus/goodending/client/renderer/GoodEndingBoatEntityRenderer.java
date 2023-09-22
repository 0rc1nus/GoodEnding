package net.orcinus.goodending.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;
import org.joml.Quaternionf;

import java.util.Map;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class GoodEndingBoatEntityRenderer extends EntityRenderer<GoodEndingBoatEntity> {
    private final Map<GoodEndingBoatEntity.BoatType, Pair<ResourceLocation, BoatModel>> texturesAndModels;

    public GoodEndingBoatEntityRenderer(EntityRendererProvider.Context ctx, boolean chest) {
        super(ctx);
        this.shadowRadius = 0.8f;
        this.texturesAndModels = Stream.of(GoodEndingBoatEntity.BoatType.values()).collect(ImmutableMap.toImmutableMap(type -> type, type -> Pair.of(new ResourceLocation(GoodEnding.MODID, GoodEndingBoatEntityRenderer.getTexture(type, chest)), this.createModel(ctx, type, chest))));
    }

    private BoatModel createModel(EntityRendererProvider.Context ctx, GoodEndingBoatEntity.BoatType type, boolean chest) {
        ModelLayerLocation entityModelLayer = chest ? GoodEndingModelLayers.createChestBoat(type) : GoodEndingModelLayers.createBoat(type);
        ModelPart modelPart = ctx.bakeLayer(entityModelLayer);
        return chest ? new ChestBoatModel(modelPart) : new BoatModel(modelPart);
    }

    private static String getTexture(GoodEndingBoatEntity.BoatType type, boolean chest) {
        if (chest) {
            return "textures/entity/chest_boat/" + type.getName() + ".png";
        }
        return "textures/entity/boat/" + type.getName() + ".png";
    }

    @Override
    public void render(GoodEndingBoatEntity boatEntity, float f, float g, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int i) {
        matrixStack.pushPose();
        matrixStack.translate(0.0, 0.375, 0.0);
        matrixStack.mulPose(Axis.YP.rotationDegrees(180.0f - f));
        float h = (float)boatEntity.getHurtTime() - g;
        float j = boatEntity.getDamage() - g;
        if (j < 0.0f) {
            j = 0.0f;
        }
        if (h > 0.0f) {
            matrixStack.mulPose(Axis.XP.rotationDegrees(Mth.sin(h) * h * j / 10.0f * (float)boatEntity.getHurtDir()));
        }
        if (!Mth.equal(boatEntity.getBubbleAngle(g), 0.0f)) {
            matrixStack.mulPose(new Quaternionf().setAngleAxis(boatEntity.getBubbleAngle(g) * ((float)Math.PI / 180), 1.0f, 0.0f, 1.0f));
        }
        Pair<ResourceLocation, BoatModel> pair = this.texturesAndModels.get(boatEntity.getGoodEndingBoatType());
        ResourceLocation identifier = pair.getFirst();
        BoatModel boatEntityModel = pair.getSecond();
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.mulPose(Axis.YP.rotationDegrees(90.0f));
        boatEntityModel.setupAnim(boatEntity, g, 0.0f, -0.1f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(boatEntityModel.renderType(identifier));
        boatEntityModel.renderToBuffer(matrixStack, vertexConsumer, i, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
        if (!boatEntity.isUnderWater()) {
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderType.waterMask());
            boatEntityModel.waterPatch().render(matrixStack, vertexConsumer2, i, OverlayTexture.NO_OVERLAY);
        }
        matrixStack.popPose();
        super.render(boatEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public ResourceLocation getTextureLocation(GoodEndingBoatEntity entity) {
        return this.texturesAndModels.get(entity.getGoodEndingBoatType()).getFirst();
    }
}
