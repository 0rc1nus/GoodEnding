package net.orcinus.goodending.util;

import net.minecraft.block.AbstractPlantStemBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class WorldGenUtil {

    public static void generateHangingVines(StructureWorldAccess world, float probability, Random random, List<BlockPos> leavePositions) {
        leavePositions.forEach((pos) -> {
            BlockPos.Mutable mutable = pos.mutableCopy();
            if (random.nextFloat() < probability) {
                int length = MathHelper.nextInt(random, 1, 2);
                for (int i = 0; i <= length; i++) {
                    if (world.isAir(mutable)) {
                        if (i == length || !world.isAir(mutable.down())) {
                            world.setBlockState(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES.getDefaultState().with(AbstractPlantStemBlock.AGE, MathHelper.nextInt(random, 17, 25)), Block.NOTIFY_LISTENERS);
                            break;
                        }
                        world.setBlockState(mutable, GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.getDefaultState(), Block.NOTIFY_LISTENERS);
                    }
                    mutable.move(Direction.DOWN);
                }
            }
        });
    }

    public static void generateVines(StructureWorldAccess world, float probability, Random random, List<BlockPos> leavePositions) {
        leavePositions.forEach((pos) -> {
            BlockPos blockPos;
            if (random.nextFloat() < probability) {
                blockPos = pos.west();
                if (world.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.EAST, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.east();
                if (world.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.WEST, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.north();
                if (world.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.SOUTH, world);
                }
            }

            if (random.nextFloat() < probability) {
                blockPos = pos.south();
                if (world.isAir(blockPos)) {
                    placeVines(blockPos, VineBlock.NORTH, world);
                }
            }

        });
    }

    private static void placeVines(BlockPos pos, BooleanProperty faceProperty, StructureWorldAccess world) {
        world.setBlockState(pos, Blocks.VINE.getDefaultState().with(faceProperty, true), 19);
        int i = 4;

        for(pos = pos.down(); world.isAir(pos) && i > 0; --i) {
            world.setBlockState(pos, Blocks.VINE.getDefaultState().with(faceProperty, true), 19);
            pos = pos.down();
        }

    }

}
