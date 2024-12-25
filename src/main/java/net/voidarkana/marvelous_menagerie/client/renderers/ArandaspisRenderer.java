package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.client.models.ArandaspisModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ArandaspisEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ArandaspisRenderer extends GeoEntityRenderer<ArandaspisEntity> {

    public ArandaspisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ArandaspisModel());
    }

    @Override
    protected void applyRotations(ArandaspisEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);

        poseStack.mulPose(Axis.ZP.rotationDegrees(animatable.currentRoll*360/4));
    }
}
