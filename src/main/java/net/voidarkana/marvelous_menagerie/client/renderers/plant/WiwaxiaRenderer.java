package net.voidarkana.marvelous_menagerie.client.renderers.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.PlantModel;
import net.voidarkana.marvelous_menagerie.client.models.WiwaxiaModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WiwaxiaRenderer extends GeoEntityRenderer<PlantEntity> {

    public WiwaxiaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new WiwaxiaModel());
    }

}
