package net.orcinus.goodending.init;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingSoundEvents {

    public static final SoundEvent ENTITY_WOODPECKER_IDLE = woodpecker("idle");
    public static final SoundEvent ENTITY_WOODPECKER_WOODY_IDLE = woodpecker("woody_idle");
    public static final SoundEvent ENTITY_WOODPECKER_DRUM = woodpecker("drum");
    public static final SoundEvent ENTITY_WOODPECKER_DEATH = woodpecker("death");
    public static final SoundEvent ENTITY_WOODPECKER_HURT = woodpecker("hurt");
    public static final SoundEvent ENTITY_WOODPECKER_FLY = woodpecker("fly");
    private static SoundEvent woodpecker(String type) { return entity("woodpecker", type); }

    public static final SoundEvent ENTITY_MARSH_IDLE = marsh("idle");
    public static final SoundEvent ENTITY_MARSH_BREWING_IDLE = marsh("idle_brewing");
    public static final SoundEvent ENTITY_MARSH_DEATH = marsh("death");
    public static final SoundEvent ENTITY_MARSH_HURT = marsh("hurt");
    public static final SoundEvent ENTITY_MARSH_BURP = marsh("burp");
    private static SoundEvent marsh(String type) { return entity("marsh", type); }

    private static SoundEvent entity(String entity, String type) { return register("entity." + entity + "." + type); }

    private static SoundEvent register(String name) {
        Identifier identifier = new Identifier(GoodEnding.MODID, name);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }
}
