package net.orcinus.goodending.world.gen.features.processors;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.Chunk;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import org.jetbrains.annotations.Nullable;

public class PillarLogProcessor extends StructureProcessor {
    public static final PillarLogProcessor INSTANCE = new PillarLogProcessor();
    public static final Codec<PillarLogProcessor> CODEC = Codec.unit(INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        if (currentBlockInfo.state.getBlock() == Blocks.MAGENTA_GLAZED_TERRACOTTA) {
            BlockState muddyOakLog = GoodEndingBlocks.MUDDY_OAK_LOG.getDefaultState().with(PillarBlock.AXIS, Direction.Axis.Y);
            currentBlockInfo = new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, muddyOakLog, currentBlockInfo.nbt);
            for (int i = 0; i < world.getHeight(); i++) {
                BlockPos down = currentBlockInfo.pos.down(i);
                Chunk chunk = world.getChunk(currentBlockInfo.pos);
                BlockState blockState = world.getBlockState(down);
                if ((blockState.getFluidState().isOf(Fluids.WATER) || world.isAir(down)) && currentBlockInfo.pos.getY() > world.getBottomY() + 1) {
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
