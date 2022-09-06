package net.orcinus.goodending.init;

import com.google.common.collect.Maps;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OceanPlacedFeatures;
import net.orcinus.goodending.GoodEnding;

import java.util.Map;

public class GoodEndingBiomes {
    private static final Map<Biome, RegistryKey<Biome>> BIOME_KEYS = Maps.newLinkedHashMap();

    public static final Biome MARSHY_SWAMP = GoodEndingBiomes.marshySwamp();
    public static final Biome OAK_HAMMOCK_FOREST = GoodEndingBiomes.oakHammockForest();
    public static final RegistryKey<Biome> MARSHY_SWAMP_KEY = register("marshy_swamp", MARSHY_SWAMP);
    public static final RegistryKey<Biome> OAK_HAMMOCK_FOREST_KEY = register("oak_hammock_forest", OAK_HAMMOCK_FOREST);

    public static Biome oakHammockForest() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        DefaultBiomeFeatures.addForestFlowers(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        DefaultBiomeFeatures.addDefaultFlowers(builder2);
        DefaultBiomeFeatures.addForestGrass(builder2);
        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        DefaultBiomeFeatures.addDefaultVegetation(builder2);
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).temperature(0.7f).downfall(0.8f).effects(new BiomeEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(0.7f)).grassColorModifier(BiomeEffects.GrassColorModifier.DARK_FOREST).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    public static Biome marshySwamp() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder);
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityType.SLIME, 1, 1, 1));
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FROG, 10, 2, 5));
        GenerationSettings.Builder builder2 = new GenerationSettings.Builder();
        DefaultBiomeFeatures.addFossils(builder2);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addSprings(builder2);
        DefaultBiomeFeatures.addFrozenTopLayer(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addClayDisk(builder2);
        DefaultBiomeFeatures.addSwampFeatures(builder2);
        DefaultBiomeFeatures.addDefaultMushrooms(builder2);
        DefaultBiomeFeatures.addSwampVegetation(builder2);
        builder2.feature(GenerationStep.Feature.VEGETAL_DECORATION, OceanPlacedFeatures.SEAGRASS_SWAMP);
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_SWAMP);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).temperature(0.8f).downfall(0.9f).effects(new BiomeEffects.Builder().waterColor(5069667).waterFogColor(2302743).fogColor(12638463).skyColor(getSkyColor(0.8f)).foliageColor(10134582).grassColorModifier(BiomeEffects.GrassColorModifier.SWAMP).moodSound(BiomeMoodSound.CAVE).music(musicSound).build()).spawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    protected static int getSkyColor(float temperature) {
        float value = temperature / 3.0F;
        value = MathHelper.clamp(value, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - value * 0.05F, 0.5F + value * 0.1F, 1.0F);
    }

    private static RegistryKey<Biome> register(String string, Biome biome) {
        RegistryKey<Biome> key = RegistryKey.of(Registry.BIOME_KEY, new Identifier(GoodEnding.MODID, string));
        BIOME_KEYS.put(biome, key);
        return key;
    }

    public static void init() {
        for (Biome biome : BIOME_KEYS.keySet()) {
            Registry.register(BuiltinRegistries.BIOME, BIOME_KEYS.get(biome).getValue(), biome);
        }
    }
}
