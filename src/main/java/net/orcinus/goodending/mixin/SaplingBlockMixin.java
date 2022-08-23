package net.orcinus.goodending.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.orcinus.goodending.world.gen.features.generators.SwampSaplingGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/block/sapling/SaplingGenerator;generate(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/world/gen/chunk/ChunkGenerator;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/random/Random;)Z"), method = "generate", cancellable = true)
    private void GE$generate(ServerWorld world, BlockPos pos, BlockState state, Random random, CallbackInfo ci) {
        if (world.getBlockState(pos.down()).isOf(Blocks.MUD) && state.isOf(Blocks.OAK_SAPLING)) {
            ci.cancel();
            SwampSaplingGenerator swampSaplingGenerator = new SwampSaplingGenerator();
            swampSaplingGenerator.generate(world, world.getChunkManager().getChunkGenerator(), pos, state, random);
        }
    }

}
