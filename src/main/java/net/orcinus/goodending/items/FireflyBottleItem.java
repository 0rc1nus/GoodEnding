package net.orcinus.goodending.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

public class FireflyBottleItem extends Item {

    public FireflyBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
        World world = context.getWorld();
        PlayerEntity playerEntity = context.getPlayer();
        BlockPos blockPos = itemPlacementContext.getBlockPos();
        ItemStack stack = context.getStack();

        if (world instanceof ServerWorld serverWorld) {
            if (!playerEntity.getAbilities().creativeMode) {
                stack.decrement(1);
            }
            if (!playerEntity.getInventory().insertStack(new ItemStack(Items.GLASS_BOTTLE))) {
                playerEntity.giveItemStack(new ItemStack(Items.GLASS_BOTTLE));
            }
            FireflyEntity fireflyEntity = GoodEndingEntityTypes.FIREFLY_SWARM.create(world);
            fireflyEntity.setPos(blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5);
            fireflyEntity.setCount(1);
            fireflyEntity.setFromBottle(true);
            world.playSound(null, blockPos, GoodEndingSoundEvents.ITEM_FIREFLY_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 2.0F, 1.0F);
            serverWorld.spawnEntityAndPassengers(fireflyEntity);
            world.emitGameEvent(context.getPlayer(), GameEvent.ENTITY_PLACE, blockPos);
            return ActionResult.SUCCESS;
        }
        return ActionResult.success(world.isClient);
    }

}
