package net.orcinus.goodending.client.renderer;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3f;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

import java.util.Map;
import java.util.stream.Stream;

@Environment(EnvType.CLIENT)
public class GoodEndingBoatEntityRenderer extends EntityRenderer<GoodEndingBoatEntity> {
    private final Map<GoodEndingBoatEntity.BoatType, Pair<Identifier, BoatEntityModel>> texturesAndModels;

    public GoodEndingBoatEntityRenderer(EntityRendererFactory.Context ctx, boolean chest) {
        super(ctx);
        this.shadowRadius = 0.8f;
        this.texturesAndModels = Stream.of(GoodEndingBoatEntity.BoatType.values()).collect(ImmutableMap.toImmutableMap(type -> type, type -> Pair.of(new Identifier(GoodEnding.MODID, GoodEndingBoatEntityRenderer.getTexture(type, chest)), this.createModel(ctx, type, chest))));
    }

    private BoatEntityModel createModel(EntityRendererFactory.Context ctx, GoodEndingBoatEntity.BoatType type, boolean chest) {
        EntityModelLayer entityModelLayer = chest ? GoodEndingModelLayers.createChestBoat(type) : GoodEndingModelLayers.createBoat(type);
        return new BoatEntityModel(ctx.getPart(entityModelLayer), chest);
    }

    private static String getTexture(GoodEndingBoatEntity.BoatType type, boolean chest) {
        if (chest) {
            return "textures/entity/chest_boat/" + type.getName() + ".png";
        }
        return "textures/entity/boat/" + type.getName() + ".png";
    }

    @Override
    public void render(GoodEndingBoatEntity boatEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0, 0.375, 0.0);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0f - f));
        float h = (float)boatEntity.getDamageWobbleTicks() - g;
        float j = boatEntity.getDamageWobbleStrength() - g;
        if (j < 0.0f) {
            j = 0.0f;
        }
        if (h > 0.0f) {
            matrixStack.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(MathHelper.sin(h) * h * j / 10.0f * (float)boatEntity.getDamageWobbleSide()));
        }
        if (!MathHelper.approximatelyEquals(boatEntity.interpolateBubbleWobble(g), 0.0f)) {
            matrixStack.multiply(new Quaternion(new Vec3f(1.0f, 0.0f, 1.0f), boatEntity.interpolateBubbleWobble(g), true));
        }
        Pair<Identifier, BoatEntityModel> pair = this.texturesAndModels.get(boatEntity.getGoodEndingBoatType());
        Identifier identifier = pair.getFirst();
        BoatEntityModel boatEntityModel = pair.getSecond();
        matrixStack.scale(-1.0f, -1.0f, 1.0f);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0f));
        boatEntityModel.setAngles(boatEntity, g, 0.0f, -0.1f, 0.0f, 0.0f);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(boatEntityModel.getLayer(identifier));
        boatEntityModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f);
        if (!boatEntity.isSubmergedInWater()) {
            VertexConsumer vertexConsumer2 = vertexConsumerProvider.getBuffer(RenderLayer.getWaterMask());
            boatEntityModel.getWaterPatch().render(matrixStack, vertexConsumer2, i, OverlayTexture.DEFAULT_UV);
        }
        matrixStack.pop();
        super.render(boatEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(GoodEndingBoatEntity boatEntity) {
        return this.texturesAndModels.get(boatEntity.getGoodEndingBoatType()).getFirst();
    }

}
