package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ArandaspisEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ArandaspisModel extends GeoModel<ArandaspisEntity> {

    @Override
    public ResourceLocation getModelResource(ArandaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/arandaspis.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ArandaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/arandaspis.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ArandaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/arandaspis.animation.json");
    }

    @Override
    public void setCustomAnimations(ArandaspisEntity animatable, long instanceId, AnimationState<ArandaspisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_rot");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }
}
