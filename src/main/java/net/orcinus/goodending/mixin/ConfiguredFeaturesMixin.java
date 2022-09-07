package net.orcinus.goodending.mixin;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.orcinus.goodending.world.gen.features.decorators.HangingLeavesDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {

    @Inject(at = @At("HEAD"), method = "register(Ljava/lang/String;Lnet/minecraft/world/gen/feature/Feature;Lnet/minecraft/world/gen/feature/FeatureConfig;)Lnet/minecraft/util/registry/RegistryEntry;")
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(String id, F feature, FC featureConfig, CallbackInfoReturnable<RegistryEntry<ConfiguredFeature<FC, ?>>> cir) {
        if (featureConfig instanceof TreeFeatureConfig treeFeatureConfig) {
            TrunkPlacer trunkPlacer = treeFeatureConfig.trunkPlacer;
            List<TreeDecorator> decorators = treeFeatureConfig.decorators;
            if (trunkPlacer instanceof LargeOakTrunkPlacer) {
                decorators.add(new HangingLeavesDecorator(false));
            }
            if (trunkPlacer instanceof DarkOakTrunkPlacer) {
                decorators.add(new HangingLeavesDecorator(true));
            }
        }
    }

}
