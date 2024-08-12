package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PikaiaEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class PikaiaLayer extends GeoRenderLayer<PikaiaEntity> {

    public PikaiaLayer(GeoRenderer<PikaiaEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, PikaiaEntity entity, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (!entity.isInvisible()) {

            RenderType cameo = RenderType.entityTranslucent(new ResourceLocation(MarvelousMenagerie.MOD_ID, entity.isPikachu() ? "textures/entity/pikaia/pikaiachu.png" : "textures/entity/pikaia/pikaia.png"));

            ResourceLocation trilobiteModel = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/pikaia.geo.json");

            this.getRenderer().reRender(this.getGeoModel().getBakedModel(trilobiteModel), poseStack, bufferSource, entity, renderType,
                        bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }

}
