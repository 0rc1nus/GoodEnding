package net.orcinus.goodending.client.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.orcinus.goodending.entities.MarshEntity;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.*;
import static net.minecraft.util.math.MathHelper.*;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class MarshEntityModel<T extends MarshEntity> extends SinglePartEntityModel<T> {
    public static final String HAIR = "hair";
    public static final String HAIR2 = "hair2";

    private final ModelPart root;
    private final ModelPart body;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;
    private final ModelPart leftEar;
    private final ModelPart rightEar;
    private final ModelPart hair;
    private final ModelPart hair2;

    public MarshEntityModel(ModelPart root) {
        this.root = root;
        
        this.body = root.getChild(BODY);

        this.leftLeg = body.getChild(LEFT_LEG);
        this.rightLeg = body.getChild(RIGHT_LEG);
        this.leftEar = body.getChild(LEFT_EAR);
        this.rightEar = body.getChild(RIGHT_EAR);
        this.hair = body.getChild(HAIR);
        this.hair2 = body.getChild(HAIR2);
    }

    public static TexturedModelData getTexturedModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData body = root.addChild(
            BODY, ModelPartBuilder.create()
                                  .uv(0, 0)
                                  .cuboid(-6.0F, -10.5F, -4.0F, 12.0F, 13.0F, 8.0F)
                                  .uv(32, 0)
                                  .cuboid(-3.0F, -7.5F, -6.0F, 6.0F, 5.0F, 2.0F),
            ModelTransform.pivot(0.0F, 16.5F, 0.0F)
        );

        ModelPartData leftEar = body.addChild(
            LEFT_EAR, ModelPartBuilder.create()
                                       .uv(0, 27)
                                      .cuboid(0.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F),
            ModelTransform.of(6.0F, -6.0F, 0.0F, 0.0F, -0.6109F, 0.0F)
        );

        ModelPartData rightEar = body.addChild(
            RIGHT_EAR, ModelPartBuilder.create()
                                        .uv(0, 27)
                                        .mirrored()
                                        .cuboid(-5.0F, -2.5F, 0.0F, 5.0F, 5.0F, 0.0F),
            ModelTransform.of(-6.0F, -6.0F, 0.0F, 0.0F, 0.6109F, 0.0F));

        ModelPartData hair = body.addChild(
            HAIR, ModelPartBuilder.create()
                                    .uv(0, 21)
                                    .cuboid(-6.0F, -3.0F, 0.0F, 12.0F, 3.0F, 0.0F),
            ModelTransform.of(0.0F, -10.5F, 0.0F, 0.0F, -0.3927F, 0.0F)
        );

        ModelPartData hair2 = body.addChild(
            HAIR2, ModelPartBuilder.create()
                                     .uv(0, 24)
                                     .cuboid(-6.0F, -3.0F, 0.0F, 12.0F, 3.0F, 0.0F),
            ModelTransform.of(0.0F, -10.5F, 0.0F, 0.0F, 0.3927F, 0.0F)
        );

        ModelPartData leftLeg = body.addChild(
            LEFT_LEG, ModelPartBuilder.create()
                                       .uv(40, 7)
                                       .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F),
            ModelTransform.pivot(-3.0F, 2.5F, 0.0F)
        );

        ModelPartData rightLeg = body.addChild(
            RIGHT_LEG, ModelPartBuilder.create()
                                        .uv(40, 7)
                                        .mirrored()
                                        .cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 5.0F, 4.0F),
            ModelTransform.pivot(3.0F, 2.5F, 0.0F)
        );

        return TexturedModelData.of(data, 64, 32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        limbDistance = clamp(limbDistance, -0.45F, 0.45F);
        float speed = 1f;
        float degree = 1.0f;


        rightLeg.pitch = cos(limbAngle * speed * 0.6F) * 1.4F * limbDistance;
        leftLeg.pitch = cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limbDistance;
        leftEar.yaw = MathHelper.cos(animationProgress * speed * 0.15F) * degree * 0.2F * 0.5F - 0.6109F;
        rightEar.yaw = MathHelper.cos(animationProgress * speed * 0.15F + (float)Math.PI) * degree * 0.2F * 0.5F + 0.6109F;
        body.roll = cos(limbAngle * speed * 0.3F) * 0.35F * limbDistance;
        this.body.pivotY = cos(limbAngle * speed * 1.2F + (float)Math.PI / 2) * 2F * limbDistance + 16.5F;
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
