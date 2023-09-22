package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.init.GoodEndingTags;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;

public class BoulderFeature extends Feature<BoulderConfig> {

    public BoulderFeature(Codec<BoulderConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<BoulderConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        BoulderConfig config = context.config();
        if (world.getBlockState(origin.below()).is(GoodEndingTags.BASE_BOULDER) && world.isStateAtPosition(origin, DripstoneUtils::isEmptyOrWater)) {
            float size = config.size().sample(random);
            this.generateBoulder(world, origin.above(), random, config, size);
            this.modifyGrass(world, origin, random, size, config);
            return true;
        } else {
            return false;
        }
    }

    private void generateBoulder(WorldGenLevel world, BlockPos origin, RandomSource random, BoulderConfig config, float size) {
        for (float x = -size; x < size; x++) {
            for (float y = -size; y < size; y++) {
                for (float z = -size; z < size; z++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= size * ((size - y) / 4)) {
                        BlockPos pos = BlockPos.containing(x + origin.getX(), y + origin.getY(), z + origin.getZ());
                        BlockState state = y + 1 > 0 && random.nextFloat() > 0.25F ? config.stone().getState(random, pos) : config.secondaryStone().getState(random, pos);
                        world.setBlock(pos, state, 3);
                    }
                }
            }
        }
    }

    private void modifyGrass(WorldGenLevel world, BlockPos origin, RandomSource random, float size, BoulderConfig config) {
        int radius = (int) (size * 1.5F);
        BlockPos.withinManhattanStream(origin, radius, 3, radius).forEach((pos) -> {
            if ((world.getBlockState(pos).is(Blocks.GRASS_BLOCK) || (world.getBlockState(pos).is(Blocks.PODZOL))) && random.nextBoolean() && config.coarseDirt()) {
                world.setBlock(pos, Blocks.COARSE_DIRT.defaultBlockState(), 3);
            }
        });
    }
}