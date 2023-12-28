package net.orcinus.goodending.world.gen.features.processors;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeverBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.orcinus.goodending.init.GoodEndingStructureProcessors;
import org.jetbrains.annotations.Nullable;

public class LeverProcessor extends StructureProcessor {
    public static final LeverProcessor INSTANCE = new LeverProcessor();
    public static final Codec<LeverProcessor> CODEC = Codec.unit(INSTANCE);

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlaceSettings data) {
        BlockState state = currentBlockInfo.state();
        if (state.getBlock() == Blocks.LEVER && !state.getValue(LeverBlock.POWERED) && data.getRandom(currentBlockInfo.pos()).nextFloat() < 0.5F) {
            currentBlockInfo = new StructureTemplate.StructureBlockInfo(currentBlockInfo.pos(), state.setValue(LeverBlock.POWERED, true), currentBlockInfo.nbt());
        }
        return currentBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return GoodEndingStructureProcessors.LEVER_PROCESSOR;
    }
}
