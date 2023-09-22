package net.orcinus.goodending.mixin.invokers;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeInvoker {
    @Invoker
    static <P extends TreeDecorator> TreeDecoratorType<P> callRegister(String id, Codec<P> codec) {
        throw new UnsupportedOperationException();
    }
}
