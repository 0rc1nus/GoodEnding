package net.orcinus.goodending.init;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.items.AlgaeBucketItem;
import net.orcinus.goodending.items.FireflyBottleItem;
import net.orcinus.goodending.items.GoodEndingBoatItem;
import net.orcinus.goodending.items.PlaceableUnderWaterItem;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = GoodEnding.MODID)
public class GoodEndingItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, GoodEnding.MODID);

    //Muddy oak wood set
    public static final RegistryObject<Item> MUDDY_OAK_PLANKS = ITEMS.register("muddy_oak_planks", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_LOG = ITEMS.register("muddy_oak_log", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_MUDDY_OAK_LOG = ITEMS.register("stripped_muddy_oak_log", () -> new BlockItem(GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_MUDDY_OAK_WOOD = ITEMS.register("stripped_muddy_oak_wood", () -> new BlockItem(GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_WOOD = ITEMS.register("muddy_oak_wood", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_SLAB = ITEMS.register("muddy_oak_slab", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_FENCE = ITEMS.register("muddy_oak_fence", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_STAIRS = ITEMS.register("muddy_oak_stairs", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_BUTTON = ITEMS.register("muddy_oak_button", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_PRESSURE_PLATE = ITEMS.register("muddy_oak_pressure_plate", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_DOOR = ITEMS.register("muddy_oak_door", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_TRAPDOOR = ITEMS.register("muddy_oak_trapdoor", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_FENCE_GATE = ITEMS.register("muddy_oak_fence_gate", () -> new BlockItem(GoodEndingBlocks.MUDDY_OAK_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> MUDDY_OAK_SIGN = ITEMS.register("muddy_oak_sign", () -> new SignItem(new Item.Properties().stacksTo(16), GoodEndingBlocks.MUDDY_OAK_SIGN.get(), GoodEndingBlocks.MUDDY_OAK_WALL_SIGN.get()));
    public static final RegistryObject<Item> MUDDY_OAK_HANGING_SIGN = ITEMS.register("muddy_oak_hanging_sign", () -> new HangingSignItem(GoodEndingBlocks.MUDDY_OAK_HANGING_SIGN.get(), GoodEndingBlocks.MUDDY_OAK_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> MUDDY_OAK_BOAT = ITEMS.register("muddy_oak_boat", () -> new GoodEndingBoatItem(false, GoodEndingBoatEntity.BoatType.MUDDY_OAK, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> MUDDY_OAK_CHEST_BOAT = ITEMS.register("muddy_oak_chest_boat", () -> new GoodEndingBoatItem(true, GoodEndingBoatEntity.BoatType.MUDDY_OAK, new Item.Properties().stacksTo(1)));

    //Cypress wood set
    public static final RegistryObject<Item> CYPRESS_PLANKS = ITEMS.register("cypress_planks", () -> new BlockItem(GoodEndingBlocks.CYPRESS_PLANKS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_LOG = ITEMS.register("cypress_log", () -> new BlockItem(GoodEndingBlocks.CYPRESS_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_SAPLING = ITEMS.register("cypress_sapling", () -> new BlockItem(GoodEndingBlocks.CYPRESS_SAPLING.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_CYPRESS_LOG = ITEMS.register("stripped_cypress_log", () -> new BlockItem(GoodEndingBlocks.STRIPPED_CYPRESS_LOG.get(), new Item.Properties()));
    public static final RegistryObject<Item> STRIPPED_CYPRESS_WOOD = ITEMS.register("stripped_cypress_wood", () -> new BlockItem(GoodEndingBlocks.STRIPPED_CYPRESS_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_WOOD = ITEMS.register("cypress_wood", () -> new BlockItem(GoodEndingBlocks.CYPRESS_WOOD.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_LEAVES = ITEMS.register("cypress_leaves", () -> new BlockItem(GoodEndingBlocks.CYPRESS_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_SLAB = ITEMS.register("cypress_slab", () -> new BlockItem(GoodEndingBlocks.CYPRESS_SLAB.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_FENCE = ITEMS.register("cypress_fence", () -> new BlockItem(GoodEndingBlocks.CYPRESS_FENCE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_STAIRS = ITEMS.register("cypress_stairs", () -> new BlockItem(GoodEndingBlocks.CYPRESS_STAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_BUTTON = ITEMS.register("cypress_button", () -> new BlockItem(GoodEndingBlocks.CYPRESS_BUTTON.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_PRESSURE_PLATE = ITEMS.register("cypress_pressure_plate", () -> new BlockItem(GoodEndingBlocks.CYPRESS_PRESSURE_PLATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_DOOR = ITEMS.register("cypress_door", () -> new BlockItem(GoodEndingBlocks.CYPRESS_DOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_TRAPDOOR = ITEMS.register("cypress_trapdoor", () -> new BlockItem(GoodEndingBlocks.CYPRESS_TRAPDOOR.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_FENCE_GATE = ITEMS.register("cypress_fence_gate", () -> new BlockItem(GoodEndingBlocks.CYPRESS_FENCE_GATE.get(), new Item.Properties()));
    public static final RegistryObject<Item> CYPRESS_SIGN = ITEMS.register("cypress_sign", () -> new SignItem(new Item.Properties().stacksTo(16), GoodEndingBlocks.CYPRESS_SIGN.get(), GoodEndingBlocks.CYPRESS_WALL_SIGN.get()));
    public static final RegistryObject<Item> CYPRESS_HANGING_SIGN = ITEMS.register("cypress_hanging_sign", () -> new HangingSignItem(GoodEndingBlocks.CYPRESS_HANGING_SIGN.get(), GoodEndingBlocks.CYPRESS_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> CYPRESS_BOAT = ITEMS.register("cypress_boat", () -> new GoodEndingBoatItem(false, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CYPRESS_CHEST_BOAT = ITEMS.register("cypress_chest_boat", () -> new GoodEndingBoatItem(true, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Properties().stacksTo(1)));

    //Lily pads
    public static final RegistryObject<Item> LARGE_LILY_PAD = ITEMS.register("large_lily_pad", () -> new PlaceableUnderWaterItem(GoodEndingBlocks.LARGE_LILY_PAD.get(), new Item.Properties()));
    public static final RegistryObject<Item> PURPLE_FLOWERING_LILY_PAD = ITEMS.register("purple_flowering_lily_pad", () -> new PlaceOnWaterBlockItem(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get(), new Item.Properties()));
    public static final RegistryObject<Item> PINK_FLOWERING_LILY_PAD = ITEMS.register("pink_flowering_lily_pad", () -> new PlaceOnWaterBlockItem(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get(), new Item.Properties()));
    public static final RegistryObject<Item> YELLOW_FLOWERING_LILY_PAD = ITEMS.register("yellow_flowering_lily_pad", () -> new PlaceOnWaterBlockItem(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get(), new Item.Properties()));

    //Flowers
    public static final RegistryObject<Item> PASTEL_WILDFLOWERS = ITEMS.register("pastel_wildflowers", () -> new BlockItem(GoodEndingBlocks.PASTEL_WILDFLOWERS.get(), new Item.Properties()));
    public static final RegistryObject<Item> TWILIGHT_WILDFLOWERS = ITEMS.register("twilight_wildflowers", () -> new BlockItem(GoodEndingBlocks.TWILIGHT_WILDFLOWERS.get(), new Item.Properties()));
    public static final RegistryObject<Item> SPICY_WILDFLOWERS = ITEMS.register("spicy_wildflowers", () -> new BlockItem(GoodEndingBlocks.SPICY_WILDFLOWERS.get(), new Item.Properties()));
    public static final RegistryObject<Item> BALMY_WILDFLOWERS = ITEMS.register("balmy_wildflowers", () -> new BlockItem(GoodEndingBlocks.BALMY_WILDFLOWERS.get(), new Item.Properties()));
    public static final RegistryObject<Item> POLLENFLAKE = ITEMS.register("pollenflake", () -> new BlockItem(GoodEndingBlocks.POLLENFLAKE.get(), new Item.Properties()));

    //Birch forest related
    public static final RegistryObject<Item> BIRCH_MUSHROOM = ITEMS.register("birch_mushroom", () -> new BlockItem(GoodEndingBlocks.BIRCH_MUSHROOM.get(), new Item.Properties()));

    //General world enhancing blocks
    public static final RegistryObject<Item> DENSE_BIRCH_LEAVES = ITEMS.register("dense_birch_leaves", () -> new BlockItem(GoodEndingBlocks.DENSE_BIRCH_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> DENSE_DARK_OAK_LEAVES = ITEMS.register("dense_dark_oak_leaves", () -> new BlockItem(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> HANGING_OAK_LEAVES = ITEMS.register("hanging_oak_leaves", () -> new BlockItem(GoodEndingBlocks.HANGING_OAK_LEAVES.get(), new Item.Properties()));
    public static final RegistryObject<Item> HANGING_DARK_OAK_LEAVES = ITEMS.register("hanging_dark_oak_leaves", () -> new BlockItem(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get(), new Item.Properties()));

    //Swamp related
    public static final RegistryObject<Item> DUCKWEED = ITEMS.register("duckweed", () -> new PlaceOnWaterBlockItem(GoodEndingBlocks.DUCKWEED.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALGAE_BUCKET = ITEMS.register("algae_bucket", () -> new AlgaeBucketItem(GoodEndingBlocks.ALGAE.get(), new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1), SoundEvents.LILY_PAD_PLACE));
    public static final RegistryObject<Item> CATTAIL = ITEMS.register("cattail", () -> new DoubleHighBlockItem(GoodEndingBlocks.CATTAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> FIREFLY_BOTTLE = ITEMS.register("firefly_bottle", () -> new FireflyBottleItem(new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> FIREFLY_LANTERN = ITEMS.register("firefly_lantern", () -> new BlockItem(GoodEndingBlocks.FIREFLY_LANTERN.get(), new Item.Properties()));

    public static final RegistryObject<Item> FIREFLY_SWARM_SPAWN_EGG = ITEMS.register("firefly_swarm_spawn_egg", () -> new ForgeSpawnEggItem(GoodEndingEntityTypes.FIREFLY_SWARM, 0x000000, 0xFFF4A4, new Item.Properties()));
    public static final RegistryObject<Item> MARSH_SPAWN_EGG = ITEMS.register("marsh_spawn_egg", () -> new ForgeSpawnEggItem(GoodEndingEntityTypes.MARSH, 3423006, 6979129 , new Item.Properties()));
    public static final RegistryObject<Item> WOODPECKER_SPAWN_EGG = ITEMS.register("woodpecker_spawn_egg", () -> new ForgeSpawnEggItem(GoodEndingEntityTypes.WOODPECKER, 0xCB4613, 0xFFFFFF , new Item.Properties()));

}
