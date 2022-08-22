package net.orcinus.goodending.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PurpleMushroomFullBlock extends Block {

    public PurpleMushroomFullBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.bypassesLandingEffects()) super.onLandedUpon(world, state, pos, entity, fallDistance);
        else entity.handleFallDamage(fallDistance, 0.0F, DamageSource.FALL);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        double e = 0.4 + d * 0.2;
        if (d < 0.1 && !entity.bypassesSteppingEffects()) entity.setVelocity(entity.getVelocity().multiply(e, 1.0, e));
        if (!entity.bypassesLandingEffects()) entity.addVelocity(0, 1, 0);
        super.onSteppedOn(world, pos, state, entity);
    }

}
