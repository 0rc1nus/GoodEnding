package net.orcinus.goodending.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.orcinus.goodending.init.GoodEndingParticleTypes;

public class FireflyLanternBlock extends LanternBlock {

    public FireflyLanternBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double x = pos.getX() + 0.5D;
        double y = pos.getY();
        double z = pos.getZ() + 0.5D;
        double xVelocity = random.nextFloat() / 17.0F * (random.nextBoolean() ? -1 : 1);
        double yVelocity = random.nextFloat() / 16.0F * -1;
        double zVelocity = random.nextFloat() / 17.0F * (random.nextBoolean() ? -1 : 1);
        world.addParticle(GoodEndingParticleTypes.FIREFLY, x, y, z, xVelocity, yVelocity, zVelocity);
    }
}
