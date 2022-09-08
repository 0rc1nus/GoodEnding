package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.orcinus.goodending.entities.MarshEntity;

import java.util.List;

public class ConsumeToolGoal extends Goal {
    private final MarshEntity marshEntity;
    private ItemEntity item;

    public ConsumeToolGoal(MarshEntity marshEntity) {
        this.marshEntity = marshEntity;
    }

    @Override
    public boolean canStart() {
        return this.isItemNearby() && this.marshEntity.getStoredPotion() != Potions.EMPTY;
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
                this.marshEntity.playSound(SoundEvents.ENTITY_PLAYER_BURP, 1.0F, 1.0F);
                ItemStack stack = this.item.getStack();
                if (stack.getNbt() != null) {
                    PotionUtil.setPotion(stack, this.marshEntity.getStoredPotion());
                    stack.getNbt().putInt("Amount", 10);
                    ItemEntity itemEntity = new ItemEntity(this.marshEntity.world, this.marshEntity.getX(), this.marshEntity.getY() + 1, this.marshEntity.getZ(), stack);
                    itemEntity.setToDefaultPickupDelay();
                    this.marshEntity.world.spawnEntity(itemEntity);
                    this.item.discard();
                    this.marshEntity.setStoredPotion(Potions.EMPTY);
                }
            }
        }
    }

    public boolean isItemNearby() {
        List<ItemEntity> itemEntities = this.marshEntity.world.getNonSpectatingEntities(ItemEntity.class, this.marshEntity.getBoundingBox().expand(8.0D));
        for (ItemEntity item : itemEntities) {
            for (StatusEffectInstance effectInstance : this.marshEntity.getStoredPotion().getEffects()) {
                StatusEffectCategory category = effectInstance.getEffectType().getCategory();
                Item stackItem = item.getStack().getItem();
                if (category == StatusEffectCategory.HARMFUL && (stackItem instanceof SwordItem || stackItem instanceof AxeItem)) {
                    this.item = item;
                    return true;
                } else if (category == StatusEffectCategory.BENEFICIAL && stackItem instanceof ShieldItem) {
                    this.item = item;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

}
