package net.orcinus.goodending;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
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
                GoodEndingBlocks.PURPLE_MUSHROOM,
                GoodEndingBlocks.CATTAIL,
                GoodEndingBlocks.DUCKWEED,
                GoodEndingBlocks.SWAMP_LEAVES,
                GoodEndingBlocks.SWAMP_DOOR,
                GoodEndingBlocks.CYPRESS_DOOR,
                GoodEndingBlocks.CYPRESS_TRAPDOOR,
                GoodEndingBlocks.SWAMP_TRAPDOOR,
                GoodEndingBlocks.SWAMP_SAPLING,
                GoodEndingBlocks.CYPRESS_SAPLING,
                GoodEndingBlocks.POTTED_SWAMP_SAPLING,
                GoodEndingBlocks.POTTED_CYPRESS_SAPLING,
                GoodEndingBlocks.POTTED_PURPLE_MUSHROOM
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
                GoodEndingBlocks.SWAMP_LEAVES
        );

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                    return blockColor.get(((BlockItem)stack.getItem()).getBlock()).getColor(blockState, null, null, tintIndex);
                },
                GoodEndingBlocks.CYPRESS_LEAVES,
                GoodEndingBlocks.SWAMP_LEAVES
        );

    }
}
