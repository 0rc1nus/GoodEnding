package net.orcinus.goodending.client.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.orcinus.goodending.client.animations.MarshAnimations;
import net.orcinus.goodending.entities.MarshEntity;

@Environment(EnvType.CLIENT)
public class MarshEntityModel<T extends MarshEntity> extends SinglePartEntityModel<T> {
    private final ModelPart root;
    private final ModelPart body;

    public MarshEntityModel(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
    }

    public static TexturedModelData createBodyLayer() {
        ModelData meshdefinition = new ModelData();
        ModelPartData partdefinition = meshdefinition.getRoot();

        ModelPartData body = partdefinition.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -10.5F, -4.0F, 12.0F, 13.0F, 8.0F, new Dilation(0.0F)).uv(32, 0).cuboid(-3.0F, -7.5F, -6.0F, 6.0F, 5.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.5F, 0.0F));

        ModelPartData leftEar = body.addChild("leftEar", ModelPartBuilder.create().uv(0, 27).cuboid(0.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(6.0F, -6.0F, 0.0F, 0.0F, -0.6109F, 0.0F));

        ModelPartData rightEar = body.addChild("rightEar", ModelPartBuilder.create().uv(0, 27).mirrored().cuboid(-5.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(-6.0F, -6.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

        ModelPartData hair = body.addChild("hair", ModelPartBuilder.create().uv(0, 21).cuboid(-6.0F, -3.0F, 0.0F, 12.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -10.5F, 0.0F, 0.0F, -0.3927F, 0.0F));

        ModelPartData hair2 = body.addChild("hair2", ModelPartBuilder.create().uv(0, 24).cuboid(-6.0F, -3.0F, 0.0F, 12.0F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -10.5F, 0.0F, 0.0F, 0.3927F, 0.0F));

        ModelPartData leftLeg = body.addChild("leftLeg", ModelPartBuilder.create().uv(40, 7).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 2.5F, 0.0F));

        ModelPartData rightLeg = body.addChild("rightLeg", ModelPartBuilder.create().uv(40, 7).mirrored().cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(3.0F, 2.5F, 0.0F));

        return TexturedModelData.of(meshdefinition, 64, 32);
    }

    @Override
    public void setAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.updateAnimation(entity.idlingAnimationState, MarshAnimations.MARSH_IDLE, ageInTicks);
        this.updateAnimation(entity.walkingAnimationState, MarshAnimations.MARSH_WALK, ageInTicks);
    }

    @Override
    public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }
}
