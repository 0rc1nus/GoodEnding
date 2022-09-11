package net.orcinus.goodending.mixin;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import net.orcinus.goodending.init.GoodEndingBiomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(VanillaSurfaceRules.class)
public class VanillaSurfaceRulesMixin {

    @Shadow
    @Final
    private static MaterialRules.MaterialRule WATER;

    @Shadow @Final private static MaterialRules.MaterialRule COARSE_DIRT;

    @Shadow @Final private static MaterialRules.MaterialRule PODZOL;

    @ModifyVariable(method = "createDefaultRule", at = @At("STORE"), ordinal = 8)
    private static MaterialRules.MaterialRule injected(MaterialRules.MaterialRule materialRule) {
        MaterialRules.MaterialRule condition = MaterialRules.condition(MaterialRules.biome(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), MaterialRules.sequence(MaterialRules.condition(surfaceNoiseThreshold(1.75), COARSE_DIRT), MaterialRules.condition(surfaceNoiseThreshold(-0.75), PODZOL), COARSE_DIRT));
        MaterialRules.MaterialRule swamp = MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(GoodEndingBiomes.MARSHY_SWAMP_KEY), MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(62), 0), MaterialRules.condition(MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(63), 0)), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), WATER)))));
        return MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, swamp), MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, condition), materialRule);
    }

    private static MaterialRules.MaterialCondition surfaceNoiseThreshold(double min) {
        return MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE, min / 8.25, Double.MAX_VALUE);
    }

}
