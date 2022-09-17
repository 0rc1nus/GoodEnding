package net.orcinus.goodending.world.gen.features.structures;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.IglooGenerator;
import net.minecraft.structure.SimpleStructurePiece;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
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
import net.orcinus.goodending.world.gen.features.processors.LeverProcessor;
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
            return new StructurePlacementData().setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS).addProcessor(PillarLogProcessor.INSTANCE).addProcessor(LeverProcessor.INSTANCE).addProcessor(new RuleStructureProcessor(ImmutableList.of(new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CLAY, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.BONE_BLOCK.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SAND, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.SOUL_SAND.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.PUMPKIN.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CLAY, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.HAY_BLOCK.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.SAND, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.GLOWSTONE.getDefaultState()), new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.GLASS, 0.3f), AlwaysTrueRuleTest.INSTANCE, Blocks.PURPLE_WOOL.getDefaultState()))));
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
        }

        @Override
        public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            BlockPos blockPos = new BlockPos(chunkPos.getStartPos());
            int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos.getX(), blockPos.getZ());
            this.pos = new BlockPos(this.pos.getX(), i + 1, this.pos.getZ());
            super.generate(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pivot);
        }
        //-1984 ~ 104288
    }


}
