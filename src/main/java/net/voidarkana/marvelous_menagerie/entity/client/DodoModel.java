package net.voidarkana.marvelous_menagerie.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import javax.annotation.Nullable;

public class DodoModel extends GeoModel<DodoEntity> {

    @Override
    public ResourceLocation getModelResource(DodoEntity dodoEntity) {
        if (dodoEntity.isBaby()){
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_dodo.geo.json");
        }
        else {
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/dodo.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(DodoEntity object) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(DodoEntity dodoEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/dodo.animation.json");
    }

    @Override
    public void setCustomAnimations(DodoEntity dodo, long instanceId, @Nullable AnimationState<DodoEntity> animationEvent) {
        super.setCustomAnimations(dodo, instanceId, animationEvent);

        if (animationEvent == null) return;

        /*CoreGeoBone headForBaby = this.getAnimationProcessor().getBone("head");
        CoreGeoBone neck_bone = this.getAnimationProcessor().getBone("neck");
        //changes the proportions of babies
        if(dodo.isBaby()) {
            headForBaby.setScaleX(1.3F);
            headForBaby.setScaleY(1.3F);
            headForBaby.setScaleZ(1.3F);
            neck_bone.setPosY(1);
        }*/

        //makes the head turn around dynamically
        CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        EntityModelData entityData = animationEvent.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX(-(entityData.headPitch() * ((float) Math.PI / 180F)));
        head.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));

    }

}
