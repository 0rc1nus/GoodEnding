package net.orcinus.goodending.mixin.invokers;

import net.minecraft.util.SignType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SignType.class)
public interface SignTypeInvoker {

    @Invoker
    static SignType invokeRegister(SignType type) { throw new AssertionError(); }

}