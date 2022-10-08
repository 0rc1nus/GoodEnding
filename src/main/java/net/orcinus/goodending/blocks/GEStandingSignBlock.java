package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.orcinus.goodending.init.GoodEndingBlockEntityTypes;

public class GEStandingSignBlock extends StandingSignBlock {

    public GEStandingSignBlock(Properties properties, WoodType type) {
        super(properties, type);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos p_154556_, BlockState p_154557_) {
        return GoodEndingBlockEntityTypes.GE_SIGN.get().create(p_154556_, p_154557_);
    }
}
