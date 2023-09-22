package net.orcinus.goodending.init;

import com.google.common.collect.Lists;
import net.minecraft.world.level.block.state.properties.WoodType;

import java.util.LinkedList;
import java.util.List;

public class GoodEndingSignTypes {
    public static final List<WoodType> VALUES = Lists.newArrayList();

    public static final WoodType CYPRESS = register(new WoodType("cypress", GoodEndingBlockSetTypes.MUDDY_OAK));
    public static final WoodType MUDDY_OAK = register(new WoodType("muddy_oak", GoodEndingBlockSetTypes.CYPRESS));

    private static WoodType register(WoodType woodType) {
        VALUES.add(woodType);
        return woodType;
    }

}
