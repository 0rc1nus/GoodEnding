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
import net.orcinus.goodending.init.GoodEndingTags;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class MarshEntity extends PathAwareEntity {
    private static final TrackedData<Boolean> TRUSTED = DataTracker.registerData(MarshEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> BREWING = DataTracker.registerData(MarshEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> BURPING = DataTracker.registerData(MarshEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private Potion potion = Potions.EMPTY;
    private boolean infinite;

    public MarshEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(TRUSTED, false);
        this.dataTracker.startTracking(BREWING, 0);
        this.dataTracker.startTracking(BURPING, 0);
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
        if (this.getStoredPotion() != Potions.EMPTY && this.getBrewingTicks() > 0) {
            Collection<StatusEffectInstance> collection = this.getStoredPotion().getEffects();
            int i = PotionUtil.getColor(collection);
            float f1 = this.getRandom().nextFloat();
            if (f1 > 0.35f) {
                return;
            }
            double d = (double)(i >> 16 & 0xFF) / 255.0;
            double e = (double)(i >> 8 & 0xFF) / 255.0;
            double f = (double)(i & 0xFF) / 255.0;
            this.world.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX(), this.getY() + 1f, this.getZ(), d, e, f);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.world.isClient()) {
            if (this.getBrewingTicks() > 0) {
                if (this.getBrewingTicks() == 1) {
                    this.playSound(GoodEndingSoundEvents.ENTITY_MARSH_BURP, 1.0F, 1.0F);
                }
                this.setBrewingTicks(this.getBrewingTicks() - 1);
            }
            if (this.getBurpingTicks() > 0) {
                this.setBurping(this.getBurpingTicks() - 1);
            }
        }
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.isIn(GoodEndingTags.MARSH_TRUSTED_ITEMS) && !this.isTrusted()) {
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }
            this.setTrusted(true);
            this.world.sendEntityStatus(this, EntityStatuses.ADD_BREEDING_PARTICLES);
            return ActionResult.success(world.isClient);
        }
        if (item instanceof PotionItem && this.getStoredPotion() == Potions.EMPTY && this.isTrusted()) {
            if (!PotionUtil.getPotion(itemStack).hasInstantEffect()) {
                this.setInfinite(item instanceof LingeringPotionItem);
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
                this.setBrewingTicks(1800 + this.getRandom().nextInt(1200));
                return ActionResult.SUCCESS;
            }
        }
        if (item instanceof MilkBucketItem && this.getStoredPotion() != Potions.EMPTY && this.isTrusted()) {
            if (!player.getAbilities().creativeMode) {
                player.setStackInHand(hand, Items.BUCKET.getDefaultStack());
            }
            this.setStoredPotion(Potions.EMPTY);
            this.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 1, 1);
            this.setBrewingTicks(0);
            return ActionResult.SUCCESS;
        }
        if (this.getStoredPotion() != Potions.EMPTY && itemStack.getNbt() != null && !itemStack.getNbt().contains("Potion") && this.getValidItems(item) && this.getBrewingTicks() == 0) {
            if (itemStack.getNbt() != null) {
                if (this.isInfinite()) {
                    itemStack.getNbt().putBoolean("Infinite", true);
                    this.setInfinite(false);
                } else {
                    itemStack.getNbt().putInt("Amount", 20);
                }
                PotionUtil.setPotion(itemStack, this.getStoredPotion());
                this.setBurping(15);
                this.playSound(GoodEndingSoundEvents.ENTITY_MARSH_BURP, 1, 1);
                this.setStoredPotion(Potions.EMPTY);
                this.setBrewingTicks(0);
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
        nbt.putBoolean("Infinite", this.isInfinite());
        nbt.putBoolean("Trusted", this.isTrusted());
        nbt.putInt("BrewingTicks", this.getBrewingTicks());
        nbt.putInt("BurpingTicks", this.getBurpingTicks());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setInfinite(nbt.getBoolean("Infinite"));
        this.setTrusted(nbt.getBoolean("Trusted"));
        this.setStoredPotion(PotionUtil.getPotion(nbt));
        this.setBrewingTicks(nbt.getInt("BrewingTicks"));
        this.setBurping(nbt.getInt("BurpingTicks"));
    }

    public int getBurpingTicks() {
        return this.dataTracker.get(BURPING);
    }

    public void setBurping(int burpingTicks) {
        this.dataTracker.set(BURPING, burpingTicks);
    }

    public int getBrewingTicks() {
        return this.dataTracker.get(BREWING);
    }

    public void setBrewingTicks(int brewingTicks) {
        this.dataTracker.set(BREWING, brewingTicks);
    }

    public boolean isInfinite() {
        return this.infinite;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
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
