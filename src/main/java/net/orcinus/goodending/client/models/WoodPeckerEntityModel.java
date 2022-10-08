package net.orcinus.goodending.client.models;

import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Pose;
import net.orcinus.goodending.client.animations.WoodpeckerAnimations;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class WoodPeckerEntityModel extends HierarchicalModel<WoodpeckerEntity> {

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

        this.body      = this.root.getChild(PartNames.BODY);
        this.head      = this.root.getChild(PartNames.HEAD);

        this.leftLeg   = this.body.getChild(PartNames.LEFT_LEG);
        this.rightLeg  = this.body.getChild(PartNames.RIGHT_LEG);
        this.leftWing  = this.body.getChild(PartNames.LEFT_WING);
        this.rightWing = this.body.getChild(PartNames.RIGHT_WING);
        this.tail      = this.body.getChild(PartNames.TAIL);
    }

    @SuppressWarnings("unused")
    public static LayerDefinition getLayerDefinition() {

        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition head = root.addOrReplaceChild(
                PartNames.HEAD,
            CubeListBuilder.create()
                            .texOffs(0, 0)
                            .addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 5.0F)
                            .texOffs(13, 0)
                            .addBox(-0.5F, -2.0F, -6.0F, 1.0F, 1.0F, 3.0F),
                PartPose.offsetAndRotation(0.0F, 18.0F, 1.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition body = root.addOrReplaceChild(
                PartNames.BODY,
            CubeListBuilder.create()
                            .texOffs(0, 9)
                            .addBox(-2.0F, -3.0F, -2.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 21.0F, 1.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftWing = body.addOrReplaceChild(
                PartNames.LEFT_WING,
            CubeListBuilder.create()
                            .texOffs(15, 6)
                            .addBox(0.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(2.0F, -3.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightWing = body.addOrReplaceChild(
                PartNames.RIGHT_WING,
            CubeListBuilder.create()
                            .texOffs(15, 6)
                            .mirror(true)
                            .addBox(-1.0F, 0.0F, -1.5F, 1.0F, 4.0F, 3.0F),
                PartPose.offsetAndRotation(-2.0F, -3.0F, 0.5F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition tail = body.addOrReplaceChild(
                PartNames.TAIL,
            CubeListBuilder.create()
                            .texOffs(0, 17)
                            .addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 2.0F),
                PartPose.offsetAndRotation(0.0F, 0.5F, 2.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition leftLeg = body.addOrReplaceChild(
                PartNames.LEFT_LEG,
            CubeListBuilder.create()
                            .texOffs(0, 20)
                            .addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                            .texOffs(-1, 20)
                            .addBox(-0.5F, 2.0F, -1.5F, 1.0F, 0.0F, 3.0F),
                PartPose.offsetAndRotation(1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightLeg = body.addOrReplaceChild(
                PartNames.RIGHT_LEG,
            CubeListBuilder.create()
                            .texOffs(0, 20)
                            .mirror(true)
                            .addBox(-0.5F, 0.0F, 0.0F, 1.0F, 2.0F, 0.0F)
                            .texOffs(-1, 20)
                            .mirror(true)
                            .addBox(-0.5F, 2.0F, -1.5F, 1.0F, 0.0F, 3.0F),
                PartPose.offsetAndRotation(-1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(data, 32, 32);
    }

    @Override
    public void setupAnim(WoodpeckerEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        limbDistance = Mth.clamp(limbDistance, -0.45F, 0.45F);
        if (entity.getPose() != Pose.STANDING && entity.getPose() != Pose.DIGGING) this.setHeadAngle(headYaw, headPitch);
        this.setBodyAngles(limbDistance);
        this.animate(entity.standingAnimationState, WoodpeckerAnimations.WOODPECKER_STANDING, animationProgress);
        this.animate(entity.flyingAnimationState, WoodpeckerAnimations.WOODPECKER_FLY, animationProgress);
        this.animate(entity.peckingAnimationState, WoodpeckerAnimations.WOODPECKER_PECK, animationProgress);
    }

    private void setBodyAngles(float limbDistance) {
        float tilt = Math.min(limbDistance / 0.3f, 1.0f);
        body.xRot += tilt * 0.7f;
        head.z += tilt * -2.5f + 1.0F;
        head.y += tilt * 0.5f;
        tail.xRot += tilt * -1f;
    }

    private void setHeadAngle(float yaw, float pitch) {
        this.head.xRot = pitch * ((float)Math.PI / 180);
        this.head.yRot = yaw * ((float)Math.PI / 180);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }


}
