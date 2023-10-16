package net.orcinus.goodending.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AlgaeBucketItem extends BlockItem implements DispensibleContainerItem {
    private final SoundEvent placeSound;

    public AlgaeBucketItem(Block block, SoundEvent soundEvent, Item.Properties properties) {
        super(block, properties);
        this.placeSound = soundEvent;
    }

    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        InteractionResult interactionResult = super.useOn(useOnContext);
        Player player = useOnContext.getPlayer();
        if (interactionResult.consumesAction() && player != null && !player.isCreative()) {
            if (player.getInventory().getFreeSlot() != -1) player.addItem(Items.BUCKET.getDefaultInstance());
            else player.drop(Items.BUCKET.getDefaultInstance(), true);
        }

        return interactionResult;
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Override
    protected SoundEvent getPlaceSound(BlockState blockState) {
        return this.placeSound;
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level level, BlockPos blockPos, @Nullable BlockHitResult blockHitResult) {
        if (level.isInWorldBounds(blockPos) && level.isEmptyBlock(blockPos)) {
            if (!level.isClientSide) {
                level.setBlock(blockPos, this.getBlock().defaultBlockState(), 3);
            }

            level.gameEvent(player, GameEvent.FLUID_PLACE, blockPos);
            level.playSound(player, blockPos, this.placeSound, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        } else {
            return false;
        }
    }
}
