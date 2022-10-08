package net.orcinus.goodending.init;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import net.minecraft.world.level.block.TrapDoorBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.blocks.AlgaeBlock;
import net.orcinus.goodending.blocks.BirchMushroomPlantBlock;
import net.orcinus.goodending.blocks.CattailBlock;
import net.orcinus.goodending.blocks.CypressSaplingBlock;
import net.orcinus.goodending.blocks.DenseLeavesBlock;
import net.orcinus.goodending.blocks.FireflyLanternBlock;
import net.orcinus.goodending.blocks.GEStandingSignBlock;
import net.orcinus.goodending.blocks.GEWallSignBlock;
import net.orcinus.goodending.blocks.HangingDarkOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.HangingLeavesBlock;
import net.orcinus.goodending.blocks.HangingOakLeavesPlantBlock;
import net.orcinus.goodending.blocks.LargeLilyPadBlock;
import net.orcinus.goodending.blocks.PlaceableOnWaterBlock;
import net.orcinus.goodending.blocks.WildflowersBlock;
import net.orcinus.goodending.world.gen.features.generators.CypressSaplingGenerator;

import static net.orcinus.goodending.GoodEnding.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    //Muddy oak wood set
    public static final RegistryObject<Block> MUDDY_OAK_PLANKS = BLOCKS.register("muddy_oak_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PLANT).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_LOG = BLOCKS.register("muddy_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.COLOR_GREEN : MaterialColor.COLOR_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_MUDDY_OAK_LOG = BLOCKS.register("stripped_muddy_oak_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_MUDDY_OAK_WOOD = BLOCKS.register("stripped_muddy_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GREEN).strength(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_WOOD = BLOCKS.register("muddy_oak_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_GRAY).strength(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_SLAB = BLOCKS.register("muddy_oak_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PLANT).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_FENCE = BLOCKS.register("muddy_oak_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_PLANKS.get().defaultMaterialColor()).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> MUDDY_OAK_STAIRS = BLOCKS.register("muddy_oak_stairs", () -> new StairBlock(MUDDY_OAK_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(MUDDY_OAK_PLANKS.get())));
    public static final RegistryObject<Block> MUDDY_OAK_BUTTON = BLOCKS.register("muddy_oak_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_PRESSURE_PLATE = BLOCKS.register("muddy_oak_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> MUDDY_OAK_DOOR = BLOCKS.register("muddy_oak_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_PLANKS.get().defaultMaterialColor()).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> MUDDY_OAK_TRAPDOOR = BLOCKS.register("muddy_oak_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.PLANT).strength(3.0f).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, world, pos, type) -> false)));
    public static final RegistryObject<Block> MUDDY_OAK_FENCE_GATE = BLOCKS.register("muddy_oak_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_PLANKS.get().defaultMaterialColor()).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> MUDDY_OAK_SIGN = BLOCKS.register("muddy_oak_sign", () -> new GEStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD), GoodEndingSignTypes.MUDDY_OAK));
    public static final RegistryObject<Block> MUDDY_OAK_WALL_SIGN = BLOCKS.register("muddy_oak_wall_sign", () -> new GEWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, MUDDY_OAK_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(MUDDY_OAK_SIGN.get()), GoodEndingSignTypes.MUDDY_OAK));

    //Cypress wood set
    public static final RegistryObject<Block> CYPRESS_PLANKS = BLOCKS.register("cypress_planks", () -> new Block(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_LOG = BLOCKS.register("cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? MaterialColor.COLOR_BROWN : MaterialColor.COLOR_LIGHT_GRAY).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_SAPLING = BLOCKS.register("cypress_sapling", () -> new CypressSaplingBlock(new CypressSaplingGenerator(), BlockBehaviour.Properties.of(Material.PLANT).noCollission().randomTicks().instabreak().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> POTTED_CYPRESS_SAPLING = BLOCKS.register("potted_cypress_sapling", () -> new FlowerPotBlock(CYPRESS_SAPLING.get(), BlockBehaviour.Properties.of(Material.DECORATION).instabreak().noOcclusion()));
    public static final RegistryObject<Block> STRIPPED_CYPRESS_LOG = BLOCKS.register("stripped_cypress_log", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0F).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> STRIPPED_CYPRESS_WOOD = BLOCKS.register("stripped_cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_WOOD = BLOCKS.register("cypress_wood", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).strength(2.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_LEAVES = BLOCKS.register("cypress_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> CYPRESS_SLAB = BLOCKS.register("cypress_slab", () -> new SlabBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).strength(2.0f, 3.0f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_FENCE = BLOCKS.register("cypress_fence", () -> new FenceBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS.get().defaultMaterialColor()).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CYPRESS_STAIRS = BLOCKS.register("cypress_stairs", () -> new StairBlock(CYPRESS_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(CYPRESS_PLANKS.get())));
    public static final RegistryObject<Block> CYPRESS_BUTTON = BLOCKS.register("cypress_button", () -> new WoodButtonBlock(BlockBehaviour.Properties.of(Material.DECORATION).noCollission().strength(0.5f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_PRESSURE_PLATE = BLOCKS.register("cypress_pressure_plate", () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS.get().defaultMaterialColor()).noCollission().strength(0.5f).sound(SoundType.WOOD)));
    public static final RegistryObject<Block> CYPRESS_DOOR = BLOCKS.register("cypress_door", () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CYPRESS_TRAPDOOR = BLOCKS.register("cypress_trapdoor", () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD, MaterialColor.TERRACOTTA_BROWN).strength(3.0f).sound(SoundType.WOOD).noOcclusion().isValidSpawn((state, world, pos, type) -> false)));
    public static final RegistryObject<Block> CYPRESS_FENCE_GATE = BLOCKS.register("cypress_fence_gate", () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_PLANKS.get().defaultMaterialColor()).strength(3.0f).sound(SoundType.WOOD).noOcclusion()));
    public static final RegistryObject<Block> CYPRESS_SIGN = BLOCKS.register("cypress_sign", () -> new GEStandingSignBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD), GoodEndingSignTypes.CYPRESS));
    public static final RegistryObject<Block> CYPRESS_WALL_SIGN = BLOCKS.register("cypress_wall_sign", () -> new GEWallSignBlock(BlockBehaviour.Properties.of(Material.WOOD, CYPRESS_LOG.get().defaultMaterialColor()).noCollission().strength(1.0F).sound(SoundType.WOOD).dropsLike(CYPRESS_SIGN.get()), GoodEndingSignTypes.CYPRESS));

    //Lily pads
    public static final RegistryObject<Block> LARGE_LILY_PAD = BLOCKS.register("large_lily_pad", () -> new LargeLilyPadBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final RegistryObject<Block> PURPLE_FLOWERING_LILY_PAD = BLOCKS.register("purple_flowering_lily_pad", () -> new PlaceableOnWaterBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final RegistryObject<Block> PINK_FLOWERING_LILY_PAD = BLOCKS.register("pink_flowering_lily_pad", () -> new PlaceableOnWaterBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));
    public static final RegistryObject<Block> YELLOW_FLOWERING_LILY_PAD = BLOCKS.register("yellow_flowering_lily_pad", () -> new PlaceableOnWaterBlock(BlockBehaviour.Properties.copy(Blocks.LILY_PAD)));

    //Flowers
    public static final RegistryObject<Block> PASTEL_WILDFLOWERS = BLOCKS.register("pastel_wildflowers", () -> new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistryObject<Block> TWILIGHT_WILDFLOWERS = BLOCKS.register("twilight_wildflowers", () -> new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistryObject<Block> SPICY_WILDFLOWERS = BLOCKS.register("spicy_wildflowers", () -> new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistryObject<Block> BALMY_WILDFLOWERS = BLOCKS.register("balmy_wildflowers", () -> new WildflowersBlock(BlockBehaviour.Properties.copy(Blocks.POPPY).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistryObject<Block> POLLENFLAKE = BLOCKS.register("pollenflake", () -> new TallFlowerBlock(BlockBehaviour.Properties.copy(Blocks.PEONY)));

    //Birch forest related
    public static final RegistryObject<Block> BIRCH_MUSHROOM = BLOCKS.register("birch_mushroom", () -> new BirchMushroomPlantBlock(BlockBehaviour.Properties.copy(Blocks.BROWN_MUSHROOM).noCollission().instabreak()));

    //General world enhancing blocks
    public static final RegistryObject<Block> DENSE_BIRCH_LEAVES = BLOCKS.register("dense_birch_leaves", () -> new DenseLeavesBlock(GoodEndingParticleTypes.BIRCH_LEAF, BlockBehaviour.Properties.copy(Blocks.BIRCH_LEAVES).sound(GoodEndingBlockSoundGroups.DENSE_LEAVES)));
    public static final RegistryObject<Block> DENSE_DARK_OAK_LEAVES = BLOCKS.register("dense_dark_oak_leaves", () -> new DenseLeavesBlock(GoodEndingParticleTypes.DARK_OAK_LEAF, BlockBehaviour.Properties.copy(Blocks.DARK_OAK_LEAVES).sound(GoodEndingBlockSoundGroups.DENSE_LEAVES)));
    public static final RegistryObject<Block> HANGING_OAK_LEAVES_PLANT = BLOCKS.register("hanging_oak_leaves_plant", () -> new HangingOakLeavesPlantBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingBlockSoundGroups.HANGING_LEAVES)));
    public static final RegistryObject<Block> HANGING_OAK_LEAVES = BLOCKS.register("hanging_oak_leaves", () -> new HangingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks().noCollission().instabreak().sound(GoodEndingBlockSoundGroups.HANGING_LEAVES), HANGING_OAK_LEAVES_PLANT.get()));
    public static final RegistryObject<Block> HANGING_DARK_OAK_LEAVES_PLANT = BLOCKS.register("hanging_dark_oak_leaves_plant", () -> new HangingDarkOakLeavesPlantBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).noCollission().instabreak().sound(GoodEndingBlockSoundGroups.HANGING_LEAVES)));
    public static final RegistryObject<Block> HANGING_DARK_OAK_LEAVES = BLOCKS.register("hanging_dark_oak_leaves", () -> new HangingLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).randomTicks().noCollission().instabreak().sound(GoodEndingBlockSoundGroups.HANGING_LEAVES), HANGING_DARK_OAK_LEAVES_PLANT.get()));

    //Swamp related
    public static final RegistryObject<Block> DUCKWEED = BLOCKS.register("duckweed", () -> new WaterlilyBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).instabreak().sound(SoundType.MOSS_CARPET).noCollission().noOcclusion()));
    public static final RegistryObject<Block> ALGAE = BLOCKS.register("algae", () -> new AlgaeBlock(BlockBehaviour.Properties.of(Material.REPLACEABLE_PLANT).instabreak().sound(SoundType.SLIME_BLOCK).noOcclusion()));
    public static final RegistryObject<Block> CATTAIL = BLOCKS.register("cattail", () -> new CattailBlock(BlockBehaviour.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.SMALL_DRIPLEAF).offsetType(BlockBehaviour.OffsetType.XYZ)));
    public static final RegistryObject<Block> FIREFLY_LANTERN = BLOCKS.register("firefly_lantern", () -> new FireflyLanternBlock(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(3.5F).sound(GoodEndingBlockSoundGroups.FIREFLY_LANTERN).lightLevel(state -> 4).noOcclusion()));

}
