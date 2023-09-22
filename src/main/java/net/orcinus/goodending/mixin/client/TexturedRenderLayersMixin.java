package net.orcinus.goodending.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingSignTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(Sheets.class)
public class TexturedRenderLayersMixin {

    @Inject(at = @At("HEAD"), method = "createSignMaterial", cancellable = true)
    private static void GE$createSignTextureId(WoodType type, CallbackInfoReturnable<Material> cir) {
        if (GoodEndingSignTypes.VALUES.contains(type)) {
            cir.setReturnValue(new Material(Sheets.SIGN_SHEET, new ResourceLocation(GoodEnding.MODID, "entity/signs/%s".formatted(type.name()))));
        }
    }

}

