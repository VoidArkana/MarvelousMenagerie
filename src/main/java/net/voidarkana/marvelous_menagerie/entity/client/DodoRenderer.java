package net.voidarkana.marvelous_menagerie.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class DodoRenderer extends GeoEntityRenderer<DodoEntity> {


    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo/dodo.png");
    private static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo/dodo_variant.png");
    private static final ResourceLocation TEXTURE_BABY_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo/baby_dodo.png");
    private static final ResourceLocation TEXTURE_BABY_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo/baby_dodo_variant.png");

    public DodoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DodoModel());
    }


    @Override
    public ResourceLocation getTextureLocation(DodoEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> entity.isBaby() ? TEXTURE_BABY_BLUE : TEXTURE_BLUE;
            default -> entity.isBaby() ? TEXTURE_BABY_BROWN : TEXTURE_BROWN;
        };
    }

    @Override
    public void render(DodoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                                    MultiBufferSource bufferSource, int packedLightIn) {

        poseStack.scale(1F, 1F, 1F);
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }

}
