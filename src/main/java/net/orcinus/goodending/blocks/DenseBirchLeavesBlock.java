package net.orcinus.goodending.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.orcinus.goodending.init.GoodEndingParticleTypes;

public class DenseBirchLeavesBlock extends LeavesBlock {
    public DenseBirchLeavesBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int l = 0; l < 1; ++l) {
            mutable.set(i + MathHelper.nextInt(random, -6, 6), j - random.nextInt(6), k + MathHelper.nextInt(random, -6, 6));
            BlockState blockState = world.getBlockState(mutable);
            if (blockState.isFullCube(world, mutable)) continue;
            world.addParticle(GoodEndingParticleTypes.BIRCH_LEAF, (double)mutable.getX() + random.nextDouble(), (double)mutable.getY() + random.nextDouble(), (double)mutable.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }
}
