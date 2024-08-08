package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.TrilobiteLayer;
import net.voidarkana.marvelous_menagerie.client.models.TrilobiteModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.TrilobiteEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class TrilobiteRenderer extends GeoEntityRenderer<TrilobiteEntity> {

    public TrilobiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TrilobiteModel());
        this.addRenderLayer(new TrilobiteLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TrilobiteEntity entity) {
        if (entity.isLGBTrilo()){
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/lgbtrilos/"+entity.getModelName(entity.getVariantModel())+
                    "/trilobite_"+entity.getModelName(entity.getVariantModel())+"_"+entity.getTriloName()+".png");
        }
        else{
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_"+entity.getModelName(entity.getVariantModel())+
                    "_base/trilobite_"+entity.getModelName(entity.getVariantModel())+"_base_"
                    +entity.getMainColorName(entity.getVariantBaseColor())+".png");
        }
    }

    @Override
    public void render(TrilobiteEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
