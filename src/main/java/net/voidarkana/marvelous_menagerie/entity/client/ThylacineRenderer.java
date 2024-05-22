package net.voidarkana.marvelous_menagerie.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import net.voidarkana.marvelous_menagerie.entity.custom.ThylacineEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ThylacineRenderer extends GeoEntityRenderer<ThylacineEntity> {

    public ThylacineRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ThylacineModel());
    }
    private static final ResourceLocation TEXTURE_NO = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine.png");
    private static final ResourceLocation TEXTURE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_red.png");
    private static final ResourceLocation TEXTURE_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_orange.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_yellow.png");
    private static final ResourceLocation TEXTURE_LIME = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_lime.png");
    private static final ResourceLocation TEXTURE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_green.png");
    private static final ResourceLocation TEXTURE_CYAN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_cyan.png");
    private static final ResourceLocation TEXTURE_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_light_blue.png");
    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_blue.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_purple.png");
    private static final ResourceLocation TEXTURE_MAGENTA = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_magenta.png");
    private static final ResourceLocation TEXTURE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_pink.png");
    private static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_brown.png");
    private static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_black.png");
    private static final ResourceLocation TEXTURE_GRAY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_gray.png");
    private static final ResourceLocation TEXTURE_LIGHT_GRAY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_light_gray.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine_white.png");

    @Override
    public ResourceLocation getTextureLocation(ThylacineEntity entity) {

        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_RED;
            case 2 -> TEXTURE_ORANGE;
            case 3 -> TEXTURE_YELLOW;
            case 4 -> TEXTURE_LIME;
            case 5 -> TEXTURE_GREEN;
            case 6 -> TEXTURE_CYAN;
            case 7 -> TEXTURE_LIGHT_BLUE;
            case 8 -> TEXTURE_BLUE;
            case 9 -> TEXTURE_PURPLE;
            case 10 -> TEXTURE_MAGENTA;
            case 11 -> TEXTURE_PINK;
            case 12 -> TEXTURE_BROWN;
            case 13 -> TEXTURE_BLACK;
            case 14 -> TEXTURE_GRAY;
            case 15 -> TEXTURE_LIGHT_GRAY;
            case 16 -> TEXTURE_WHITE;
            default -> TEXTURE_NO;
        };
        //return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/thylacine.png");
        /*return switch (entity.getVariant()) {
            case 1 -> TEXTURE_BLUE;
            default -> TEXTURE_BROWN;
        };*/
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
