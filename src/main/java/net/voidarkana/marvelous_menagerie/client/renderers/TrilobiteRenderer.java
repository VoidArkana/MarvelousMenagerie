package net.voidarkana.marvelous_menagerie.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.layer.TrilobiteLayer;
import net.voidarkana.marvelous_menagerie.client.models.TrilobiteModel;
import net.voidarkana.marvelous_menagerie.entity.custom.TrilobiteEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TrilobiteRenderer extends GeoEntityRenderer<TrilobiteEntity> {

    private static final ResourceLocation CURLY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_black.png");
    private static final ResourceLocation CURLY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_blue.png");
    private static final ResourceLocation CURLY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_brown.png");
    private static final ResourceLocation CURLY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_green.png");
    private static final ResourceLocation CURLY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_pink.png");
    private static final ResourceLocation CURLY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_red.png");
    private static final ResourceLocation CURLY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_teal.png");

    private static final ResourceLocation TRIDENT_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_black.png");
    private static final ResourceLocation TRIDENT_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_blue.png");
    private static final ResourceLocation TRIDENT_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_brown.png");
    private static final ResourceLocation TRIDENT_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_green.png");
    private static final ResourceLocation TRIDENT_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_pink.png");
    private static final ResourceLocation TRIDENT_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_red.png");
    private static final ResourceLocation TRIDENT_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_teal.png");

    private static final ResourceLocation MOON_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_black.png");
    private static final ResourceLocation MOON_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_blue.png");
    private static final ResourceLocation MOON_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_brown.png");
    private static final ResourceLocation MOON_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_green.png");
    private static final ResourceLocation MOON_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_pink.png");
    private static final ResourceLocation MOON_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_red.png");
    private static final ResourceLocation MOON_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_teal.png");

    private static final ResourceLocation SPIKY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_black.png");
    private static final ResourceLocation SPIKY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_blue.png");
    private static final ResourceLocation SPIKY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_brown.png");
    private static final ResourceLocation SPIKY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_green.png");
    private static final ResourceLocation SPIKY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_pink.png");
    private static final ResourceLocation SPIKY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_red.png");
    private static final ResourceLocation SPIKY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_teal.png");

    private static final ResourceLocation ITTY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_black.png");
    private static final ResourceLocation ITTY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_blue.png");
    private static final ResourceLocation ITTY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_brown.png");
    private static final ResourceLocation ITTY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_green.png");
    private static final ResourceLocation ITTY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_pink.png");
    private static final ResourceLocation ITTY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_red.png");
    private static final ResourceLocation ITTY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_teal.png");

    private static final ResourceLocation FAT_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_black.png");
    private static final ResourceLocation FAT_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_blue.png");
    private static final ResourceLocation FAT_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_brown.png");
    private static final ResourceLocation FAT_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_green.png");
    private static final ResourceLocation FAT_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_pink.png");
    private static final ResourceLocation FAT_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_red.png");
    private static final ResourceLocation FAT_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_teal.png");

    public TrilobiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TrilobiteModel());
        this.addRenderLayer(new TrilobiteLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TrilobiteEntity entity) {
        return switch (entity.getVariantModel()) {
            case 1 -> switch (entity.getVariantBaseColor()) {
                case 1 -> TRIDENT_BASE_BLACK;
                case 2 -> TRIDENT_BASE_BLUE;
                case 3 -> TRIDENT_BASE_GREEN;
                case 4 -> TRIDENT_BASE_PINK;
                case 5 -> TRIDENT_BASE_RED;
                case 6 -> TRIDENT_BASE_TEAL;
                default -> TRIDENT_BASE_BROWN;
            };
            case 2 -> switch (entity.getVariantBaseColor()) {
                case 1 -> MOON_BASE_BLACK;
                case 2 -> MOON_BASE_BLUE;
                case 3 -> MOON_BASE_GREEN;
                case 4 -> MOON_BASE_PINK;
                case 5 -> MOON_BASE_RED;
                case 6 -> MOON_BASE_TEAL;
                default -> MOON_BASE_BROWN;
            };
            case 3 -> switch (entity.getVariantBaseColor()) {
                case 1 -> SPIKY_BASE_BLACK;
                case 2 -> SPIKY_BASE_BLUE;
                case 3 -> SPIKY_BASE_GREEN;
                case 4 -> SPIKY_BASE_PINK;
                case 5 -> SPIKY_BASE_RED;
                case 6 -> SPIKY_BASE_TEAL;
                default -> SPIKY_BASE_BROWN;
            };
            case 4 -> switch (entity.getVariantBaseColor()) {
                case 1 -> ITTY_BASE_BLACK;
                case 2 -> ITTY_BASE_BLUE;
                case 3 -> ITTY_BASE_GREEN;
                case 4 -> ITTY_BASE_PINK;
                case 5 -> ITTY_BASE_RED;
                case 6 -> ITTY_BASE_TEAL;
                default -> ITTY_BASE_BROWN;
            };
            case 5 -> switch (entity.getVariantBaseColor()) {
                case 1 -> FAT_BASE_BLACK;
                case 2 -> FAT_BASE_BLUE;
                case 3 -> FAT_BASE_GREEN;
                case 4 -> FAT_BASE_PINK;
                case 5 -> FAT_BASE_RED;
                case 6 -> FAT_BASE_TEAL;
                default -> FAT_BASE_BROWN;
            };
            default -> switch (entity.getVariantBaseColor()) {
                case 1 -> CURLY_BASE_BLACK;
                case 2 -> CURLY_BASE_BLUE;
                case 3 -> CURLY_BASE_GREEN;
                case 4 -> CURLY_BASE_PINK;
                case 5 -> CURLY_BASE_RED;
                case 6 -> CURLY_BASE_TEAL;
                default -> CURLY_BASE_BROWN;
            };
        };
    }

    @Override
    public void render(TrilobiteEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        /*if(entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
        else {
            poseStack.scale(1.0F, 1.0F, 1.0F);
        }*/
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
