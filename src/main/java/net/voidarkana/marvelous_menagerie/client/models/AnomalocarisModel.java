package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.AnomalocarisEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class AnomalocarisModel extends GeoModel<AnomalocarisEntity> {

    @Override
    public ResourceLocation getModelResource(AnomalocarisEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/anomalocaris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AnomalocarisEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/anomalocaris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AnomalocarisEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/anomalocaris.animation.json");
    }

    @Override
    public void setCustomAnimations(AnomalocarisEntity animatable, long instanceId, AnimationState<AnomalocarisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX( (entityData.headPitch() * ((float) Math.PI / 180F)*0.75F) );
    }
}
