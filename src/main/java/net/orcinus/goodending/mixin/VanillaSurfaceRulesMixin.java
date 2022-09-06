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

    @Shadow @Final private static MaterialRules.MaterialRule DIRT;

    @ModifyVariable(method = "createDefaultRule", at = @At("STORE"), ordinal = 8)
    private static MaterialRules.MaterialRule injected(MaterialRules.MaterialRule materialRule) {
        MaterialRules.MaterialRule condition = MaterialRules.condition(MaterialRules.biome(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, DIRT), COARSE_DIRT));
        return MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(GoodEndingBiomes.MARSHY_SWAMP_KEY), MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(62), 0), MaterialRules.condition(MaterialRules.not(MaterialRules.aboveY(YOffset.fixed(63), 0)), MaterialRules.condition(MaterialRules.noiseThreshold(NoiseParametersKeys.SURFACE_SWAMP, 0.0), WATER)))))), condition, materialRule);
    }

}
