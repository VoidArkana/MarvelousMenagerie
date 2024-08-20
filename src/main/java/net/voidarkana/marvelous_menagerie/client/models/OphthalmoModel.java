package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class OphthalmoModel extends GeoModel<OphthalmoEntity> {

    private static final ResourceLocation NEUTRAL_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo.png");
    private static final ResourceLocation ANGRY_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_angry.png");
    private static final ResourceLocation TAME_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_tamed.png");

    @Override
    public ResourceLocation getModelResource(OphthalmoEntity ophthalmo) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"geo/ophthalmo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OphthalmoEntity ophthalmo) {

        if (ophthalmo.isTamed()) {
            return TAME_LOCATION;
        } else {
            return ophthalmo.isAngry() ? ANGRY_LOCATION : NEUTRAL_LOCATION;
        }

    }

    @Override
    public ResourceLocation getAnimationResource(OphthalmoEntity ophthalmo) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/ophthalmo.animation.json");
    }

    @Override
    public void setCustomAnimations(OphthalmoEntity animatable, long instanceId, AnimationState<OphthalmoEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");
        swimControl.setRotX( (entityData.headPitch() * ((float) Math.PI / 180F)*0.75F) );

        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        CoreGeoBone headRot = this.getAnimationProcessor().getBone("head_rot");
        headRot.setRotY(-((animatable.tilt * ((float) Math.PI / 180F))));

        CoreGeoBone tailRot = this.getAnimationProcessor().getBone("tail_rot");
        tailRot.setRotY(((animatable.tilt * ((float) Math.PI / 180F))));

        CoreGeoBone tailTipRot = this.getAnimationProcessor().getBone("tail_tip_rot");
        tailTipRot.setRotY(((animatable.tilt * ((float) Math.PI / 180F))));

        if (!animatable.isVehicle()){

            head.setRotX( (entityData.headPitch() * ((float) Math.PI / 180F)*0.05F) );
            head.setRotY( (entityData.netHeadYaw() * ((float) Math.PI / 180F)*0.05F) );

        }

    }
}
