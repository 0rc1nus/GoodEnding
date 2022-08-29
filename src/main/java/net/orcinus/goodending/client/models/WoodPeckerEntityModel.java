package net.orcinus.goodending.client.models;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.entity.mob.PathAwareEntity;
import net.orcinus.goodending.client.animations.WoodpeckerAnimations;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.*;
import static net.minecraft.util.math.MathHelper.*;

public class WoodPeckerEntityModel<W extends PathAwareEntity> extends SinglePartEntityModel<WoodpeckerEntity> {
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
        limbDistance = clamp(limbDistance, -0.45F, 0.45F);

        float speed = 1.0f;
        float degree = 1.0f;
        float tilt = Math.min(limbDistance / 0.3f, 1.0f);
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.updateAnimation(entity.peckingAnimationState, WoodpeckerAnimations.WOODPECKER_PECK, animationProgress);
        this.updateAnimation(entity.standingAnimationState, WoodpeckerAnimations.WOODPECKER_STANDING, animationProgress);

        if (!entity.hasWoodPos()) {
            head.pitch = headPitch * ((float) Math.PI / 180f);
            head.yaw = headYaw * ((float) Math.PI / 180f);
            if (entity.isOnGround()) {
                leftWing.roll = 0F;
                leftWing.yaw = 0F;
                leftWing.pitch = 0.45f;
                rightWing.pitch = 0.45f;
                rightWing.roll = 0F;
                rightWing.yaw = 0F;
                body.pivotY = 21F;
                body.pivotZ = 1F;
                head.pivotY = 18F;
                leftLeg.pitch = 0F;
                rightLeg.pitch = 0F;
            } else {
                leftWing.roll = cos(animationProgress * speed * 1.2F) * degree * 4F * 0.25F - 1F;
                leftWing.yaw = cos(animationProgress * speed * 1.2F + (float) Math.PI / 2) * degree * 0.5F * 0.25F;
                rightWing.roll = cos(animationProgress * speed * 1.2F + (float) Math.PI) * degree * 4F * 0.25F + 1F;
                rightWing.yaw = cos(animationProgress * speed * 1.2F + (float) Math.PI + (float) Math.PI / 2) * degree * 0.5F * 0.25F;
                body.pivotY = cos(animationProgress * speed * 0.6F + (float) Math.PI + (float) Math.PI / 2) * degree * 0.5F * 0.25F + 21F;
                head.pivotY = cos(animationProgress * speed * 0.6F) * degree * 0.5F * 0.25F + 18F;
                leftLeg.pitch = cos(animationProgress * speed * 0.3F) * degree * -0.25F;
                rightLeg.pitch = cos(animationProgress * speed * 0.3F + 0.9F) * degree * -0.25F;
            }
            body.pitch = tilt * 0.7f;
            head.pivotZ = tilt * -2.5f + 1.0F;
            head.pivotY += tilt * 0.5f;
            tail.pitch = tilt * -1f;
        }
    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }


}
