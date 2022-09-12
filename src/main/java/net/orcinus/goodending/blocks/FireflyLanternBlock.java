package net.orcinus.goodending.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import net.orcinus.goodending.init.GoodEndingParticleTypes;

public class FireflyLanternBlock extends LanternBlock {
    public static final BooleanProperty OPEN = Properties.OPEN;

    public FireflyLanternBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(OPEN, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(OPEN);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.cycle(OPEN), 2);
        world.syncWorldEvent(player, state.get(OPEN) ? WorldEvents.IRON_DOOR_OPENS : WorldEvents.IRON_DOOR_CLOSES, pos, 0);
        world.emitGameEvent(player, state.get(OPEN) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return ActionResult.success(world.isClient);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(OPEN)) {
            double x = pos.getX() + 0.5D;
            double y = pos.getY();
            double z = pos.getZ() + 0.5D;
            double xVelocity = random.nextFloat() / 17.0F * (random.nextBoolean() ? -1 : 1);
            double yVelocity = random.nextFloat() / 16.0F * (state.get(HANGING) ? -1 : 1);
            double zVelocity = random.nextFloat() / 17.0F * (random.nextBoolean() ? -1 : 1);
            world.addParticle(GoodEndingParticleTypes.FIREFLY, x, y, z, xVelocity, yVelocity, zVelocity);
        }
    }
}
