package net.orcinus.goodending.init;

import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.structures.RevampedSwampHutGenerator;

public class GoodEndingStructurePieceTypes {

    public static final StructurePieceType REVAMPED_SWAMP_HUT = register(RevampedSwampHutGenerator.Piece::new, "revamped_swamp_hut");

    private static StructurePieceType register(StructurePieceType.ManagerAware type, String id) {
        return register((StructurePieceType)type, id);
    }

    private static StructurePieceType register(StructurePieceType type, String id) {
        return Registry.register(Registry.STRUCTURE_PIECE, new Identifier(GoodEnding.MODID, id), type);
    }

}
