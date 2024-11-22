package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class OphthalmoArmorLayer extends GeoRenderLayer<OphthalmoEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor.png");
    private static final ResourceLocation TEXTURE_CRACKED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor_cracked.png");
    private static final ResourceLocation TEXTURE_VERY_CRACKED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_armor_very_cracked.png");

    private static final ResourceLocation MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/ophthalmo.geo.json");

    public OphthalmoArmorLayer(GeoRenderer<OphthalmoEntity> entityRendererIn) {super(entityRendererIn);}

    @Override
    public void render(PoseStack poseStack, OphthalmoEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (entityLivingBaseIn.getHasArmor()) {

            RenderType cameo;
            if (entityLivingBaseIn.isArmorHalfwayDamaged()){
                cameo = RenderType.armorCutoutNoCull(TEXTURE_CRACKED);
            }else if (entityLivingBaseIn.isArmorQuarterDamaged()){
                cameo = RenderType.armorCutoutNoCull(TEXTURE_VERY_CRACKED);
            }else {
                cameo = RenderType.armorCutoutNoCull(TEXTURE);
            }

            this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                        bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }

    }
}
