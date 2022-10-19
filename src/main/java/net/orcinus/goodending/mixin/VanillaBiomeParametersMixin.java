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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

@Mixin(OverworldBiomeBuilder.class)
public class VanillaBiomeParametersMixin {

    @Shadow @Final private Climate.Parameter nearInlandContinentalness;

    @Shadow @Final private Climate.Parameter farInlandContinentalness;

    @Shadow @Final private Climate.Parameter coastContinentalness;

    @Shadow @Final private Climate.Parameter midInlandContinentalness;

    @Shadow @Final private Climate.Parameter[] temperatures;

    @Shadow @Final private Climate.Parameter[] humidities;

    @Shadow @Final private Climate.Parameter[] erosions;

    @Shadow @Final private Climate.Parameter inlandContinentalness;

    @Shadow @Final private Climate.Parameter FULL_RANGE;

    @Inject(at = @At("RETURN"), method = "addLowSlice")
    private void GE$addLowBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter temperatureRange = this.temperatures[i];
            for (Climate.Parameter parameterRange : this.humidities) {
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(-0.11f, 0.3f), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, this.nearInlandContinentalness, Climate.Parameter.span(this.erosions[2], this.erosions[3]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                if (i != 0) continue;
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addValleys")
    private void GE$addValleyBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (Climate.Parameter temperatureRange : this.temperatures) {
            for (Climate.Parameter parameterRange : this.humidities) {
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(-0.11f, 0.3f), this.erosions[6], weirdness, 0.0F, GoodEndingBiomes.MARSHY_SWAMP_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, temperatureRange, parameterRange, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addPeaks")
    private void GE$addPeakBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (Climate.Parameter parameterRange : this.temperatures) {
            for (Climate.Parameter parameterRange2 : this.humidities) {
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addHighSlice")
    private void GE$addHighBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (Climate.Parameter parameterRange : this.temperatures) {
            for (Climate.Parameter parameterRange2 : this.humidities) {
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, Climate.Parameter.span(this.erosions[0], this.erosions[1]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, this.coastContinentalness, Climate.Parameter.span(this.erosions[1], this.erosions[3]), weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[4], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, this.erosions[3], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.midInlandContinentalness, this.farInlandContinentalness), this.erosions[5], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker)this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, this.midInlandContinentalness, Climate.Parameter.span(this.erosions[1], this.erosions[2]), weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }

    @Inject(at = @At("RETURN"), method = "addMidSlice")
    private void GE$addMidBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> parameters, Climate.Parameter weirdness, CallbackInfo ci) {
        for (int i = 0; i < this.temperatures.length; ++i) {
            Climate.Parameter parameterRange = this.temperatures[i];
            for (Climate.Parameter parameterRange2 : this.humidities) {
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.coastContinentalness, this.nearInlandContinentalness), this.erosions[3], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, Climate.Parameter.span(this.temperatures[1], this.temperatures[2]), this.FULL_RANGE, Climate.Parameter.span(this.inlandContinentalness, this.farInlandContinentalness), this.erosions[1], weirdness, 0.0F, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
                if (i != 0) continue;
                ((VanillaBiomeParametersInvoker) this).callAddSurfaceBiome(parameters, parameterRange, parameterRange2, Climate.Parameter.span(this.nearInlandContinentalness, this.farInlandContinentalness), this.erosions[6], weirdness, 0.0f, GoodEndingBiomes.OAK_HAMMOCK_FOREST_KEY);
            }
        }
    }


    //-2693824285148779362
    //2349 -40 2398

}
