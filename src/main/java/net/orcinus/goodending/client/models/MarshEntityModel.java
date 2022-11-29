package net.orcinus.goodending.client.models;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.util.math.MathHelper;
import net.orcinus.goodending.entities.MarshEntity;

import static net.minecraft.client.render.entity.model.EntityModelPartNames.BODY;
import static net.minecraft.client.render.entity.model.EntityModelPartNames.LEFT_FIN;
import static net.minecraft.client.render.entity.model.EntityModelPartNames.LEFT_LEG;
import static net.minecraft.client.render.entity.model.EntityModelPartNames.RIGHT_FIN;
import static net.minecraft.client.render.entity.model.EntityModelPartNames.RIGHT_LEG;
import static net.minecraft.client.render.entity.model.EntityModelPartNames.TAIL;
import static net.minecraft.util.math.MathHelper.clamp;
import static net.minecraft.util.math.MathHelper.cos;

@SuppressWarnings("FieldCanBeLocal, unused")
@Environment(EnvType.CLIENT)
public class MarshEntityModel<T extends MarshEntity> extends SinglePartEntityModel<T> {
    private final ModelPart root;

    private final ModelPart body;
    private final ModelPart tail;
    private final ModelPart leftFin;
    private final ModelPart rightFin;
    private final ModelPart leftLeg;
    private final ModelPart rightLeg;

    public MarshEntityModel(ModelPart root) {
        this.root = root;

        this.body = this.root.getChild(BODY);
        this.leftLeg = this.root.getChild(LEFT_LEG);
        this.rightLeg = this.root.getChild(RIGHT_LEG);

        this.tail = this.body.getChild(TAIL);
        this.leftFin = this.body.getChild(LEFT_FIN);
        this.rightFin = this.body.getChild(RIGHT_FIN);
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData data = new ModelData();
        ModelPartData root = data.getRoot();

        ModelPartData body = root.addChild(
            BODY,
            ModelPartBuilder.create()
                            .uv(0, 0)
                            .cuboid(-6.0F, -10.0F, -7.0F, 12.0F, 10.0F, 12.0F)
                            .uv(0, 15)
                            .cuboid(0.0F, -14.0F, 2.0F, 0.0F, 12.0F, 7.0F),
            ModelTransform.of(0.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData tail = body.addChild(
            TAIL,
            ModelPartBuilder.create()
                            .uv(22, 38)
                            .cuboid(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 8.0F),
            ModelTransform.of(0.0F, -2.0F, 5.0F, -0.7854F, 0.0F, 0.0F)
        );

        ModelPartData leftFin = body.addChild(
            LEFT_FIN,
            ModelPartBuilder.create()
                            .uv(0, 34)
                            .cuboid(0.0F, -4.5F, 0.0F, 5.0F, 6.0F, 0.0F),
            ModelTransform.of(6.0F, -1.5F, 1.0F, 0.0F, -0.7854F, 0.0F)
        );

        ModelPartData rightFin = body.addChild(
            RIGHT_FIN,
            ModelPartBuilder.create()
                            .uv(0, 34)
                            .mirrored(true)
                            .cuboid(-5.0F, -4.5F, 0.0F, 5.0F, 6.0F, 0.0F),
            ModelTransform.of(-6.0F, -1.5F, 1.0F, 0.0F, 0.7854F, 0.0F)
        );

        ModelPartData leftLeg = root.addChild(
            LEFT_LEG,
            ModelPartBuilder.create()
                            .uv(28, 22)
                            .cuboid(-1.5F, 0.0F, -3.0F, 4.0F, 5.0F, 6.0F),
            ModelTransform.of(3.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        ModelPartData rightLeg = root.addChild(
            RIGHT_LEG,
            ModelPartBuilder.create()
                            .uv(28, 22)
                            .mirrored(true)
                            .cuboid(-2.5F, 0.0F, -3.0F, 4.0F, 5.0F, 6.0F),
            ModelTransform.of(-3.0F, 19.0F, 0.0F, 0.0F, 0.0F, 0.0F)
        );

        return TexturedModelData.of(data, 48, 48);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float limb = clamp(limbDistance, -0.45F, 0.45F);
        float tilt = Math.min(limbDistance / 0.3f, 1.0f);
        float speed = 0.75f;
        float degree = 0.5f;

        leftFin.yaw = -tilt * 0.65F;
        rightFin.yaw = tilt * 0.65F;
        tail.pitch = -0.7854F + tilt;

        rightFin.yaw += MathHelper.cos(animationProgress * speed * 0.3F + (float)Math.PI) * degree * 0.2F * 0.5F + 0.7854F;
        leftFin.yaw += MathHelper.cos(animationProgress * speed * 0.3F) * degree * 0.2F * 0.5F - 0.7854F;

        rightLeg.pitch = cos(limbAngle * speed * 0.6F) * 1.4F * limb;
        leftLeg.pitch = cos(limbAngle * speed * 0.6F + (float)Math.PI) * 1.4F * limb;
        tail.yaw = cos(limbAngle * speed * 0.3F + (float)Math.PI / 2) * 1.4F * limb;
        body.roll = cos(limbAngle * speed * 0.3F) * 0.35F * limb;
        body.pivotY = cos(limbAngle * speed * 1.2F + (float)Math.PI / 2) * limb + 19F;

    }

    @Override
    public ModelPart getPart() {
        return this.root;
    }


}