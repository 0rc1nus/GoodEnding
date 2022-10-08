package net.orcinus.goodending.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.common.util.ForgeSoundType;

public class GoodEndingBlockSoundGroups {

    public static final SoundType DENSE_LEAVES = new ForgeSoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_BREAK,
            () -> SoundEvents.GRASS_STEP,
        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_PLACE,
            () -> SoundEvents.GRASS_HIT,
        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_FALL
    );

    public static final SoundType FIREFLY_LANTERN = new ForgeSoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_FIREFLY_LANTERN_BREAK,
            () -> SoundEvents.LANTERN_STEP,
            () -> SoundEvents.LANTERN_PLACE,
            () -> SoundEvents.LANTERN_HIT,
            () -> SoundEvents.LANTERN_FALL
    );

    public static final SoundType HANGING_LEAVES = new ForgeSoundType(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_BREAK,
            () -> SoundEvents.VINE_STEP,
        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_PLACE,
            () -> SoundEvents.CAVE_VINES_HIT,
        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_FALL
    );
}
