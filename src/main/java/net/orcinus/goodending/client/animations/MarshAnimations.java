package net.orcinus.goodending.client.animations;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.animation.Transformation.Interpolations;
import net.minecraft.client.render.entity.animation.Transformation.Targets;

import static net.minecraft.client.render.entity.animation.AnimationHelper.*;

@Environment(EnvType.CLIENT)
public interface MarshAnimations {

    Animation MARSH_IDLE = Animation.Builder.create(1f)
                                            .looping()
                                            .addBoneAnimation("leftEar", new Transformation(Targets.ROTATE,
                                                new Keyframe(0f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.25f, method_41829(0f, 10f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.5f, method_41829(0f, 20f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.75f, method_41829(0f, 10f, 0f), Interpolations.field_37884),
                                                new Keyframe(1f, method_41829(0f, 0f, 0f), Interpolations.field_37884))
                                            )
                                            .addBoneAnimation("rightEar", new Transformation(Targets.ROTATE,
                                                new Keyframe(0f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.25f, method_41829(0f, -10f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.5f, method_41829(0f, -20f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.75f, method_41829(0f, -10f, 0f), Interpolations.field_37884),
                                                new Keyframe(1f, method_41829(0f, 0f, 0f), Interpolations.field_37884))
                                            )
                                            .build();

    Animation MARSH_WALK = Animation.Builder.create(0.67f)
                                            .looping()
                                            .addBoneAnimation("body", new Transformation(Targets.ROTATE,
                                                new Keyframe(0.167f, method_41829(-15f, 0f, -5f), Interpolations.field_37884),
                                                new Keyframe(0f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.34f, method_41829(-15f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.5f, method_41829(0f, 0f, 5f), Interpolations.field_37884),
                                                new Keyframe(0.67f, method_41829(0f, 0f, 0f), Interpolations.field_37884))
                                            )
                                            .addBoneAnimation("leftLeg", new Transformation(Targets.ROTATE,
                                                new Keyframe(0.167f, method_41829(32.5f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.34f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.5f, method_41829(-32.5f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.67f, method_41829(0f, 0f, 0f), Interpolations.field_37884))
                                            )
                                            .addBoneAnimation("rightLeg", new Transformation(Targets.ROTATE,
                                                new Keyframe(0f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.167f, method_41829(-32.5f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.34f, method_41829(0f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.5f, method_41829(32.5f, 0f, 0f), Interpolations.field_37884),
                                                new Keyframe(0.67f, method_41829(0f, 0f, 0f), Interpolations.field_37884))
                                            )
                                            .build();
}
