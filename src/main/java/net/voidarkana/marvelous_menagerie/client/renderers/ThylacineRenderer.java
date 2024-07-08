package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.ThylacineHandkerchiefLayer;
import net.voidarkana.marvelous_menagerie.client.models.ThylacineModel;
import net.voidarkana.marvelous_menagerie.entity.custom.ThylacineEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThylacineRenderer extends GeoEntityRenderer<ThylacineEntity> {

    private static final ResourceLocation BASE_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine.png");
    private static final ResourceLocation METRO_TEXTURE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_metropolitan.png");

    public ThylacineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ThylacineModel());
        this.addRenderLayer(new ThylacineHandkerchiefLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(ThylacineEntity entity) {

        return entity.isMetro() ? METRO_TEXTURE : BASE_TEXTURE;

    }

    @Override
    public void render(ThylacineEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        if(entity.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        }
        else {
            poseStack.scale(1.0F, 1.0F, 1.0F);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
