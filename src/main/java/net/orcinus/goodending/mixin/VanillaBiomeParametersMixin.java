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
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange temperatureRange = this.temperatureParameters[i];
            for (MultiNoiseUtil.ParameterRange parameterRange : this.humidityParameters) {
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.of(-0.11f, 0.3f), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, this.nearInlandContinentalness, MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[2], this.erosionParameters[3]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[5], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                if (i != 0) continue;
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeValleyBiomes")
    private void GE$writeValleyBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange temperatureRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange : this.humidityParameters) {
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.of(-0.11f, 0.3f), this.erosionParameters[6], weirdness, 0.0F, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, temperatureRange, parameterRange, MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness), MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writePeakBiomes")
    private void GE$writePeakBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange parameterRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness), this.erosionParameters[3], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness), this.erosionParameters[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeHighBiomes")
    private void GE$writeHighBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (MultiNoiseUtil.ParameterRange parameterRange : this.temperatureParameters) {
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[0], this.erosionParameters[1]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, this.coastContinentalness, MultiNoiseUtil.ParameterRange.combine(this.erosionParameters[1], this.erosionParameters[3]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness), this.erosionParameters[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosionParameters[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.midInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[5], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "writeMidBiomes")
    private void GE$writeMidBiomes(Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> parameters, MultiNoiseUtil.ParameterRange weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatureParameters.length; ++i) {
            MultiNoiseUtil.ParameterRange parameterRange = this.temperatureParameters[i];
            for (MultiNoiseUtil.ParameterRange parameterRange2 : this.humidityParameters) {
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.coastContinentalness, this.nearInlandContinentalness), this.erosionParameters[3], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, MultiNoiseUtil.ParameterRange.combine(this.temperatureParameters[1], this.temperatureParameters[2]), this.defaultParameter, MultiNoiseUtil.ParameterRange.combine(this.riverContinentalness, this.farInlandContinentalness), this.erosionParameters[1], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                if (i != 0) continue;
                ((VanillaBiomeParametersInvoker) this).callWriteBiomeParameters(parameters, parameterRange, parameterRange2, MultiNoiseUtil.ParameterRange.combine(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosionParameters[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }


    //-2693824285148779362
    //2349 -40 2398

}
