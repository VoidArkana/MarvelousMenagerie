package net.voidarkana.marvelous_menagerie.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.StellerEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class StellerRenderer extends GeoEntityRenderer<StellerEntity> {

    private static final ResourceLocation STELLER_NORMAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/steller_sea_cow.png");

    public StellerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StellerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(StellerEntity entity) {
        return STELLER_NORMAL; //return entity.isSteller ? STELLAR_COW : STELLER_NORMAL ;
    }

    @Override
    public void render(StellerEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
            MultiBufferSource bufferSource, int packedLightIn) {
            poseStack.scale(1.1F, 1.1F, 1.1F);
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }


}
