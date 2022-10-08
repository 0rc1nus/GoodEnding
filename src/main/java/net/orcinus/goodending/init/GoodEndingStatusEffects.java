package net.orcinus.goodending.init;

import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.effects.ImmunityEffect;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingStatusEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, GoodEnding.MODID);

    public static final RegistryObject<MobEffect> STRONG_IMMUNITY = MOB_EFFECTS.register("strong_immunity", ImmunityEffect::new);
    public static final RegistryObject<MobEffect> CONTEMPORARY_IMMUNITY = MOB_EFFECTS.register("contemporary_immunity", ImmunityEffect::new);
    public static final RegistryObject<MobEffect> SHATTERED_IMMUNITY = MOB_EFFECTS.register("shattered_immunity", ImmunityEffect::new);

}
