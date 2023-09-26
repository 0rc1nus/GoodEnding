package net.orcinus.goodending.init;

import net.minecraft.world.level.block.Block;

public enum GoodEndingBoatTypes {
    MUDDY_OAK(GoodEndingBlocks.MUDDY_OAK_PLANKS, "oak"),
    CYPRESS(GoodEndingBlocks.CYPRESS_PLANKS, "oak");

    public final Block block;
    public final String name;

    GoodEndingBoatTypes(Block block, String name) {
        this.block = block;
        this.name = name;
    }

}
