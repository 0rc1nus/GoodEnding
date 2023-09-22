package net.orcinus.goodending.client.animations;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

@Environment(EnvType.CLIENT)
public class WoodpeckerAnimations {


    public static final AnimationDefinition WOODPECKER_PECK = AnimationDefinition.Builder.withLength(2.5416666666666665f)
                                                 .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                     new Keyframe(0f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.8333333333333334f, KeyframeAnimations.posVec(0f, -0.5f, 2.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.0416666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.0833333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.125f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.1666666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.2083333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.25f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.2916666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.3333333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.375f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.4166666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.4583333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5416666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5833333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.625f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.6666666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.7083333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.75f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.7916666666666667f, KeyframeAnimations.posVec(0f, -0.5f, 1.5f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.8333333333333333f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(2.1666666666666665f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(2.5416666666666665f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(2.1666666666666665f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(2.5416666666666665f, KeyframeAnimations.degreeVec(-15f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                     new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.0416666666666667f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.0833333333333333f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.125f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.1666666666666667f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.2083333333333333f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.25f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.2916666666666667f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.3333333333333333f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.375f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.4166666666666667f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.4583333333333333f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5416666666666667f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.5833333333333333f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.625f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.6666666666666667f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.7083333333333333f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.75f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.7916666666666667f, KeyframeAnimations.posVec(0f, 0f, -1.15f), AnimationChannel.Interpolations.CATMULLROM),
                                                     new Keyframe(1.8333333333333333f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.CATMULLROM))
                                                 )
                                                 .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("left_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 0f, -7.5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, 0f, -7.5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.0416666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.1666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.2083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.3333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.375f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.4166666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5416666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.625f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.7083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.7916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(2.0833333333333335f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("right_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.5416666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 7.5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 7.5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.0416666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.0833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.125f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.1666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.2083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.25f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.2916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.3333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.375f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.4166666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.4583333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5416666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.5833333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.625f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.6666666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.7083333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.75f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(1.7916666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 5f), AnimationChannel.Interpolations.LINEAR),
                                                     new Keyframe(2.0833333333333335f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 ).addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-52.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .build();

    public static final AnimationDefinition WOODPECKER_STANDING = AnimationDefinition.Builder.withLength(0f)
                                                 .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                     new Keyframe(0f, KeyframeAnimations.posVec(0f, -0.5f, 1f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-15f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                     new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("left_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("right_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("tail", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-52.5f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .addAnimation("right_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                     new Keyframe(0f, KeyframeAnimations.degreeVec(-45f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                 )
                                                 .build();

    public static final AnimationDefinition WOODPECKER_FLY = AnimationDefinition.Builder.withLength(1f)
                                                .looping()
                                                .addAnimation("head", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.3333333333333333f, KeyframeAnimations.posVec(0f, 0.5f, -1f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.8333333333333334f, KeyframeAnimations.posVec(0f, 0.5f, -1f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, -1f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .addAnimation("body", new AnimationChannel(AnimationChannel.Targets.POSITION,
                                                    new Keyframe(0f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.25f, KeyframeAnimations.posVec(0f, 0.25f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.75f, KeyframeAnimations.posVec(0f, 0.25f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.posVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .addAnimation("left_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5833333333333334f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.6666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(0f, 0f, -45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .addAnimation("right_wing", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.08333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.25f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.4166666666666667f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5833333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.6666666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.75f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.8333333333333334f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.9166666666666666f, KeyframeAnimations.degreeVec(0f, 0f, 45f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .addAnimation("left_leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.16666666666666666f, KeyframeAnimations.degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.6666666666666666f, KeyframeAnimations.degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .addAnimation("`right_leg`", new AnimationChannel(AnimationChannel.Targets.ROTATION,
                                                    new Keyframe(0f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.3333333333333333f, KeyframeAnimations.degreeVec(10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.5f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(0.875f, KeyframeAnimations.degreeVec(-10f, 0f, 0f), AnimationChannel.Interpolations.LINEAR),
                                                    new Keyframe(1f, KeyframeAnimations.degreeVec(0f, 0f, 0f), AnimationChannel.Interpolations.LINEAR))
                                                )
                                                .build();
}
