package net.orcinus.goodending.init;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.processors.LeverProcessor;
import net.orcinus.goodending.world.gen.features.processors.PillarLogProcessor;

public class GoodEndingStructureProcessors {

    public static void init() { }

    public static final StructureProcessorType<PillarLogProcessor> PILLAR_LOG_PROCESSOR = register("pillar_log", PillarLogProcessor.CODEC);
    public static final StructureProcessorType<LeverProcessor> LEVER_PROCESSOR = register("lever", LeverProcessor.CODEC);

    public static <P extends StructureProcessor> StructureProcessorType<P> register(String id, Codec<P> codec) {
        return Registry.register(BuiltInRegistries.STRUCTURE_PROCESSOR, new ResourceLocation(GoodEnding.MODID, id), () -> codec);
    }

}
