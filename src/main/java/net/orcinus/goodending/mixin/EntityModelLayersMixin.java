package net.orcinus.goodending.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.util.GoodEndingSignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(EntityModelLayers.class)
public class EntityModelLayersMixin {

    @Inject(at = @At("HEAD"), method = "createSign", cancellable = true)
    private static void createSign(SignType type, CallbackInfoReturnable<EntityModelLayer> cir) {
        if (type instanceof GoodEndingSignType signType) {
            cir.setReturnValue(new EntityModelLayer(new Identifier(GoodEnding.MODID, "entity/signs/%s".formatted(signType.getId())), "main"));
        }
    }

}
