package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.FlowerPotBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LilyPadBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.SignBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.blocks.CattailBlock;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.blocks.HangingDarkOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.HangingOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.InundatedSaplingBlock;
import net.orcinus.goodending.blocks.LargeLilyPadBlock;
import net.orcinus.goodending.blocks.WildflowersBlock;
import net.orcinus.goodending.world.gen.features.generators.CypressSaplingGenerator;

import java.util.Map;

public class GoodEndingBlocks {

    private static final Map<Identifier, Block> BLOCKS = Maps.newLinkedHashMap();

    public static final Block CYPRESS_LOG = registerBlock("cypress_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.BROWN : MapColor.LIGHT_GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_CYPRESS_LOG = registerBlock("stripped_cypress_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_WOOD = registerBlock("cypress_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.LIGHT_GRAY).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_CYPRESS_WOOD = registerBlock("stripped_cypress_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_PLANKS = registerBlock("cypress_planks", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_SLAB = registerBlock("cypress_slab", new SlabBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_STAIRS = registerBlock("cypress_stairs", new StairsBlock(CYPRESS_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(CYPRESS_PLANKS)));
    public static final Block CYPRESS_BUTTON = registerBlock("cypress_button", new WoodenButtonBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_PRESSURE_PLATE = registerBlock("cypress_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_DOOR = registerBlock("cypress_door", new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_FENCE = registerBlock("cypress_fence", new FenceBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_FENCE_GATE = registerBlock("cypress_fence_gate", new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_TRAPDOOR = registerBlock("cypress_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning((state, world, pos, type) -> false)));
    public static final Block CYPRESS_SAPLING = registerBlock("cypress_sapling", new InundatedSaplingBlock(new CypressSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block CYPRESS_LEAVES = registerBlock("cypress_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block LARGE_LILY_PAD = registerNoTabBlock("large_lily_pad", new LargeLilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block PURPLE_FLOWERING_LILY_PAD = registerNoTabBlock("purple_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block PINK_FLOWERING_LILY_PAD = registerNoTabBlock("pink_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block YELLOW_FLOWERING_LILY_PAD = registerNoTabBlock("yellow_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block PASTEL_WILDFLOWERS = registerBlock("pastel_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block TWILIGHT_WILDFLOWERS = registerBlock("twilight_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block SPICY_WILDFLOWERS = registerBlock("spicy_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block BALMY_WILDFLOWERS = registerBlock("balmy_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block BIRCH_MUSHROOM = registerBlock("birch_mushroom", new BirchMushroomPlantBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM).noCollision().breakInstantly()));
    public static final Block HANGING_OAK_LEAVES_PLANT = registerNoTabBlock("hanging_oak_leaves_plant", new HangingOakLeavesPlantBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision().breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET)));
    public static final Block HANGING_OAK_LEAVES = registerBlock("hanging_oak_leaves", new HangingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET), HANGING_OAK_LEAVES_PLANT));
    public static final Block HANGING_DARK_OAK_LEAVES_PLANT = registerNoTabBlock("hanging_dark_oak_leaves_plant", new HangingDarkOakLeavesPlantBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision().breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET)));
    public static final Block HANGING_DARK_OAK_LEAVES = registerBlock("hanging_dark_oak_leaves", new HangingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).ticksRandomly().noCollision().breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET), HANGING_DARK_OAK_LEAVES_PLANT));
    public static final Block DUCKWEED = registerNoTabBlock("duckweed", new LilyPadBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET).noCollision().nonOpaque()));
    public static final Block CATTAIL = registerBlock("cattail", new CattailBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.SMALL_DRIPLEAF).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block POTTED_CYPRESS_SAPLING = registerNoTabBlock("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final Block CYPRESS_SIGN = registerNoTabBlock("cypress_sign", new SignBlock(FabricBlockSettings.of(Material.WOOD, CYPRESS_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_WALL_SIGN = registerNoTabBlock("cypress_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CYPRESS_SIGN), GoodEndingSignTypes.CYPRESS));


    public static <B extends Block> B registerBlock(String name, B block) {
        Identifier id = new Identifier(GoodEnding.MODID, name);
        BLOCKS.put(id, block);
        GoodEndingItems.ITEMS.put(id, new BlockItem(block, new Item.Settings().group(GoodEnding.TAB)));
        return block;
    }

    public static <B extends Block> B registerNoTabBlock(String name, B block) {
        BLOCKS.put(new Identifier(GoodEnding.MODID, name), block);
        return block;
    }

    public static void init() {
        BLOCKS.forEach((identifier, block) -> Registry.register(Registry.BLOCK, identifier, block));
    }

}
