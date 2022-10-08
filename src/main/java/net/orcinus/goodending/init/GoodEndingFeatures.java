package net.orcinus.goodending.init;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.AlgaePatchFeature;
import net.orcinus.goodending.world.gen.features.BoulderFeature;
import net.orcinus.goodending.world.gen.features.CypressTreeFeature;
import net.orcinus.goodending.world.gen.features.DuckweedFeature;
import net.orcinus.goodending.world.gen.features.FallenLogFeature;
import net.orcinus.goodending.world.gen.features.FancyDarkOakFeature;
import net.orcinus.goodending.world.gen.features.HalfWaterloggedDecorationFeature;
import net.orcinus.goodending.world.gen.features.LargeLilyPadFeature;
import net.orcinus.goodending.world.gen.features.MuddyOakTreeFeature;
import net.orcinus.goodending.world.gen.features.ShallowWaterFeature;
import net.orcinus.goodending.world.gen.features.TallBirchTreeFeature;
import net.orcinus.goodending.world.gen.features.config.BoulderConfig;
import net.orcinus.goodending.world.gen.features.config.FallenLogConfig;
import net.orcinus.goodending.world.gen.features.config.FancyDarkOakTreeConfig;
import net.orcinus.goodending.world.gen.features.config.HalfWaterloggedDecorationConfig;
import net.orcinus.goodending.world.gen.features.config.MuddyOakFeatureConfig;
import net.orcinus.goodending.world.gen.features.config.ShallowWaterConfig;
import net.orcinus.goodending.world.gen.features.config.WaterTreeFeatureConfig;

public class GoodEndingFeatures {

    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, GoodEnding.MODID);

    public static final RegistryObject<Feature<FallenLogConfig>> FALLEN_LOG = FEATURES.register("fallen_log", () -> new FallenLogFeature(FallenLogConfig.CODEC));
    public static final RegistryObject<Feature<HalfWaterloggedDecorationConfig>> HALF_WATERLOGGED_DECORATION = FEATURES.register("half_waterlogged_decoration", () -> new HalfWaterloggedDecorationFeature(HalfWaterloggedDecorationConfig.CODEC));
    public static final RegistryObject<Feature<ShallowWaterConfig>> SHALLOW_WATER = FEATURES.register("shallow_water", () -> new ShallowWaterFeature(ShallowWaterConfig.CODEC));
    public static final RegistryObject<Feature<WaterTreeFeatureConfig>> CYPRESS_TREE = FEATURES.register("cypress_tree", () -> new CypressTreeFeature(WaterTreeFeatureConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> DUCKWEED_PATCH = FEATURES.register("duckweed_patch", () -> new DuckweedFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<MuddyOakFeatureConfig>> MUDDY_OAK_TREE_FEATURE = FEATURES.register("swamp_tree", () -> new MuddyOakTreeFeature(MuddyOakFeatureConfig.CODEC));
    public static final RegistryObject<Feature<TreeConfiguration>> TALL_BIRCH_TREE = FEATURES.register("tall_birch_tree", () -> new TallBirchTreeFeature(TreeConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> LARGE_LILY_PADS = FEATURES.register("large_lily_pad", () -> new LargeLilyPadFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<FancyDarkOakTreeConfig>> FANCY_DARK_OAK = FEATURES.register("fancy_dark_oak", () -> new FancyDarkOakFeature(FancyDarkOakTreeConfig.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ALGAE_PATCH = FEATURES.register("algae_patch", () -> new AlgaePatchFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<BoulderConfig>> BOULDER = FEATURES.register("boulder", () -> new BoulderFeature(BoulderConfig.CODEC));

}