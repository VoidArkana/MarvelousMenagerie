package net.voidarkana.marvelous_menagerie.client.models.plant_entity;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.model.GeoModel;

public class CharniaModel extends GeoModel<PlantEntity> {

    @Override
    public ResourceLocation getModelResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/charnia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PlantEntity plantEntity) {
        return null;
    }

}
