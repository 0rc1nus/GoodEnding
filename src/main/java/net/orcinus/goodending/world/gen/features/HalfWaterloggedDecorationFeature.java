package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;

public class HalfWaterloggedDecorationFeature extends Feature<HalfWaterloggedDecorationConfig> {

    public HalfWaterloggedDecorationFeature(Codec<HalfWaterloggedDecorationConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<HalfWaterloggedDecorationConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        HalfWaterloggedDecorationConfig config = context.getConfig();
        int tries = UniformIntProvider.create(40, 60).get(random);
        if (world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            boolean flag = false;
            for (int i = 0; i < tries; i++) {
                int radius = config.radius.get(random);
                BlockPos.Mutable mutable = new BlockPos.Mutable().set(blockPos, -radius + radius, 0, -radius + radius);
                BlockPos immutable = mutable.toImmutable();
                if (world.getFluidState(immutable).isIn(FluidTags.WATER) && !world.getBlockState(immutable).isOf(Blocks.MUD) && world.getBlockState(immutable.up()).isAir() && world.getBlockState(immutable.down()).isIn(BlockTags.DIRT)) {
                    TallPlantBlock.placeAt(world, config.placed_block.getBlockState(random, immutable), immutable, 3);
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