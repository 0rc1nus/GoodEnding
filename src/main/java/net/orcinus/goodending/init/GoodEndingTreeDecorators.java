package net.orcinus.goodending.init;

import net.minecraft.util.Identifier;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.mixin.invokers.TreeDecoratorTypeInvoker;
import net.orcinus.goodending.world.gen.features.decorators.HangingLeavesDecorator;

public class GoodEndingTreeDecorators {

    public static final TreeDecoratorType<HangingLeavesDecorator> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeInvoker.callRegister(new Identifier(GoodEnding.MODID, "hanging_vines").toString(), HangingLeavesDecorator.CODEC);

    public static void init() { }

}
