package net.voidarkana.marvelous_menagerie.client.renderers.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.voidarkana.marvelous_menagerie.client.models.plant_entity.WiwaxiaModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WiwaxiaRenderer extends GeoEntityRenderer<PlantEntity> {

    public WiwaxiaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WiwaxiaModel());
    }

}
