package net.voidarkana.marvelous_menagerie.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.ThylacineEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import javax.annotation.Nullable;

public class ThylacineModel extends GeoModel<ThylacineEntity> {
    @Override
    public ResourceLocation getModelResource(ThylacineEntity thylacineEntity) {

        if (thylacineEntity.isBaby()){
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_thylacine.geo.json");
        }
        else {
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/thylacine.geo.json");
        }
    }

    @Override
    public ResourceLocation getTextureResource(ThylacineEntity thylacineEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ThylacineEntity thylacineEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/thylacine.animation.json");
    }


    @Override
    public void setCustomAnimations(ThylacineEntity thyla, long instanceId, @Nullable AnimationState<ThylacineEntity> animationEvent) {

        super.setCustomAnimations(thyla, instanceId, animationEvent);
        if (animationEvent == null) return;

        CoreGeoBone head = this.getAnimationProcessor().getBone("headrot");
        EntityModelData entityData = animationEvent.getData(DataTickets.ENTITY_MODEL_DATA);
        head.setRotX((entityData.headPitch() * ((float) Math.PI / 180F)) * 0.75F);
        head.setRotY((entityData.netHeadYaw() * ((float) Math.PI / 180F)) * 0.75F);
    }
}
