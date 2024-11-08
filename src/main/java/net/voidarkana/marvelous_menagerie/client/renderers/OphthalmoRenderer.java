package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoArmorLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoPassengerLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoPatternLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoSaddleLayer;
import net.voidarkana.marvelous_menagerie.client.models.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.client.renderers.util.ICustomPlayerRidePose;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class OphthalmoRenderer extends GeoEntityRenderer<OphthalmoEntity> implements ICustomPlayerRidePose {

    public OphthalmoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new OphthalmoModel());
        this.addRenderLayer(new OphthalmoPatternLayer(this));
        this.addRenderLayer(new OphthalmoSaddleLayer(this));
        this.addRenderLayer(new OphthalmoArmorLayer(this));
        this.addRenderLayer(new OphthalmoPassengerLayer(this));
    }

    @Override
    public <T extends LivingEntity> void applyRiderPose(HumanoidModel<T> pHumanoidModel, @NotNull T pRider) {
        //TODO: figure out animations and head rotation
        pHumanoidModel.rightArm.xRot = this.rad(-155.0F);
        pHumanoidModel.leftArm.xRot = this.rad(-155.0F);
        pHumanoidModel.rightLeg.xRot = this.rad(5.0F);
        pHumanoidModel.rightLeg.zRot = this.rad(10.0F);
        pHumanoidModel.leftLeg.xRot = this.rad(5.0F);
        pHumanoidModel.leftLeg.zRot = this.rad(-10.0F);
        pHumanoidModel.head.xRot = this.rad( -80.0F);
        pHumanoidModel.head.yRot = Mth.clamp(pHumanoidModel.head.yRot, this.rad(-35.0F), this.rad(35.0F));

        pHumanoidModel.hat.xRot = pHumanoidModel.head.xRot;
        pHumanoidModel.hat.yRot = pHumanoidModel.head.yRot;
    }

    @Override
    public <T extends Entity> void applyRiderMatrixStack(@NotNull T pVehicle, @NotNull PoseStack pMatrixStack) {
        //this.getModel().setMatrixStack(pMatrixStack);
        pMatrixStack.translate(0.0F, 0.05F - pVehicle.getBbHeight(), 1.7F);
        pMatrixStack.mulPose(Axis.YN.rotationDegrees(180.0F));
        pMatrixStack.mulPose(Axis.XN.rotationDegrees(295.0F));
    }

//    @Override
//    public ResourceLocation getTextureLocation(OphthalmoEntity ophthalmo) {
//
//        if (ophthalmo.isTamed()) {
//            return TAME_LOCATION;
//        } else {
//            return ophthalmo.isAngry() ? ANGRY_LOCATION : NEUTRAL_LOCATION;
//        }
//
//    }

    @Override
    public void render(OphthalmoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }

    @Override
    protected void applyRotations(OphthalmoEntity animatable, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTick) {
        super.applyRotations(animatable, poseStack, ageInTicks, rotationYaw, partialTick);

        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, -animatable.prevTilt, -animatable.tilt)));
    }
}
