package net.orcinus.goodending.entities;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.LingeringPotionItem;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.item.PotionItem;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ThrowablePotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.orcinus.goodending.entities.ai.FollowMobWithEffectGoal;
import net.orcinus.goodending.entities.ai.StrangerDangerGoal;
import net.orcinus.goodending.init.GoodEndingCriteriaTriggers;
import net.orcinus.goodending.init.GoodEndingSoundEvents;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class MarshEntity extends PathAwareEntity {
    private static final TrackedData<Boolean> TRUSTED = DataTracker.registerData(MarshEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private Potion potion = Potions.EMPTY;
    public int brewingTicks = 1;
    public int burpingTicks;
    private boolean infinite;

    public MarshEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TRUSTED, false);
    }

    public static DefaultAttributeContainer.Builder createMarshAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new StrangerDangerGoal<>(this, PlayerEntity.class));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.25f));
        this.goalSelector.add(2, new FollowMobWithEffectGoal(this));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0f));
        this.goalSelector.add(3, new LookAroundGoal(this));
    }

    public boolean isTrusted() {
        return this.dataTracker.get(TRUSTED);
    }

    public void setTrusted(boolean trusted) {
        this.dataTracker.set(TRUSTED, trusted);
    }

    @Override
    public void tickMovement() {
        super.tickMovement();

        if (this.getStoredPotion() != Potions.EMPTY && this.brewingTicks > 0) {
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
    public void tick() {
        super.tick();
        if (this.getStoredPotion() != Potions.EMPTY) {
            this.brewingTicks--;
        }
        if (burpingTicks > 0) {
            this.burpingTicks--;
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();

        if (item == Items.FERMENTED_SPIDER_EYE && !this.isTrusted()) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            this.setTrusted(true);
            this.world.sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
            return ActionResult.success(world.isClient);
        }

        if (item instanceof PotionItem && this.getStoredPotion() == Potions.EMPTY && this.isTrusted()) {

            if (!PotionUtil.getPotion(itemStack).hasInstantEffect()) {

                if (item instanceof LingeringPotionItem) {
                    this.infinite = true;
                }
                if (!player.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                    if (!(item instanceof ThrowablePotionItem)) {
                        this.dropItem(Items.GLASS_BOTTLE, 1);
                    }
                }

                if (player instanceof ServerPlayerEntity serverPlayer) {
                    GoodEndingCriteriaTriggers.BREW_POTION.trigger(serverPlayer);
                }

                this.setStoredPotion(PotionUtil.getPotion(itemStack));
                this.brewingTicks = 20 * 90 + random.nextInt(20 * 60);

                return ActionResult.SUCCESS;
            }
        }

        if (item instanceof MilkBucketItem && this.getStoredPotion() != Potions.EMPTY && this.isTrusted()) {
            if (!player.getAbilities().creativeMode) {
                player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
            }
            this.setStoredPotion(Potions.EMPTY);
            this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);
            this.brewingTicks = 1;

            return ActionResult.SUCCESS;
        }

        if (this.getStoredPotion() != Potions.EMPTY && itemStack.getNbt() != null && !itemStack.getNbt().contains("Potion") && this.getValidItems(item) && this.brewingTicks < 0) {

            if (itemStack.getNbt() != null) {
                if (this.infinite) {
                    itemStack.getNbt().putBoolean("Infinite", true);
                    this.infinite = false;
                } else {
                    itemStack.getNbt().putInt("Amount", 20);
                }

                PotionUtil.setPotion(itemStack, this.getStoredPotion());
                this.burpingTicks = 15;
                this.playSound(GoodEndingSoundEvents.ENTITY_MARSH_BURP, 1, 1);
                this.setStoredPotion(Potions.EMPTY);
                this.brewingTicks = 1;

                return ActionResult.SUCCESS;
            }
        }

        return super.interactMob(player, hand);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.ADD_SPLASH_PARTICLES) {
            this.spawnParticles(5, ParticleTypes.SPLASH, 1.0);
        } else if (status == EntityStatuses.ADD_BREEDING_PARTICLES) {
            this.spawnParticles(7, ParticleTypes.HEART, 0.5);
        } else {
            super.handleStatus(status);
        }
    }

    private void spawnParticles(int x, DefaultParticleType splash, double x1) {
        for (int i = 0; i < x; ++i) {
            double d = this.random.nextGaussian() * 0.02;
            double e = this.random.nextGaussian() * 0.02;
            double f = this.random.nextGaussian() * 0.02;
            this.world.addParticle(splash, this.getParticleX(1.0), this.getRandomBodyY() + x1, this.getParticleZ(1.0), d, e, f);
        }
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
        nbt.putBoolean("Trusted", this.isTrusted());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.infinite = nbt.getBoolean("Infinite");
        this.setTrusted(nbt.getBoolean("Trusted"));
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

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (this.getStoredPotion() != Potions.EMPTY) return GoodEndingSoundEvents.ENTITY_MARSH_BREWING_IDLE;
        else return GoodEndingSoundEvents.ENTITY_MARSH_IDLE;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return GoodEndingSoundEvents.ENTITY_MARSH_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return GoodEndingSoundEvents.ENTITY_MARSH_DEATH;
    }
}
