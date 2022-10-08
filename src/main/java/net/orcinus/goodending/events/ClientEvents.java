package net.orcinus.goodending.events;

import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeableLeatherItem;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.client.models.WoodPeckerEntityModel;
import net.orcinus.goodending.client.particles.FireflyParticle;
import net.orcinus.goodending.client.particles.LeafParticle;
import net.orcinus.goodending.client.renderer.FireflyRenderer;
import net.orcinus.goodending.client.renderer.GoodEndingBoatEntityRenderer;
import net.orcinus.goodending.client.renderer.MarshRenderer;
import net.orcinus.goodending.client.renderer.WoodpeckerRenderer;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.init.GoodEndingBlockEntityTypes;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingModelLayers;
import net.orcinus.goodending.init.GoodEndingParticleTypes;
import net.orcinus.goodending.init.GoodEndingSignTypes;

import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void onClientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.ALGAE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.BIRCH_MUSHROOM.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.CATTAIL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.LARGE_LILY_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.PASTEL_WILDFLOWERS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.TWILIGHT_WILDFLOWERS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.SPICY_WILDFLOWERS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.BALMY_WILDFLOWERS.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.DUCKWEED.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.FIREFLY_LANTERN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.HANGING_OAK_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.MUDDY_OAK_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.CYPRESS_DOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.CYPRESS_TRAPDOOR.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.CYPRESS_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.POTTED_CYPRESS_SAPLING.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(GoodEndingBlocks.POLLENFLAKE.get(), RenderType.cutout());

        event.enqueueWork(GoodEndingSignTypes::init);
    }

    @SubscribeEvent
    public static void registerEntityModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GoodEndingModelLayers.MARSH, MarshEntityModel::getLayerDefinition);
        event.registerLayerDefinition(GoodEndingModelLayers.WOODPECKER, WoodPeckerEntityModel::getLayerDefinition);
        Arrays.stream(GoodEndingBoatEntity.BoatType.values()).forEach(type -> {
            event.registerLayerDefinition(GoodEndingModelLayers.createBoat(type), () -> BoatModel.createBodyModel(false));
            event.registerLayerDefinition(GoodEndingModelLayers.createChestBoat(type), () -> BoatModel.createBodyModel(true));
        });
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(GoodEndingBlockEntityTypes.GE_SIGN.get(), SignRenderer::new);
        event.registerEntityRenderer(GoodEndingEntityTypes.FIREFLY_SWARM.get(), FireflyRenderer::new);
        event.registerEntityRenderer(GoodEndingEntityTypes.MARSH.get(), MarshRenderer::new);
        event.registerEntityRenderer(GoodEndingEntityTypes.WOODPECKER.get(), WoodpeckerRenderer::new);
        event.registerEntityRenderer(GoodEndingEntityTypes.BOAT.get(), ctx -> new GoodEndingBoatEntityRenderer(ctx, false));
        event.registerEntityRenderer(GoodEndingEntityTypes.CHEST_BOAT.get(), ctx -> new GoodEndingBoatEntityRenderer(ctx, true));
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return FoliageColor.getDefaultColor();
                    }
                    return BiomeColors.getAverageFoliageColor(world, pos);
                },
                GoodEndingBlocks.CYPRESS_LEAVES.get(),
                GoodEndingBlocks.HANGING_OAK_LEAVES.get(),
                GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT.get(),
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get(),
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT.get(),
                GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get(),
                Blocks.LILY_PAD,
                GoodEndingBlocks.LARGE_LILY_PAD.get(),
                GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get(),
                GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get(),
                GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get()
        );
        event.register((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), GoodEndingBlocks.DENSE_BIRCH_LEAVES.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return event.getBlockColors().getColor(blockstate, null, null, tintIndex);
        }, GoodEndingBlocks.DENSE_BIRCH_LEAVES.get(),
           GoodEndingBlocks.DENSE_DARK_OAK_LEAVES.get(),
           GoodEndingBlocks.LARGE_LILY_PAD.get(),
           Blocks.LILY_PAD,
           GoodEndingBlocks.CYPRESS_LEAVES.get(),
           GoodEndingBlocks.HANGING_OAK_LEAVES.get(),
           GoodEndingBlocks.HANGING_DARK_OAK_LEAVES.get());

        event.register((stack, tintIndex) -> {
            BlockState blockstate = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
            return tintIndex > 0 ? -1 : event.getBlockColors().getColor(blockstate, null, null, tintIndex);
        },
        GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD.get(),
        GoodEndingBlocks.PINK_FLOWERING_LILY_PAD.get(),
        GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD.get());
    }

    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event) {
        event.register(GoodEndingParticleTypes.FIREFLY.get(), FireflyParticle.Factory::new);
        event.register(GoodEndingParticleTypes.BIRCH_LEAF.get(), LeafParticle.Factory::new);
        event.register(GoodEndingParticleTypes.DARK_OAK_LEAF.get(), LeafParticle.Factory::new);
    }

}
