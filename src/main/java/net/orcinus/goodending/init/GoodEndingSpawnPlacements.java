package net.orcinus.goodending.init;

import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.entities.WoodpeckerEntity;

public class GoodEndingSpawnPlacements {

    public static void init() {
        SpawnPlacements.register(GoodEndingEntityTypes.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflyEntity::canSpawn);
        SpawnPlacements.register(GoodEndingEntityTypes.WOODPECKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WoodpeckerEntity::canSpawn);
    }

}
