package net.orcinus.goodending.init;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, GoodEnding.MODID);

    public static final RegistryObject<SimpleParticleType> FIREFLY = PARTICLE_TYPES.register("firefly", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BIRCH_LEAF = PARTICLE_TYPES.register("birch_leaf", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> DARK_OAK_LEAF = PARTICLE_TYPES.register("dark_oak_leaf", () -> new SimpleParticleType(false));

}
