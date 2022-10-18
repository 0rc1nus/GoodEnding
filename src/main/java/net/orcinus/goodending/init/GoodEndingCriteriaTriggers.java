package net.orcinus.goodending.init;

import net.minecraft.advancement.criterion.Criteria;
import net.orcinus.goodending.criterion.GoodEndingCriterion;

public class GoodEndingCriteriaTriggers {

    public static GoodEndingCriterion IMMUNITY;
    public static GoodEndingCriterion BREW_POTION;
    public static GoodEndingCriterion CAPTURE_FIREFLY;

    public static void init() {
        IMMUNITY = Criteria.register(new GoodEndingCriterion("immunity"));
        BREW_POTION = Criteria.register(new GoodEndingCriterion("brew_potion"));
        CAPTURE_FIREFLY = Criteria.register(new GoodEndingCriterion("capture_firefly"));
    }

}