package net.orcinus.goodending.world.gen.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;
import net.orcinus.goodending.init.GoodEndingStructureType;

import java.util.Optional;

public class RevampedSwampHutStructure extends Structure {
    public static final Codec<RevampedSwampHutStructure> CODEC = RevampedSwampHutStructure.createCodec(RevampedSwampHutStructure::new);

    public RevampedSwampHutStructure(Config config) {
        super(config);
    }

    @Override
    public Optional<StructurePosition> getStructurePosition(Context context) {
        return RevampedSwampHutStructure.getStructurePosition(context, Heightmap.Type.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
    }

    private void addPieces(StructurePiecesCollector collector, Structure.Context context) {
        BlockRotation blockRotation = BlockRotation.random(context.random());
        BlockPos blockPos = new BlockPos(context.chunkPos().getStartX(), 64, context.chunkPos().getStartZ());
        RevampedSwampHutGenerator.addParts(context.structureTemplateManager(), blockPos, blockRotation, collector, context.random());
    }

    @Override
    public StructureType<?> getType() {
        return GoodEndingStructureType.REVAMPED_SWAMP_HUT;
    }

}
