package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.util.WorldGenUtil;

import java.util.List;

public class FancyDarkOakFeature extends Feature<DefaultFeatureConfig> {

    public FancyDarkOakFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        int baseRadius = 2;
        List<BlockPos> list = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        if (!world.getBlockState(blockPos.down()).isIn(BlockTags.DIRT)) {
            return false;
        } else {
            for (int x = -baseRadius; x <= baseRadius; x++) {
                for (int z = -baseRadius; z <= baseRadius; z++) {
                    for (int y = 0; y < 7; y++) {
                        BlockPos pos = new BlockPos(blockPos.getX() + x, blockPos.getY() + y, blockPos.getZ() + z);
                        boolean flag = x == -baseRadius || x == baseRadius;
                        boolean flag1 = z == -baseRadius || z == baseRadius;
                        if (!(flag && flag1)) {
                            if (((x == 1 && flag1) || (x == -1 && flag1) || (z == 1 && flag) || (z == -1 && flag)) && y > 1) {
                                continue;
                            }
                            if (((x == 0 && flag1) || (z == 0 && flag)) && y > 2) {
                                continue;
                            }
                            if (((x == 1 || x == -1) && (z == 1 || z == -1) && y > 3)) {
                                continue;
                            }
                            if (y > 4 && random.nextInt(3) == 0) {
                                list.add(pos);
                            }
                            world.setBlockState(pos, Blocks.DARK_OAK_LOG.getDefaultState(), 19);
                        }
                    }
                }
            }
            for (BlockPos pos : list) {
                Direction direction = Direction.Type.HORIZONTAL.random(random);
                int length = UniformIntProvider.create(3, 4).get(random);
                for (int i = 0; i < length; i++) {
                    if (world.isAir(pos.offset(direction, i))) {
                        world.setBlockState(pos.offset(direction, i), Blocks.DARK_OAK_LOG.getDefaultState().with(PillarBlock.AXIS, direction.getAxis()), 2);
                    }
                }
                for (int i = 0; i < length; i++) {
                    int i1 = MathHelper.nextInt(random, 3, 5);
                    for (int t = 0; t <= i1; t++) {
                        world.setBlockState(pos.offset(direction, length).up(t), Blocks.DARK_OAK_LOG.getDefaultState(), 2);
                    }
                    leavePoses.add(pos.offset(direction, length).up(i1));
                }
            }
            List<BlockPos> vinePos = Lists.newArrayList();
            for (BlockPos leavePos : leavePoses) {
                for (int yy = -1; yy < 1; yy++) {
                    for (int xx = -2; xx <= 2; xx++) {
                        for (int zz = -2; zz <= 2; zz++) {
                            BlockPos placePos = new BlockPos(leavePos.getX() + xx, leavePos.getY() + yy, leavePos.getZ() + zz);
                            boolean b = (xx == -2 || xx == 2) && (zz == -2 || zz == 2);
                            boolean bottomFlag = (xx == -2 || xx == -1 || xx == 1 || xx == 2) && (zz == -2 || zz == -1 || zz == 1 || zz == 2);
                            if (b) continue;
                            if (yy == 0 && (xx == 2 || xx == -2 || zz == 2 || zz == -2)) continue;
                            world.setBlockState(placePos, Blocks.DARK_OAK_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19);
                            if (random.nextInt(3) == 0) {
                                vinePos.add(placePos);
                            }
                        }
                    }
                }
            }
            WorldGenUtil.generateHangingVines(world, 0.4F, random, vinePos);
            return true;
        }
    }
}
