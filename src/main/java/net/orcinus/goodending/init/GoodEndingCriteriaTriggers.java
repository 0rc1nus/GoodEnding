package net.orcinus.goodending.init;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.fml.common.Mod;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.criterion.GoodEndingCriterion;

@Mod.EventBusSubscriber(modid = GoodEnding.MODID)
public class GoodEndingCriteriaTriggers {

    public static final GoodEndingCriterion IMMUNITY = CriteriaTriggers.register(new GoodEndingCriterion("immunity"));
    public static final GoodEndingCriterion BREW_POTION = CriteriaTriggers.register(new GoodEndingCriterion("brew_potion"));
    public static final GoodEndingCriterion CAPTURE_FIREFLY = CriteriaTriggers.register(new GoodEndingCriterion("capture_firefly"));

}
