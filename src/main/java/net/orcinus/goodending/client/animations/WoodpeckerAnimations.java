package net.orcinus.goodending.client.animations;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

import static net.minecraft.client.render.entity.animation.AnimationHelper.*;

@Environment(EnvType.CLIENT)
public interface WoodpeckerAnimations {


    Animation WOODPECKER_PECK = Animation.Builder.create(2.5416666666666665f)
                                                 .addBoneAnimation("head", new Transformation(Transformation.Targets.TRANSLATE,
                                                     new Keyframe(0f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.8333333333333334f, method_41823(0f, -0.5f, 2.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.0416666666666667f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.0833333333333333f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.125f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.1666666666666667f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.2083333333333333f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.25f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.2916666666666667f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.3333333333333333f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.375f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.4166666666666667f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.4583333333333333f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5416666666666667f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5833333333333333f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.625f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.6666666666666667f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.7083333333333333f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.75f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.7916666666666667f, method_41823(0f, -0.5f, 1.5f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.8333333333333333f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(2.1666666666666665f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(2.5416666666666665f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-15f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.8333333333333334f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(2.1666666666666665f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(2.5416666666666665f, method_41829(-15f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                                                     new Keyframe(0f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.0416666666666667f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.0833333333333333f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.125f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.1666666666666667f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.2083333333333333f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.25f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.2916666666666667f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.3333333333333333f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.375f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.4166666666666667f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.4583333333333333f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5416666666666667f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.5833333333333333f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.625f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.6666666666666667f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.7083333333333333f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.75f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.7916666666666667f, method_41823(0f, 0f, -1.15f), Transformation.Interpolations.field_37885),
                                                     new Keyframe(1.8333333333333333f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37885))
                                                 )
                                                 .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("left_wing", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.5416666666666666f, method_41829(0f, 0f, -7.5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.8333333333333334f, method_41829(0f, 0f, -7.5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.0416666666666667f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.0833333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.125f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.1666666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.2083333333333333f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.25f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.2916666666666667f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.3333333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.375f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.4166666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.4583333333333333f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5416666666666667f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5833333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.625f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.6666666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.7083333333333333f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.75f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.7916666666666667f, method_41829(0f, 0f, -5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(2.0833333333333335f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("right_wing", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.5416666666666666f, method_41829(0f, 0f, 7.5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(0.8333333333333334f, method_41829(0f, 0f, 7.5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.0416666666666667f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.0833333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.125f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.1666666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.2083333333333333f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.25f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.2916666666666667f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.3333333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.375f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.4166666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.4583333333333333f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5416666666666667f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.5833333333333333f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.625f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.6666666666666667f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.7083333333333333f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.75f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(1.7916666666666667f, method_41829(0f, 0f, 5f), Transformation.Interpolations.field_37884),
                                                     new Keyframe(2.0833333333333335f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 ).addBoneAnimation("tail", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-52.5f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .build();

    Animation WOODPECKER_STANDING = Animation.Builder.create(0f)
                                                 .addBoneAnimation("head", new Transformation(Transformation.Targets.TRANSLATE,
                                                     new Keyframe(0f, method_41823(0f, -0.5f, 1f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("head", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-15f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("body", new Transformation(Transformation.Targets.TRANSLATE,
                                                     new Keyframe(0f, method_41823(0f, 0f, -1f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("body", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("left_wing", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("right_wing", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(0f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("tail", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-52.5f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("left_leg", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .addBoneAnimation("right_leg", new Transformation(Transformation.Targets.ROTATE,
                                                     new Keyframe(0f, method_41829(-45f, 0f, 0f), Transformation.Interpolations.field_37884))
                                                 )
                                                 .build();
}
