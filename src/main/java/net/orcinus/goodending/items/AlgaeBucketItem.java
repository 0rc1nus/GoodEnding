package net.orcinus.goodending.items;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class AlgaeBucketItem extends BlockItem implements DispensibleContainerItem {
    private final SoundEvent placeSound;

    public AlgaeBucketItem(Block block, Properties settings, SoundEvent placeSound) {
        super(block, settings);
        this.placeSound = placeSound;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {

        BlockHitResult blockHitResult = AlgaeBucketItem.getPlayerPOVHitResult(world, user, ClipContext.Fluid.SOURCE_ONLY);
        BlockHitResult blockHitResult2 = blockHitResult.withPosition(blockHitResult.getBlockPos());
        InteractionResult actionResult = super.useOn(new UseOnContext(user, hand, blockHitResult2));

        if (actionResult.consumesAction() && !user.isCreative()) {
            user.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
        }
        return new InteractionResultHolder<>(actionResult, user.getItemInHand(hand));
    }

    @Override
    public String getDescriptionId() {
        return this.getOrCreateDescriptionId();
    }

    @Override
    protected SoundEvent getPlaceSound(BlockState state) {
        return this.placeSound;
    }

    @Override
    public boolean emptyContents(@Nullable Player player, Level world, BlockPos pos, @Nullable BlockHitResult hitResult) {
        if (world.isInWorldBounds(pos) && world.isWaterAt(pos)) {
            if (!world.isClientSide()) {
                world.setBlock(pos, this.getBlock().defaultBlockState(), 3);
            }
            world.gameEvent(player, GameEvent.FLUID_PLACE, pos);
            world.playSound(player, pos, this.placeSound, SoundSource.BLOCKS, 1.0f, 1.0f);
            return true;
        }
        return false;
    }

}
