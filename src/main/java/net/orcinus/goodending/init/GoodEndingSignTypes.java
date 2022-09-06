package net.orcinus.goodending.init;

import net.orcinus.goodending.mixin.invokers.SignTypeInvoker;
import net.orcinus.goodending.util.GoodEndingSignType;
import org.jetbrains.annotations.NotNull;

public class GoodEndingSignTypes {

    public static final GoodEndingSignType CYPRESS = registerSign("cypress");
    public static final GoodEndingSignType MUDDY_OAK = registerSign("muddy_oak");

    @NotNull
    private static GoodEndingSignType registerSign(String id) {
        return (GoodEndingSignType) SignTypeInvoker.invokeRegister(new GoodEndingSignType(id));
    }

}
