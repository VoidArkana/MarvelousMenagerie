package net.voidarkana.marvelous_menagerie.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.ElephantBirdEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ElephantBirdModel extends GeoModel<ElephantBirdEntity> {

    @Override
    public ResourceLocation getModelResource(ElephantBirdEntity elephantBirdEntity) {
        if (elephantBirdEntity.isBaby()){
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_elephant_bird.geo.json");
        }
        else {
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/elephant_bird.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(ElephantBirdEntity elephantBirdEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/elephant_bird.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ElephantBirdEntity elephantBirdEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/elephant_bird.animation.json");
    }

    @Override
    public void setCustomAnimations(ElephantBirdEntity animatable, long instanceId, AnimationState<ElephantBirdEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone head = this.getAnimationProcessor().getBone("neck_x");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.75F));
        head.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))*0.75F));
    }

}
