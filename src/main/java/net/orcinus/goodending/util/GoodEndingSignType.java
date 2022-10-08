package net.orcinus.goodending.util;

import net.minecraft.world.level.block.state.properties.WoodType;

public class GoodEndingSignType extends WoodType {
    private final String id;

    public GoodEndingSignType(String name) {
        super(name);
        this.id = name;
    }

    public String getId() {
        return this.id;
    }

}
