package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.entities.GoodEndingChestBoatEntity;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class GoodEndingEntityTypes {
    public static final EntityType<GoodEndingBoatEntity> BOAT = register("boat", FabricEntityTypeBuilder.<GoodEndingBoatEntity>create().entityFactory(GoodEndingBoatEntity::new).spawnGroup(SpawnGroup.MISC).dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).trackRangeChunks(10), null);
    public static final EntityType<GoodEndingChestBoatEntity> CHEST_BOAT = register("chest_boat", FabricEntityTypeBuilder.<GoodEndingChestBoatEntity>create().entityFactory(GoodEndingChestBoatEntity::new).spawnGroup(SpawnGroup.MISC).dimensions(EntityDimensions.fixed(1.375f, 0.5625f)).trackRangeChunks(10), null);
    public static final EntityType<FireflyEntity> FIREFLY_SWARM = register(
            "firefly_swarm",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(FireflyEntity::new)
                    .defaultAttributes(FireflyEntity::createFireflyAttributes)
                    .spawnGroup(SpawnGroup.CREATURE)
                    .spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE_WG, FireflyEntity::canSpawn)
                    .dimensions(EntityDimensions.changing(1F, 1F))
                    .trackRangeBlocks(8),
            new int[]{ 0x000000, 0xFFF4A4 }
    );
    public static final EntityType<MarshEntity> MARSH = register(
            "marsh",
            FabricEntityTypeBuilder.createMob()
                    .entityFactory(MarshEntity::new)
                    .defaultAttributes(MarshEntity::createMarshAttributes)
                    .spawnGroup(SpawnGroup.CREATURE)
                    .dimensions(EntityDimensions.fixed(0.5F, 1.4F)),
            new int[]{ 3423006, 6979129 }
    );

    public static final EntityType<WoodpeckerEntity> WOODPECKER = register(
        "woodpecker",
        FabricEntityTypeBuilder.createMob()
                               .entityFactory(WoodpeckerEntity::new)
                               .defaultAttributes(WoodpeckerEntity::createWoodPeckerAttributes)
                               .spawnGroup(SpawnGroup.CREATURE)
                               .dimensions(EntityDimensions.changing(0.35F, 0.65F)),
        new int[]{ 0xCB4613, 0xFFFFFF }
    );

    @SuppressWarnings("unchecked")
    private static <T extends Entity> EntityType<T> register(String id, EntityType<T> entityType, int[] spawnEggColors) {
        if (spawnEggColors != null)
            Registry.register(Registry.ITEM, new Identifier(GoodEnding.MODID, id + "_spawn_egg"), new SpawnEggItem((EntityType<? extends MobEntity>) entityType, spawnEggColors[0], spawnEggColors[1], new Item.Settings().maxCount(64).group(GoodEnding.TAB)));

        return Registry.register(Registry.ENTITY_TYPE, new Identifier(GoodEnding.MODID, id), entityType);
    }

    private static <T extends Entity> EntityType<T> register(String id, FabricEntityTypeBuilder<T> entityType, int[] spawnEggColors) {
        return register(id, entityType.build(), spawnEggColors);
    }
}
