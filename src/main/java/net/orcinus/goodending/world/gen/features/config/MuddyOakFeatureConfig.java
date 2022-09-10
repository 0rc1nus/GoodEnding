package net.orcinus.goodending.world.gen.features.config;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.FeatureSize;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.root.RootPlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.trunk.TrunkPlacer;

import java.util.List;
import java.util.Optional;

public class MuddyOakFeatureConfig extends TreeFeatureConfig {
    public static final Codec<MuddyOakFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider), TrunkPlacer.TYPE_CODEC.fieldOf("trunk_placer").forGetter(config -> config.trunkPlacer), BlockStateProvider.TYPE_CODEC.fieldOf("foliage_provider").forGetter(config -> config.foliageProvider), FoliagePlacer.TYPE_CODEC.fieldOf("foliage_placer").forGetter(config -> config.foliagePlacer), RootPlacer.TYPE_CODEC.optionalFieldOf("root_placer").forGetter(config -> config.rootPlacer), BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider").forGetter(config -> config.dirtProvider), FeatureSize.TYPE_CODEC.fieldOf("minimum_size").forGetter(config -> config.minimumSize), TreeDecorator.TYPE_CODEC.listOf().fieldOf("decorators").forGetter(config -> config.decorators), Codec.BOOL.fieldOf("ignore_vines").orElse(false).forGetter(config -> config.ignoreVines), Codec.BOOL.fieldOf("force_dirt").orElse(false).forGetter(config -> config.forceDirt), Codec.BOOL.fieldOf("generateBranch").orElse(false).forGetter(config -> config.generateBranch)).apply(instance, MuddyOakFeatureConfig::new));
    public boolean generateBranch;

    public MuddyOakFeatureConfig(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, BlockStateProvider dirtProvider, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, boolean forceDirt, boolean generateBranch) {
        super(trunkProvider, trunkPlacer, foliageProvider, foliagePlacer, rootPlacer, dirtProvider, minimumSize, decorators, ignoreVines, forceDirt);
        this.generateBranch = generateBranch;
    }

    public static class MuddyOakFeatureBuilder extends Builder {
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
            this.dirtProvider = BlockStateProvider.of(Blocks.DIRT);
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
