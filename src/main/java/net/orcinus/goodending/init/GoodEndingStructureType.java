package net.orcinus.goodending.init;

import com.mojang.serialization.Codec;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.structures.RevampedSwampHutStructure;

public class GoodEndingStructureType {

    public static final StructureType<RevampedSwampHutStructure> REVAMPED_SWAMP_HUT = register("revamped_swamp_hut", RevampedSwampHutStructure.CODEC);

    private static <S extends Structure> StructureType<S> register(String id, Codec<S> codec) {
        return Registry.register(Registry.STRUCTURE_TYPE, new Identifier(GoodEnding.MODID, id), () -> codec);
    }

}
