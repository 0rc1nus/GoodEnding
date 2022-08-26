package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.client.models.MarshEntityModel;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.init.GoodEndingModelLayers;

@Environment(EnvType.CLIENT)
public class MarshRenderer extends MobEntityRenderer<MarshEntity, MarshEntityModel<MarshEntity>> {
    private static final Identifier TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh.png");
    private static final Identifier BURP_TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/marsh_burping.png");
    private static final Identifier BAD_PIGGY_TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/bad_piggy.png");
    private static final Identifier BAD_PIGGY_BURP_TEXTURE = new Identifier(GoodEnding.MODID, "textures/entity/marsh/bad_piggy_burping.png");

    public MarshRenderer(EntityRendererFactory.Context context) {
        super(context, new MarshEntityModel<>(context.getPart(GoodEndingModelLayers.MARSH)), 0.5F);
    }

    @Override
    public Identifier getTexture(MarshEntity entity) {
        if ("Bad Piggy".equals(Formatting.strip(entity.getName().getString()))) {
            return entity.getBurpingTicks() > 0 ? BAD_PIGGY_BURP_TEXTURE : BAD_PIGGY_TEXTURE;
        } else {
            return entity.getBurpingTicks() > 0 ? BURP_TEXTURE : TEXTURE;
        }
    }
}
