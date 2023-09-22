package net.orcinus.goodending.world.gen.features.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.material.Fluids;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import org.jetbrains.annotations.Nullable;

public class PillarLogProcessor extends StructureProcessor {
    public static final PillarLogProcessor INSTANCE = new PillarLogProcessor();
    public static final Codec<PillarLogProcessor> CODEC = Codec.unit(INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlaceSettings data) {
        if (currentBlockInfo.state().getBlock() == Blocks.MAGENTA_GLAZED_TERRACOTTA) {
            BlockState muddyOakLog = GoodEndingBlocks.MUDDY_OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, Direction.Axis.Y);
            currentBlockInfo = new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), muddyOakLog, currentBlockInfo.nbt());
            for (int i = 0; i < world.getHeight(); i++) {
                BlockPos down = currentBlockInfo.pos().below(i);
                ChunkAccess chunk = world.getChunk(currentBlockInfo.pos());
                BlockState blockState = world.getBlockState(down);
                if ((blockState.getFluidState().is(Fluids.WATER) || world.isEmptyBlock(down)) && currentBlockInfo.pos().getY() > world.getMinBuildHeight() + 1) {
                    chunk.setBlockState(down, muddyOakLog, false);
                }
            }
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return GoodEndingStructureProcessors.PILLAR_LOG_PROCESSOR;
    }
}