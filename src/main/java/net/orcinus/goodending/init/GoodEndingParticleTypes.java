package net.orcinus.goodending.init;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingParticleTypes {

    public static final DefaultParticleType FIREFLY = Registry.register(Registry.PARTICLE_TYPE, new Identifier(GoodEnding.MODID, "firefly"), FabricParticleTypes.simple());
    public static final DefaultParticleType BIRCH_LEAF = Registry.register(Registry.PARTICLE_TYPE, new Identifier(GoodEnding.MODID, "birch_leaf"), FabricParticleTypes.simple());

}
