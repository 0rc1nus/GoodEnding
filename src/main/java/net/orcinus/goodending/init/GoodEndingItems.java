package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DoubleHighBlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.item.SignItem;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.items.AlgaeBucketItem;
import net.orcinus.goodending.items.FireflyBottleItem;
import net.orcinus.goodending.items.GoodEndingBoatItem;
import net.orcinus.goodending.items.PlaceableUnderWaterItem;

import static net.orcinus.goodending.GoodEnding.MODID;

@SuppressWarnings("unused")
public class GoodEndingItems {

    //Muddy oak wood set
    public static final Item MUDDY_OAK_PLANKS = register("muddy_oak_planks", new BlockItem(GoodEndingBlocks.MUDDY_OAK_PLANKS, new Item.Properties()));
    public static final Item MUDDY_OAK_LOG = register("muddy_oak_log", new BlockItem(GoodEndingBlocks.MUDDY_OAK_LOG, new Item.Properties()));
    public static final Item STRIPPED_MUDDY_OAK_LOG = register("stripped_muddy_oak_log", new BlockItem(GoodEndingBlocks.STRIPPED_MUDDY_OAK_LOG, new Item.Properties()));
    public static final Item STRIPPED_MUDDY_OAK_WOOD = register("stripped_muddy_oak_wood", new BlockItem(GoodEndingBlocks.STRIPPED_MUDDY_OAK_WOOD, new Item.Properties()));
    public static final Item MUDDY_OAK_WOOD = register("muddy_oak_wood", new BlockItem(GoodEndingBlocks.MUDDY_OAK_WOOD, new Item.Properties()));
    public static final Item MUDDY_OAK_SLAB = register("muddy_oak_slab", new BlockItem(GoodEndingBlocks.MUDDY_OAK_SLAB, new Item.Properties()));
    public static final Item MUDDY_OAK_FENCE = register("muddy_oak_fence", new BlockItem(GoodEndingBlocks.MUDDY_OAK_FENCE, new Item.Properties()));
    public static final Item MUDDY_OAK_STAIRS = register("muddy_oak_stairs", new BlockItem(GoodEndingBlocks.MUDDY_OAK_STAIRS, new Item.Properties()));
    public static final Item MUDDY_OAK_BUTTON = register("muddy_oak_button", new BlockItem(GoodEndingBlocks.MUDDY_OAK_BUTTON, new Item.Properties()));
    public static final Item MUDDY_OAK_PRESSURE_PLATE = register("muddy_oak_pressure_plate", new BlockItem(GoodEndingBlocks.MUDDY_OAK_PRESSURE_PLATE, new Item.Properties()));
    public static final Item MUDDY_OAK_DOOR = register("muddy_oak_door", new BlockItem(GoodEndingBlocks.MUDDY_OAK_DOOR, new Item.Properties()));
    public static final Item MUDDY_OAK_TRAPDOOR = register("muddy_oak_trapdoor", new BlockItem(GoodEndingBlocks.MUDDY_OAK_TRAPDOOR, new Item.Properties()));
    public static final Item MUDDY_OAK_FENCE_GATE = register("muddy_oak_fence_gate", new BlockItem(GoodEndingBlocks.MUDDY_OAK_FENCE_GATE, new Item.Properties()));
    public static final Item MUDDY_OAK_SIGN = register("muddy_oak_sign", new SignItem(new Item.Properties().stacksTo(16), GoodEndingBlocks.MUDDY_OAK_SIGN, GoodEndingBlocks.MUDDY_OAK_WALL_SIGN));
    public static final Item MUDDY_OAK_BOAT = register("muddy_oak_boat", new GoodEndingBoatItem(false, GoodEndingBoatEntity.BoatType.MUDDY_OAK, new Item.Properties().stacksTo(1)));
    public static final Item MUDDY_OAK_CHEST_BOAT = register("muddy_oak_chest_boat", new GoodEndingBoatItem(true, GoodEndingBoatEntity.BoatType.MUDDY_OAK, new Item.Properties().stacksTo(1)));

    //Cypress wood set
    public static final Item CYPRESS_PLANKS = register("cypress_planks", new BlockItem(GoodEndingBlocks.CYPRESS_PLANKS, new Item.Properties()));
    public static final Item CYPRESS_LOG = register("cypress_log", new BlockItem(GoodEndingBlocks.CYPRESS_LOG, new Item.Properties()));
    public static final Item CYPRESS_SAPLING = register("cypress_sapling", new BlockItem(GoodEndingBlocks.CYPRESS_SAPLING, new Item.Properties()));
    public static final Item STRIPPED_CYPRESS_LOG = register("stripped_cypress_log", new BlockItem(GoodEndingBlocks.STRIPPED_CYPRESS_LOG, new Item.Properties()));
    public static final Item STRIPPED_CYPRESS_WOOD = register("stripped_cypress_wood", new BlockItem(GoodEndingBlocks.STRIPPED_CYPRESS_WOOD, new Item.Properties()));
    public static final Item CYPRESS_WOOD = register("cypress_wood", new BlockItem(GoodEndingBlocks.CYPRESS_WOOD, new Item.Properties()));
    public static final Item CYPRESS_LEAVES = register("cypress_leaves", new BlockItem(GoodEndingBlocks.CYPRESS_LEAVES, new Item.Properties()));
    public static final Item CYPRESS_SLAB = register("cypress_slab", new BlockItem(GoodEndingBlocks.CYPRESS_SLAB, new Item.Properties()));
    public static final Item CYPRESS_FENCE = register("cypress_fence", new BlockItem(GoodEndingBlocks.CYPRESS_FENCE, new Item.Properties()));
    public static final Item CYPRESS_STAIRS = register("cypress_stairs", new BlockItem(GoodEndingBlocks.CYPRESS_STAIRS, new Item.Properties()));
    public static final Item CYPRESS_BUTTON = register("cypress_button", new BlockItem(GoodEndingBlocks.CYPRESS_BUTTON, new Item.Properties()));
    public static final Item CYPRESS_PRESSURE_PLATE = register("cypress_pressure_plate", new BlockItem(GoodEndingBlocks.CYPRESS_PRESSURE_PLATE, new Item.Properties()));
    public static final Item CYPRESS_DOOR = register("cypress_door", new BlockItem(GoodEndingBlocks.CYPRESS_DOOR, new Item.Properties()));
    public static final Item CYPRESS_TRAPDOOR = register("cypress_trapdoor", new BlockItem(GoodEndingBlocks.CYPRESS_TRAPDOOR, new Item.Properties()));
    public static final Item CYPRESS_FENCE_GATE = register("cypress_fence_gate", new BlockItem(GoodEndingBlocks.CYPRESS_FENCE_GATE, new Item.Properties()));
    public static final Item CYPRESS_SIGN = register("cypress_sign", new SignItem(new Item.Properties().stacksTo(16), GoodEndingBlocks.CYPRESS_SIGN, GoodEndingBlocks.CYPRESS_WALL_SIGN));
    public static final Item CYPRESS_BOAT = register("cypress_boat", new GoodEndingBoatItem(false, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Properties().stacksTo(1)));
    public static final Item CYPRESS_CHEST_BOAT = register("cypress_chest_boat", new GoodEndingBoatItem(true, GoodEndingBoatEntity.BoatType.CYPRESS, new Item.Properties().stacksTo(1)));

    //Lily pads
    public static final Item LARGE_LILY_PAD = register("large_lily_pad", new PlaceableUnderWaterItem(GoodEndingBlocks.LARGE_LILY_PAD, new Item.Properties()));
    public static final Item PURPLE_FLOWERING_LILY_PAD = register("purple_flowering_lily_pad", new PlaceOnWaterBlockItem(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD, new Item.Properties()));
    public static final Item PINK_FLOWERING_LILY_PAD = register("pink_flowering_lily_pad", new PlaceOnWaterBlockItem(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD, new Item.Properties()));
    public static final Item YELLOW_FLOWERING_LILY_PAD = register("yellow_flowering_lily_pad", new PlaceOnWaterBlockItem(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD, new Item.Properties()));

    //Flowers
    public static final Item PASTEL_WILDFLOWERS = register("pastel_wildflowers", new BlockItem(GoodEndingBlocks.PASTEL_WILDFLOWERS, new Item.Properties()));
    public static final Item TWILIGHT_WILDFLOWERS = register("twilight_wildflowers", new BlockItem(GoodEndingBlocks.TWILIGHT_WILDFLOWERS, new Item.Properties()));
    public static final Item SPICY_WILDFLOWERS = register("spicy_wildflowers", new BlockItem(GoodEndingBlocks.SPICY_WILDFLOWERS, new Item.Properties()));
    public static final Item BALMY_WILDFLOWERS = register("balmy_wildflowers", new BlockItem(GoodEndingBlocks.BALMY_WILDFLOWERS, new Item.Properties()));
    public static final Item POLLENFLAKE = register("pollenflake", new BlockItem(GoodEndingBlocks.POLLENFLAKE, new Item.Properties()));

    //Birch forest related
    public static final Item BIRCH_MUSHROOM = register("birch_mushroom", new BlockItem(GoodEndingBlocks.BIRCH_MUSHROOM, new Item.Properties()));

    //General world enhancing blocks
    public static final Item DENSE_BIRCH_LEAVES = register("dense_birch_leaves", new BlockItem(GoodEndingBlocks.DENSE_BIRCH_LEAVES, new Item.Properties()));
    public static final Item DENSE_DARK_OAK_LEAVES = register("dense_dark_oak_leaves", new BlockItem(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES, new Item.Properties()));
    public static final Item HANGING_OAK_LEAVES = register("hanging_oak_leaves", new BlockItem(GoodEndingBlocks.HANGING_OAK_LEAVES, new Item.Properties()));
    public static final Item HANGING_DARK_OAK_LEAVES = register("hanging_dark_oak_leaves", new BlockItem(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES, new Item.Properties()));

    //Swamp related
    public static final Item DUCKWEED = register("duckweed", new PlaceOnWaterBlockItem(GoodEndingBlocks.DUCKWEED, new Item.Properties()));
    public static final Item ALGAE_BUCKET = register("algae_bucket", new AlgaeBucketItem(GoodEndingBlocks.ALGAE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1), SoundEvents.LILY_PAD_PLACE));
    public static final Item CATTAIL = register("cattail", new DoubleHighBlockItem(GoodEndingBlocks.CATTAIL, new Item.Properties()));
    public static final Item FIREFLY_BOTTLE = register("firefly_bottle", new FireflyBottleItem(new Item.Properties().stacksTo(16)));
    public static final Item FIREFLY_LANTERN = register("firefly_lantern", new BlockItem(GoodEndingBlocks.FIREFLY_LANTERN, new Item.Properties()));

    private static Item register(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, id), item);
    }

    static {
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.CYPRESS_LEAVES, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.DUCKWEED, 0.5F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.CATTAIL, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.CYPRESS_SAPLING, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.PASTEL_WILDFLOWERS, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.TWILIGHT_WILDFLOWERS, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.SPICY_WILDFLOWERS, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.BALMY_WILDFLOWERS, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.BIRCH_MUSHROOM, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.LARGE_LILY_PAD, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.DENSE_BIRCH_LEAVES, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.DENSE_DARK_OAK_LEAVES, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.HANGING_OAK_LEAVES, 0.3F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES, 0.65F);
        CompostingChanceRegistry.INSTANCE.add(GoodEndingBlocks.POLLENFLAKE, 0.65F);
    }
}
