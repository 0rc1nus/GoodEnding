package net.orcinus.goodending.world.gen.features.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingTreeDecorators;

public class HangingLeavesDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.fieldOf("darkOak").forGetter(config -> config.darkOak)).apply(instance, HangingLeavesDecorator::new));

    private final boolean darkOak;

    public HangingLeavesDecorator(boolean darkOak) {
        this.darkOak = darkOak;
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return GoodEndingTreeDecorators.HANGING_LEAVES_DECORATOR.get();
    }

    @Override
    public void place(Context generator) {
        LevelSimulatedReader world = generator.level();
        if (generator.random().nextBoolean()) {
            for (BlockPos pos : generator.leaves()) {
                RandomSource random = generator.random();
                if (random.nextFloat() < 0.5F && world.isStateAtPosition(pos.below(), BlockBehaviour.BlockStateBase::isAir) && world.isStateAtPosition(pos, blockState -> blockState.is(this.darkOak ? Blocks.DARK_OAK_LEAVES : Blocks.OAK_LEAVES))) {
                    int height = Mth.nextInt(random, 0, 2);
                    for (int i = 0; i <= height; i++) {
                        BlockPos placePos = pos.below(i);
                        if (i == height) {
                            if (world.isStateAtPosition(placePos, BlockBehaviour.BlockStateBase::isAir)) {
                                generator.setBlock(placePos, this.darkOak ? GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get().defaultBlockState().setValue(HangingLeavesBlock.AGE, Mth.nextInt(random, 22, 25)) : GoodEndingBlocks.HANGING_OAK_LEAVES.get().defaultBlockState().setValue(HangingLeavesBlock.AGE, Mth.nextInt(random, 22, 25)));
                                break;
                            }
                        }
                        if (world.isStateAtPosition(placePos, BlockBehaviour.BlockStateBase::isAir)) {
                            generator.setBlock(placePos, this.darkOak ? GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.get().defaultBlockState() : GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.get().defaultBlockState());
                        }
                    }
                }
            }
        }
    }

}
