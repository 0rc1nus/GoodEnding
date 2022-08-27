package net.orcinus.goodending.init;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;

public class GoodEndingModelLayers {

    public static final EntityModelLayer MARSH = new EntityModelLayer(new Identifier(GoodEnding.MODID, "marsh"), "main");
    public static final EntityModelLayer WOODPECKER = new EntityModelLayer(new Identifier(GoodEnding.MODID, "woodpecker"), "main");

    public static EntityModelLayer createChestBoat(GoodEndingBoatEntity.BoatType type) {
        return create("chest_boat/" + type.getName(), "main");
    }

    public static EntityModelLayer createBoat(GoodEndingBoatEntity.BoatType type) {
        return create("boat/" + type.getName(), "main");
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier(GoodEnding.MODID, id), layer);
    }

}
