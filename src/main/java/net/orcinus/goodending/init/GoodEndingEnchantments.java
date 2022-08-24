package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.enchantments.RelocationEnchantment;

import java.util.Map;

public class GoodEndingEnchantments {
    public static final Map<Identifier, Enchantment> ENCHANTMENTS = Maps.newLinkedHashMap();

    public static final Enchantment RELOCATION = registerEnchantment("relocation", new RelocationEnchantment());

    public static <E extends Enchantment> E registerEnchantment(String name, E enchantment) {
        ENCHANTMENTS.put(new Identifier(GoodEnding.MODID, name), enchantment);
        return enchantment;
    }

    public static void init() {
        for (Identifier id : ENCHANTMENTS.keySet()) {
            Registry.register(Registry.ENCHANTMENT, id, ENCHANTMENTS.get(id));
        }
    }

}
