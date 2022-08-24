package net.orcinus.goodending.world.gen.features.decorators;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingWorldGen;

public class DenseBirchLeaveDecorator extends TreeDecorator {
    public static final DenseBirchLeaveDecorator INSTANCE = new DenseBirchLeaveDecorator();
    public static final Codec<DenseBirchLeaveDecorator> CODEC = Codec.unit(() -> INSTANCE);

    public DenseBirchLeaveDecorator() {
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return GoodEndingWorldGen.DENSE_BIRCH_LEAVES;
    }

    @Override
    public void generate(Generator generator) {
        BlockPos blockPos = generator.getLeavesPositions().stream().filter(pos -> generator.getWorld().testBlockState(pos, blockState -> blockState.isOf(Blocks.BIRCH_LEAVES))).toList().get(generator.getRandom().nextInt(generator.getLeavesPositions().size()));
        generator.replace(blockPos, GoodEndingBlocks.DENSE_BIRCH_LEAVES.getDefaultState());
    }

}
