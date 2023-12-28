package net.orcinus.goodending.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GoodEnding.MODID);

    public static final RegistryObject<CreativeModeTab> GOOD_ENDING = CREATIVE_MODE_TABS.register("good_ending", () -> {
        return CreativeModeTab.builder().icon(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD.get()::getDefaultInstance).title(Component.translatable("itemGroup.goodending.goodending")).displayItems((itemDisplayParameters, output) -> {
            output.accept(GoodEndingItems.FIREFLY_SWARM_SPAWN_EGG.get());
            output.accept(GoodEndingItems.MARSH_SPAWN_EGG.get());
            output.accept(GoodEndingItems.WOODPECKER_SPAWN_EGG.get());
            output.accept(GoodEndingItems.MUDDY_OAK_PLANKS.get());
            output.accept(GoodEndingItems.MUDDY_OAK_LOG.get());
            output.accept(GoodEndingItems.STRIPPED_MUDDY_OAK_LOG.get());
            output.accept(GoodEndingItems.STRIPPED_MUDDY_OAK_WOOD.get());
            output.accept(GoodEndingItems.MUDDY_OAK_WOOD.get());
            output.accept(GoodEndingItems.MUDDY_OAK_SLAB.get());
            output.accept(GoodEndingItems.MUDDY_OAK_FENCE.get());
            output.accept(GoodEndingItems.MUDDY_OAK_STAIRS.get());
            output.accept(GoodEndingItems.MUDDY_OAK_BUTTON.get());
            output.accept(GoodEndingItems.MUDDY_OAK_PRESSURE_PLATE.get());
            output.accept(GoodEndingItems.MUDDY_OAK_DOOR.get());
            output.accept(GoodEndingItems.MUDDY_OAK_TRAPDOOR.get());
            output.accept(GoodEndingItems.MUDDY_OAK_FENCE_GATE.get());
            output.accept(GoodEndingItems.MUDDY_OAK_SIGN.get());
            output.accept(GoodEndingItems.MUDDY_OAK_HANGING_SIGN.get());
            output.accept(GoodEndingItems.MUDDY_OAK_BOAT.get());
            output.accept(GoodEndingItems.MUDDY_OAK_CHEST_BOAT.get());
            output.accept(GoodEndingItems.CYPRESS_PLANKS.get());
            output.accept(GoodEndingItems.CYPRESS_LOG.get());
            output.accept(GoodEndingItems.CYPRESS_SAPLING.get());
            output.accept(GoodEndingItems.STRIPPED_CYPRESS_LOG.get());
            output.accept(GoodEndingItems.STRIPPED_CYPRESS_WOOD.get());
            output.accept(GoodEndingItems.CYPRESS_WOOD.get());
            output.accept(GoodEndingItems.CYPRESS_LEAVES.get());
            output.accept(GoodEndingItems.CYPRESS_SLAB.get());
            output.accept(GoodEndingItems.CYPRESS_FENCE.get());
            output.accept(GoodEndingItems.CYPRESS_STAIRS.get());
            output.accept(GoodEndingItems.CYPRESS_BUTTON.get());
            output.accept(GoodEndingItems.CYPRESS_PRESSURE_PLATE.get());
            output.accept(GoodEndingItems.CYPRESS_DOOR.get());
            output.accept(GoodEndingItems.CYPRESS_TRAPDOOR.get());
            output.accept(GoodEndingItems.CYPRESS_FENCE_GATE.get());
            output.accept(GoodEndingItems.CYPRESS_SIGN.get());
            output.accept(GoodEndingItems.CYPRESS_HANGING_SIGN.get());
            output.accept(GoodEndingItems.CYPRESS_BOAT.get());
            output.accept(GoodEndingItems.CYPRESS_CHEST_BOAT.get());
            output.accept(GoodEndingItems.LARGE_LILY_PAD.get());
            output.accept(GoodEndingItems.PURPLE_FLOWERING_LILY_PAD.get());
            output.accept(GoodEndingItems.PINK_FLOWERING_LILY_PAD.get());
            output.accept(GoodEndingItems.YELLOW_FLOWERING_LILY_PAD.get());
            output.accept(GoodEndingItems.PASTEL_WILDFLOWERS.get());
            output.accept(GoodEndingItems.TWILIGHT_WILDFLOWERS.get());
            output.accept(GoodEndingItems.SPICY_WILDFLOWERS.get());
            output.accept(GoodEndingItems.BALMY_WILDFLOWERS.get());
            output.accept(GoodEndingItems.POLLENFLAKE.get());
            output.accept(GoodEndingItems.BIRCH_MUSHROOM.get());
            output.accept(GoodEndingItems.DENSE_BIRCH_LEAVES.get());
            output.accept(GoodEndingItems.DENSE_DARK_OAK_LEAVES.get());
            output.accept(GoodEndingItems.HANGING_OAK_LEAVES.get());
            output.accept(GoodEndingItems.HANGING_DARK_OAK_LEAVES.get());
            output.accept(GoodEndingItems.DUCKWEED.get());
            output.accept(GoodEndingItems.ALGAE_BUCKET.get());
            output.accept(GoodEndingItems.CATTAIL.get());
            output.accept(GoodEndingItems.FIREFLY_BOTTLE.get());
            output.accept(GoodEndingItems.FIREFLY_LANTERN.get());
        }).build();
    });

}
