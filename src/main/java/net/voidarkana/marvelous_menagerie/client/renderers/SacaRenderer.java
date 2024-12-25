package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.models.SacaModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.SacabambaspisEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SacaRenderer extends GeoEntityRenderer<SacabambaspisEntity> {

    public SacaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SacaModel());
    }

    @Override
    protected void applyRotations(SacabambaspisEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);

        poseStack.mulPose(Axis.ZP.rotationDegrees(animatable.currentRoll*360/4));
    }
}
