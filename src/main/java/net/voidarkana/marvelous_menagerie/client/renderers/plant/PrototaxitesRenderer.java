package net.voidarkana.marvelous_menagerie.client.renderers.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.voidarkana.marvelous_menagerie.client.models.plant_entity.PrototaxitesModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PrototaxitesRenderer extends GeoEntityRenderer<PlantEntity> {

    public PrototaxitesRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PrototaxitesModel());
    }

}
