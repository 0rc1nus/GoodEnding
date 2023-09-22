package net.orcinus.goodending.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;

public class GoodEndingSoundTypes {

    public static final SoundType DENSE_LEAVES = new SoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.DENSE_LEAVES_BREAK,
        SoundEvents.GRASS_STEP,
        GoodEndingSoundEvents.DENSE_LEAVES_PLACE,
        SoundEvents.GRASS_HIT,
        GoodEndingSoundEvents.DENSE_LEAVES_FALL
    );

    public static final SoundType FIREFLY_LANTERN = new SoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.FIREFLY_LANTERN_BREAK,
        SoundEvents.LANTERN_STEP,
        SoundEvents.LANTERN_PLACE,
        SoundEvents.LANTERN_HIT,
        SoundEvents.LANTERN_FALL
    );

    public static final SoundType HANGING_LEAVES = new SoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.HANGING_LEAVES_BREAK,
        SoundEvents.VINE_STEP,
        GoodEndingSoundEvents.HANGING_LEAVES_PLACE,
        SoundEvents.CAVE_VINES_HIT,
        GoodEndingSoundEvents.HANGING_LEAVES_FALL
    );

    public static final SoundType ALGAE = new SoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.ALGAE_BREAK,
        GoodEndingSoundEvents.ALGAE_STEP,
        GoodEndingSoundEvents.ALGAE_PLACE,
        GoodEndingSoundEvents.ALGAE_HIT,
        GoodEndingSoundEvents.ALGAE_FALL
    );
}
