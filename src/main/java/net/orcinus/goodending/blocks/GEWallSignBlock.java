package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.orcinus.goodending.init.GoodEndingBlockEntityTypes;

public class GEWallSignBlock extends WallSignBlock {

    public GEWallSignBlock(Properties p_58068_, WoodType p_58069_) {
        super(p_58068_, p_58069_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return GoodEndingBlockEntityTypes.GE_SIGN.get().create(p_154556_, p_154557_);
    }
}
