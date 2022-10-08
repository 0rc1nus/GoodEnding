package net.orcinus.goodending.init;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.blocks.blockentities.GESignBlockEntity;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GoodEnding.MODID);

    public static final RegistryObject<BlockEntityType<GESignBlockEntity>> GE_SIGN = BLOCK_ENTITY_TYPES.register("sign", () -> BlockEntityType.Builder.of(GESignBlockEntity::new, GoodEndingBlocks.CYPRESS_SIGN.get(), GoodEndingBlocks.CYPRESS_WALL_SIGN.get(), GoodEndingBlocks.MUDDY_OAK_SIGN.get(), GoodEndingBlocks.MUDDY_OAK_WALL_SIGN.get()).build(null));

}
