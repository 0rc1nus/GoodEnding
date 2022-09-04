package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.BigLilyPadConfig;

public class BigLilyPadFeature extends Feature<BigLilyPadConfig> {

    public BigLilyPadFeature(Codec<BigLilyPadConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BigLilyPadConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        int size = context.getConfig().size;
        this.setBlockState(world, blockPos, GoodEndingBlocks.LARGE_LILY_PAD.getDefaultState());
        this.setBlockState(world, blockPos.east(), GoodEndingBlocks.LARGE_LILY_PAD.getDefaultState());
        return true;
    }

}
