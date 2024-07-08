package net.voidarkana.marvelous_menagerie.item.client;

import net.voidarkana.marvelous_menagerie.item.custom.AnomalousGogglesItem;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AnomalousGogglesRenderer extends GeoArmorRenderer<AnomalousGogglesItem> {
    public AnomalousGogglesRenderer() {
        super(new AnomalousGogglesModel());
    }
}
