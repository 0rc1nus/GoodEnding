package net.orcinus.goodending.mixin.accessor;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BlockSetType.class)
public interface BlockSetTypeAccessor {
    @Invoker
    static BlockSetType callRegister(BlockSetType blockSetType) {
        throw new UnsupportedOperationException();
    }
}
