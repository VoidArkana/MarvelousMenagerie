package net.voidarkana.marvelous_menagerie.item.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.item.custom.AnomalousGogglesItem;
import software.bernie.geckolib.model.GeoModel;

public class AnomalousGogglesModel extends GeoModel<AnomalousGogglesItem> {

    @Override
    public ResourceLocation getModelResource(AnomalousGogglesItem anomalousGogglesItem) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/armor/anomalous_goggles.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnomalousGogglesItem anomalousGogglesItem) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/armor/anomalous_goggles.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnomalousGogglesItem anomalousGogglesItem) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/armor.animation.json");
    }
}
