package net.orcinus.goodending.events;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.FireflyEntity;
import net.orcinus.goodending.entities.MarshEntity;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingEntityTypes;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MobEvents {

    @SubscribeEvent
    public static void registerEntityAttribute(EntityAttributeCreationEvent event) {
        SpawnPlacements.register(GoodEndingEntityTypes.FIREFLY_SWARM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, FireflyEntity::canSpawn);
        SpawnPlacements.register(GoodEndingEntityTypes.WOODPECKER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (p_217081_, world, p_217083_, pos, p_217085_) -> world.getBlockState(pos.below()).is(BlockTags.PARROTS_SPAWNABLE_ON));
        event.put(GoodEndingEntityTypes.MARSH.get(), MarshEntity.createMarshAttributes().build());
        event.put(GoodEndingEntityTypes.FIREFLY_SWARM.get(), FireflyEntity.createFireflyAttributes().build());
        event.put(GoodEndingEntityTypes.WOODPECKER.get(), WoodpeckerEntity.createWoodPeckerAttributes().build());
    }

}
