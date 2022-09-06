package net.orcinus.goodending.mixin.accessor;

import net.minecraft.entity.vehicle.BoatEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BoatEntity.class)
public interface BoatEntityAccessor {
    @Accessor
    void setFallVelocity(double fallVelocity);

    @Accessor
    BoatEntity.Location getLocation();

}
