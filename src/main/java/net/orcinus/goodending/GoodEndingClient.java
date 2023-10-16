package net.orcinus.goodending;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.goodending.client.gui.tooltip.PotionApplicationTooltipComponent;
import net.orcinus.goodending.client.gui.tooltip.PotionApplicationTooltipData;
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
        TooltipComponentCallback.EVENT.register(data -> {
            if (data instanceof PotionApplicationTooltipData potionApplicationTooltipData) {
                return new PotionApplicationTooltipComponent(potionApplicationTooltipData);
            }
            return null;
        });

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
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

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.translucent(),
                GoodEndingBlocks.ALGAE
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
            EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.createBoat(type), BoatModel::createBodyModel);
            EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.createChestBoat(type), ChestBoatModel::createBodyModel);
        });
        EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.MARSH, MarshEntityModel::getLayerDefinition);
        EntityModelLayerRegistry.registerModelLayer(GoodEndingModelLayers.WOODPECKER, WoodPeckerEntityModel::getLayerDefinition);

        ColorProviderRegistry<Block, BlockColor> blockColor = ColorProviderRegistry.BLOCK;
        blockColor.register((state, world, pos, tintIndex) -> {
                    if (world == null || pos == null) {
                        return FoliageColor.getDefaultColor();
                    }
                    return BiomeColors.getAverageFoliageColor(world, pos);
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

        blockColor.register((state, world, pos, tintIndex) -> FoliageColor.getBirchColor(), GoodEndingBlocks.DENSE_BIRCH_LEAVES);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                    BlockState blockState = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
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
                BlockState blockState = ((BlockItem)stack.getItem()).getBlock().defaultBlockState();
                return tintIndex > 0 ? -1 : blockColor.get(((BlockItem)stack.getItem()).getBlock()).getColor(blockState, null, null, tintIndex);
            },
            GoodEndingBlocks.PURPLE_FLOWERING_LILY_PAD,
            GoodEndingBlocks.PINK_FLOWERING_LILY_PAD,
            GoodEndingBlocks.YELLOW_FLOWERING_LILY_PAD
        );

    }

}
