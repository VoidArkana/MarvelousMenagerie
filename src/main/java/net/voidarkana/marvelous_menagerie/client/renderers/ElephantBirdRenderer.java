package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.ElephantBirdModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ElephantBirdRenderer extends GeoEntityRenderer<ElephantBirdEntity> {

    public ElephantBirdRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ElephantBirdModel());
    }

    @Override
    public void render(ElephantBirdEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {

        if (entity.isBaby()){

            poseStack.scale(0.8F, 0.8F, 0.8F);
        }else {
            poseStack.scale(1.1F, 1.1F, 1.1F);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }

}
