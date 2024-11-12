package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ArandaspisEntity;
import net.voidarkana.marvelous_menagerie.common.entity.custom.HallucigeniaEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class HallucigeniaModel extends GeoModel<HallucigeniaEntity> {

    @Override
    public ResourceLocation getModelResource(HallucigeniaEntity hallucigenia) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/hallu.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(HallucigeniaEntity hallucigenia) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/hallucigenia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(HallucigeniaEntity hallucigenia) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/hallucigenia.animation.json");
    }

    @Override
    public void setCustomAnimations(HallucigeniaEntity animatable, long instanceId, AnimationState<HallucigeniaEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone head = this.getAnimationProcessor().getBone("look_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
        head.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))));
    }
}
