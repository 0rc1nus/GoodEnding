package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.client.renderer.layer.MarshEffectRenderer;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class MarshRenderer extends MobEntityRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final Identifier TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh.png");
    private static final Identifier TEXTURE_BREWING = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_brewing.png");
    private static final Identifier TEXTURE_READY = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_ready.png");

    public MarshRenderer(EntityRendererFactory.Context context) {
        super(context, new MarshEntityModel<>(context.getPart(GoodEndingModelLayers.MARSH)), 0.5F);
        this.addFeature(new MarshEffectRenderer(this));
    }

    @Override
    protected int getBlockLight(MarshEntity entity, BlockPos pos) {
        return entity.getStoredPotion() != Potions.EMPTY ? 15 : super.getBlockLight(entity, pos);
    }

    @Override
    public Identifier getTexture(MarshEntity entity) {
        if (entity.burpingTicks > 0) return TEXTURE_BREWING;

        if (entity.getStoredPotion() != Potions.EMPTY) {
            return entity.brewingTicks < 0 ? TEXTURE_READY : TEXTURE_BREWING;
        }
        return TEXTURE;
    }
}
