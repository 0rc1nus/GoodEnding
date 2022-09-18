package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingTags;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;

public class BoulderFeature extends Feature<BoulderConfig> {

    public BoulderFeature(Codec<BoulderConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BoulderConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();
        BoulderConfig config = context.getConfig();
        if (world.getBlockState(origin.down()).isIn(GoodEndingTags.BASE_BOULDER) && world.testBlockState(origin, DripstoneHelper::canGenerate)) {
            float size = config.size().get(random);
            this.generateBoulder(world, origin.up(), random, config, size);
            this.modifyGrass(world, origin, random, size, config);
            return true;
        } else {
            return false;
        }
    }

    private void generateBoulder(StructureWorldAccess world, BlockPos origin, Random random, BoulderConfig config, float size) {
        for (float x = -size; x < size; x++) {
            for (float y = -size; y < size; y++) {
                for (float z = -size; z < size; z++) {
                    double distance = Math.sqrt(x * x + y * y + z * z);
                    if (distance <= size * ((size - y) / 4)) {
                        BlockPos pos = origin.add(x, y, z);
                        BlockState state = y + 1 > 0 && random.nextFloat() > 0.25F ? config.stone().getBlockState(random, pos) : config.secondaryStone().getBlockState(random, pos);
                        world.setBlockState(pos, state, 3);
                    }
                }
            }
        }
    }

    private void modifyGrass(StructureWorldAccess world, BlockPos origin, Random random, float size, BoulderConfig config) {
        int radius = (int) (size * 1.5F);
        BlockPos.streamOutwards(origin, radius, 3, radius).forEach((pos) -> {
            if ((world.getBlockState(pos).isOf(Blocks.GRASS_BLOCK) || (world.getBlockState(pos).isOf(Blocks.PODZOL))) && random.nextBoolean() && config.coarseDirt()) {
                world.setBlockState(pos, Blocks.COARSE_DIRT.getDefaultState(), 3);
            }
        });
    }
}
