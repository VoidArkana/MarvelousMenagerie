package net.voidarkana.marvelous_menagerie.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.BabyStellerEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BabyStellerRenderer extends GeoEntityRenderer<BabyStellerEntity> {
    public BabyStellerRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BabyStellerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BabyStellerEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/baby_steller_sea_cow.png");
    }

    @Override
    public void render(BabyStellerEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }


}
