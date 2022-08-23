package net.orcinus.goodending;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.FoliageColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.orcinus.goodending.client.particles.FireflyParticle;
import net.orcinus.goodending.client.renderer.FireflyRenderer;
import net.orcinus.goodending.init.GoodEndingBlocks;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import net.orcinus.goodending.init.GoodEndingParticleTypes;

public class GoodEndingClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                GoodEndingBlocks.CATTAIL,
                GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
                GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PASTEL_WILDFLOWERS,
                GoodEndingBlocks.TWILIGHT_WILDFLOWERS,
                GoodEndingBlocks.SPICY_WILDFLOWERS,
                GoodEndingBlocks.BALMY_WILDFLOWERS,
                GoodEndingBlocks.DUCKWEED,
                GoodEndingBlocks.HANGING_OAK_LEAVES,
                GoodEndingBlocks.HANGING_OAK_LEAVES_PLANT,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES,
                GoodEndingBlocks.HANGING_DARK_OAK_LEAVES_PLANT,
                GoodEndingBlocks.CYPRESS_DOOR,
                GoodEndingBlocks.CYPRESS_TRAPDOOR,
                GoodEndingBlocks.CYPRESS_SAPLING,
                GoodEndingBlocks.POTTED_CYPRESS_SAPLING
        );

        ParticleFactoryRegistry.getInstance().register(GoodEndingParticleTypes.FIREFLY, FireflyParticle.Factory::new);
        EntityRendererRegistry.register(GoodEndingEntityTypes.FIREFLY_SWARM, FireflyRenderer::new);

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
                Blocks.LILY_PAD,
                GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
                GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
                GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                    return blockColor.get(((BlockItem)stack.getItem()).getBlock()).getColor(blockState, null, null, tintIndex);
                },
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
