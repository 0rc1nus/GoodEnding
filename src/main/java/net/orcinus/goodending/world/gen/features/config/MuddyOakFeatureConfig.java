package net.orcinus.goodending.world.gen.features.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class MuddyOakFeatureConfig extends TreeConfiguration {
    public static final Codec<MuddyOakFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider), TrunkPlacer.CODEC.fieldOf("trunk_placer").forGetter(config -> config.trunkPlacer), BlockStateProvider.CODEC.fieldOf("foliage_provider").forGetter(config -> config.foliageProvider), FoliagePlacer.CODEC.fieldOf("foliage_placer").forGetter(config -> config.foliagePlacer), RootPlacer.CODEC.optionalFieldOf("root_placer").forGetter(config -> config.rootPlacer), BlockStateProvider.CODEC.fieldOf("dirt_provider").forGetter(config -> config.dirtProvider), FeatureSize.CODEC.fieldOf("minimum_size").forGetter(config -> config.minimumSize), TreeDecorator.CODEC.listOf().fieldOf("decorators").forGetter(config -> config.decorators), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter(config -> config.forceDirt), Codec.BOOL.fieldOf("generateBranch").orElse(false).forGetter(config -> config.generateBranch)).apply(instance, MuddyOakFeatureConfig::new));
    public boolean generateBranch;

    public MuddyOakFeatureConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, BlockStateProvider dirtProvider, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt, boolean generateBranch) {
        super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, dirtProvider, minimumSize, decorators, ignoreVines, forceDirt);
        this.generateBranch = generateBranch;
    }

    public static class MuddyOakFeatureBuilder extends TreeConfigurationBuilder {
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
        public boolean generateBranch;

        public MuddyOakFeatureBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, FeatureSize minimumSize, boolean generateBranch) {
            super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, minimumSize);
            this.trunkProvider = trunkProvider;
            this.trunkPlacer = trunkPlacer;
            this.foliageProvider = foliageProvider;
            this.dirtProvider = BlockStateProvider.simple(Blocks.DIRT);
            this.foliagePlacer = foliagePlacer;
            this.rootPlacer = rootPlacer;
            this.minimumSize = minimumSize;
            this.generateBranch = generateBranch;
        }

        public MuddyOakFeatureBuilder(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, FeatureSize minimumSize, boolean generateBranch) {
            this(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, Optional.empty(), minimumSize, generateBranch);
        }

        @Override
        public MuddyOakFeatureConfig build() {
            return new MuddyOakFeatureConfig(this.trunkProvider, this.trunkPlacer, this.foliageProvider, this.foliagePlacer, this.rootPlacer, this.dirtProvider, this.minimumSize, this.decorators, this.ignoreVines, this.forceDirt, this.generateBranch);
        }
    }

}
