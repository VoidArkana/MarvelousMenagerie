package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.StellerEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class StellerModel extends GeoModel<StellerEntity> {

    @Override
    public ResourceLocation getModelResource(StellerEntity StellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/steller_sea_cow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StellerEntity stellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/steller_sea_cow.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StellerEntity stellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/steller_sea_cow.animation.json");
    }

    @Override
    public void setCustomAnimations(StellerEntity animatable, long instanceId, AnimationState<StellerEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone head = this.getAnimationProcessor().getBone("head");
        CoreGeoBone neck = this.getAnimationProcessor().getBone("neck");

        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.15F));
        head.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))*0.15F));

        neck.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.20F));
        neck.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))*0.15F));

        body.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.25F));


        CoreGeoBone headRot = this.getAnimationProcessor().getBone("head_rot");
        headRot.setRotY(-((animatable.tilt * ((float) Math.PI / 180F))));

        CoreGeoBone neckRot = this.getAnimationProcessor().getBone("neck_rot");
        neckRot.setRotY(-((animatable.tilt * ((float) Math.PI / 180F))));

        CoreGeoBone tailRot = this.getAnimationProcessor().getBone("tail_rot");
        tailRot.setRotY(((animatable.tilt * ((float) Math.PI / 180F))));

        CoreGeoBone tailTipRot = this.getAnimationProcessor().getBone("tail_tip_rot");
        tailTipRot.setRotY(((animatable.tilt * ((float) Math.PI / 180F)))/2);
    }

}
