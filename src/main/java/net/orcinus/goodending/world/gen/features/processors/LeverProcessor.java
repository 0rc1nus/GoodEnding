package net.orcinus.goodending.world.gen.features.processors;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeverBlock;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import org.jetbrains.annotations.Nullable;

public class LeverProcessor extends StructureProcessor {
    public static final LeverProcessor INSTANCE = new LeverProcessor();
    public static final Codec<LeverProcessor> CODEC = Codec.unit(INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
        BlockState state = currentBlockInfo.state;
        if (state.getBlock() == Blocks.LEVER && !state.get(LeverBlock.POWERED) && data.getRandom(currentBlockInfo.pos).nextFloat() < 0.5F) {
            currentBlockInfo = new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos, state.with(LeverBlock.POWERED, true), currentBlockInfo.nbt);
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return GoodEndingStructureProcessors.LEVER_PROCESSOR;
    }
}
