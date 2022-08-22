package net.orcinus.goodending.util;

import net.minecraft.block.Blocks;
import net.minecraft.block.VineBlock;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;

import java.util.List;

public class WorldGenUtil {

    public static void generate(StructureWorldAccess world, float probability, Random random, List<BlockPos> leavePositions) {
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
