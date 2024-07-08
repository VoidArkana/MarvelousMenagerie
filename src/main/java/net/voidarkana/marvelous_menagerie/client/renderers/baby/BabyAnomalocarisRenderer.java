package net.voidarkana.marvelous_menagerie.client.renderers.baby;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.baby.BabyAnomalocarisModel;
import net.voidarkana.marvelous_menagerie.entity.custom.baby.BabyAnomalocarisEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BabyAnomalocarisRenderer extends GeoEntityRenderer<BabyAnomalocarisEntity> {

    public BabyAnomalocarisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BabyAnomalocarisModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BabyAnomalocarisEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/anomalocaris.png");
    }

    @Override
    public void render(BabyAnomalocarisEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {

        poseStack.scale(0.5F, 0.5F, 0.5F);
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
