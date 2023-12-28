package net.orcinus.goodending.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraftforge.common.PlantType;

public class PlaceableOnWaterBlock extends WaterlilyBlock {

    public PlaceableOnWaterBlock(Properties properties) {
        super(properties);
    }

    @Override
    public PlantType getPlantType(BlockGetter level, BlockPos pos) {
        return PlantType.WATER;
    }
}
