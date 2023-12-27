package net.orcinus.goodending.init;

import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class GoodEndingSurfaceRules {
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource COARSE_DIRT = makeStateRule(Blocks.COARSE_DIRT);
    private static final SurfaceRules.RuleSource PODZOL = makeStateRule(Blocks.PODZOL);

    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.RuleSource ifTrue = SurfaceRules.ifTrue(SurfaceRules.isBiome(GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY), SurfaceRules.sequence(SurfaceRules.ifTrue(surfaceNoiseThreshold(1.75), COARSE_DIRT), SurfaceRules.ifTrue(surfaceNoiseThreshold(-0.75), PODZOL), COARSE_DIRT));
        SurfaceRules.RuleSource swamp = SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(GoodEndingBiomes.MARSHY_SWAMP_KEY), SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0), SurfaceRules.ifTrue(SurfaceRules.not(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)), SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER)))));
        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(
                        SurfaceRules.ON_FLOOR,
                        SurfaceRules.ifTrue(
                                SurfaceRules.isBiome(Biomes.SWAMP),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(Noises.SURFACE, -0.0575, 0.0575), MUD),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0),
                                                SurfaceRules.ifTrue(
                                                        SurfaceRules.not(
                                                                SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0)
                                                        ),
                                                        SurfaceRules.ifTrue(
                                                                SurfaceRules.noiseCondition(Noises.SWAMP, 0.0), WATER
                                                        ))
                                        )))),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, swamp),
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, ifTrue));
    }

    private static SurfaceRules.ConditionSource surfaceNoiseThreshold(double min) {
        return SurfaceRules.noiseCondition(Noises.SURFACE, min / 8.25, Double.MAX_VALUE);
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
