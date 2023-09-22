package net.orcinus.goodending.mixin;

import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(FeatureUtils.class)
public class ConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "register(Lnet/minecraft/data/worldgen/BootstapContext;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/levelgen/feature/Feature;Lnet/minecraft/world/level/levelgen/feature/configurations/FeatureConfiguration;)V")
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> bootstapContext, ResourceKey<ConfiguredFeature<?, ?>> resourceKey, F feature, FC featureConfiguration, CallbackInfo ci) {
        if (featureConfiguration instanceof TreeConfiguration treeFeatureConfig) {
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
