package net.orcinus.goodending.mixin;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
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

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/sapling/SaplingGenerator;generate(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/random/Random;)Z"), method = "generate", cancellable = true)
    private void GE$generate(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        List<BlockPos> grassPos = Lists.newArrayList();
        if (state.isOf(Blocks.OAK_SAPLING)) {
            if (world.getBlockState(pos.down()).isOf(Blocks.MUD)) {
                ci.cancel();
                SwampSaplingGenerator swampSaplingGenerator = new SwampSaplingGenerator();
                swampSaplingGenerator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
            }
            else if (world.getBlockState(pos.down()).isOf(Blocks.PODZOL)) {
                ci.cancel();
                LargeOakSaplingGenerator largeOakSaplingGenerator = new LargeOakSaplingGenerator();
                largeOakSaplingGenerator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
            }
        }
        if (state.isOf(Blocks.DARK_OAK_SAPLING) && world.getBlockState(pos.down()).isOf(Blocks.PODZOL)) {
            ci.cancel();
            FancyDarkOakSaplingGenerator fancyDarkOakSaplingGenerator = new FancyDarkOakSaplingGenerator();
            fancyDarkOakSaplingGenerator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
        }
        if (state.isOf(Blocks.BIRCH_SAPLING)) {
            if (this.isTallGrass(world, pos.east()) && this.isTallGrass(world, pos.south()) && this.isTallGrass(world, pos.west()) && this.isTallGrass(world, pos.north()) && this.isTallGrass(world, pos.add(-1, 0, 1)) && this.isTallGrass(world, pos.add(-1, 0, -1)) && this.isTallGrass(world, pos.add(1, 0, -1)) && this.isTallGrass(world, pos.add(1, 0, 1))) {
                ci.cancel();
                TallBirchSaplingGenerator tallBirchSaplingGenerator = new TallBirchSaplingGenerator();
                tallBirchSaplingGenerator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
            }
        }
    }

    private boolean isTallGrass(ServerWorld world, BlockPos blockPos) {
        return world.getBlockState(blockPos).isOf(Blocks.TALL_GRASS);
    }

}
