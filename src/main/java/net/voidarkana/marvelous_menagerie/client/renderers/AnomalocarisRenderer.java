package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.AnomalocarisModel;
import net.voidarkana.marvelous_menagerie.entity.custom.AnomalocarisEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnomalocarisRenderer extends GeoEntityRenderer<AnomalocarisEntity> {

    public AnomalocarisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new AnomalocarisModel());
    }

    @Override
    public ResourceLocation getTextureLocation(AnomalocarisEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/anomalocaris.png");
    }

    @Override
    public void render(AnomalocarisEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
