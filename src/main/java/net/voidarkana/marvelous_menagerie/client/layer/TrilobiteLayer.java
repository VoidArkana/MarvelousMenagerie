package net.voidarkana.marvelous_menagerie.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.TrilobiteEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

public class TrilobiteLayer extends GeoRenderLayer<TrilobiteEntity> {

    private static final ResourceLocation CURLY_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_blue.png");
    private static final ResourceLocation CURLY_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_brown.png");
    private static final ResourceLocation CURLY_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_green.png");
    private static final ResourceLocation CURLY_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_light_blue.png");
    private static final ResourceLocation CURLY_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_orange.png");
    private static final ResourceLocation CURLY_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_pink.png");
    private static final ResourceLocation CURLY_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_purple.png");
    private static final ResourceLocation CURLY_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_red.png");
    private static final ResourceLocation CURLY_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_teal.png");
    private static final ResourceLocation CURLY_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_white.png");
    private static final ResourceLocation CURLY_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_second/trilobite_curly_second_yellow.png");

    private static final ResourceLocation TRIDENT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_blue.png");
    private static final ResourceLocation TRIDENT_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_brown.png");
    private static final ResourceLocation TRIDENT_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_green.png");
    private static final ResourceLocation TRIDENT_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_light_blue.png");
    private static final ResourceLocation TRIDENT_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_orange.png");
    private static final ResourceLocation TRIDENT_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_pink.png");
    private static final ResourceLocation TRIDENT_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_purple.png");
    private static final ResourceLocation TRIDENT_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_red.png");
    private static final ResourceLocation TRIDENT_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_teal.png");
    private static final ResourceLocation TRIDENT_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_white.png");
    private static final ResourceLocation TRIDENT_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_second/trilobite_trident_second_yellow.png");

    private static final ResourceLocation MOON_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_blue.png");
    private static final ResourceLocation MOON_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_brown.png");
    private static final ResourceLocation MOON_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_green.png");
    private static final ResourceLocation MOON_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_light_blue.png");
    private static final ResourceLocation MOON_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_orange.png");
    private static final ResourceLocation MOON_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_pink.png");
    private static final ResourceLocation MOON_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_purple.png");
    private static final ResourceLocation MOON_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_red.png");
    private static final ResourceLocation MOON_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_teal.png");
    private static final ResourceLocation MOON_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_white.png");
    private static final ResourceLocation MOON_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_second/trilobite_moon_second_yellow.png");

    private static final ResourceLocation SPIKY_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_blue.png");
    private static final ResourceLocation SPIKY_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_brown.png");
    private static final ResourceLocation SPIKY_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_green.png");
    private static final ResourceLocation SPIKY_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_light_blue.png");
    private static final ResourceLocation SPIKY_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_orange.png");
    private static final ResourceLocation SPIKY_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_pink.png");
    private static final ResourceLocation SPIKY_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_purple.png");
    private static final ResourceLocation SPIKY_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_red.png");
    private static final ResourceLocation SPIKY_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_teal.png");
    private static final ResourceLocation SPIKY_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_white.png");
    private static final ResourceLocation SPIKY_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_second/trilobite_spiky_second_yellow.png");

    private static final ResourceLocation ITTY_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_blue.png");
    private static final ResourceLocation ITTY_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_brown.png");
    private static final ResourceLocation ITTY_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_green.png");
    private static final ResourceLocation ITTY_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_light_blue.png");
    private static final ResourceLocation ITTY_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_orange.png");
    private static final ResourceLocation ITTY_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_pink.png");
    private static final ResourceLocation ITTY_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_purple.png");
    private static final ResourceLocation ITTY_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_red.png");
    private static final ResourceLocation ITTY_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_teal.png");
    private static final ResourceLocation ITTY_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_white.png");
    private static final ResourceLocation ITTY_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_second/trilobite_itty_second_yellow.png");

    private static final ResourceLocation FAT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_blue.png");
    private static final ResourceLocation FAT_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_brown.png");
    private static final ResourceLocation FAT_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_green.png");
    private static final ResourceLocation FAT_LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_light_blue.png");
    private static final ResourceLocation FAT_ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_orange.png");
    private static final ResourceLocation FAT_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_pink.png");
    private static final ResourceLocation FAT_PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_purple.png");
    private static final ResourceLocation FAT_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_red.png");
    private static final ResourceLocation FAT_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_teal.png");
    private static final ResourceLocation FAT_WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_white.png");
    private static final ResourceLocation FAT_YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_second/trilobite_fat_second_yellow.png");

    private static final ResourceLocation MODEL_CURLY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_curly.geo.json");
    private static final ResourceLocation MODEL_TRIDENT = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_trident.geo.json");
    private static final ResourceLocation MODEL_MOON = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_moon.geo.json");
    private static final ResourceLocation MODEL_SPIKY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_spiky.geo.json");
    private static final ResourceLocation MODEL_ITTY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_itty.geo.json");
    private static final ResourceLocation MODEL_FAT = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/trilobite/trilobite_fat.geo.json");

    public TrilobiteLayer(GeoRenderer<TrilobiteEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, TrilobiteEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        RenderType cameo;
        ResourceLocation trilobiteModel;

        switch (entityLivingBaseIn.getVariantModel()) {
            case 1:
                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(TRIDENT_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(TRIDENT_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(TRIDENT_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(TRIDENT_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(TRIDENT_PINK);
                    case 6 -> cameo = RenderType.entityCutout(TRIDENT_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(TRIDENT_RED);
                    case 8 -> cameo = RenderType.entityCutout(TRIDENT_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(TRIDENT_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(TRIDENT_YELLOW);
                    default -> cameo = RenderType.entityCutout(TRIDENT_BROWN);
                }
                trilobiteModel = MODEL_TRIDENT;
                break;

            case 2:

                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(MOON_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(MOON_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(MOON_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(MOON_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(MOON_PINK);
                    case 6 -> cameo = RenderType.entityCutout(MOON_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(MOON_RED);
                    case 8 -> cameo = RenderType.entityCutout(MOON_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(MOON_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(MOON_YELLOW);
                    default -> cameo = RenderType.entityCutout(MOON_BROWN);
                }
                trilobiteModel = MODEL_MOON;
                break;

            case 3:

                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(SPIKY_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(SPIKY_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(SPIKY_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(SPIKY_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(SPIKY_PINK);
                    case 6 -> cameo = RenderType.entityCutout(SPIKY_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(SPIKY_RED);
                    case 8 -> cameo = RenderType.entityCutout(SPIKY_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(SPIKY_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(SPIKY_YELLOW);
                    default -> cameo = RenderType.entityCutout(SPIKY_BROWN);
                }
                trilobiteModel = MODEL_SPIKY;
                break;

            case 4:

                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(ITTY_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(ITTY_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(ITTY_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(ITTY_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(ITTY_PINK);
                    case 6 -> cameo = RenderType.entityCutout(ITTY_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(ITTY_RED);
                    case 8 -> cameo = RenderType.entityCutout(ITTY_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(ITTY_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(ITTY_YELLOW);
                    default -> cameo = RenderType.entityCutout(ITTY_BROWN);
                }
                trilobiteModel = MODEL_ITTY;
                break;

            case 5:

                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(FAT_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(FAT_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(FAT_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(FAT_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(FAT_PINK);
                    case 6 -> cameo = RenderType.entityCutout(FAT_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(FAT_RED);
                    case 8 -> cameo = RenderType.entityCutout(FAT_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(FAT_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(FAT_YELLOW);
                    default -> cameo = RenderType.entityCutout(FAT_BROWN);
                }
                trilobiteModel = MODEL_FAT;
                break;

            default:
                switch (entityLivingBaseIn.getVariantSecondColor()){
                    case 1 -> cameo = RenderType.entityCutout(CURLY_BLUE);
                    case 2 -> cameo = RenderType.entityCutout(CURLY_GREEN);
                    case 3 -> cameo = RenderType.entityCutout(CURLY_LIGHT_BLUE);
                    case 4 -> cameo = RenderType.entityCutout(CURLY_ORANGE);
                    case 5 -> cameo = RenderType.entityCutout(CURLY_PINK);
                    case 6 -> cameo = RenderType.entityCutout(CURLY_PURPLE);
                    case 7 -> cameo = RenderType.entityCutout(CURLY_RED);
                    case 8 -> cameo = RenderType.entityCutout(CURLY_TEAL);
                    case 9 -> cameo = RenderType.entityCutout(CURLY_WHITE);
                    case 10 -> cameo = RenderType.entityCutout(CURLY_YELLOW);
                    default -> cameo = RenderType.entityCutout(CURLY_BROWN);
                }
                trilobiteModel = MODEL_CURLY;
                break;
        }

        this.getRenderer().reRender(this.getGeoModel().getBakedModel(trilobiteModel), poseStack, bufferSource, entityLivingBaseIn, renderType,
                bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

    }

}
