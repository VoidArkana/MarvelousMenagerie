package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.PikaiaLayer;
import net.voidarkana.marvelous_menagerie.client.layer.TrilobiteLayer;
import net.voidarkana.marvelous_menagerie.client.models.PikaiaModel;
import net.voidarkana.marvelous_menagerie.client.models.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.PikaiaEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PikaiaRenderer extends GeoEntityRenderer<PikaiaEntity> {
    public PikaiaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PikaiaModel());
        this.addRenderLayer(new PikaiaLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(PikaiaEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID,"textures/entity/empty.png");
    }

    @Override
    public void render(PikaiaEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {

        poseStack.scale(0.6F, 0.6F, 0.6F);

        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
