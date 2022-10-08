package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;

public class HalfWaterloggedDecorationFeature extends Feature<HalfWaterloggedDecorationConfig> {

    public HalfWaterloggedDecorationFeature(Codec<HalfWaterloggedDecorationConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<HalfWaterloggedDecorationConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        HalfWaterloggedDecorationConfig config = context.config();
        int tries = UniformInt.of(40, 60).sample(random);
        if (world.getBlockState(blockPos.below()).is(BlockTags.DIRT)) {
            boolean flag = false;
            for (int i = 0; i < tries; i++) {
                int radius = config.radius().sample(random);
                BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos().setWithOffset(blockPos, -radius + radius, 0, -radius + radius);
                BlockPos immutable = mutable.immutable();
                if (world.getFluidState(immutable).is(FluidTags.WATER) && !world.getBlockState(immutable).is(Blocks.MUD) && world.getBlockState(immutable.above()).isAir() && world.getBlockState(immutable.below()).is(BlockTags.DIRT)) {
                    DoublePlantBlock.placeAt(world, config.placed_block().getState(random, immutable), immutable, 3);
                    flag = true;
                } else {
                    flag = false;
                }
            }
            return flag;
        }
        else {
            return false;
        }
    }
}