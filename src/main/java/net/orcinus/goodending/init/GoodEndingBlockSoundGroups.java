package net.orcinus.goodending.init;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;

public class GoodEndingBlockSoundGroups {

    public static final BlockSoundGroup DENSE_LEAVES = new BlockSoundGroup(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_BREAK,
        SoundEvents.BLOCK_GRASS_STEP,
        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_PLACE,
        SoundEvents.BLOCK_GRASS_HIT,
        GoodEndingSoundEvents.BLOCK_DENSE_LEAVES_FALL
    );

    public static final BlockSoundGroup FIREFLY_LANTERN = new BlockSoundGroup(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_FIREFLY_LANTERN_BREAK,
        SoundEvents.BLOCK_LANTERN_STEP,
        SoundEvents.BLOCK_LANTERN_PLACE,
        SoundEvents.BLOCK_LANTERN_HIT,
        SoundEvents.BLOCK_LANTERN_FALL
    );

    public static final BlockSoundGroup HANGING_LEAVES = new BlockSoundGroup(
        1.0F, 1.0F,

        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_BREAK,
        SoundEvents.BLOCK_VINE_STEP,
        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_PLACE,
        SoundEvents.BLOCK_CAVE_VINES_HIT,
        GoodEndingSoundEvents.BLOCK_HANGING_LEAVES_FALL
    );
}
