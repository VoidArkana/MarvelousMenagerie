package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyOphthalmoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class BabyOphthalmoPatternLayer extends GeoRenderLayer<BabyOphthalmoEntity> {

    private static final ResourceLocation MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_ophthalmo.geo.json");

    public BabyOphthalmoPatternLayer(GeoRenderer<BabyOphthalmoEntity> entityRendererIn) {super(entityRendererIn);}

    @Override
    public void render(PoseStack poseStack, BabyOphthalmoEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (entityLivingBaseIn.getPattern()!=0) {

            String color = entityLivingBaseIn.getColorName(entityLivingBaseIn.getBaseColor());
            String pattern = entityLivingBaseIn.getPatternName(entityLivingBaseIn.getPattern());

            RenderType cameo = RenderType.entityCutoutNoCull(
                    new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/baby_ophthalmo"+pattern+color+".png"));

            this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                        bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }

    }
}
