package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WildflowersBlock extends BushBlock implements BonemealableBlock {
    protected static final VoxelShape SHAPE = box(2, 0, 2, 14, 6, 14);

    public WildflowersBlock(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext p_60558_) {
        Vec3 vec3d = state.getOffset(world, pos);
        return SHAPE.move(vec3d.x, vec3d.y, vec3d.z);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level p_220878_, RandomSource p_220879_, BlockPos p_220880_, BlockState p_220881_) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel world, RandomSource random, BlockPos pos, BlockState state) {
        if (world.getBlockState(pos.below()).is(BlockTags.DIRT)) {
            int j = 1;
            int l = 0;
            int m = pos.getX() - 2;
            int n = 0;

            for (int o = 0; o < 5; ++o) {
                for (int p = 0; p < j; ++p) {
                    int q = 2 + pos.getY();
                    for (int r = q - 2; r < q; ++r) {
                        BlockPos blockPos = new BlockPos(m + o, r, pos.getZ() - n + p);
                        if (blockPos == pos || random.nextInt(4) != 0  || !world.getBlockState(blockPos.below()).is(BlockTags.DIRT) || !world.getBlockState(blockPos).isAir()) continue;
                        world.setBlock(blockPos, state, 3);
                    }
                }
                if (l < 2) {
                    j += 2;
                    ++n;
                } else {
                    j -= 2;
                    --n;
                }
                ++l;
            }
            world.setBlock(pos, state, 2);
        }
    }
}

