package net.orcinus.goodending.mixin;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.orcinus.goodending.world.gen.features.decorators.DenseBirchLeaveDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ConfiguredFeatures.class)
public class ConfiguredFeaturesMixin {
    @Inject(at = @At("HEAD"), method = "register(Ljava/lang/String;Lnet/minecraft/world/gen/feature/Feature;Lnet/minecraft/world/gen/feature/FeatureConfig;)Lnet/minecraft/util/registry/RegistryEntry;")
    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(String id, F feature, FC config, CallbackInfoReturnable<RegistryEntry<ConfiguredFeature<FC, ?>>> cir) {
        if (config instanceof TreeFeatureConfig treeFeatureConfig) {
            treeFeatureConfig.decorators.add(new DenseBirchLeaveDecorator());
        }
    }

}
