package net.voidarkana.marvelous_menagerie.client.models.plant_entity;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PlantEntity;
import software.bernie.geckolib.model.GeoModel;

public class WiwaxiaModel extends GeoModel<PlantEntity> {

    @Override
    public ResourceLocation getModelResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/wiwaxia.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PlantEntity plantEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/wiwaxia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PlantEntity plantEntity) {
        return null;
    }

}
