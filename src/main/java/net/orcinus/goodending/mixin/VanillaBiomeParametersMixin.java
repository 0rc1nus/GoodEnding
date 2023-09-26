package net.orcinus.goodending.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
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

@Mixin(OverworldBiomeBuilder.class)
public class VanillaBiomeParametersMixin {

    @Shadow @Final private Climate.Parameter[] temperatures;

    @Shadow @Final private Climate.Parameter nearInlandContinentalness;

    @Shadow @Final private Climate.Parameter farInlandContinentalness;

    @Shadow @Final private Climate.Parameter[] erosions;

    @Shadow @Final private Climate.Parameter[] humidities;

    @Shadow @Final private Climate.Parameter coastContinentalness;

    @Shadow @Final private Climate.Parameter midInlandContinentalness;

    @Inject(at = @At("RETURN"), method = "addLowSlice")
    private void GE$writeLowBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> consumer, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter temperatureParameter = this.temperatures[i];
            for (int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter humidityParameter = this.humidities[j];
                addCustomBiome(consumer, temperatureParameter, humidityParameter, Climate.Parameter.span(-0.11f, 0.3f), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                if (i == 3 && j == 2) {
                    addCustomBiome(consumer, temperatureParameter, humidityParameter, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    //remedy for -5673436505571856945 & 14 78 -662
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addMidSlice")
    private void GE$writeMidBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter temperatureParameter = this.temperatures[i];
            for (int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter humidityParameter = this.humidities[j];
                if (i == 3 && j == 2) {
                    //1968 86 9127
                    //3576410170704156714
//                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.nearInlandContinentalness, this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addHighSlice")
    private void GE$writeHighBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter temperatureParameter = this.temperatures[i];
            for (int j = 0; j < this.humidities.length; ++j) {
                Climate.Parameter humidityParameter = this.humidities[j];
                //6012941714076729884
                //-586 94 -1515
                if (i == 3 && j == 2) {
//                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.nearInlandContinentalness, this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, Climate.Parameter.span(this.nearInlandContinentalness, this.midInlandContinentalness), this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, this.midInlandContinentalness, this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);

                    //Remedy for seed -5947534612827330122 & -1274 116 40169
                    addCustomBiome(parameters, temperatureParameter, humidityParameter, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addPeaks")
    private void GE$writePeakBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; i++) {
            Climate.Parameter temperatureRange = this.temperatures[i];
            for (int j = 0; j < this.humidities.length; j++) {
                Climate.Parameter humidityRange = this.humidities[j];
                if (i == 3 && j == 2) {
                    addCustomBiome(parameters, temperatureRange, humidityRange, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[4], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                }
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addValleys")
    private void GE$writeValleyBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (Climate.Parameter temperatureRange : this.temperatures) {
            for (Climate.Parameter parameterRange : this.humidities) {
                addCustomBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(-0.11f, 0.3f), this.erosions[6], weirdness, 0.0F, GoodEndingBiomes.MARSHY_SWAMP_KEY);
            }
        }
    }

    @Unique
    private void addCustomBiome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter temperatureRange, Climate.Parameter parameterRange, Climate.Parameter nearInlandContinentalness, Climate.Parameter erosions, Climate.Parameter weirdness, float offset, ResourceKey<Biome> oakHammockForestKey) {
        ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, nearInlandContinentalness, erosions, weirdness, offset, oakHammockForestKey);
    }

    //3576410170704156714
    //2137 99 9491
}
