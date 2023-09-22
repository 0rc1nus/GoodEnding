package net.orcinus.goodending.mixin.accessor;

import net.minecraft.world.entity.vehicle.Boat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Boat.class)
public interface BoatAccessor {

    @Accessor("lastYd")
    void setLastYd(double lastYd);

    @Accessor
    Boat.Status getStatus();
}
