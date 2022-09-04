package net.orcinus.goodending.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WardenEntity;
import net.orcinus.goodending.init.GoodEndingEntityTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WardenEntity.class)
public class WardenEntityMixin {

    @Inject(at = @At("HEAD"), method = "isValidTarget", cancellable = true)
    private void GE$isValidTarget(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity.getType() == GoodEndingEntityTypes.FIREFLY_SWARM) {
            cir.setReturnValue(false);
        }
    }

}
