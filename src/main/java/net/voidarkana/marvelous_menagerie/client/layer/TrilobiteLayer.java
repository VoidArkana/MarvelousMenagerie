package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.TrilobiteEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class TrilobiteLayer extends GeoRenderLayer<TrilobiteEntity> {

    public TrilobiteLayer(GeoRenderer<TrilobiteEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, TrilobiteEntity entity, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (!entity.isLGBTrilo()){

            RenderType cameo = RenderType.entityCutout(new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_"+entity.getModelName(entity.getVariantModel())
                    +"_second/trilobite_"+entity.getModelName(entity.getVariantModel())+"_second_"+entity.getSecondColorName(entity.getVariantSecondColor())+".png"));
            ResourceLocation trilobiteModel = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_"
                    +entity.getModelName(entity.getVariantModel())+".geo.json");

            this.getRenderer().reRender(this.getGeoModel().getBakedModel(trilobiteModel), poseStack, bufferSource, entity, renderType,
                    bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }

    }

}
