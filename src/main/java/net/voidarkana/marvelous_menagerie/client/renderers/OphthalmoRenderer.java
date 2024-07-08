package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoArmorLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoPassengerLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoSaddleLayer;
import net.voidarkana.marvelous_menagerie.client.models.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.entity.custom.OphthalmoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OphthalmoRenderer extends GeoEntityRenderer<OphthalmoEntity> {

    private static final ResourceLocation NEUTRAL_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo.png");
    private static final ResourceLocation ANGRY_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_angry.png");
    private static final ResourceLocation TAME_LOCATION = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/ophthalmo/ophthalmo_tamed.png");

    public OphthalmoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OphthalmoModel());
        this.addRenderLayer(new OphthalmoSaddleLayer(this));
        this.addRenderLayer(new OphthalmoArmorLayer(this));
        this.addRenderLayer(new OphthalmoPassengerLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(OphthalmoEntity ophthalmo) {

        if (ophthalmo.isTamed()) {
            return TAME_LOCATION;
        } else {
            return ophthalmo.isAngry() ? ANGRY_LOCATION : NEUTRAL_LOCATION;
        }

    }

    @Override
    public void render(OphthalmoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
