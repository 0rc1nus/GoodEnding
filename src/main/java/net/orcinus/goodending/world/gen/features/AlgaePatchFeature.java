package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.blocks.AlgaeBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;

import java.util.HashSet;

public class AlgaePatchFeature extends Feature<NoneFeatureConfiguration> {

    public AlgaePatchFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        HashSet<BlockPos> hashSet = Sets.newHashSet();
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
                        world.setBlock(placePos, GoodEndingBlocks.ALGAE.defaultBlockState().setValue(AlgaeBlock.WATERLOGGED, true), 2);
                        hashSet.add(placePos);
                    }
                }
            }
            for (BlockPos pos : hashSet) {
                placeSquare(pos, world, random, 0);
            }
            return true;
        }
        return false;
    }

    public boolean placeSquare(BlockPos blockPos, WorldGenLevel world, RandomSource random, int tries) {
        tries++;
        if (tries > 6) {
            return false;
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (random.nextFloat() < 0.5F && world.getBlockState(pos).is(Blocks.GRASS_BLOCK) && world.getBlockState(pos.above()).isAir()) {
                    world.setBlock(pos, Blocks.MUD.defaultBlockState(), 2);
                    if (random.nextInt(10) != 0) {
                        placeSquare(pos.relative(Direction.Plane.HORIZONTAL.getRandomDirection(random)), world, random, tries);
                    }
                }
            }
        }
        return true;
    }

    public boolean waterAtDirection(WorldGenLevel world, BlockPos blockPos, Direction direction) {
        return world.getBlockState(blockPos.relative(direction)).is(BlockTags.DIRT);
    }
}