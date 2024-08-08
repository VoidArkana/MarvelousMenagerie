package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.*;
import net.voidarkana.marvelous_menagerie.client.models.JosephoModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.JosephoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class JosephoRenderer extends GeoEntityRenderer<JosephoEntity> {

    public JosephoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new JosephoModel());
        this.addRenderLayer(new JosephoSaddleLayer(this));
        this.addRenderLayer(new JosephoPassengerLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(JosephoEntity ophthalmo) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/josepho/josepho.png");
    }

    @Override
    public void render(JosephoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        if(entity.isBaby()) {
            poseStack.scale(0.4F, 0.4F, 0.4F);
        }
        else {
            poseStack.scale(1.0F, 1.0F, 1.0F);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
