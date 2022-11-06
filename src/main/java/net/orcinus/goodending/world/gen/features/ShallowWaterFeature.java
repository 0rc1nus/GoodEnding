package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;

public class ShallowWaterFeature extends Feature<ShallowWaterConfig> {

    public ShallowWaterFeature(Codec<ShallowWaterConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<ShallowWaterConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        boolean flag = !world.getBlockState(blockPos.down()).isAir() && world.getBlockState(blockPos).isOf(Blocks.WATER) && world.getBlockState(blockPos.up()).isAir();
        if (flag) {
            ShallowWaterConfig config = context.getConfig();
            this.placeSquare(config, blockPos, world, random, 0);
            return true;
        } else {
            return false;
        }
    }

    public boolean placeSquare(ShallowWaterConfig config, BlockPos blockPos, StructureWorldAccess world, Random random, int tries) {
        tries++;
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY(), blockPos.getZ() + z);
                if (random.nextFloat() < config.chance() && world.getFluidState(pos).isIn(FluidTags.WATER) && world.getBlockState(pos.up()).isAir()) {
                    world.setBlockState(pos, config.state().getBlockState(random, pos), 2);
                    if (config.decorate() && world.getBlockState(pos.up()).isAir() && random.nextInt(3) == 0) {
                        Block placeState = random.nextInt(5) == 0 ? Blocks.AIR : Blocks.GRASS;
                        if (random.nextInt(5) != 0) {
                            world.setBlockState(pos.up(), placeState.getDefaultState(), 2);
                        } else {
                            TallPlantBlock.placeAt(world, GoodEndingBlocks.CATTAIL.getDefaultState(), pos.up(), 3);
                        }
                    }
                }
                if (random.nextInt(2) == 0 && tries < config.maxTries()) {
                    return placeSquare(config, pos.offset(Direction.Type.HORIZONTAL.random(random)), world, random, tries);
                }
            }
        }
        return true;
    }
}