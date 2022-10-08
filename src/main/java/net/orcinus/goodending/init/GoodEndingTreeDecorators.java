package net.orcinus.goodending.init;

import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.world.gen.features.decorators.HangingLeavesDecorator;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingTreeDecorators {

    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_TYPES = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, GoodEnding.MODID);

    public static final RegistryObject<TreeDecoratorType<HangingLeavesDecorator>> HANGING_LEAVES_DECORATOR = TREE_DECORATOR_TYPES.register("hanging_vines", () -> new TreeDecoratorType<>(HangingLeavesDecorator.CODEC));

}
