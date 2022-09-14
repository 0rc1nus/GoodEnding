package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.blocks.AlgaeBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

public class AlgaePatchFeature extends Feature<DefaultFeatureConfig> {

    public AlgaePatchFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        if (world.getBlockState(blockPos).isOf(Blocks.WATER) && (waterAtDirection(world, blockPos, Direction.NORTH) || waterAtDirection(world, blockPos, Direction.SOUTH) || waterAtDirection(world, blockPos, Direction.EAST) || waterAtDirection(world, blockPos, Direction.WEST))) {
            int radius = UniformIntProvider.create(3, 6).get(random) + 1;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    boolean flag = x == -radius || x == radius;
                    boolean flag1 = z == -radius || z == radius;
                    if (flag && flag1) {
                        continue;
                    }
                    if (world.isAir(blockPos.add(x, 0, z)) && world.getBlockState(blockPos.add(x, 0, z)).isOf(Blocks.WATER)) {
                        world.setBlockState(blockPos.add(x, 0, z), GoodEndingBlocks.ALGAE.getDefaultState().with(AlgaeBlock.WATERLOGGED, true), 2);
                    }
                }
            }
            return true;
        }
        return false;
    }

    public boolean waterAtDirection(StructureWorldAccess world, BlockPos blockPos, Direction direction) {
        return world.getBlockState(blockPos.offset(direction)).isIn(BlockTags.DIRT);
    }
}
