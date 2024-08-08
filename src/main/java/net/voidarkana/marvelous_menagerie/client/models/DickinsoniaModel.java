package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.model.GeoModel;

public class DickinsoniaModel extends GeoModel<PlantEntity> {

    @Override
    public ResourceLocation getModelResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/dickinsonia.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/dickinsonia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PlantEntity plantEntity) {
        return null;
    }

}
