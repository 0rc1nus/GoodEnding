package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BeehiveBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.DripstoneUtils;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class TallBirchTreeFeature extends Feature<TreeConfiguration> {

    public TallBirchTreeFeature(Codec<TreeConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<TreeConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        int height = UniformInt.of(6, 12).sample(random);
        if (random.nextInt(3) == 0 && height <= 10) {
            height *= 1.25;
        }
        TreeConfiguration config = context.config();
        List<BlockPos> decorationPoses = Lists.newArrayList();
        List<BlockPos> branchPoses = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        if (!(world.isEmptyBlock(blockPos) || world.getBlockState(blockPos).is(Blocks.BIRCH_SAPLING))) {
            return false;
        }
        for (int i = 0; i <= height; i++) {
            if (world.isStateAtPosition(blockPos.above(i), blockState -> blockState.is(Blocks.BIRCH_SAPLING) || blockState.isAir() || blockState.is(Blocks.WATER) || blockState.is(BlockTags.LEAVES))) {
                world.setBlock(blockPos.above(i), config.trunkProvider.getState(random, blockPos.above(i)), 19);
                decorationPoses.add(blockPos.above(i));
                if (i >= height / 2) {
                    branchPoses.add(blockPos.above(i));
                }
            }
        }
        for (int y = -3; y <= 2; y++) {
            int radius = y <= -2 ? 1 : (y >= 1 ? 1 : 2);
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos leavePos = blockPos.offset(x, y + height, z);
                    if (world.isStateAtPosition(leavePos, DripstoneUtils::isEmptyOrWater)) {
                        boolean flag = (x == radius || x == -radius) && (z == radius || z == -radius);
                        if (flag) {
                            if ((y >= -1 && y < 1) || y == 2 || y == -3) continue;
                        }
                        world.setBlock(leavePos, config.foliageProvider.getState(random, leavePos).setValue(LeavesBlock.DISTANCE, 1), 19);
                        leavePoses.add(leavePos);
                    }
                }
            }
        }
        world.setBlock(leavePoses.get(world.getRandom().nextInt(leavePoses.size())), GoodEndingBlocks.DENSE_BIRCH_LEAVES.defaultBlockState().setValue(LeavesBlock.DISTANCE, 1), 19);
        decorationPoses.forEach(pos -> {
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos offset = pos.relative(direction);
            if (random.nextInt(10) == 0 && world.isEmptyBlock(offset)) {
                world.setBlock(offset, GoodEndingBlocks.BIRCH_MUSHROOM.defaultBlockState().setValue(BirchMushroomPlantBlock.FACING, direction), 2);
            }
        });
        branchPoses.forEach(branchPos -> {
            Direction randomDirection = Direction.Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos offsetPos = branchPos.relative(randomDirection);
            if (random.nextInt(3) == 0 && world.isEmptyBlock(offsetPos) && world.isEmptyBlock(offsetPos.below())) {
                world.setBlock(offsetPos, config.trunkProvider.getState(random, offsetPos).setValue(RotatedPillarBlock.AXIS, randomDirection.getAxis()), 19);
                if (world.isEmptyBlock(offsetPos.below()) && random.nextFloat() < 0.02F) {
                    this.setBeehive(world, random, offsetPos, randomDirection);
                }
            }
        });
        return true;
    }

    private void setBeehive(WorldGenLevel world, RandomSource random, BlockPos offsetPos, Direction direction) {
        if (world.getBlockState(offsetPos.relative(direction)).is(BlockTags.LOGS)) {
            direction = direction.getOpposite();
        }
        world.setBlock(offsetPos.below(), Blocks.BEE_NEST.defaultBlockState().setValue(BeehiveBlock.FACING, direction), 2);
        world.getBlockEntity(offsetPos.below(), BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
            int i = 2 + random.nextInt(2);
            for (int j = 0; j < i; ++j) {
                CompoundTag nbtCompound = new CompoundTag();
                nbtCompound.putString("id", BuiltInRegistries.ENTITY_TYPE.getKey(EntityType.BEE).toString());
                blockEntity.storeBee(nbtCompound, random.nextInt(599), false);
            }
        });
    }
}