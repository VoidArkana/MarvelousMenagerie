package net.voidarkana.marvelous_menagerie.entity.client.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CooksoniaRenderer extends GeoEntityRenderer<PlantEntity> {

    public CooksoniaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlantModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PlantEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/plants/cooksonia.png");
    }

}
