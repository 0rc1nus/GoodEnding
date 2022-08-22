package net.orcinus.goodending.init;

import net.orcinus.goodending.mixin.SignTypeInvoker;
import net.orcinus.goodending.util.GoodEndingSignType;
import org.jetbrains.annotations.NotNull;

public class GoodEndingSignTypes {

    public static final GoodEndingSignType SWAMP = registerSign("swamp");
    public static final GoodEndingSignType CYPRESS = registerSign("cypress");

    @NotNull
    private static GoodEndingSignType registerSign(String id) {
        return (GoodEndingSignType) SignTypeInvoker.invokeRegister(new GoodEndingSignType(id));
    }

}
