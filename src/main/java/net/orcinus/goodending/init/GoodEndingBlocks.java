package net.orcinus.goodending.init;

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
import net.minecraft.block.TallFlowerBlock;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodenButtonBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.blocks.CattailBlock;
import net.orcinus.goodending.blocks.CypressSaplingBlock;
import net.orcinus.goodending.blocks.DenseLeavesBlock;
import net.orcinus.goodending.blocks.FireflyLanternBlock;
import net.orcinus.goodending.blocks.HangingDarkOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.blocks.HangingOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.LargeLilyPadBlock;
import net.orcinus.goodending.blocks.WildflowersBlock;
import net.orcinus.goodending.world.gen.features.generators.CypressSaplingGenerator;

import static net.orcinus.goodending.GoodEnding.*;

public class GoodEndingBlocks {

    //Muddy oak wood set
    public static final Block MUDDY_OAK_PLANKS = register("muddy_oak_planks", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.DARK_GREEN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_LOG = register("muddy_oak_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.GREEN : MapColor.GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_MUDDY_OAK_LOG = register("stripped_muddy_oak_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.GREEN).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_MUDDY_OAK_WOOD = register("stripped_muddy_oak_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.GREEN).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_WOOD = register("muddy_oak_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.GRAY).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_SLAB = register("muddy_oak_slab", new SlabBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.DARK_GREEN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_FENCE = register("muddy_oak_fence", new FenceBlock(AbstractBlock.Settings.of(Material.WOOD, MUDDY_OAK_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MUDDY_OAK_STAIRS = register("muddy_oak_stairs", new StairsBlock(MUDDY_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(MUDDY_OAK_PLANKS)));
    public static final Block MUDDY_OAK_BUTTON = register("muddy_oak_button", new WoodenButtonBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_PRESSURE_PLATE = register("muddy_oak_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, MUDDY_OAK_PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block MUDDY_OAK_DOOR = register("muddy_oak_door", new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, MUDDY_OAK_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MUDDY_OAK_TRAPDOOR = register("muddy_oak_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.DARK_GREEN).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning((state, world, pos, type) -> false)));
    public static final Block MUDDY_OAK_FENCE_GATE = register("muddy_oak_fence_gate", new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, MUDDY_OAK_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block MUDDY_OAK_SIGN = register("muddy_oak_sign", new SignBlock(FabricBlockSettings.of(Material.WOOD, MUDDY_OAK_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), GoodEndingSignTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_WALL_SIGN = register("muddy_oak_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, MUDDY_OAK_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(MUDDY_OAK_SIGN), GoodEndingSignTypes.MUDDY_OAK));

    //Cypress wood set
    public static final Block CYPRESS_PLANKS = register("cypress_planks", new Block(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_LOG = register("cypress_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.BROWN : MapColor.LIGHT_GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_SAPLING = register("cypress_sapling", new CypressSaplingBlock(new CypressSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)));
    public static final Block POTTED_CYPRESS_SAPLING = register("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, AbstractBlock.Settings.of(Material.DECORATION).breakInstantly().nonOpaque()));
    public static final Block STRIPPED_CYPRESS_LOG = register("stripped_cypress_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD)));
    public static final Block STRIPPED_CYPRESS_WOOD = register("stripped_cypress_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_WOOD = register("cypress_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.LIGHT_GRAY).strength(2.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_LEAVES = register("cypress_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block CYPRESS_SLAB = register("cypress_slab", new SlabBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_FENCE = register("cypress_fence", new FenceBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_STAIRS = register("cypress_stairs", new StairsBlock(CYPRESS_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(CYPRESS_PLANKS)));
    public static final Block CYPRESS_BUTTON = register("cypress_button", new WoodenButtonBlock(AbstractBlock.Settings.of(Material.DECORATION).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_PRESSURE_PLATE = register("cypress_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).noCollision().strength(0.5f).sounds(BlockSoundGroup.WOOD)));
    public static final Block CYPRESS_DOOR = register("cypress_door", new DoorBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_TRAPDOOR = register("cypress_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.TERRACOTTA_BROWN).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque().allowsSpawning((state, world, pos, type) -> false)));
    public static final Block CYPRESS_FENCE_GATE = register("cypress_fence_gate", new FenceGateBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_PLANKS.getDefaultMapColor()).strength(3.0f).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block CYPRESS_SIGN = register("cypress_sign", new SignBlock(FabricBlockSettings.of(Material.WOOD, CYPRESS_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_WALL_SIGN = register("cypress_wall_sign", new WallSignBlock(AbstractBlock.Settings.of(Material.WOOD, CYPRESS_LOG.getDefaultMapColor()).noCollision().strength(1.0F).sounds(BlockSoundGroup.WOOD).dropsLike(CYPRESS_SIGN), GoodEndingSignTypes.CYPRESS));

    //Lily pads
    public static final Block LARGE_LILY_PAD = register("large_lily_pad", new LargeLilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block PURPLE_FLOWERING_LILY_PAD = register("purple_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block PINK_FLOWERING_LILY_PAD = register("pink_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));
    public static final Block YELLOW_FLOWERING_LILY_PAD = register("yellow_flowering_lily_pad", new LilyPadBlock(AbstractBlock.Settings.copy(Blocks.LILY_PAD)));

    //Flowers
    public static final Block PASTEL_WILDFLOWERS = register("pastel_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block TWILIGHT_WILDFLOWERS = register("twilight_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block SPICY_WILDFLOWERS = register("spicy_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block BALMY_WILDFLOWERS = register("balmy_wildflowers", new WildflowersBlock(AbstractBlock.Settings.copy(Blocks.POPPY).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block POLLENFLAKE = register("pollenflake", new TallFlowerBlock(AbstractBlock.Settings.copy(Blocks.PEONY)));

    //Birch forest related
    public static final Block BIRCH_MUSHROOM = register("birch_mushroom", new BirchMushroomPlantBlock(AbstractBlock.Settings.copy(Blocks.BROWN_MUSHROOM).noCollision().breakInstantly()));

    //General world enhancing blocks
    public static final Block DENSE_BIRCH_LEAVES = register("dense_birch_leaves", new DenseLeavesBlock(() -> GoodEndingParticleTypes.BIRCH_LEAF, AbstractBlock.Settings.copy(Blocks.BIRCH_LEAVES).sounds(GoodEndingBlockSoundGroups.DENSE_LEAVES)));
    public static final Block DENSE_DARK_OAK_LEAVES = register("dense_dark_oak_leaves", new DenseLeavesBlock(() -> GoodEndingParticleTypes.DARK_OAK_LEAF, AbstractBlock.Settings.copy(Blocks.DARK_OAK_LEAVES).sounds(GoodEndingBlockSoundGroups.DENSE_LEAVES)));
    public static final Block HANGING_OAK_LEAVES_PLANT = register("hanging_oak_leaves_plant", new HangingOakLeavesPlantBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision().breakInstantly().sounds(GoodEndingBlockSoundGroups.HANGING_LEAVES)));
    public static final Block HANGING_OAK_LEAVES = register("hanging_oak_leaves", new HangingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).ticksRandomly().noCollision().breakInstantly().sounds(GoodEndingBlockSoundGroups.HANGING_LEAVES), HANGING_OAK_LEAVES_PLANT));
    public static final Block HANGING_DARK_OAK_LEAVES_PLANT = register("hanging_dark_oak_leaves_plant", new HangingDarkOakLeavesPlantBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).noCollision().breakInstantly().sounds(GoodEndingBlockSoundGroups.HANGING_LEAVES)));
    public static final Block HANGING_DARK_OAK_LEAVES = register("hanging_dark_oak_leaves", new HangingLeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES).ticksRandomly().noCollision().breakInstantly().sounds(GoodEndingBlockSoundGroups.HANGING_LEAVES), HANGING_DARK_OAK_LEAVES_PLANT));

    //Swamp related
    public static final Block DUCKWEED = register("duckweed", new LilyPadBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).breakInstantly().sounds(BlockSoundGroup.MOSS_CARPET).noCollision().nonOpaque()));
    public static final Block CATTAIL = register("cattail", new CattailBlock(AbstractBlock.Settings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.SMALL_DRIPLEAF).offsetType(AbstractBlock.OffsetType.XYZ)));
    public static final Block FIREFLY_LANTERN = register("firefly_lantern", new FireflyLanternBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.5F).sounds(GoodEndingBlockSoundGroups.FIREFLY_LANTERN).luminance(state -> 4).nonOpaque()));

    private static Block register(String id, Block block) { return Registry.register(Registry.BLOCK, new Identifier(MODID, id), block); }
}
