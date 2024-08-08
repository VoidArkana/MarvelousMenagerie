package net.voidarkana.marvelous_menagerie.client.models;

import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.TrilobiteEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import javax.annotation.Nullable;

public class TrilobiteModel extends GeoModel<TrilobiteEntity> {

    private static final ResourceLocation MODEL_CURLY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_curly.geo.json");
    private static final ResourceLocation MODEL_TRIDENT = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_trident.geo.json");
    private static final ResourceLocation MODEL_MOON = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_moon.geo.json");
    private static final ResourceLocation MODEL_SPIKY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_spiky.geo.json");
    private static final ResourceLocation MODEL_ITTY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_itty.geo.json");
    private static final ResourceLocation MODEL_FAT = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_fat.geo.json");

    @Override
    public ResourceLocation getModelResource(TrilobiteEntity trilobiteEntity) {
        return switch (trilobiteEntity.getVariantModel()){
            case 1 -> MODEL_TRIDENT;
            case 2 -> MODEL_MOON;
            case 3 -> MODEL_SPIKY;
            case 4 -> MODEL_ITTY;
            case 5 -> MODEL_FAT;
            default -> MODEL_CURLY;
        };
    }

    @Override
    public ResourceLocation getTextureResource(TrilobiteEntity trilobiteEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_medium_base/trilobite_medium_base.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TrilobiteEntity trilobiteEntity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/trilobite.animation.json");
    }

    @Override
    public void setCustomAnimations(TrilobiteEntity trilo, long instanceId, @Nullable AnimationState<TrilobiteEntity> animationEvent) {

        super.setCustomAnimations(trilo, instanceId, animationEvent);
        if (animationEvent == null) return;

        CoreGeoBone head = this.getAnimationProcessor().getBone("body");
        EntityModelData entityData = animationEvent.getData(DataTickets.ENTITY_MODEL_DATA);

        if (!trilo.isInWater()){
            head.setRotY((entityData.netHeadYaw() * ((float) Math.PI / 180F)) * 0.75F);
        }

    }
}
