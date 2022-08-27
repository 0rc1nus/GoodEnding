package net.orcinus.goodending.util;

import net.fabricmc.fabric.api.tag.convention.v1.ConventionalItemTags;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;

import java.util.List;

public enum EffectIntake {
    KEEP(ConventionalItemTags.AXES, ConventionalItemTags.PICKAXES, ConventionalItemTags.SHOVELS, ConventionalItemTags.HOES),
    EMIT(ConventionalItemTags.SWORDS, ConventionalItemTags.SPEARS);

    private final List<TagKey<Item>> itemTagKey;

    @SafeVarargs
    EffectIntake(TagKey<Item>... itemTagKey) {
        this.itemTagKey = List.of(itemTagKey);
    }

    public List<TagKey<Item>> getItemTagKey() {
        return this.itemTagKey;
    }

}
