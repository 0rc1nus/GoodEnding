package net.orcinus.goodending.client.renderer;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;

@OnlyIn(Dist.CLIENT)
public class FireflyRenderer<T extends FireflyEntity> extends EntityRenderer<T> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(GoodEnding.MODID, "textures/particle/firefly_lit.png");

    public FireflyRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.shadowStrength = 0.0F;
        this.shadowRadius = 0.0F;
    }

    @Override
    public ResourceLocation getTextureLocation(T p_114482_) {
        return TEXTURE;
    }

}
