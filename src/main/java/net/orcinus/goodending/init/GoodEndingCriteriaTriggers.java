package net.orcinus.goodending.init;

import net.minecraft.advancements.CriteriaTriggers;
import net.orcinus.goodending.criterion.GoodEndingCriterion;

public class GoodEndingCriteriaTriggers {

    public static GoodEndingCriterion IMMUNITY;
    public static GoodEndingCriterion BREW_POTION;
    public static GoodEndingCriterion CAPTURE_FIREFLY;

    public static void init() {
        IMMUNITY = CriteriaTriggers.register(new GoodEndingCriterion("immunity"));
        BREW_POTION = CriteriaTriggers.register(new GoodEndingCriterion("brew_potion"));
        CAPTURE_FIREFLY = CriteriaTriggers.register(new GoodEndingCriterion("capture_firefly"));
    }

}