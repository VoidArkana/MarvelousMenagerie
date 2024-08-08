package net.voidarkana.marvelous_menagerie.common.item.client;

import net.voidarkana.marvelous_menagerie.common.item.custom.AnomalousGogglesItem;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class AnomalousGogglesRenderer extends GeoArmorRenderer<AnomalousGogglesItem> {
    public AnomalousGogglesRenderer() {
        super(new AnomalousGogglesModel());
    }
}
