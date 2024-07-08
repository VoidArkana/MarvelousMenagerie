package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.JosephoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class JosephoModel extends GeoModel<JosephoEntity> {
    @Override
    public ResourceLocation getModelResource(JosephoEntity josepho) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, josepho.isBaby() ? "geo/baby_josepho.geo.json" : "geo/josepho.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(JosephoEntity josepho) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/josepho/josepho.png");
    }

    @Override
    public ResourceLocation getAnimationResource(JosephoEntity josepho) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/josepho.animation.json");
    }

    @Override
    public void setCustomAnimations(JosephoEntity animatable, long instanceId, AnimationState<JosephoEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);


        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        head.setRotX( (entityData.headPitch() * ((float) Math.PI / 180F)*0.5F) );
        head.setRotY( (entityData.netHeadYaw() * ((float) Math.PI / 180F)*0.5F) );


    }
}
