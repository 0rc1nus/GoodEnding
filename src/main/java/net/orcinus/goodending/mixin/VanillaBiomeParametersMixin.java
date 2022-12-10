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

    @Shadow @Final private MultiNoiseUtil.ParameterRange[] humidityParameters;

    @Shadow @Final private MultiNoiseUtil.ParameterRange coastContinentalness;

    @Shadow @Final private MultiNoiseUtil.ParameterRange midInlandContinentalness;

    @Inject(at = @At("RETURN"), method = "writeLowBiomes")
    private void GE$writeLowBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange temperatureParameter = this.temperatureParameters[i];
            for (int j = 0; j < this.humidityParameters.length; ++j) {
                MultiNoiseUtil.ParameterRange humidityParameter = this.humidityParameters[j];
                addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.of(-0.11f, 0.3f), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                if (i == 3 && j == 2) {
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    //remedy for -5673436505571856945 & 14 78 -662
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeMidBiomes")
    private void GE$writeMidBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange temperatureParameter = this.temperatureParameters[i];
            for (int j = 0; j < this.humidityParameters.length; ++j) {
                MultiNoiseUtil.ParameterRange humidityParameter = this.humidityParameters[j];
                if (i == 3 && j == 2) {
                    //1968 86 9127
                    //3576410170704156714
//                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.nearInlandContinentalness, this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeHighBiomes")
    private void GE$writeHighBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange temperatureParameter = this.temperatureParameters[i];
            for (int j = 0; j < this.humidityParameters.length; ++j) {
                MultiNoiseUtil.ParameterRange humidityParameter = this.humidityParameters[j];
                //6012941714076729884
                //-586 94 -1515
                if (i == 3 && j == 2) {
//                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.nearInlandContinentalness, this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.midInlandContinentalness, this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness), this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);

                    //Remedy for seed -5947534612827330122 & -1274 116 40169
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness), this.erosionParameters[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writePeakBiomes")
    private void GE$writePeakBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; i++) {
            MultiNoiseUtil.ParameterRange temperatureRange = this.temperatureParameters[i];
            for (int j = 0; j < this.humidityParameters.length; j++) {
                MultiNoiseUtil.ParameterRange humidityRange = this.humidityParameters[j];
                if (i == 3 && j == 2) {
                    addCustomBiome(parameters, temperatureRange, humidityRange, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness), this.erosionParameters[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
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

    @Unique
    private void addCustomBiome(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange temperatureRange, MultiNoiseUtil.ParameterRange parameterRange, MultiNoiseUtil.ParameterRange nearInlandContinentalness, MultiNoiseUtil.ParameterRange erosionParameters, MultiNoiseUtil.ParameterRange weirdness, float offset, RegistryKey<Biome> oakHammockForestKey) {
        ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, nearInlandContinentalness, erosionParameters, weirdness, offset, oakHammockForestKey);
    }

    //3576410170704156714
    //2137 99 9491
}
