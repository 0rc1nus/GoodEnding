package net.orcinus.goodending.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.mixin.invokers.VanillaBiomeParametersInvoker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(VanillaBiomeParameters.class)
public class VanillaBiomeParametersMixin {

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] temperatureParameters;

    @Shadow @Final private MultiNoiseUtil.ParameterRange nearInlandContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange farInlandContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] erosionParameters;

    @Shadow @Final private MultiNoiseUtil.ParameterRange riverContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] humidityParameters;

    @Shadow @Final private MultiNoiseUtil.ParameterRange defaultParameter;

    @Inject(at = @At("RETURN"), method = "writeLowBiomes")
    private void GE$writeLowBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.humidityParameters[2], this.humidityParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
    }

    @Inject(at = @At("RETURN"), method = "writeValleyBiomes")
    private void GE$writeValleyBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.humidityParameters[2], this.humidityParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0F, GoodEndingBiomes.MARSHY_SWAMP_KEY);
    }

    @Inject(at = @At("RETURN"), method = "writeMidBiomes")
    private void GE$writeMidBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]), this.defaultParameter, MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness), this.erosionParameters[1], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
        ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.humidityParameters[2], this.humidityParameters[4]), MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
    }

}
