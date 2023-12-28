package net.orcinus.goodending.init;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.structures.RevampedSwampHutGenerator;

public class GoodEndingStructurePieceTypes {

    public static void init() { }

    public static final StructurePieceType REVAMPED_SWAMP_HUT = register(RevampedSwampHutGenerator.Piece::new, "revamped_swamp_hut");

    private static StructurePieceType register(StructurePieceType.StructureTemplateType type, String id) {
        return register((StructurePieceType)type, id);
    }

    private static StructurePieceType register(StructurePieceType type, String id) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PIECE, new ResourceLocation(GoodEnding.MODID, id), type);
    }

}
