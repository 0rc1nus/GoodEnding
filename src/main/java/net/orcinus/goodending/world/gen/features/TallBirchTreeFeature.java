package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.DripstoneHelper;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.List;

public class TallBirchTreeFeature extends Feature<TreeFeatureConfig> {

    public TallBirchTreeFeature(Codec<TreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        int height = UniformIntProvider.create(5, 11).get(random);
        List<BlockPos> decorationPoses = Lists.newArrayList();
        List<BlockPos> branchPoses = Lists.newArrayList();
        List<BlockPos> leavePoses = Lists.newArrayList();
        if (!world.isAir(blockPos)) {
            return false;
        }
        for (int i = 0; i <= height; i++) {
            if (world.testBlockState(blockPos.up(i), blockState -> blockState.isAir() || blockState.isOf(Blocks.WATER) || blockState.isIn(BlockTags.LEAVES))) {
                world.setBlockState(blockPos.up(i), Blocks.BIRCH_LOG.getDefaultState(), 19);
                decorationPoses.add(blockPos.up(i));
                if (i >= height / 2) {
                    branchPoses.add(blockPos.up(i));
                }
            }
        }
        for (int y = -1; y <= 2; y++) {
            int radius = y >= 1 ? 1 : 2;
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos leavePos = blockPos.add(x, y + height, z);
                    if (world.testBlockState(leavePos, DripstoneHelper::canGenerate)) {
                        world.setBlockState(leavePos, Blocks.BIRCH_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19);
                        leavePoses.add(leavePos);
                    }
                }
            }
        }
        world.setBlockState(leavePoses.get(world.getRandom().nextInt(leavePoses.size())), GoodEndingBlocks.DENSE_BIRCH_LEAVES.getDefaultState().with(LeavesBlock.DISTANCE, 1), 19);
        decorationPoses.forEach(pos -> {
            Direction direction = Direction.Type.HORIZONTAL.random(random);
            BlockPos offset = pos.offset(direction);
            if (random.nextInt(10) == 0 && world.isAir(offset)) {
                world.setBlockState(offset, GoodEndingBlocks.BIRCH_MUSHROOM.getDefaultState().with(BirchMushroomPlantBlock.FACING, direction), 2);
            }
        });
        branchPoses.forEach(branchPos -> {
            Direction randomDirection = Direction.Type.HORIZONTAL.random(random);
            BlockPos offsetPos = branchPos.offset(randomDirection);
            if (random.nextInt(5) == 0 && world.isAir(offsetPos)) {
                world.setBlockState(offsetPos, Blocks.BIRCH_LOG.getDefaultState().with(PillarBlock.AXIS, randomDirection.getAxis()), 19);
                if (world.isAir(offsetPos.down()) && random.nextFloat() < 0.01F) {
                    this.setBeehive(world, random, offsetPos);
                }
            }
        });
        return false;
    }

    private void setBeehive(StructureWorldAccess world, Random random, BlockPos offsetPos) {
        world.setBlockState(offsetPos.down(), Blocks.BEE_NEST.getDefaultState().with(BeehiveBlock.FACING, Direction.SOUTH), 2);
        world.getBlockEntity(offsetPos.down(), BlockEntityType.BEEHIVE).ifPresent(blockEntity -> {
            int i = 2 + random.nextInt(2);
            for (int j = 0; j < i; ++j) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putString("id", Registry.ENTITY_TYPE.getId(EntityType.BEE).toString());
                blockEntity.addBee(nbtCompound, random.nextInt(599), false);
            }
        });
    }
}
