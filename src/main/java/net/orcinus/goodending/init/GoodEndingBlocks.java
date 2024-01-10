package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ButtonBlock;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.orcinus.goodending.blocks.AlgaeBlock;
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

import static net.orcinus.goodending.GoodEnding.MODID;

public class GoodEndingBlocks {

    //Muddy oak wood set
    public static final Block MUDDY_OAK_PLANKS = register("muddy_oak_planks", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(2.0f, 3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block MUDDY_OAK_LOG = register("muddy_oak_log", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_LIGHT_GREEN : MapColor.COLOR_GRAY).strength(2.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MUDDY_OAK_LOG = register("stripped_muddy_oak_log", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(2.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block STRIPPED_MUDDY_OAK_WOOD = register("stripped_muddy_oak_wood", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GREEN).strength(2.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block MUDDY_OAK_WOOD = register("muddy_oak_wood", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).strength(2.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block MUDDY_OAK_SLAB = register("muddy_oak_slab", new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(2.0f, 3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block MUDDY_OAK_FENCE = register("muddy_oak_fence", new FenceBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_PLANKS.defaultMapColor()).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion()));
    public static final Block MUDDY_OAK_STAIRS = register("muddy_oak_stairs", new StairBlock(MUDDY_OAK_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(MUDDY_OAK_PLANKS)));
    public static final Block MUDDY_OAK_BUTTON = register("muddy_oak_button", new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5f).pushReaction(PushReaction.DESTROY), GoodEndingBlockSetTypes.MUDDY_OAK, 30, true));
    public static final Block MUDDY_OAK_PRESSURE_PLATE = register("muddy_oak_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_PLANKS.defaultMapColor()).noCollission().strength(0.5f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD), GoodEndingBlockSetTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_DOOR = register("muddy_oak_door", new DoorBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_PLANKS.defaultMapColor()).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion(), GoodEndingBlockSetTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_TRAPDOOR = register("muddy_oak_trapdoor", new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GREEN).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, world, pos, type) -> false), GoodEndingBlockSetTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_FENCE_GATE = register("muddy_oak_fence_gate", new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_PLANKS.defaultMapColor()).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion(), GoodEndingSignTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_SIGN = register("muddy_oak_sign", new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_LOG.defaultMapColor()).noCollission().strength(1.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD), GoodEndingSignTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_WALL_SIGN = register("muddy_oak_wall_sign", new WallSignBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_LOG.defaultMapColor()).noCollission().strength(1.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).dropsLike(MUDDY_OAK_SIGN), GoodEndingSignTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_HANGING_SIGN = register("muddy_oak_hanging_sign", new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava(), GoodEndingSignTypes.MUDDY_OAK));
    public static final Block MUDDY_OAK_WALL_HANGING_SIGN = register("muddy_oak_wall_hanging_sign", new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(MUDDY_OAK_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava().dropsLike(MUDDY_OAK_HANGING_SIGN), GoodEndingSignTypes.MUDDY_OAK));

    //Cypress wood set
    public static final Block CYPRESS_PLANKS = register("cypress_planks", new Block(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block CYPRESS_LOG = register("cypress_log", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MapColor.COLOR_BROWN : MapColor.COLOR_LIGHT_GRAY).strength(2.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block CYPRESS_SAPLING = register("cypress_sapling", new CypressSaplingBlock(new CypressSaplingGenerator(), BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final Block POTTED_CYPRESS_SAPLING = register("potted_cypress_sapling", new FlowerPotBlock(CYPRESS_SAPLING, BlockBehaviour.Properties.of().instabreak().noOcclusion()));
    public static final Block STRIPPED_CYPRESS_LOG = register("stripped_cypress_log", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block STRIPPED_CYPRESS_WOOD = register("stripped_cypress_wood", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).strength(2.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block CYPRESS_WOOD = register("cypress_wood", new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).strength(2.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block CYPRESS_LEAVES = register("cypress_leaves", new LeavesBlock(FabricBlockSettings.copyOf(Blocks.OAK_LEAVES)));
    public static final Block CYPRESS_SLAB = register("cypress_slab", new SlabBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD)));
    public static final Block CYPRESS_FENCE = register("cypress_fence", new FenceBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_PLANKS.defaultMapColor()).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion()));
    public static final Block CYPRESS_STAIRS = register("cypress_stairs", new StairBlock(CYPRESS_PLANKS.defaultBlockState(), BlockBehaviour.Properties.copy(CYPRESS_PLANKS)));
    public static final Block CYPRESS_BUTTON = register("cypress_button", new ButtonBlock(BlockBehaviour.Properties.of().noCollission().strength(0.5f).pushReaction(PushReaction.DESTROY), GoodEndingBlockSetTypes.CYPRESS, 30, true));
    public static final Block CYPRESS_PRESSURE_PLATE = register("cypress_pressure_plate", new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(CYPRESS_PLANKS.defaultMapColor()).noCollission().strength(0.5f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD), GoodEndingBlockSetTypes.CYPRESS));
    public static final Block CYPRESS_DOOR = register("cypress_door", new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion(), GoodEndingBlockSetTypes.CYPRESS));
    public static final Block CYPRESS_TRAPDOOR = register("cypress_trapdoor", new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, world, pos, type) -> false), GoodEndingBlockSetTypes.CYPRESS));
    public static final Block CYPRESS_FENCE_GATE = register("cypress_fence_gate", new FenceGateBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_PLANKS.defaultMapColor()).strength(3.0f).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).noOcclusion(), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_SIGN = register("cypress_sign", new StandingSignBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_LOG.defaultMapColor()).noCollission().strength(1.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_WALL_SIGN = register("cypress_wall_sign", new WallSignBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_LOG.defaultMapColor()).noCollission().strength(1.0F).instrument(NoteBlockInstrument.BASS).sound(SoundType.WOOD).dropsLike(CYPRESS_SIGN), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_HANGING_SIGN = register("cypress_hanging_sign", new CeilingHangingSignBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava(), GoodEndingSignTypes.CYPRESS));
    public static final Block CYPRESS_WALL_HANGING_SIGN = register("cypress_wall_hanging_sign", new WallHangingSignBlock(BlockBehaviour.Properties.of().mapColor(CYPRESS_LOG.defaultMapColor()).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0f).ignitedByLava().dropsLike(CYPRESS_HANGING_SIGN), GoodEndingSignTypes.CYPRESS));

    //Lily pads
    public static final Block LARGE_LILY_PAD = register("large_lily_pad", new LargeLilyPadBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final Block PURPLE_FLOWERING_LILY_PAD = register("purple_flowering_lily_pad", new WaterlilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final Block PINK_FLOWERING_LILY_PAD = register("pink_flowering_lily_pad", new WaterlilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final Block YELLOW_FLOWERING_LILY_PAD = register("yellow_flowering_lily_pad", new WaterlilyBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

    //Flowers
    public static final Block PASTEL_WILDFLOWERS = register("pastel_wildflowers", new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final Block TWILIGHT_WILDFLOWERS = register("twilight_wildflowers", new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final Block SPICY_WILDFLOWERS = register("spicy_wildflowers", new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final Block BALMY_WILDFLOWERS = register("balmy_wildflowers", new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final Block POLLENFLAKE = register("pollenflake", new DoublePlantBlock(BlockBehaviour.Properties.copy(Blocks.PEONY)));

    //Birch forest related
    public static final Block BIRCH_MUSHROOM = register("birch_mushroom", new BirchMushroomPlantBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM).noCollission().instabreak()));

    //General world enhancing blocks
    public static final Block DENSE_BIRCH_LEAVES = register("dense_birch_leaves", new DenseLeavesBlock(() -> GoodEndingParticleTypes.BIRCH_LEAF, BlockBehaviour.Properties.copy(Blocks.BIRCH_LEAVES).sound(GoodEndingSoundTypes.DENSE_LEAVES)));
    public static final Block DENSE_DARK_OAK_LEAVES = register("dense_dark_oak_leaves", new DenseLeavesBlock(() -> GoodEndingParticleTypes.DARK_OAK_LEAF, BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).sound(GoodEndingSoundTypes.DENSE_LEAVES)));
    public static final Block HANGING_OAK_LEAVES_PLANT = register("hanging_oak_leaves_plant", new HangingOakLeavesPlantBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingSoundTypes.HANGING_LEAVES)));
    public static final Block HANGING_OAK_LEAVES = register("hanging_oak_leaves", new HangingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingSoundTypes.HANGING_LEAVES), HANGING_OAK_LEAVES_PLANT));
    public static final Block HANGING_DARK_OAK_LEAVES_PLANT = register("hanging_dark_oak_leaves_plant", new HangingDarkOakLeavesPlantBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingSoundTypes.HANGING_LEAVES)));
    public static final Block HANGING_DARK_OAK_LEAVES = register("hanging_dark_oak_leaves", new HangingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingSoundTypes.HANGING_LEAVES), HANGING_DARK_OAK_LEAVES_PLANT));

    //Swamp related
    public static final Block DUCKWEED = register("duckweed", new WaterlilyBlock(BlockBehaviour.Properties.of().instabreak().sound(SoundType.MOSS_CARPET).noCollission().noOcclusion()));
    public static final Block ALGAE = register("algae", new AlgaeBlock(BlockBehaviour.Properties.of().sound(GoodEndingSoundTypes.ALGAE).replaceable().noCollission().strength(0.2F).pushReaction(PushReaction.DESTROY)));
    public static final Block CATTAIL = register("cattail", new CattailBlock(BlockBehaviour.Properties.of().noCollission().instabreak().sound(SoundType.SMALL_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final Block FIREFLY_LANTERN = register("firefly_lantern", new FireflyLanternBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5F).sound(GoodEndingSoundTypes.FIREFLY_LANTERN).lightLevel(state -> 4).noOcclusion()));

    private static Block register(String id, Block block) { return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MODID, id), block); }
}
