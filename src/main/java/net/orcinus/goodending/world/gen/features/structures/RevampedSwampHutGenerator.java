package net.orcinus.goodending.world.gen.features.structures;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.IglooGenerator;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingStructurePieceTypes;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import net.orcinus.goodending.world.gen.features.processors.PillarLogProcessor;

public class RevampedSwampHutGenerator {
    private static final Identifier REGULAR_TEMPLATES = new Identifier(GoodEnding.MODID, "revamped_swamp_hut");

    public static void addParts(StructureTemplateManager structureTemplateManager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder holder, Random random) {
        holder.addPiece(new RevampedSwampHutGenerator.Piece(structureTemplateManager, REGULAR_TEMPLATES, pos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {

        public Piece(StructureTemplateManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation) {
            super(GoodEndingStructurePieceTypes.REVAMPED_SWAMP_HUT, 0, manager, identifier, identifier.toString(), RevampedSwampHutGenerator.Piece.createPlacementData(rotation), pos);
        }

        public Piece(StructureTemplateManager manager, NbtCompound nbt) {
            super(GoodEndingStructurePieceTypes.REVAMPED_SWAMP_HUT, nbt, manager, identifier -> RevampedSwampHutGenerator.Piece.createPlacementData(BlockRotation.valueOf(nbt.getString("Rot"))));
        }

        @Override
        protected void writeNbt(StructureContext context, NbtCompound nbt) {
            super.writeNbt(context, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation) {
            return new StructurePlacementData().setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS).addProcessor(PillarLogProcessor.INSTANCE);
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            StructurePlacementData structurePlacementData = createPlacementData(this.placementData.getRotation());
            BlockPos blockPos = BlockPos.ORIGIN;
            BlockPos blockPos2 = this.pos.add(StructureTemplate.transform(structurePlacementData, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
            int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.getX(), blockPos2.getZ());
            this.pos = new BlockPos(this.pos.getX(), i, this.pos.getZ());
            super.generate(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pivot);
        }
    }


}
