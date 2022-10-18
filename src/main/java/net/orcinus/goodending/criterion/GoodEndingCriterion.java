package net.orcinus.goodending.criterion;

import com.google.gson.JsonObject;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.AbstractCriterionConditions;
import net.minecraft.predicate.entity.AdvancementEntityPredicateDeserializer;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;

public class GoodEndingCriterion extends AbstractCriterion<GoodEndingCriterion.TriggerInstance> {
    private final Identifier ID;

    public GoodEndingCriterion(String name) {
        ID = new Identifier(GoodEnding.MODID, name);
    }

    @Override
    public Identifier getId() {
        return ID;
    }

    public void trigger(ServerPlayerEntity player) {
        this.trigger(player, conditions -> true);
    }

    @Override
    protected TriggerInstance conditionsFromJson(JsonObject obj, EntityPredicate.Extended playerPredicate, AdvancementEntityPredicateDeserializer predicateDeserializer) {
        return new TriggerInstance(ID, playerPredicate);
    }

    public static class TriggerInstance extends AbstractCriterionConditions {

        public TriggerInstance(Identifier id, EntityPredicate.Extended entity) {
            super(id, entity);
        }

    }
}