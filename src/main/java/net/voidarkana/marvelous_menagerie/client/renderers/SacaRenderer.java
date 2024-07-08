package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.SacaModel;
import net.voidarkana.marvelous_menagerie.entity.custom.SacabambaspisEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SacaRenderer extends GeoEntityRenderer<SacabambaspisEntity> {
    public SacaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SacaModel());
    }

    @Override
    public ResourceLocation getTextureLocation(SacabambaspisEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/saca.png");
    }

    @Override
    public void render(SacabambaspisEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
