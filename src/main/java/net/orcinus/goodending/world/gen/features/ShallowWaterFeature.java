package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;

public class ShallowWaterFeature extends Feature<ShallowWaterConfig> {

    public ShallowWaterFeature(Codec<ShallowWaterConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<ShallowWaterConfig> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        boolean flag = !world.getBlockState(blockPos.below()).isAir() && world.getBlockState(blockPos).is(Blocks.WATER) && world.getBlockState(blockPos.above()).isAir();
        if (flag) {
            ShallowWaterConfig config = context.config();
            this.placeSquare(config, blockPos, world, random, 0);
            return true;
        } else {
            return false;
        }
    }

    public boolean placeSquare(ShallowWaterConfig config, BlockPos blockPos, WorldGenLevel world, RandomSource random, int tries) {
        tries++;
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (random.nextFloat() < config.chance() && world.getFluidState(pos).is(FluidTags.WATER) && world.getBlockState(pos.above()).isAir()) {
                    world.setBlock(pos, config.state().getState(random, pos), 2);
                    if (config.decorate() && world.getBlockState(pos.above()).isAir() && random.nextInt(3) == 0) {
                        Block placeState = random.nextInt(5) == 0 ? Blocks.AIR : Blocks.GRASS;
                        if (random.nextInt(5) != 0) {
                            world.setBlock(pos.above(), placeState.defaultBlockState(), 2);
                        } else {
                            DoublePlantBlock.placeAt(world, GoodEndingBlocks.CATTAIL.defaultBlockState(), pos.above(), 3);
                        }
                    }
                }
                if (random.nextInt(2) == 0 && tries < config.maxTries()) {
                    return placeSquare(config, pos.relative(Direction.Plane.HORIZONTAL.getRandomDirection(random)), world, random, tries);
                }
            }
        }
        return true;
    }
}