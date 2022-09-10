package net.orcinus.goodending.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.orcinus.goodending.entities.ai.FollowMobWithEffectGoal;

import java.util.Collection;

public class MarshEntity extends PathAwareEntity {
    private Potion potion = Potions.EMPTY;
    private int dimensionalOpenTicks;
    private boolean infinite;

    public MarshEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createMarshAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25f));
        this.goalSelector.add(2, new FollowMobWithEffectGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0f));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.dimensionalOpenTicks < 7 && this.getStoredPotion() != Potions.EMPTY) {
            this.dimensionalOpenTicks++;
        }
        if (this.dimensionalOpenTicks == 7 && this.getStoredPotion() != Potions.EMPTY) {
            this.dimensionalOpenTicks = 1;
        }
    }

    public int getDimensionalOpenTicks() {
        return this.dimensionalOpenTicks;
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.getStoredPotion() != Potions.EMPTY) {
            Collection<StatusEffectInstance> collection = this.getStoredPotion().getEffects();
            int i = PotionUtil.getColor(collection);

            float f1 = random.nextFloat();
            if (f1 > 0.35f) return;

            double d = (double)(i >> 16 & 0xFF) / 255.0;
            double e = (double)(i >> 8 & 0xFF) / 255.0;
            double f = (double)(i & 0xFF) / 255.0;
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY() + 1f, this.getZ(), d, e, f);
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (item instanceof PotionItem && this.getStoredPotion() == Potions.EMPTY) {
            if (item instanceof LingeringPotionItem) {
                this.infinite = true;
            }
            this.setStoredPotion(PotionUtil.getPotion(itemStack));
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
                if (!(item instanceof ThrowablePotionItem)) this.dropItem(Items.GLASS_BOTTLE, 1);
            }
            return ActionResult.SUCCESS;
        }

        if (this.getStoredPotion() != Potions.EMPTY && this.getValidItems(item) && itemStack.getNbt() != null && !itemStack.getNbt().contains("Potion")) {
            if (itemStack.getNbt() != null) {
                if (this.infinite) {
                    itemStack.getNbt().putBoolean("Infinite", true);
                    this.infinite = false;
                } else {
                    itemStack.getNbt().putInt("Amount", 10);
                }
                PotionUtil.setPotion(itemStack, this.getStoredPotion());
                System.out.println("AMOGUS");
                this.setStoredPotion(Potions.EMPTY);
                return ActionResult.SUCCESS;
            }
        }

        return super.interactMob(player, hand);
    }


    public boolean getValidItems(Item item) {
        for (StatusEffectInstance effectInstance : this.getStoredPotion().getEffects()) {
            StatusEffectCategory category = effectInstance.getEffectType().getCategory();
            if (category == StatusEffectCategory.HARMFUL && item instanceof SwordItem) {
                return true;
            } else if (category == StatusEffectCategory.BENEFICIAL && item instanceof ShieldItem) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Potion", Registry.POTION.getId(this.getStoredPotion()).toString());
        nbt.putBoolean("Infinite", this.infinite);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.infinite = nbt.getBoolean("Infinite");
        this.setStoredPotion(PotionUtil.getPotion(nbt));
    }


    @Override
    public boolean canBreatheInWater() {
        return true;
    }

    public Potion getStoredPotion() {
        return this.potion;
    }

    public void setStoredPotion(Potion potion) {
        this.potion = potion;
    }

}
