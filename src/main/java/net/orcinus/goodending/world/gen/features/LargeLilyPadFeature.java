package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.blocks.LargeLilyPadBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class LargeLilyPadFeature extends Feature<DefaultFeatureConfig> {

    public LargeLilyPadFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        int max = context.getRandom().nextFloat() < 0.25F ? 1 : 0;
        List<BlockPos> placePos = Lists.newArrayList();
        for (int x = -1; x <= max; x++) {
            for (int z = -1; z <= max; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() - 1, blockPos.getZ() + z);
                placePos.add(pos);
            }
        }
        for (BlockPos pos : placePos) {
            if (!world.getFluidState(pos).isIn(FluidTags.WATER) || world.getBlockState(pos.up()).getBlock() instanceof LilyPadBlock) {
                return false;
            } else {
                world.setBlockState(pos, GoodEndingBlocks.LARGE_LILY_PAD.getDefaultState().with(LargeLilyPadBlock.WATERLOGGED, true), 2);
            }
        }
        return false;
    }

}
