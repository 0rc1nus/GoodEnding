package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.LargeLilyPadFeature;
import net.orcinus.goodending.world.gen.features.CypressTreeFeature;
import net.orcinus.goodending.world.gen.features.DuckweedFeature;
import net.orcinus.goodending.world.gen.features.FallenLogFeature;
import net.orcinus.goodending.world.gen.features.HalfWaterloggedDecorationFeature;
import net.orcinus.goodending.world.gen.features.ShallowWaterFeature;
import net.orcinus.goodending.world.gen.features.MuddyOakTreeFeature;
import net.orcinus.goodending.world.gen.features.TallBirchTreeFeature;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

import java.util.Map;

public class GoodEndingFeatures {

    public static final Map<Feature<?>, String> FEATURES = Maps.newLinkedHashMap();

    public static final Feature<FallenLogConfig> FALLEN_LOG = registerFeature("fallen_log", new FallenLogFeature(FallenLogConfig.CODEC));
    public static final Feature<HalfWaterloggedDecorationConfig> HALF_WATERLOGGED_DECORATION = registerFeature("half_waterlogged_decoration", new HalfWaterloggedDecorationFeature(HalfWaterloggedDecorationConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> SHALLOW_WATER = registerFeature("shallow_water", new ShallowWaterFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<WaterTreeFeatureConfig> CYPRESS_TREE = registerFeature("cypress_tree", new CypressTreeFeature(WaterTreeFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> DUCKWEED_PATCH = registerFeature("duckweed_patch", new DuckweedFeature(DefaultFeatureConfig.CODEC));
    public static final Feature<TreeFeatureConfig> MUDDY_OAK_TREE_FEATURE = registerFeature("swamp_tree", new MuddyOakTreeFeature(TreeFeatureConfig.CODEC));
    public static final Feature<TreeFeatureConfig> TALL_BIRCH_TREE = registerFeature("tall_birch_tree", new TallBirchTreeFeature(TreeFeatureConfig.CODEC));
    public static final Feature<DefaultFeatureConfig> LARGE_LILY_PADS = registerFeature("large_lily_pad", new LargeLilyPadFeature(DefaultFeatureConfig.CODEC));

    public static <FC extends FeatureConfig, F extends Feature<FC>> F registerFeature(String name, F feature) {
        FEATURES.put(feature, name);
        return feature;
    }

    public static void init() {
        FEATURES.keySet().forEach(feature -> Registry.register(Registry.FEATURE, new Identifier(GoodEnding.MODID, FEATURES.get(feature)), feature));
    }

}