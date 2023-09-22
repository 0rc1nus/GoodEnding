package net.orcinus.goodending.client.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartNames;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.orcinus.goodending.entities.MarshEntity;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class MarshEntityModel<T extends MarshEntity> extends HierarchicalModel<T> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public MarshEntityModel(ModelPart root) {
        this.root = root;

        this.body = this.root.getChild(PartNames.BODY);
        this.leftLeg = this.root.getChild(PartNames.LEFT_LEG);
        this.rightLeg = this.root.getChild(PartNames.RIGHT_LEG);

        this.tail = this.body.getChild(PartNames.TAIL);
        this.leftFin = this.body.getChild(PartNames.LEFT_FIN);
        this.rightFin = this.body.getChild(PartNames.RIGHT_FIN);
    }

    public static LayerDefinition getLayerDefinition() {
        MeshDefinition data = new MeshDefinition();
        PartDefinition root = data.getRoot();

        PartDefinition body = root.addOrReplaceChild(
            PartNames.BODY,
            CubeListBuilder.create()
                            .texOffs(0, 0)
                            .addBox(-6.0F, -10.0F, -7.0F, 12.0F, 10.0F, 12.0F)
                            .texOffs(0, 15)
                            .addBox(0.0F, -14.0F, 2.0F, 0.0F, 12.0F, 7.0F),
                PartPose.offsetAndRotation(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition tail = body.addOrReplaceChild(
            PartNames.TAIL,
            CubeListBuilder.create()
                            .texOffs(22, 38)
                            .addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, -2.0F, 5.0F, -0.7854F, 0.0F, 0.0F)
        );

        PartDefinition leftFin = body.addOrReplaceChild(
            PartNames.LEFT_FIN,
            CubeListBuilder.create()
                            .texOffs(0, 34)
                            .addBox(0.0F, -4.5F, 0.0F, 5.0F, 6.0F, 0.0F),
                PartPose.offsetAndRotation(6.0F, -1.5F, 1.0F, 0.0F, -0.7854F, 0.0F)
        );

        PartDefinition rightFin = body.addOrReplaceChild(
            PartNames.RIGHT_FIN,
            CubeListBuilder.create()
                            .texOffs(0, 34)
                            .mirror(true)
                            .addBox(-5.0F, -4.5F, 0.0F, 5.0F, 6.0F, 0.0F),
                PartPose.offsetAndRotation(-6.0F, -1.5F, 1.0F, 0.0F, 0.7854F, 0.0F)
        );

        PartDefinition leftLeg = root.addOrReplaceChild(
            PartNames.LEFT_LEG,
            CubeListBuilder.create()
                            .texOffs(28, 22)
                            .addBox(-1.5F, 0.0F, -3.0F, 4.0F, 5.0F, 6.0F),
                PartPose.offsetAndRotation(3.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        PartDefinition rightLeg = root.addOrReplaceChild(
                PartNames.RIGHT_LEG,
            CubeListBuilder.create()
                            .texOffs(28, 22)
                            .mirror(true)
                            .addBox(-2.5F, 0.0F, -3.0F, 4.0F, 5.0F, 6.0F),
                PartPose.offsetAndRotation(-3.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        return LayerDefinition.create(data, 48, 48);
    }

    @Override
    public void setupAnim(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float limb = Mth.clamp(limbDistance, -0.45F, 0.45F);
        float tilt = Math.min(limbDistance / 0.3f, 1.0f);
        float speed = 0.75f;
        float degree = 0.5f;

        leftFin.yRot = -tilt * 0.65F;
        rightFin.yRot = tilt * 0.65F;
        tail.xRot = -0.7854F + tilt;

        rightFin.yRot += Mth.cos(animationProgress * speed * 0.3F + (float)Math.PI) * degree * 0.2F * 0.5F + 0.7854F;
        leftFin.yRot += Mth.cos(animationProgress * speed * 0.3F) * degree * 0.2F * 0.5F - 0.7854F;

        rightLeg.xRot = Mth.cos(limbAngle * speed * 0.6F) * 1.4F * limb;
        leftLeg.xRot = Mth.cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limb;
        tail.yRot = Mth.cos(limbAngle * speed * 0.3F + (float)Math.PI / 2) * 1.4F * limb;
        body.zRot = Mth.cos(limbAngle * speed * 0.3F) * 0.35F * limb;
        body.y = Mth.cos(limbAngle * speed * 1.2F + (float)Math.PI / 2) * limb + 19F;

    }

    @Override
    public ModelPart root() {
        return this.root;
    }

}