package net.voidarkana.marvelous_menagerie.client.renderers.baby;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.baby.BabyAnomalocarisModel;
import net.voidarkana.marvelous_menagerie.client.models.baby.BabyOphthalmoModel;
import net.voidarkana.marvelous_menagerie.entity.custom.baby.BabyAnomalocarisEntity;
import net.voidarkana.marvelous_menagerie.entity.custom.baby.BabyOphthalmoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class BabyOphthalmoRenderer extends GeoEntityRenderer<BabyOphthalmoEntity> {

    public BabyOphthalmoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BabyOphthalmoModel());
    }

    @Override
    public ResourceLocation getTextureLocation(BabyOphthalmoEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/baby_ophthalmo.png");
    }

    @Override
    public void render(BabyOphthalmoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
