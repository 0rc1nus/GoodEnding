package net.orcinus.goodending.client.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;

@Environment(EnvType.CLIENT)
public class FireflyRenderer<T extends FireflyEntity> extends EntityRenderer<T> {
    private static final Identifier TEXTURE = new Identifier(GoodEnding.MODID, "textures/particle/firefly_lit.png");

    public FireflyRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.shadowOpacity = 0.0F;
        this.shadowRadius = 0.0F;
    }

    @Override
    public Identifier getTexture(T entity) {
        return TEXTURE;
    }
}
