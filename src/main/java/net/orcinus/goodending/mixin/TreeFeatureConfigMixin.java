package net.orcinus.goodending.mixin;

import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(TreeConfiguration.TreeConfigurationBuilder.class)
public class TreeFeatureConfigMixin {
    @Shadow
    private List<TreeDecorator> decorators;

    @Inject(at = @At("HEAD"), method = "build")
    private void build(CallbackInfoReturnable<TreeConfiguration> cir) {
        decorators = new ArrayList<>(decorators);
    }
}
