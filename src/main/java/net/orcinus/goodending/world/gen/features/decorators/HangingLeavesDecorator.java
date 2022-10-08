package net.orcinus.goodending.world.gen.features.decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.blocks.HangingOakLeavesPlantBlock;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingTreeDecorators;

public class HangingLeavesDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesDecorator> CODEC = RecordCodecBuilder.create(instance -> instance.group(Codec.BOOL.fieldOf("darkOak").forGetter(config -> config.darkOak)).apply(instance, HangingLeavesDecorator::new));

    private final boolean darkOak;

    public HangingLeavesDecorator(boolean darkOak) {
        this.darkOak = darkOak;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return GoodEndingTreeDecorators.HANGING_LEAVES_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        TestableWorld world = generator.getWorld();
        if (generator.getRandom().nextBoolean()) {
            for (BlockPos pos : generator.getLeavesPositions()) {
                Random random = generator.getRandom();
                if (random.nextFloat() < 0.5F && world.testBlockState(pos.down(), AbstractBlock.AbstractBlockState::isAir) && world.testBlockState(pos, blockState -> blockState.isOf(this.darkOak ? Blocks.DARK_OAK_LEAVES : Blocks.OAK_LEAVES))) {
                    int height = MathHelper.nextInt(random, 0, 2);
                    for (int i = 0; i <= height; i++) {
                        BlockPos placePos = pos.down(i);
                        if (i == height) {
                            if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                                generator.replace(placePos, this.darkOak ? GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.getDefaultState().with(HangingLeavesBlock.AGE, MathHelper.nextInt(random, 22, 25)) : GoodEndingBlocks.HANGING_OAK_LEAVES.getDefaultState().with(HangingLeavesBlock.AGE, MathHelper.nextInt(random, 22, 25)));
                                break;
                            }
                        }
                        if (world.testBlockState(placePos, AbstractBlock.AbstractBlockState::isAir)) {
                            generator.replace(placePos, this.darkOak ? GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.getDefaultState() : GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.getDefaultState());
                        }
                    }
                }
            }
        }
    }

}
