package net.voidarkana.marvelous_menagerie.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class DodoModel extends GeoModel<DodoEntity> {

    @Override
    public ResourceLocation getModelResource(DodoEntity dodoEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/dodo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DodoEntity object) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo.png");
        //return DodoRenderer.LOCATION_BY_VARIANT.get(object.getVariant());
    }

    @Override
    public ResourceLocation getAnimationResource(DodoEntity dodoEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/dodo.animation.json");
    }

    @Override
    public void setCustomAnimations(DodoEntity entity, long instanceId, AnimationState<DodoEntity> animationEvent) {
        super.setCustomAnimations(entity, instanceId, animationEvent);

        CoreGeoBone head = this.getAnimationProcessor().getBone("head_rotation");
        CoreGeoBone neck_bone = this.getAnimationProcessor().getBone("neck");

        //changes the proportions of babies
        if(entity.isBaby()) {
            head.setScaleX(1.3F);
            head.setScaleY(1.3F);
            head.setScaleZ(1.3F);
            neck_bone.setPosY(1);
        }
        else {
            head.setScaleX(1F);
            head.setScaleY(1F);
            head.setScaleZ(1F);
        }

        //makes the head turn around dynamically
        EntityModelData entityData = animationEvent.getData(DataTickets.ENTITY_MODEL_DATA);

        head.setRotX(-(entityData.headPitch() * ((float) Math.PI / 180F)));
        head.setRotY(entityData.netHeadYaw() * ((float) Math.PI / 180F));


    }
}
