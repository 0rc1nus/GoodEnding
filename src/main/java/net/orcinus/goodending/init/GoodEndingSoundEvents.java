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

    public static final SoundEvent ENTITY_FIREFLY_SWARM_IDLE = fireflySwarm("idle");
    private static SoundEvent fireflySwarm(String type) { return entity("firefly_swarm", type); }

    public static final SoundEvent ITEM_FIREFLY_BOTTLE_FILL = fireflyBottle("fill");
    public static final SoundEvent ITEM_FIREFLY_BOTTLE_EMPTY = fireflyBottle("empty");
    private static SoundEvent fireflyBottle(String type) { return item("firefly_bottle", type); }

    public static final SoundEvent BLOCK_DENSE_LEAVES_BREAK = denseLeaves("break");
    public static final SoundEvent BLOCK_DENSE_LEAVES_PLACE = denseLeaves("place");
    public static final SoundEvent BLOCK_DENSE_LEAVES_FALL = denseLeaves("fall");
    private static SoundEvent denseLeaves(String type) { return block("dense_leaves", type); }

    public static final SoundEvent BLOCK_FIREFLY_LANTERN_BREAK = fireflyLantern("break");
    public static final SoundEvent BLOCK_FIREFLY_LANTERN_OPEN = fireflyLantern("open");
    public static final SoundEvent BLOCK_FIREFLY_LANTERN_CLOSE = fireflyLantern("close");
    private static SoundEvent fireflyLantern(String type) { return block("firefly_lantern", type); }

    public static final SoundEvent BLOCK_HANGING_LEAVES_BREAK = hangingLeaves("break");
    public static final SoundEvent BLOCK_HANGING_LEAVES_PLACE = hangingLeaves("place");
    public static final SoundEvent BLOCK_HANGING_LEAVES_FALL = hangingLeaves("fall");
    private static SoundEvent hangingLeaves(String type) { return block("hanging_leaves", type); }

    public static final SoundEvent BLOCK_ALGAE_BREAK = algae("break");
    public static final SoundEvent BLOCK_ALGAE_STEP = algae("step");
    public static final SoundEvent BLOCK_ALGAE_HIT = algae("hit");
    public static final SoundEvent BLOCK_ALGAE_PLACE = algae("place");
    public static final SoundEvent BLOCK_ALGAE_FALL = algae("fall");
    private static SoundEvent algae(String type) { return block("algae", type); }

    private static SoundEvent entity(String entity, String type) { return register("entity." + entity + "." + type); }
    private static SoundEvent item(String item, String type) { return register("item." + item + "." + type); }
    private static SoundEvent block(String block, String type) { return register("block." + block + "." + type); }


    private static SoundEvent register(String id) {
        Identifier identifier = new Identifier(GoodEnding.MODID, id);
        return Registry.register(Registry.SOUND_EVENT, identifier, new SoundEvent(identifier));
    }
}
