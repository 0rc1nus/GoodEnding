package net.orcinus.goodending.init;

import com.mojang.serialization.Codec;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.processors.PillarLogProcessor;

public class GoodEndingStructureProcessors {

    public static final StructureProcessorType<PillarLogProcessor> PILLAR_LOG_PROCESSOR = register("pillar_log", PillarLogProcessor.CODEC);

    public static <P extends StructureProcessor> StructureProcessorType<P> register(String id, Codec<P> codec) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new Identifier(GoodEnding.MODID, id), () -> codec);
    }

}
