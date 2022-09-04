package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.orcinus.goodending.entities.MarshEntity;

import java.util.List;

public class DrinkPotionGoal extends Goal {
    private final MarshEntity marshEntity;
    private ItemEntity item;

    public DrinkPotionGoal(MarshEntity marshEntity) {
        this.marshEntity = marshEntity;
    }

    @Override
    public boolean canStart() {
        return this.isItemNearby() && this.marshEntity.getStoredPotion() == Potions.EMPTY;
    }

    @Override
    public void tick() {
        if (this.item != null) {
            Vec3d vec3d = Vec3d.ofBottomCenter(this.item.getBlockPos());
            this.marshEntity.getNavigation().startMovingTo(vec3d.getX(), vec3d.getY(), vec3d.getZ(), 1.0F);
            LookControl lookControl = this.marshEntity.getLookControl();
            lookControl.lookAt(vec3d);
            boolean withinDistance = this.marshEntity.getBlockPos().isWithinDistance(this.item.getPos(), 2.0D);
            if (withinDistance) {
                this.marshEntity.setStoredPotion(PotionUtil.getPotion(this.item.getStack()));
                this.marshEntity.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1.0F, 1.0F);
                ItemEntity itemEntity = new ItemEntity(this.marshEntity.world, Math.floor(lookControl.getLookX()), this.marshEntity.getEyeY(), Math.floor(lookControl.getLookZ()), new ItemStack(Items.GLASS_BOTTLE));
                this.marshEntity.world.spawnEntity(itemEntity);
                this.item.discard();
            }
        }
    }

    public boolean isItemNearby() {
        List<ItemEntity> itemEntities = this.marshEntity.world.getNonSpectatingEntities(ItemEntity.class, this.marshEntity.getBoundingBox().expand(8.0D));
        for (ItemEntity item : itemEntities) {
            if (item.getStack().getItem() instanceof PotionItem) {
                this.item = item;
                return true;
            }
        }
        return false;
    }

}
