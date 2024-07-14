package net.voidarkana.marvelous_menagerie.client.renderers.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.PlantModel;
import net.voidarkana.marvelous_menagerie.entity.custom.PlantEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SigillariaRenderer extends GeoEntityRenderer<PlantEntity> {

    public SigillariaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlantModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PlantEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/plants/sigillaria_sapling.png");
    }

}