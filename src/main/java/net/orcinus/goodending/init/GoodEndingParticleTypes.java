package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingParticleTypes {

    public static final SimpleParticleType FIREFLY = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(GoodEnding.MODID, "firefly"), FabricParticleTypes.simple());
    public static final SimpleParticleType BIRCH_LEAF = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(GoodEnding.MODID, "birch_leaf"), FabricParticleTypes.simple());
    public static final SimpleParticleType DARK_OAK_LEAF = Registry.register(BuiltInRegistries.PARTICLE_TYPE, new ResourceLocation(GoodEnding.MODID, "dark_oak_leaf"), FabricParticleTypes.simple());

    public static void init() { }

}
