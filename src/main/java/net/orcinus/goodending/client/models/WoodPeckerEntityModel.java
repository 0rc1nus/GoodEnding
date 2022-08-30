package net.orcinus.goodending.client.models;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.EntityPose;
import net.orcinus.goodending.client.animations.WoodpeckerAnimations;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.*;
import static net.minecraft.util.math.MathHelper.*;

public class WoodPeckerEntityModel extends SinglePartEntityModel<WoodpeckerEntity> {

    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart body;
    private final ModelPart leftWing;
    private final ModelPart rightWing;
    private final ModelPart tail;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public WoodPeckerEntityModel(ModelPart root) {
        this.root = root;

        this.body      = this.root.getChild(BODY);
        this.head      = this.root.getChild(HEAD);

        this.leftLeg   = this.body.getChild(LEFT_LEG);
        this.rightLeg  = this.body.getChild(RIGHT_LEG);
        this.leftWing  = this.body.getChild(LEFT_WING);
        this.rightWing = this.body.getChild(RIGHT_WING);
        this.tail      = this.body.getChild(TAIL);
    }

    @SuppressWarnings("unused")
    public static TexturedModelData getTexturedModelData() {

        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData head = root.addChild(
            HEAD,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 5.0F)
                            .uv(13, 0)
                            .cuboid(-0.5F, -2.0F, -6.0F, 1.0F, 1.0F, 3.0F),
            ModelTransform.of(0.0F, 18.0F, 1.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData body = root.addChild(
            BODY,
            ModelPartBuilder.create()
                            .uv(0, 9)
                            .cuboid(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F),
            ModelTransform.of(0.0F, 21.0F, 1.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftWing = body.addChild(
            LEFT_WING,
            ModelPartBuilder.create()
                            .uv(15, 6)
                            .cuboid(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F),
            ModelTransform.of(2.0F, -3.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightWing = body.addChild(
            RIGHT_WING,
            ModelPartBuilder.create()
                            .uv(15, 6)
                            .mirrored(true)
                            .cuboid(-1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F),
            ModelTransform.of(-2.0F, -3.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            TAIL,
            ModelPartBuilder.create()
                            .uv(0, 17)
                            .cuboid(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 2.0F),
            ModelTransform.of(0.0F, 0.5F, 2.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData leftLeg = body.addChild(
            LEFT_LEG,
            ModelPartBuilder.create()
                            .uv(0, 20)
                            .cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                            .uv(-1, 20)
                            .cuboid(-0.5F, 2.0F, -1.5F, 1.0F, 0.0F, 3.0F),
            ModelTransform.of(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightLeg = body.addChild(
            RIGHT_LEG,
            ModelPartBuilder.create()
                            .uv(0, 20)
                            .mirrored(true)
                            .cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                            .uv(-1, 20)
                            .mirrored(true)
                            .cuboid(-0.5F, 2.0F, -1.5F, 1.0F, 0.0F, 3.0F),
            ModelTransform.of(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 32, 32);
    }

    @Override
    public void setAngles(WoodpeckerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        limbDistance = clamp(limbDistance, -0.45F, 0.45F);
        if (entity.getPose() != EntityPose.STANDING && entity.getPose() != EntityPose.DIGGING) this.setHeadAngle(headYaw, headPitch);
        this.setBodyAngles(limbDistance);
        this.updateAnimation(entity.standingAnimationState, WoodpeckerAnimations.WOODPECKER_STANDING, animationProgress);
        this.updateAnimation(entity.flyingAnimationState, WoodpeckerAnimations.WOODPECKER_FLY, animationProgress);
        this.updateAnimation(entity.peckingAnimationState, WoodpeckerAnimations.WOODPECKER_PECK, animationProgress);
    }

    private void setBodyAngles(float limbDistance) {
        float tilt = Math.min(limbDistance / 0.3f, 1.0f);
        body.pitch += tilt * 0.7f;
        head.pivotZ += tilt * -2.5f + 1.0F;
        head.pivotY += tilt * 0.5f;
        tail.pitch += tilt * -1f;
    }

    private void setHeadAngle(float yaw, float pitch) {
        this.head.pitch = pitch * ((float)Math.PI / 180);
        this.head.yaw = yaw * ((float)Math.PI / 180);
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }


}
