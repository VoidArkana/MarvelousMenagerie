package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ElephantBirdModel extends GeoModel<ElephantBirdEntity> {

    private static final ResourceLocation REGULAR_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/elephant_bird/elephant_bird.png");
    private static final ResourceLocation BABY_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/elephant_bird/baby_elephant_bird.png");

    private static final ResourceLocation REGULAR_MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/elephant_bird.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_elephant_bird.geo.json");


    @Override
    public ResourceLocation getModelResource(ElephantBirdEntity elephantBirdEntity) {
        return elephantBirdEntity.isBaby() ? BABY_MODEL : REGULAR_MODEL;
    }

    @Override
    public ResourceLocation getTextureResource(ElephantBirdEntity elephantBirdEntity) {
        return elephantBirdEntity.isBaby() ? BABY_TEXTURE : REGULAR_TEXTURE;
    }

    @Override
    public ResourceLocation getAnimationResource(ElephantBirdEntity elephantBirdEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/elephant_bird.animation.json");
    }

    @Override
    public void setCustomAnimations(ElephantBirdEntity animatable, long instanceId, AnimationState<ElephantBirdEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone head = this.getAnimationProcessor().getBone("neck_x");
        CoreGeoBone main = this.getAnimationProcessor().getBone("main");

        if (animatable.isBaby()){
            main.setScaleX(1.5F);
            main.setScaleY(1.5F);
            main.setScaleZ(1.5F);
        }
        
        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))*0.75F));
        head.setRotY(((entityData.netHeadYaw() * ((float) Math.PI / 180F))*0.75F));

    }

}
