package net.orcinus.goodending.items;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

public class FireflyBottleItem extends Item {

    public FireflyBottleItem(Properties settings) {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        BlockPlaceContext itemPlacementContext = new BlockPlaceContext(context);
        Level world = context.getLevel();
        Player playerEntity = context.getPlayer();
        BlockPos blockPos = itemPlacementContext.getClickedPos();
        ItemStack stack = context.getItemInHand();

        if (world instanceof ServerLevel serverWorld) {
            if (!playerEntity.getAbilities().instabuild) {
                stack.shrink(1);
            }
            if (!playerEntity.getInventory().add(new ItemStack(Items.GLASS_BOTTLE))) {
                playerEntity.addItem(new ItemStack(Items.GLASS_BOTTLE));
            }
            FireflyEntity fireflyEntity = GoodEndingEntityTypes.FIREFLY_SWARM.create(world);
            fireflyEntity.setPos(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
            fireflyEntity.setCount(1);
            fireflyEntity.setFromBottle(true);
            world.playSound(null, blockPos, GoodEndingSoundEvents.ITEM_FIREFLY_BOTTLE_EMPTY, SoundSource.NEUTRAL, 2.0F, 1.0F);
            serverWorld.addFreshEntity(fireflyEntity);
            world.gameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.sidedSuccess(world.isClientSide());
    }

}