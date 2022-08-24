package net.orcinus.goodending.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.util.GoodEndingSignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(TexturedRenderLayers.class)
public class TexturedRenderLayersMixin {

    @Inject(at = @At("HEAD"), method = "createSignTextureId", cancellable = true)
    private static void onCreateSignTextureId(SignType type, CallbackInfoReturnable<SpriteIdentifier> cir) {
        if (type instanceof GoodEndingSignType signType) {
            cir.setReturnValue(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, new Identifier(GoodEnding.MODID, "entity/signs/%s".formatted(signType.getId()))));
        }
    }

}

