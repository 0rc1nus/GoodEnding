package net.orcinus.goodending.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeConfiguredFeatures.class)
public class TreeConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "oak", cancellable = true)
    private static void GE$oak(CallbackInfoReturnable<TreeFeatureConfig.Builder> cir) {
        cir.setReturnValue(builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 7, 2, 0, 2).ignoreVines());
    }

    @Inject(at = @At("HEAD"), method = "birch", cancellable = true)
    private static void GE$birch(CallbackInfoReturnable<TreeFeatureConfig.Builder> cir) {
        cir.setReturnValue(builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 8, 2, 0, 2).ignoreVines());
    }

    private static TreeFeatureConfig.Builder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeFeatureConfig.Builder(BlockStateProvider.of(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.of(leaves), new BlobFoliagePlacer(ConstantIntProvider.create(radius), ConstantIntProvider.create(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

}
