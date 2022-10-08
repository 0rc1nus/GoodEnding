package net.orcinus.goodending.world.gen.features.structures;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.orcinus.goodending.init.GoodEndingStructureTypes;

import java.util.Optional;

public class RevampedSwampHutStructure extends Structure {
    public static final Codec<RevampedSwampHutStructure> CODEC = RevampedSwampHutStructure.simpleCodec(RevampedSwampHutStructure::new);

    public RevampedSwampHutStructure(StructureSettings config) {
        super(config);
    }

    @Override
    public Optional<GenerationStub> findGenerationPoint(GenerationContext context) {
        return RevampedSwampHutStructure.onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, collector -> this.addPieces(collector, context));
    }

    private void addPieces(StructurePiecesBuilder collector, Structure.GenerationContext context) {
        Rotation blockRotation = Rotation.getRandom(context.random());
        BlockPos blockPos = new BlockPos(context.chunkPos().getMinBlockX(), 64, context.chunkPos().getMinBlockZ());
        RevampedSwampHutGenerator.addParts(context.structureTemplateManager(), blockPos, blockRotation, collector, context.random());
    }

    @Override
    public StructureType<?> type() {
        return GoodEndingStructureTypes.REVAMPED_SWAMP_HUT;
    }

}
