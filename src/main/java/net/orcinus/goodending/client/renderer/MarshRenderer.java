package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.client.renderer.layer.MarshEffectRenderer;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class MarshRenderer extends MobRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh.png");
    private static final ResourceLocation TEXTURE_BREWING = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing.png");
    private static final ResourceLocation TEXTURE_READY = new ResourceLocation(GoodEnding.MODID, "textures/entity/marsh/marsh_ready.png");

    public MarshRenderer(EntityRendererProvider.Context context) {
        super(context, new MarshEntityModel<>(context.bakeLayer(GoodEndingModelLayers.MARSH)), 0.5F);
        this.addLayer(new MarshEffectRenderer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(MarshEntity entity) {
        if (entity.getBurpingTicks() > 0) {
            return TEXTURE_BREWING;
        } else if (entity.getStoredPotion() != Potions.EMPTY) {
            return entity.getBrewingTicks() == 0 ? TEXTURE_READY : TEXTURE_BREWING;
        } else {
            return TEXTURE;
        }
    }
}
