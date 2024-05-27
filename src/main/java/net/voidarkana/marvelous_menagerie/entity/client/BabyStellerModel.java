package net.voidarkana.marvelous_menagerie.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.BabyStellerEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BabyStellerModel extends GeoModel<BabyStellerEntity> {
    @Override
    public ResourceLocation getModelResource(BabyStellerEntity babyStellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_steller_sea_cow.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BabyStellerEntity babyStellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/baby_steller_sea_cow.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BabyStellerEntity babyStellerEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/baby_steller_sea_cow.animation.json");
    }

    @Override
    public void setCustomAnimations(BabyStellerEntity animatable, long instanceId, AnimationState<BabyStellerEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone head = this.getAnimationProcessor().getBone("head");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.5F));
        head.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))*0.25F));

    }

}
