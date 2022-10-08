package net.orcinus.goodending.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.client.renderer.layer.MarshEffectRenderer;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@OnlyIn(Dist.CLIENT)
public class MarshRenderer extends MobRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh.png");
    private static final ResourceLocation TEXTURE_BREWING = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing.png");
    private static final ResourceLocation TEXTURE_READY = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_ready.png");

    public MarshRenderer(EntityRendererProvider.Context context) {
        super(context, new MarshEntityModel<>(context.bakeLayer(GoodEndingModelLayers.MARSH)), 0.5F);
        this.addLayer(new MarshEffectRenderer(this));
    }

    @Override
    protected int getBlockLightLevel(MarshEntity entity, BlockPos pos) {
        return entity.getStoredPotion() != Potions.EMPTY ? 15 : super.getBlockLightLevel(entity, pos);
    }

    @Override
    public ResourceLocation getTextureLocation(MarshEntity entity) {
        if (entity.burpingTicks > 0) return TEXTURE_BREWING;

        if (entity.getStoredPotion() != Potions.EMPTY) {
            return entity.brewingTicks < 0 ? TEXTURE_READY : TEXTURE_BREWING;
        }
        return TEXTURE;
    }
}
