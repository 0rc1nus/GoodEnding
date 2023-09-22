package net.orcinus.goodending.init;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.orcinus.goodending.mixin.accessor.BlockSetTypeAccessor;

public class GoodEndingBlockSetTypes {

    public static final BlockSetType MUDDY_OAK = BlockSetTypeAccessor.callRegister(new BlockSetType("muddy_oak"));
    public static final BlockSetType CYPRESS = BlockSetTypeAccessor.callRegister(new BlockSetType("cypress"));

}
