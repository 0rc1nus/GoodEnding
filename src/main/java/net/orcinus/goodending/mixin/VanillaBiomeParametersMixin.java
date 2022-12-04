package net.orcinus.goodending.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.biome.source.util.VanillaBiomeParameters;
import net.orcinus.goodending.init.GoodEndingBiomes;
import net.orcinus.goodending.mixin.invokers.VanillaBiomeParametersInvoker;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

    @Shadow @Final private MultiNoiseUtil.ParameterRange coastContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange midInlandContinentalness;

    @Inject(at = @At("RETURN"), method = "writeLowBiomes")
    private void GE$writeLowBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange temperatureRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange humidityRange : this.humidityParameters) {
                addCustomBiome(parameters, temperatureRange, humidityRange, MultiNoiseUtil.ParameterRange.of(-0.11f, 0.3f), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);

                addCustomBiome(parameters, temperatureRange, humidityRange, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                addCustomBiome(parameters, temperatureRange, humidityRange, this.nearInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                addCustomBiome(parameters, temperatureRange, humidityRange, MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.nearInlandContinentalness), this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeValleyBiomes")
    private void GE$writeValleyBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange temperatureRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange : this.humidityParameters) {
                addCustomBiome(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.of(-0.11f, 0.3f), this.erosionParameters[6], weirdness, 0.0F, GoodEndingBiomes.MARSHY_SWAMP_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writePeakBiomes")
    private void GE$writePeakBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange parameterRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeHighBiomes")
    private void GE$writeHighBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange parameterRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
                addCustomBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeMidBiomes")
    private void GE$writeMidBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
                addCustomBiome(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.nearInlandContinentalness), this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                addCustomBiome(parameters, parameterRange, parameterRange2, this.nearInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                addCustomBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Unique
    private void addCustomBiome(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperatureRange, MultiNoiseUtil.ParameterRange parameterRange, MultiNoiseUtil.ParameterRange nearInlandContinentalness, MultiNoiseUtil.ParameterRange erosionParameters, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> oakHammockForestKey) {
        ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, nearInlandContinentalness, erosionParameters, weirdness, offset, oakHammockForestKey);
    }

}
