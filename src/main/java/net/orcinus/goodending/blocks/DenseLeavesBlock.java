package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Supplier;

public class DenseLeavesBlock extends LeavesBlock {
    public Supplier<ParticleOptions> type;

    public DenseLeavesBlock(Supplier<ParticleOptions> type, Properties settings) {
        super(settings);
        this.type = type;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();
        for (int l = 0; l < 1; ++l) {
            mutable.set(i + Mth.nextInt(random, -6, 6), j - random.nextInt(6), k + Mth.nextInt(random, -6, 6));
            BlockState blockState = world.getBlockState(mutable);
            if (blockState.isCollisionShapeFullBlock(world, mutable)) continue;
            world.addParticle(this.type.get(), (double)mutable.getX() + random.nextDouble(), (double)mutable.getY() + random.nextDouble(), (double)mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }

}
