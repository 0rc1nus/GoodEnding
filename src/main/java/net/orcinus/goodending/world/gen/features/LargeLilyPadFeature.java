package net.orcinus.goodending.world.gen.features;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.goodending.init.GoodEndingBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class LargeLilyPadFeature extends Feature<NoneFeatureConfiguration> {

    public LargeLilyPadFeature(Codec<NoneFeatureConfiguration> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos blockPos = context.origin();
        RandomSource random = context.random();
        List<BlockPos> pos = Lists.newArrayList();
        int size = Mth.nextInt(random, 1, 4);
        if (size == 1) {
            pos.add(blockPos);
            pos.add(blockPos.east());
            pos.add(blockPos.south());
            pos.add(blockPos.east().south());
            if (this.hasEnoughSpace(pos, world)) {
                this.placeLargeLilypad(pos, world);
                return true;
            }
        } else if (size == 2) {
            this.addLargeLilypads(pos, blockPos, 1, false, false);
            if (this.hasEnoughSpace(pos, world)) {
                this.placeLargeLilypad(pos, world);
                return true;
            }
        } else if (size == 3) {
            this.addLargeLilypads(pos, blockPos, 2, false, true);
            if (this.hasEnoughSpace(pos, world)) {
                this.placeLargeLilypad(pos, world);
                return true;
            }
        } else {
            this.addLargeLilypads(pos, blockPos, 2, true, true);
            if (this.hasEnoughSpace(pos, world)) {
                this.placeLargeLilypad(pos, world);
                return true;
            }
        }
        return false;
    }

    private void addLargeLilypads(List<BlockPos> pos, BlockPos origin, int range, boolean even, boolean corners) {
        int minRange = range;
        int maxRange = even ? range - 1 : range;
        for (int x = -minRange; x <= maxRange; x++) {
            for (int z = -minRange; z <= maxRange; z++) {
                if (corners && ((x == -minRange || x == maxRange) && (z == -minRange || z == maxRange))) {
                    continue;
                }
                pos.add(new BlockPos(new BlockPos(origin.getX() + x, origin.getY(), origin.getZ() + z)));
            }
        }
    }

    private void placeLargeLilypad(List<BlockPos> pos, WorldGenLevel world) {
        for (BlockPos position : pos) {
            world.setBlock(position.below(), GoodEndingBlocks.LARGE_LILY_PAD.defaultBlockState().setValue(BlockStateProperties.WATERLOGGED, true), 2);
        }
    }

    private boolean hasEnoughSpace(List<BlockPos> pos, WorldGenLevel world) {
        boolean flag = false;
        for (BlockPos position : pos) {
            Predicate<Direction> predicate = direction -> {
                BlockPos belowPos = position.below();
                return !world.getBlockState(belowPos).is(Blocks.WATER) || (world.getBlockState(belowPos.relative(direction)).is(GoodEndingBlocks.LARGE_LILY_PAD) && !world.getBlockState(belowPos.relative(direction)).is(Blocks.WATER));
            };
            Optional<Direction> relativeDir = Arrays.stream(Direction.values()).filter(predicate).findAny();
            if (relativeDir.isPresent()) {
                flag = true;
            }
        }
        return !flag;
    }

}
