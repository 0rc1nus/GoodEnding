package net.orcinus.goodending.mixin;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.FancyTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.orcinus.goodending.world.gen.features.decorators.HangingLeavesDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(FeatureUtils.class)
public class ConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "register(Ljava/lang/String;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)Lnet/minecraft/core/Holder;")
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(String p_206489_, F p_206490_, FC featureConfig, CallbackInfoReturnable<Holder<ConfiguredFeature<FC, ?>>> cir) {
        if (featureConfig instanceof TreeConfiguration treeFeatureConfig) {
            TrunkPlacer trunkPlacer = treeFeatureConfig.trunkPlacer;
            List<TreeDecorator> decorators = treeFeatureConfig.decorators;
            if (trunkPlacer instanceof FancyTrunkPlacer) {
                decorators.add(new HangingLeavesDecorator(false));
            }
            if (trunkPlacer instanceof DarkOakTrunkPlacer) {
                decorators.add(new HangingLeavesDecorator(true));
            }
        }
    }

}
