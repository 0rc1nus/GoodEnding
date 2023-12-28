package net.orcinus.goodending.init;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.orcinus.goodending.GoodEnding;
import net.orcinus.goodending.entities.GoodEndingBoatEntity;

public class GoodEndingModelLayers {

    public static final ModelLayerLocation MARSH = create("marsh");
    public static final ModelLayerLocation WOODPECKER = create("woodpecker");

    public static ModelLayerLocation createChestBoat(GoodEndingBoatEntity.BoatType type) {
        return create("chest_boat/" + type.getName(), "main");
    }

    public static ModelLayerLocation createBoat(GoodEndingBoatEntity.BoatType type) {
        return create("boat/" + type.getName(), "main");
    }

    public static ModelLayerLocation create(String name) {
        return create(name, "main");
    }

    private static ModelLayerLocation create(String id, String layer) {
        return new ModelLayerLocation(new ResourceLocation(GoodEnding.MODID, id), layer);
    }

}
