package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.SacabambaspisEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SacaModel extends GeoModel<SacabambaspisEntity> {

    @Override
    public ResourceLocation getModelResource(SacabambaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/saca.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SacabambaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/saca.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SacabambaspisEntity sacabambaspisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/saca.animation.json");
    }

    @Override
    public void setCustomAnimations(SacabambaspisEntity animatable, long instanceId, AnimationState<SacabambaspisEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }
}
