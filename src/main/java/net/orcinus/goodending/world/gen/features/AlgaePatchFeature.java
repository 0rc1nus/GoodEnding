package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.blocks.AlgaeBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

public class AlgaePatchFeature extends Feature<NoneFeatureConfiguration> {

    public AlgaePatchFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        if (world.getBlockState(blockPos).is(Blocks.WATER) && (waterAtDirection(world, blockPos, Direction.NORTH) || waterAtDirection(world, blockPos, Direction.SOUTH) || waterAtDirection(world, blockPos, Direction.EAST) || waterAtDirection(world, blockPos, Direction.WEST))) {
            int radius = UniformInt.of(3, 6).sample(random) + 1;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    boolean flag = x == -radius || x == radius;
                    boolean flag1 = z == -radius || z == radius;
                    if (flag && flag1) {
                        continue;
                    }
                    BlockPos placePos = blockPos.offset(x, 0, z);
                    int distance = Math.max(1, Math.round(Mth.sqrt((float) blockPos.distSqr(placePos))));
                    if (random.nextInt(distance) == 0 && world.isEmptyBlock(blockPos.offset(x, 1, z)) && world.getBlockState(placePos).is(Blocks.WATER)) {
                        world.setBlock(placePos, GoodEndingBlocks.ALGAE.get().defaultBlockState().setValue(AlgaeBlock.WATERLOGGED, true), 2);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean waterAtDirection(WorldGenLevel world, BlockPos blockPos, Direction direction) {
        return world.getBlockState(blockPos.relative(direction)).is(BlockTags.DIRT);
    }
}
