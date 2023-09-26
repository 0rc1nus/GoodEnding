package net.orcinus.goodending.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingBiomes {

    public static final ResourceKey<Biome> MARSHY_SWAMP_KEY = register("marshy_swamp");
    public static final ResourceKey<Biome> OAK_HAMMOCK_FOREST_KEY = register("oak_hammock_forest");

    public static void bootstrap(BootstapContext<Biome> ctx) {
        HolderGetter<PlacedFeature> holderGetter = ctx.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = ctx.lookup(Registries.CONFIGURED_CARVER);
        ctx.register(MARSHY_SWAMP_KEY, marshySwamp(holderGetter, holderGetter2));
        ctx.register(OAK_HAMMOCK_FOREST_KEY, oakHammockForest(holderGetter, holderGetter2));
    }

    public static Biome oakHammockForest(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(builder);
        BiomeDefaultFeatures.commonSpawns(builder);
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 10, 2, 3));
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        builder2.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, GoodEndingPlacedFeatures.OAK_HAMMOCK_BOULDERS);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.OAK_HAMMOCK_TREES_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.OAK_HAMMOCK_PATCH_TALL_GRASS_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.PATCH_FERN_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.PATCH_LARGE_FERN_PLACED);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addDefaultSprings(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        BiomeDefaultFeatures.addForestFlowers(builder2);
        BiomeDefaultFeatures.addDefaultOres(builder2);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder2);
        BiomeDefaultFeatures.addDefaultFlowers(builder2);
        BiomeDefaultFeatures.addForestGrass(builder2);
        BiomeDefaultFeatures.addDefaultMushrooms(builder2);
        BiomeDefaultFeatures.addDefaultExtraVegetation(builder2);
        Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_FOREST);
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(0.7f).downfall(0.8f).specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.7f)).grassColorModifier(BiomeSpecialEffects.GrassColorModifier.NONE).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(musicSound).build()).mobSpawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome marshySwamp(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder builder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.farmAnimals(builder);
        BiomeDefaultFeatures.commonSpawns(builder);
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 1, 1, 1));
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.FROG, 10, 2, 5));
        builder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(GoodEndingEntityTypes.FIREFLY_SWARM, 20, 8, 8));
        BiomeGenerationSettings.Builder builder2 = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addFossilDecoration(builder2);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder2);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder2);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder2);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder2);
        BiomeDefaultFeatures.addDefaultSprings(builder2);
        BiomeDefaultFeatures.addSurfaceFreezing(builder2);
        BiomeDefaultFeatures.addDefaultOres(builder2);
        BiomeDefaultFeatures.addSwampClayDisk(builder2);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.FLOWER_SWAMP);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_NORMAL);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_DEAD_BUSH);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_WATERLILY);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.BROWN_MUSHROOM_SWAMP);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_SWAMP);
        BiomeDefaultFeatures.addDefaultMushrooms(builder2);
        BiomeDefaultFeatures.addSwampExtraVegetation(builder2);
        builder2.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GoodEndingPlacedFeatures.PATCH_ALGAE_PLACED);
        builder2.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, GoodEndingPlacedFeatures.DISK_PODZOL_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.BIG_LILY_PADS_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.PATCH_FLOWERING_WATERLILY_PLACED);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.MARSHY_SWAMP_VEGETATION);
        builder2.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, GoodEndingPlacedFeatures.MARSH_SWAMP_TREE_PLACED);
        Music musicSound = Musics.createGameMusic(SoundEvents.MUSIC_BIOME_SWAMP);
        return new Biome.BiomeBuilder().hasPrecipitation(true).temperature(0.8f).downfall(0.9f).specialEffects(new BiomeSpecialEffects.Builder().waterColor(5082501).waterFogColor(335132).fogColor(11852004).skyColor(getSkyColor(0.8f)).foliageColorOverride(10662207).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(musicSound).build()).mobSpawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    protected static int getSkyColor(float temperature) {
        float value = temperature / 3.0F;
        value = Mth.clamp(value, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - value * 0.05F, 0.5F + value * 0.1F, 1.0F);
    }

    private static ResourceKey<Biome> register(String string) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(GoodEnding.MODID, string));
    }

}
