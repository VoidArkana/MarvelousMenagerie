package net.voidarkana.marvelous_menagerie.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class DodoRenderer extends GeoEntityRenderer<DodoEntity> {


    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo.png");
    private static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/dodo_variant.png");

    public DodoRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DodoModel());
    }


    @Override
    public ResourceLocation getTextureLocation(DodoEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_BLUE;
            default -> TEXTURE_BROWN;
        };
    }

    @Override
    public void render(DodoEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                                    MultiBufferSource bufferSource, int packedLightIn) {
        if(entity.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        }
        else {
            poseStack.scale(1.2F, 1.2F, 1.2F);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }

}
