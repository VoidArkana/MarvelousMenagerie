package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.PlantEntity;
import software.bernie.geckolib.model.GeoModel;

public class PlantModel extends GeoModel<PlantEntity> {

    @Override
    public ResourceLocation getModelResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/plant.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/plants/sigillaria_sapling.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PlantEntity plantEntity) {
        return null;
    }

}
