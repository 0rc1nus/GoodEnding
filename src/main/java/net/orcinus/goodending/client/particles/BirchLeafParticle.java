package net.orcinus.goodending.client.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.AnimatedParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

@Environment(EnvType.CLIENT)
public class BirchLeafParticle extends AnimatedParticle {
    private final SpriteProvider spriteProvider;

    BirchLeafParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, spriteProvider, 0.25F);
        this.spriteProvider = spriteProvider;
        this.collidesWithWorld = true;
        this.maxAge = world.getRandom().nextInt(80) + 20;
        this.scale(1.5f);
        this.setSpriteForAge(spriteProvider);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(spriteProvider);
    }


    @Environment(value = EnvType.CLIENT)
        public record Factory(SpriteProvider spriteProvider) implements ParticleFactory<DefaultParticleType> {

        @Override
            public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new BirchLeafParticle(clientWorld, d, e, f, this.spriteProvider);
            }
        }
}
