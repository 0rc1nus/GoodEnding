package net.orcinus.goodending.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingPotions {
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, GoodEnding.MODID);

    public static final RegistryObject<Potion> IMMUNITY = POTIONS.register("immunity", () -> new Potion(new MobEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY.get(), 3600)));
    public static final RegistryObject<Potion> LONG_IMMUNITY = POTIONS.register("long_immunity", () -> new Potion(new MobEffectInstance(GoodEndingStatusEffects.STRONG_IMMUNITY.get(), 9600)));

}
    