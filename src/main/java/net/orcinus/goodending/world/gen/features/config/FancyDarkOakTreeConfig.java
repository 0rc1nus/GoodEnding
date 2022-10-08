package net.orcinus.goodending.world.gen.features.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class FancyDarkOakTreeConfig extends TreeConfiguration {
    public static final Codec<FancyDarkOakTreeConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter(config -> config.trunkPlacer), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(config -> config.foliageProvider), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter(config -> config.foliagePlacer), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter(config -> config.rootPlacer), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter(config -> config.dirtProvider), FeatureSize.CODEC.fieldOf("minimum_size").forGetter(config -> config.minimumSize), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(config -> config.decorators), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter(config -> config.forceDirt), Codec.BOOL.fieldOf("is_fancy").orElse(false).forGetter(config -> config.isFancy), Codec.BOOL.fieldOf("planted").orElse(false).forGetter(config -> config.planted)).apply(instance, FancyDarkOakTreeConfig::new));
    public boolean isFancy;
    public boolean planted;

    public FancyDarkOakTreeConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, BlockStateProvider dirtProvider, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt, boolean isFancy, boolean planted) {
        super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, dirtProvider, minimumSize, decorators, ignoreVines, forceDirt);
        this.isFancy = isFancy;
        this.planted = planted;
    }

    public static class FancyDarkOakTreeConfigBuilder extends TreeConfigurationBuilder {
        public final BlockStateProvider trunkProvider;
        private final TrunkPlacer trunkPlacer;
        public final BlockStateProvider foliageProvider;
        private final FoliagePlacer foliagePlacer;
        private final Optional<RootPlacer> rootPlacer;
        private BlockStateProvider dirtProvider;
        private final FeatureSize minimumSize;
        private List<TreeDecorator> decorators = ImmutableList.of();
        private boolean ignoreVines;
        private boolean forceDirt;
        public boolean isFancy;
        public boolean planted;

        public FancyDarkOakTreeConfigBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, FeatureSize minimumSize, boolean isFancy, boolean planted) {
            super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, minimumSize);
            this.trunkProvider = trunkProvider;
            this.trunkPlacer = trunkPlacer;
            this.foliageProvider = foliageProvider;
            this.dirtProvider = BlockStateProvider.simple(Blocks.DIRT);
            this.foliagePlacer = foliagePlacer;
            this.rootPlacer = rootPlacer;
            this.minimumSize = minimumSize;
            this.isFancy = isFancy;
            this.planted = planted;
        }

        public FancyDarkOakTreeConfigBuilder(boolean isFancy, boolean planted) {
            this(BlockStateProvider.simple(Blocks.DARK_OAK_LOG), new DarkOakTrunkPlacer(0, 0, 0), BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES), new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)), new TwoLayersFeatureSize(0, 0, 0), isFancy, planted);
        }

        public FancyDarkOakTreeConfigBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, FeatureSize minimumSize, boolean isFancy, boolean planted) {
            this(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, Optional.empty(), minimumSize, isFancy, planted);
        }

        @Override
        public FancyDarkOakTreeConfig build() {
            return new FancyDarkOakTreeConfig(this.trunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt, this.isFancy, this.planted);
        }
    }

}
