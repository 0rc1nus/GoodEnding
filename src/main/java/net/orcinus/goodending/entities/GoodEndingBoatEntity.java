package net.orcinus.goodending.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingItems;
import net.orcinus.goodending.mixin.accessor.BoatAccessor;

public class GoodEndingBoatEntity extends Boat {
    private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(GoodEndingBoatEntity.class, EntityDataSerializers.INT);

    public GoodEndingBoatEntity(EntityType<? extends GoodEndingBoatEntity> entityType, Level world) {
        super(entityType, world);
    }

    public GoodEndingBoatEntity(Level world, double x, double y, double z) {
        this(GoodEndingEntityTypes.BOAT, world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOAT_TYPE, BOAT_TYPE.getId());
    }

    @Override
    public Item getDropItem() {
        return this.getGoodEndingBoatType() == BoatType.MUDDY_OAK ? GoodEndingItems.MUDDY_OAK_BOAT : GoodEndingItems.CYPRESS_BOAT;
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Type", 8)) {
            this.setGoodEndingBoatType(BoatType.byName(nbt.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("Type", this.getGoodEndingBoatType().getName());
    }

    public BoatType getGoodEndingBoatType() {
        return BoatType.byId(this.entityData.get(BOAT_TYPE));
    }

    public void setGoodEndingBoatType(BoatType type) {
        this.entityData.set(BOAT_TYPE, type.ordinal());
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