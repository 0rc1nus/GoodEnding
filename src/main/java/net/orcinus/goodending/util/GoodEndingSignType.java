package net.orcinus.goodending.util;

import net.minecraft.util.SignType;

public class GoodEndingSignType extends SignType {
    private final String id;

    public GoodEndingSignType(String name) {
        super(name);
        this.id = name;
    }

    public String getId() {
        return this.id;
    }

}
