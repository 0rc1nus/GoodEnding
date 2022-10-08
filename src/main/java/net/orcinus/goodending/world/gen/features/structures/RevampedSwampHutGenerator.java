package net.orcinus.goodending.world.gen.features.structures;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.AlwaysTrueTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.ProcessorRule;
import net.minecraft.world.level.levelgen.structure.templatesystem.RandomBlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingStructurePieceTypes;
import net.orcinus.goodending.world.gen.features.processors.LeverProcessor;
import net.orcinus.goodending.world.gen.features.processors.PillarLogProcessor;

public class RevampedSwampHutGenerator {
    private static final ResourceLocation REGULAR_TEMPLATES = new ResourceLocation(GoodEnding.MODID, "revamped_swamp_hut");

    public static void addParts(StructureTemplateManager structureTemplateManager, BlockPos pos, Rotation rotation, StructurePieceAccessor holder, RandomSource random) {
        holder.addPiece(new RevampedSwampHutGenerator.Piece(structureTemplateManager, REGULAR_TEMPLATES, pos, rotation));
    }

    public static class Piece extends TemplateStructurePiece {

        public Piece(StructureTemplateManager manager, ResourceLocation identifier, BlockPos pos, Rotation rotation) {
            super(GoodEndingStructurePieceTypes.REVAMPED_SWAMP_HUT, 0, manager, identifier, identifier.toString(), RevampedSwampHutGenerator.Piece.createPlacementData(rotation), pos);
        }

        public Piece(StructureTemplateManager manager, CompoundTag nbt) {
            super(GoodEndingStructurePieceTypes.REVAMPED_SWAMP_HUT, nbt, manager, identifier -> RevampedSwampHutGenerator.Piece.createPlacementData(Rotation.valueOf(nbt.getString("Rot"))));
        }

        @Override
        protected void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag nbt) {
            super.addAdditionalSaveData(context, nbt);
            nbt.putString("Rot", this.placeSettings.getRotation().name());
        }

        @Override
        protected void handleDataMarker(String metadata, BlockPos pos, ServerLevelAccessor world, RandomSource random, BoundingBox boundingBox) {
        }

        private static StructurePlaceSettings createPlacementData(Rotation rotation) {
            return new StructurePlaceSettings().setRotation(rotation).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK).addProcessor(PillarLogProcessor.INSTANCE).addProcessor(LeverProcessor.INSTANCE).addProcessor(new RuleProcessor(ImmutableList.of(new ProcessorRule(new RandomBlockMatchTest(Blocks.CLAY, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.BONE_BLOCK.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.SAND, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.SOUL_SAND.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.GLASS, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.PUMPKIN.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.CLAY, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.HAY_BLOCK.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.SAND, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.GLOWSTONE.defaultBlockState()), new ProcessorRule(new RandomBlockMatchTest(Blocks.GLASS, 0.3f), AlwaysTrueTest.INSTANCE, Blocks.PURPLE_WOOL.defaultBlockState()))));
        }

        @Override
        public void postProcess(WorldGenLevel world, StructureManager structureAccessor, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
            BlockPos blockPos = new BlockPos(chunkPos.getWorldPosition());
            int i = world.getHeight(Heightmap.Types.WORLD_SURFACE_WG, blockPos.getX(), blockPos.getZ());
            this.templatePosition = new BlockPos(this.templatePosition.getX(), i + 1, this.templatePosition.getZ());
            super.postProcess(world, structureAccessor, chunkGenerator, random, chunkBox, chunkPos, pivot);
        }
        //-1984 ~ 104288
    }


}
