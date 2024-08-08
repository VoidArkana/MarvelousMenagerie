package net.voidarkana.marvelous_menagerie.client.models.baby;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyOphthalmoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BabyOphthalmoModel extends GeoModel<BabyOphthalmoEntity> {

    @Override
    public ResourceLocation getModelResource(BabyOphthalmoEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_ophthalmo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BabyOphthalmoEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/baby_ophthalmo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BabyOphthalmoEntity anomalocarisEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/ophthalmo.animation.json");
    }

    @Override
    public void setCustomAnimations(BabyOphthalmoEntity animatable, long instanceId, AnimationState<BabyOphthalmoEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX( (entityData.headPitch() * ((float) Math.PI / 180F)*0.75F) );
    }
}
