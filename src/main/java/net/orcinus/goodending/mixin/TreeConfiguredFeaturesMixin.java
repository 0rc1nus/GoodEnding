package net.orcinus.goodending.mixin;

import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeatures.class)
public class TreeConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "createOak", cancellable = true)
    private static void GE$oak(CallbackInfoReturnable<TreeConfiguration.TreeConfigurationBuilder> cir) {
        cir.setReturnValue(builder(Blocks.OAK_LOG, Blocks.OAK_LEAVES, 5, 2, 0, 2).ignoreVines());
    }

    @Inject(at = @At("HEAD"), method = "createBirch", cancellable = true)
    private static void GE$birch(CallbackInfoReturnable<TreeConfiguration.TreeConfigurationBuilder> cir) {
        cir.setReturnValue(builder(Blocks.BIRCH_LOG, Blocks.BIRCH_LEAVES, 5, 3, 0, 2).ignoreVines());
    }

    private static TreeConfiguration.TreeConfigurationBuilder builder(Block log, Block leaves, int baseHeight, int firstRandomHeight, int secondRandomHeight, int radius) {
        return new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(log), new StraightTrunkPlacer(baseHeight, firstRandomHeight, secondRandomHeight), BlockStateProvider.simple(leaves), new BlobFoliagePlacer(ConstantInt.of(radius), ConstantInt.of(0), 3), new TwoLayersFeatureSize(1, 0, 1));
    }

}
