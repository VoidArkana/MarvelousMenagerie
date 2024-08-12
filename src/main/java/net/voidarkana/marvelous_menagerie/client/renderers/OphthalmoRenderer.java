package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoArmorLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoPassengerLayer;
import net.voidarkana.marvelous_menagerie.client.layer.OphthalmoSaddleLayer;
import net.voidarkana.marvelous_menagerie.client.models.OphthalmoModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import org.jetbrains.annotations.NotNull;
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

//    public <T extends LivingEntity> void applyRiderPose(HumanoidModel<T> pHumanoidModel, @NotNull T pRider) {
//        pHumanoidModel.rightArm.xRot = this.rad(-155.0F);
//        pHumanoidModel.leftArm.xRot = this.rad(-155.0F);
//        pHumanoidModel.rightLeg.xRot = this.rad(5.0F);
//        pHumanoidModel.rightLeg.zRot = this.rad(10.0F);
//        pHumanoidModel.leftLeg.xRot = this.rad(5.0F);
//        pHumanoidModel.leftLeg.zRot = this.rad(-10.0F);
//        pHumanoidModel.head.xRot = this.rad(-80.0F);
//        pHumanoidModel.head.yRot = Mth.clamp(pHumanoidModel.head.yRot, this.rad(-35.0F), this.rad(35.0F));
//    }

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
