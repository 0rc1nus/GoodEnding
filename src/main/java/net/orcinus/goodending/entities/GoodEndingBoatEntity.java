package net.orcinus.goodending.entities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.mixin.accessor.BoatEntityAccessor;

public class GoodEndingBoatEntity extends BoatEntity {
    private static final TrackedData<Integer> BOAT_TYPE = DataTracker.registerData(GoodEndingBoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public GoodEndingBoatEntity(EntityType<? extends GoodEndingBoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public GoodEndingBoatEntity(World world, double x, double y, double z) {
        this(GoodEndingEntityTypes.BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BOAT_TYPE, BOAT_TYPE.getId());
    }

    @Override
    public Item asItem() {
        return this.getGoodEndingBoatType() == BoatType.MUDDY_OAK ? GoodEndingItems.MUDDY_OAK_BOAT : GoodEndingItems.CYPRESS_BOAT;
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Type", 8)) {
            this.setGoodEndingBoatType(BoatType.byName(nbt.getString("Type")));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putString("Type", this.getGoodEndingBoatType().getName());
    }

    public BoatType getGoodEndingBoatType() {
        return BoatType.byId(this.dataTracker.get(BOAT_TYPE));
    }

    public void setGoodEndingBoatType(BoatType type) {
        this.dataTracker.set(BOAT_TYPE, type.ordinal());
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        ((BoatEntityAccessor)this).setFallVelocity(this.getVelocity().y);
        if (!this.hasVehicle()) {
            if (onGround) {
                if (this.fallDistance > 3.0F) {
                    if (((BoatEntityAccessor)this).getLocation() != BoatEntity.Location.ON_LAND) {
                        this.onLanding();
                        return;
                    }

                    this.handleFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.world.isClient && !this.isRemoved()) {
                        this.kill();
                        if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
                            int i;
                            for(i = 0; i < 3; ++i) {
                                this.dropItem(this.getGoodEndingBoatType().getPlanks());
                            }

                            for(i = 0; i < 2; ++i) {
                                this.dropItem(Items.STICK);
                            }
                        }
                    }
                }

                this.onLanding();
            } else if (!this.world.getFluidState(this.getBlockPos().down()).isIn(FluidTags.WATER) && heightDifference < 0.0) {
                this.fallDistance -= (float)heightDifference;
            }

        }
    }

    public enum BoatType {
        MUDDY_OAK(GoodEndingBlocks.MUDDY_OAK_PLANKS, "muddy_oak"),
        CYPRESS(GoodEndingBlocks.CYPRESS_PLANKS, "cypress");

        private final String name;
        private final Block planks;

        BoatType(Block block, String name) {
            this.name = name;
            this.planks = block;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static BoatType byId(int id) {
            BoatType[] aboat$type = values();
            if (id < 0 || id >= aboat$type.length) {
                id = 0;
            }

            return aboat$type[id];
        }

        public static BoatType byName(String name) {
            BoatType[] types = values();
            for (BoatType type : types) {
                if (type.getName().equals(name)) return type;
            }
            return types[0];
        }
    }
}
