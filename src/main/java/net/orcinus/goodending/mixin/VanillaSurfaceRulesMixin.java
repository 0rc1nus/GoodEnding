package net.orcinus.goodending.mixin;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.orcinus.goodending.init.GoodEndingBiomes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SurfaceRuleData.class)
public class VanillaSurfaceRulesMixin {

    @Shadow
    @Final
    private static SurfaceRules.RuleSource WATER;

    @Shadow @Final private static SurfaceRules.RuleSource COARSE_DIRT;

    @Shadow @Final private static SurfaceRules.RuleSource PODZOL;

    @Shadow @Final private static SurfaceRules.RuleSource MUD;

    @ModifyVariable(at = @At("STORE"), method = "overworldLike", ordinal = 8)
    private static SurfaceRules.RuleSource GE$createDefaultRule(SurfaceRules.RuleSource materialRule) {
        SurfaceRules.RuleSource ifTrue = SurfaceRules.ifTrue(SurfaceRules.isBiome(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseThreshold(1.75), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseThreshold(-0.75), PODZOL), COARSE_DIRT));
        SurfaceRules.RuleSource swamp = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(GoodEndingBiomes.MARSHY_SWAMP_KEY), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)))));
        return SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(SurfaceRules.isBiome(Biomes.SWAMP), SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SURFACE, -0.0575, 0.0575), MUD), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)))))), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, swamp), SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ifTrue), materialRule);
    }

    private static SurfaceRules.ConditionSource surfaceNoiseThreshold(double min) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, min / 8.25, Double.MAX_VALUE);
    }

}
