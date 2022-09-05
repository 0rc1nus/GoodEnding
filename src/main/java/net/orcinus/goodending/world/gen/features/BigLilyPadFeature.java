package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.tag.FluidTags;
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
        if (world.getFluidState(blockPos.down()).isIn(FluidTags.WATER)) {
            this.setBlockState(world, blockPos.down(), GoodEndingBlocks.LARGE_LILY_PAD.getDefaultState());
        }
        if (world.getFluidState(blockPos.down().east()).isIn(FluidTags.WATER)) {
            this.setBlockState(world, blockPos.down().east(), GoodEndingBlocks.LARGE_LILY_PAD.getDefaultState());
        }
        return true;
    }

}
