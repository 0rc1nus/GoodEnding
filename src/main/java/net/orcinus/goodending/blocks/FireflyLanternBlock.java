package net.orcinus.goodending.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LanternBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.goodending.init.GoodEndingParticleTypes;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

import java.util.stream.Stream;

public class FireflyLanternBlock extends LanternBlock {
    public static final BooleanProperty OPEN = Properties.OPEN;
    protected static final VoxelShape STANDING_SHAPE = Stream.of(
        Block.createCuboidShape(3, 11, 3, 13, 12, 13),
        Block.createCuboidShape(3, 0, 3, 13, 10, 13),
        Block.createCuboidShape(4, 10, 4, 12, 11, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();


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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return STANDING_SHAPE;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.cycle(OPEN), 2);

        world.playSound(null, pos, state.get(OPEN) ? GoodEndingSoundEvents.BLOCK_FIREFLY_LANTERN_CLOSE : GoodEndingSoundEvents.BLOCK_FIREFLY_LANTERN_OPEN, SoundCategory.BLOCKS, 1, 1);
        world.emitGameEvent(player, state.get(OPEN) ? GameEvent.BLOCK_OPEN : GameEvent.BLOCK_CLOSE, pos);
        return ActionResult.success(world.isClient);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if ((!state.get(OPEN)) || (state.get(OPEN) && world.getTimeOfDay() < 12000 && world.getTimeOfDay() > 0)) {
            for (int l = 0; l < 1; ++l) {
                double x = pos.getX() + 0.5D;

                double y = pos.getY();
                double z = pos.getZ() + 0.5D;
                double xVelocity = random.nextFloat() / 45.0F * (random.nextBoolean() ? -1 : 1);
                double yVelocity = random.nextFloat() / 100.0F * (state.get(HANGING) ? 2 : -1);
                double zVelocity = random.nextFloat() / 45.0F * (random.nextBoolean() ? -1 : 1);
                world.addParticle(GoodEndingParticleTypes.FIREFLY, x, y + 0.4, z, xVelocity, yVelocity, zVelocity);
            }
        }

        if (state.get(OPEN) && world.getTimeOfDay() > 12000 && world.getTimeOfDay() < 24000) {
            int i = pos.getX();
            int j = pos.getY() + (state.get(HANGING) ? -1 : 6);
            int k = pos.getZ();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            for (int l = 0; l < 10; ++l) {
                mutable.set(i + MathHelper.nextInt(random, -6, 6), j - random.nextInt(6), k + MathHelper.nextInt(random, -6, 6));
                BlockState blockState = world.getBlockState(mutable);
                if (blockState.isFullCube(world, mutable)) continue;

                double velx = random.nextGaussian() * 0.025;
                double vely = random.nextGaussian() * 0.025;
                double velz = random.nextGaussian() * 0.025;
                world.addParticle(GoodEndingParticleTypes.FIREFLY, (double) mutable.getX() + random.nextDouble(), (double) mutable.getY() + random.nextDouble(), (double) mutable.getZ() + random.nextDouble(), velx, vely, velz);
            }
        }
    }
}
