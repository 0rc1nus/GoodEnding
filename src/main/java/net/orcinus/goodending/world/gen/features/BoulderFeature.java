package net.orcinus.goodending.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class BoulderFeature extends Feature<BoulderConfig> {

    public BoulderFeature(Codec<BoulderConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<BoulderConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        BoulderConfig config = context.getConfig();
        BlockState downState = world.getBlockState(blockPos.down());
        if (!((downState.isIn(BlockTags.TERRACOTTA) || downState.isIn(BlockTags.SAND) || downState.isIn(BlockTags.DIRT)) && (world.getBlockState(blockPos).isOf(Blocks.WATER) || world.getBlockState(blockPos).isAir()))) {
            return false;
        } else {
            int size = random.nextInt(2);
            if (size == 0) {
                for (int x = -1; x <= 2; x++) {
                    for (int z = -1; z <= 2; z++) {
                        for (int y = 0; y <= 1; y++) {
                            BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                            if ((x == -1 || x == 2) && (z == -1 || z == 2)) continue;
                            if (((z == -1 || z == 2) && y == 1) || ((x == -1 || x == 2) && y == 1)) continue;
                            BlockState blockState = config.secondaryStone.getBlockState(random, pos);
                            if (y > 0) {
                                blockState = config.stone.getBlockState(random, pos);
                            }
                            if (world.isAir(pos) || world.getBlockState(pos).getMaterial().isReplaceable()) {
                                world.setBlockState(pos, blockState, 2);
                            }
                        }
                    }
                }
            }  else if (size == 1) {
                for (int x = -1; x <= 1; x++) {
                    for (int z = -1; z <= 1; z++) {
                        for (int y = -1; y <= 1; y++) {
                            BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                            if (((z == -1 || z == 1) || (x == -1 || x == 1)) && y == 1) continue;
                            if (world.isAir(pos) || world.getBlockState(pos).getMaterial().isReplaceable()) {
                                BlockState blockState = config.secondaryStone.getBlockState(random, pos);
                                if (y > 0) {
                                    blockState = config.stone.getBlockState(random, pos);
                                }
                                world.setBlockState(pos, blockState, 2);
                            }
                        }
                    }
                }
            }
//            else if (size == 1) {
//            }
//            else if (size == 2) {
//
//            }
//            if (random.nextBoolean()) {
//                for (int x = -1; x <= 2; x++) {
//                    for (int z = -1; z <= 2; z++) {
//                        boolean b = x == -1 || x == 2;
//                        boolean b1 = z == -1 || z == 2;
//                        boolean b3 = x <= 1 && (z == -1 || z == 2);
//                        boolean b2 = (x == -1) || (x == 2);
//                        if (b && b1) continue;
//                        boolean b4 = b3 || b2;
//                        int height = b4 ? MathHelper.nextInt(random, 0, 2) : MathHelper.nextInt(random, 3, 5);
//                        if (random.nextInt(2) == 0 && !b4) {
//                            height *= 1.2D;
//                        }
//                        for (int y = 0; y <= height; y++) {
//                            BlockState stone = config.stone.getBlockState(random, blockPos.add(x, y, z));
//                            if (b4) {
//                                stone = config.secondaryStone.getBlockState(random, blockPos.add(x, y, z));
//                            }
//                            world.setBlockState(blockPos.add(x, y, z), stone, 2);
//                        }
//                    }
//                }
//            } else {
//                this.generateBoulder(world, blockPos, random, config, radius);
//            }
            return true;
        }
    }

    private void generateBoulder(StructureWorldAccess world, BlockPos blockPos, Random random, BoulderConfig config, int radius) {
        int height = radius * 2 + 1;
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                for (int y = -height; y <= height; y++) {
                    BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                    boolean flag = (x == -radius || x == radius) && (z == -radius || z == radius);
                    if (flag && random.nextFloat() < 0.5F) {
                        continue;
                    }
                    for (int t = 0; t < world.getBottomY(); t++) {
                        if (y == 0 && (world.getBlockState(pos.down(t)).isOf(Blocks.WATER) || world.getBlockState(pos.down(t)).isAir())) {
                            pos = pos.down(t);
                        }
                    }
                    if (x * x + y * y + z * z <= 2 * (radius * radius) && world.getBlockState(pos).getMaterial().isReplaceable()) {
                        if (y == 2 && random.nextFloat() < 0.35F && ((x * x + z * z) >= radius * radius)) {
                            continue;
                        }
                        world.setBlockState(pos, y <= 2 && (y == 1 && random.nextFloat() < 0.65F) ? config.secondaryStone.getBlockState(random, pos) : config.stone.getBlockState(random, pos), 2);
                    }
                }
            }
        }
    }
}
