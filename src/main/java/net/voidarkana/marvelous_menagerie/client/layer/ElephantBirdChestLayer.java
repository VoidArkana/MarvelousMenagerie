package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class ElephantBirdChestLayer extends GeoRenderLayer<ElephantBirdEntity> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/elephant_bird/elephant_bird_chest.png");

    private static final ResourceLocation MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/elephant_bird.geo.json");

    public ElephantBirdChestLayer(GeoRenderer<ElephantBirdEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, ElephantBirdEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (entityLivingBaseIn.hasChest()) {

            RenderType cameo = RenderType.armorCutoutNoCull(TEXTURE);

            this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                    bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        }

    }
}
