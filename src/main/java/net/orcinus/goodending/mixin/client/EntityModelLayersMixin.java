package net.orcinus.goodending.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.init.GoodEndingSignTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(ModelLayers.class)
public class EntityModelLayersMixin {

    @Inject(at = @At("HEAD"), method = "createSignModelName", cancellable = true)
    private static void GE$createSignModelName(WoodType type, CallbackInfoReturnable<ModelLayerLocation> cir) {
        if (GoodEndingSignTypes.VALUES.contains(type)) {
            cir.setReturnValue(new ModelLayerLocation(new ResourceLocation(GoodEnding.MODID, "entity/signs/%s".formatted(type.name())), "main"));
        }
    }

}
