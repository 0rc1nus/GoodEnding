package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.blocks.LargeLilyPadBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class LargeLilyPadFeature extends Feature<NoneFeatureConfiguration> {

    public LargeLilyPadFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        int max = context.random().nextFloat() < 0.25F ? 1 : 0;
        List<BlockPos> placePos = Lists.newArrayList();
        for (int x = -1; x <= max; x++) {
            for (int z = -1; z <= max; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() - 1, blockPos.getZ() + z);
                placePos.add(pos);
            }
        }
        for (BlockPos pos : placePos) {
            if (!world.getFluidState(pos).is(FluidTags.WATER) || world.getBlockState(pos.above()).getBlock() instanceof WaterlilyBlock) {
                return false;
            } else {
                world.setBlock(pos, GoodEndingBlocks.LARGE_LILY_PAD.get().defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 2);
            }
        }
        return false;
    }

}
