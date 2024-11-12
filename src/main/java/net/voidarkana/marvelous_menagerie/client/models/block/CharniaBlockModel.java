package net.voidarkana.marvelous_menagerie.client.models.block;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.entity.CharniaBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class CharniaBlockModel extends GeoModel<CharniaBlockEntity> {

    @Override
    public ResourceLocation getModelResource(CharniaBlockEntity animatable) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CharniaBlockEntity animatable) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/charnia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CharniaBlockEntity animatable) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/charnia.animation.json");
    }

    @Override
    public RenderType getRenderType(CharniaBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityCutout(texture);
    }
}
