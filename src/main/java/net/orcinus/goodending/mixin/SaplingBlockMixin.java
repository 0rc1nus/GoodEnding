package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.goodending.world.gen.features.generators.FancyDarkOakSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.LargeOakSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.SwampSaplingGenerator;
import net.orcinus.goodending.world.gen.features.generators.TallBirchSaplingGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/grower/AbstractTreeGrower;growTree(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;)Z"), method = "advanceTree", cancellable = true)
    private void GE$growTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource random, CallbackInfo ci) {
        if (state.is(Blocks.OAK_SAPLING)) {
            if (world.getBlockState(pos.below()).is(Blocks.MUD)) {
                ci.cancel();
                SwampSaplingGenerator swampSaplingGenerator = new SwampSaplingGenerator();
                swampSaplingGenerator.growTree(world, world.getChunkSource().getGenerator(), pos, state, random);
            }
            else if (world.getBlockState(pos.below()).is(Blocks.PODZOL)) {
                ci.cancel();
                LargeOakSaplingGenerator largeOakSaplingGenerator = new LargeOakSaplingGenerator();
                largeOakSaplingGenerator.growTree(world, world.getChunkSource().getGenerator(), pos, state, random);
            }
        }
        if (state.is(Blocks.DARK_OAK_SAPLING) && world.getBlockState(pos.below()).is(Blocks.PODZOL)) {
            ci.cancel();
            FancyDarkOakSaplingGenerator fancyDarkOakSaplingGenerator = new FancyDarkOakSaplingGenerator();
            fancyDarkOakSaplingGenerator.growTree(world, world.getChunkSource().getGenerator(), pos, state, random);
        }
        if (state.is(Blocks.BIRCH_SAPLING)) {
            if (this.isTallGrass(world, pos.east()) && this.isTallGrass(world, pos.south()) && this.isTallGrass(world, pos.west()) && this.isTallGrass(world, pos.north()) && this.isTallGrass(world, pos.offset(-1, 0, 1)) && this.isTallGrass(world, pos.offset(-1, 0, -1)) && this.isTallGrass(world, pos.offset(1, 0, -1)) && this.isTallGrass(world, pos.offset(1, 0, 1))) {
                ci.cancel();
                TallBirchSaplingGenerator tallBirchSaplingGenerator = new TallBirchSaplingGenerator();
                tallBirchSaplingGenerator.growTree(world, world.getChunkSource().getGenerator(), pos, state, random);
            }
        }
    }

    private boolean isTallGrass(ServerLevel world, BlockPos blockPos) {
        return world.getBlockState(blockPos).is(Blocks.TALL_GRASS);
    }

}
