package net.voidarkana.marvelous_menagerie.client.renderers.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.voidarkana.marvelous_menagerie.client.models.plant_entity.PlantModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PlantRenderer extends GeoEntityRenderer<PlantEntity> {

    public PlantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlantModel());
    }

}
