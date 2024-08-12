package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PikaiaEntity;
import software.bernie.example.entity.FakeGlassEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class PikaiaModel extends GeoModel<PikaiaEntity> {

    @Override
    public ResourceLocation getModelResource(PikaiaEntity pikaiaEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/pikaia.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PikaiaEntity pikaiaEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/pikaia.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PikaiaEntity pikaiaEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/pikaia.animation.json");
    }

    @Override
    public void setCustomAnimations(PikaiaEntity animatable, long instanceId, AnimationState<PikaiaEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone swimControl = this.getAnimationProcessor().getBone("swim_control");

        EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

        swimControl.setRotX(((entityData.headPitch() * ((float) Math.PI / 180F))));
    }

}
