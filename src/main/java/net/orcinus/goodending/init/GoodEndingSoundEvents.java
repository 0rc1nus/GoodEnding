package net.orcinus.goodending.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.orcinus.goodending.GoodEnding;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GoodEndingSoundEvents {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, GoodEnding.MODID);

    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_IDLE = entity("woodpecker", "idle");
    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_WOODY_IDLE = entity("woodpecker", "woody_idle");
    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_DRUM = entity("woodpecker", "drum");
    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_DEATH = entity("woodpecker", "death");
    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_HURT = entity("woodpecker", "hurt");
    public static final RegistryObject<SoundEvent> ENTITY_WOODPECKER_FLY = entity("woodpecker", "fly");

    public static final RegistryObject<SoundEvent> ENTITY_MARSH_IDLE = entity("marsh", "idle");
    public static final RegistryObject<SoundEvent> ENTITY_MARSH_BREWING_IDLE = entity("marsh", "idle_brewing");
    public static final RegistryObject<SoundEvent> ENTITY_MARSH_DEATH = entity("marsh", "death");
    public static final RegistryObject<SoundEvent> ENTITY_MARSH_HURT = entity("marsh", "hurt");
    public static final RegistryObject<SoundEvent> ENTITY_MARSH_BURP = entity("marsh", "burp");

    public static final RegistryObject<SoundEvent> ENTITY_FIREFLY_SWARM_IDLE = entity("firefly_swarm", "idle");

    public static final RegistryObject<SoundEvent> ITEM_FIREFLY_BOTTLE_FILL = item("firefly_bottle", "fill");
    public static final RegistryObject<SoundEvent> ITEM_FIREFLY_BOTTLE_EMPTY = item("firefly_bottle", "empty");

    public static final RegistryObject<SoundEvent> BLOCK_DENSE_LEAVES_BREAK = block("dense_leaves", "break");
    public static final RegistryObject<SoundEvent> BLOCK_DENSE_LEAVES_PLACE = block("dense_leaves", "place");
    public static final RegistryObject<SoundEvent> BLOCK_DENSE_LEAVES_FALL = block("dense_leaves", "fall");

    public static final RegistryObject<SoundEvent> BLOCK_FIREFLY_LANTERN_BREAK = block("firefly_lantern", "break");
    public static final RegistryObject<SoundEvent> BLOCK_FIREFLY_LANTERN_OPEN = block("firefly_lantern", "open");
    public static final RegistryObject<SoundEvent> BLOCK_FIREFLY_LANTERN_CLOSE = block("firefly_lantern", "close");

    public static final RegistryObject<SoundEvent> BLOCK_HANGING_LEAVES_BREAK = block("hanging_leaves", "break");
    public static final RegistryObject<SoundEvent> BLOCK_HANGING_LEAVES_PLACE = block("hanging_leaves", "place");
    public static final RegistryObject<SoundEvent> BLOCK_HANGING_LEAVES_FALL = block("hanging_leaves", "fall");

    private static RegistryObject<SoundEvent> entity(String entity, String type) { return register("entity." + entity + "." + type); }
    private static RegistryObject<SoundEvent> item(String item, String type) { return register("item." + item + "." + type); }
    private static RegistryObject<SoundEvent> block(String block, String type) { return register("block." + block + "." + type); }

    private static RegistryObject<SoundEvent> register(String id) {
        return SOUND_EVENTS.register(id, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(GoodEnding.MODID, id)));
    }

}
