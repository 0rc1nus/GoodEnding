package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.blocks.AlgaeBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class AlgaePatchFeature extends Feature<NoneFeatureConfiguration> {

    public AlgaePatchFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        int xRange = UniformInt.of(1, 2).sample(random);
        int zRange = UniformInt.of(1, 2).sample(random);
        int yRange = xRange / 4;
        if (blockPos.getY() < 55) {
            return false;
        }
        List<BlockPos> poses = Lists.newArrayList();
        for (int x = -xRange; x <= xRange; x++) {
            for (int z = -zRange; z <= zRange; z++) {
                for (int y = -yRange; y <= yRange; y++) {
                    BlockPos offset = blockPos.offset(x, y, z);
                    int distance = Math.max(1, Math.round(Mth.sqrt((float) blockPos.distSqr(offset))));
                    if (world.getBlockState(offset).is(Blocks.WATER) && random.nextInt(distance) == 0) {
                        poses.add(offset);
                    }
                }
            }
        }
        for (BlockPos pos : poses) {
            this.collectAvailableDirections(world, pos).forEach(direction -> {
                BlockState blockState = world.getBlockState(pos);
                if (blockState.is(GoodEndingBlocks.ALGAE)) {
                    world.setBlock(pos, GoodEndingBlocks.ALGAE.withPropertiesOf(blockState).setValue(AlgaeBlock.getFaceProperty(direction), true), 2);
                } else {
                    world.setBlock(pos, GoodEndingBlocks.ALGAE.defaultBlockState().setValue(AlgaeBlock.getFaceProperty(direction), true).setValue(BlockStateProperties.WATERLOGGED, true), 2);
                }
            });
        }
        return true;
    }

    private List<Direction> collectAvailableDirections(WorldGenLevel world, BlockPos blockPos) {
        List<Direction> list = Lists.newArrayList();
        for (Direction direction : Direction.values()) {
            if (world.getBlockState(blockPos.relative(direction)).isCollisionShapeFullBlock(world, blockPos.relative(direction))) {
                list.add(direction);
            }
        }
        return list;
    }

}
