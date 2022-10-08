package net.orcinus.goodending.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.entities.GoodEndingChestBoatEntity;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.entities.WoodpeckerEntity;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingEntityTypes {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GoodEnding.MODID);

    public static final RegistryObject<EntityType<GoodEndingBoatEntity>> BOAT = ENTITY_TYPES.register("boat", () -> EntityType.Builder.<GoodEndingBoatEntity>of(GoodEndingBoatEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).clientTrackingRange(10).build(new ResourceLocation(GoodEnding.MODID, "boat").toString()));
    public static final RegistryObject<EntityType<GoodEndingChestBoatEntity>> CHEST_BOAT = ENTITY_TYPES.register("chest_boat", () -> EntityType.Builder.<GoodEndingChestBoatEntity>of(GoodEndingChestBoatEntity::new, MobCategory.MISC).sized(1.375f, 0.5625f).clientTrackingRange(10).build(new ResourceLocation(GoodEnding.MODID, "chest_boat").toString()));
    public static final RegistryObject<EntityType<FireflyEntity>> FIREFLY_SWARM = ENTITY_TYPES.register("firefly_swarm", () -> EntityType.Builder.of(FireflyEntity::new, MobCategory.CREATURE).sized(1F, 1F).clientTrackingRange(8).build(new ResourceLocation(GoodEnding.MODID, "firefly_swarm").toString()));
    public static final RegistryObject<EntityType<MarshEntity>> MARSH = ENTITY_TYPES.register("marsh", () -> EntityType.Builder.of(MarshEntity::new, MobCategory.CREATURE).sized(0.8F, 1F).build(new ResourceLocation(GoodEnding.MODID, "marsh").toString()));
    public static final RegistryObject<EntityType<WoodpeckerEntity>> WOODPECKER = ENTITY_TYPES.register("woodpecker", () -> EntityType.Builder.of(WoodpeckerEntity::new, MobCategory.CREATURE).sized(0.35F, 0.65F).build(new ResourceLocation(GoodEnding.MODID, "woodpecker").toString()));

}
