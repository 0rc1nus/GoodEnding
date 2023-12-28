package net.orcinus.goodending.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.structures.RevampedSwampHutStructure;

public class GoodEndingStructureTypes {

    public static void init() { }

    public static final StructureType<RevampedSwampHutStructure> REVAMPED_SWAMP_HUT = register("revamped_swamp_hut", RevampedSwampHutStructure.CODEC);

    private static <S extends Structure> StructureType<S> register(String id, Codec<S> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_TYPE, new ResourceLocation(GoodEnding.MODID, id), () -> codec);
    }

}
