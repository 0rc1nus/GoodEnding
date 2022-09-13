package net.orcinus.goodending;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.item.BlockItem;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.client.models.WoodPeckerEntityModel;
import net.orcinus.goodending.client.particles.FireflyParticle;
import net.orcinus.goodending.client.particles.LeafParticle;
import net.orcinus.goodending.client.renderer.FireflyRenderer;
import net.orcinus.goodending.client.renderer.GoodEndingBoatEntityRenderer;
import net.orcinus.goodending.client.renderer.MarshRenderer;
import net.orcinus.goodending.client.renderer.WoodpeckerRenderer;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingModelLayers;
import net.orcinus.goodending.init.GoodEndingParticleTypes;

import java.util.Arrays;

public class GoodEndingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                GoodEndingBlocks.ALGAE,
                GoodEndingBlocks.BIRCH_MUSHROOM,
                GoodEndingBlocks.CATTAIL,
                GoodEndingBlocks.LARGE_LILY_PAD,
                GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
                GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PASTEL_WILDFLOWERS,
                GoodEndingBlocks.TWILIGHT_WILDFLOWERS,
                GoodEndingBlocks.SPICY_WILDFLOWERS,
                GoodEndingBlocks.BALMY_WILDFLOWERS,
                GoodEndingBlocks.DUCKWEED,
                GoodEndingBlocks.FIREFLY_LANTERN,
                GoodEndingBlocks.HANGING_OAK_LEAVES,
                GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT,
                GoodEndingBlocks.MUDDY_OAK_DOOR,
                GoodEndingBlocks.CYPRESS_DOOR,
                GoodEndingBlocks.CYPRESS_TRAPDOOR,
                GoodEndingBlocks.CYPRESS_SAPLING,
                GoodEndingBlocks.POTTED_CYPRESS_SAPLING,
                GoodEndingBlocks.POLLENFLAKE
        );

        ParticleFactoryRegistry.getInstance().register(GoodEndingParticleTypes.FIREFLY, FireflyParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GoodEndingParticleTypes.BIRCH_LEAF, LeafParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(GoodEndingParticleTypes.DARK_OAK_LEAF, LeafParticle.Factory::new);

        EntityRendererRegistry.register(GoodEndingEntityTypes.BOAT, ctx -> new GoodEndingBoatEntityRenderer(ctx, false));
        EntityRendererRegistry.register(GoodEndingEntityTypes.CHEST_BOAT, ctx -> new GoodEndingBoatEntityRenderer(ctx, true));
        EntityRendererRegistry.register(GoodEndingEntityTypes.FIREFLY_SWARM, FireflyRenderer::new);
        EntityRendererRegistry.register(GoodEndingEntityTypes.MARSH, MarshRenderer::new);
        EntityRendererRegistry.register(GoodEndingEntityTypes.WOODPECKER, WoodpeckerRenderer::new);

        Arrays.stream(GoodEndingBoatEntity.BoatType.values()).forEach(type -> {
            EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.createBoat(type), () -> BoatEntityModel.getTexturedModelData(false));
            EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.createChestBoat(type), () -> BoatEntityModel.getTexturedModelData(true));
        });
        EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.MARSH, MarshEntityModel::getTexturedModelData);
        EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.WOODPECKER, WoodPeckerEntityModel::getTexturedModelData);

        ColorProviderRegistry<Block, BlockColorProvider> blockColor = ColorProviderRegistry.BLOCK;
        blockColor.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return FoliageColors.getDefaultColor();
                    }
                    return BiomeColors.getFoliageColor(world, pos);
                },
                GoodEndingBlocks.CYPRESS_LEAVES,
                GoodEndingBlocks.HANGING_OAK_LEAVES,
                GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT,
                GoodEndingBlocks.DENSE_DARK_OAK_LEAVES,
                Blocks.LILY_PAD,
                GoodEndingBlocks.LARGE_LILY_PAD,
                GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
                GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD
        );

        blockColor.register((state, world, pos, tintIndex) -> FoliageColors.getBirchColor(), GoodEndingBlocks.DENSE_BIRCH_LEAVES);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                    return blockColor.get(((BlockItem)stack.getItem()).getBlock()).getColor(blockState, null, null, tintIndex);
                },
                GoodEndingBlocks.DENSE_BIRCH_LEAVES,
                GoodEndingBlocks.DENSE_DARK_OAK_LEAVES,
                GoodEndingBlocks.LARGE_LILY_PAD,
                Blocks.LILY_PAD,
                GoodEndingBlocks.CYPRESS_LEAVES,
                GoodEndingBlocks.HANGING_OAK_LEAVES,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                return tintIndex > 0 ? -1 : blockColor.get(((BlockItem)stack.getItem()).getBlock()).getColor(blockState, null, null, tintIndex);
            },
            GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
            GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
            GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD
        );

    }
}
