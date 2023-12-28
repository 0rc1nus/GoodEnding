package net.orcinus.goodending.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.AbstractCriterionTriggerInstance;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.DeserializationContext;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingCriterion extends SimpleCriterionTrigger<GoodEndingCriterion.TriggerInstance> {
    private final ResourceLocation ID;

    public GoodEndingCriterion(String name) {
        ID = new ResourceLocation(GoodEnding.MODID, name);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player) {
        this.trigger(player, conditions -> true);
    }

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        return new TriggerInstance(ID, contextAwarePredicate);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {

        public TriggerInstance(ResourceLocation id, ContextAwarePredicate predicate) {
            super(id, predicate);
        }
    }
}
