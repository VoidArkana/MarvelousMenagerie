package net.voidarkana.marvelous_menagerie.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.ThylacineEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;

public class ThylacineHandkerchiefLayer extends GeoRenderLayer<ThylacineEntity> {

    private static final ResourceLocation RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/red.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/orange.png");
    private static final ResourceLocation YELLOW = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/yellow.png");
    private static final ResourceLocation LIME = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/lime.png");
    private static final ResourceLocation GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/green.png");
    private static final ResourceLocation CYAN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/cyan.png");
    private static final ResourceLocation LIGHT_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/light_blue.png");
    private static final ResourceLocation BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/blue.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/purple.png");
    private static final ResourceLocation MAGENTA = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/magenta.png");
    private static final ResourceLocation PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/pink.png");
    private static final ResourceLocation BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/brown.png");
    private static final ResourceLocation BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/black.png");
    private static final ResourceLocation GRAY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/gray.png");
    private static final ResourceLocation LIGHT_GRAY = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/light_gray.png");
    private static final ResourceLocation WHITE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/thylacine/handkerchief/white.png");

    private static final ResourceLocation MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/thylacine.geo.json");
    private static final ResourceLocation BABY_MODEL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/baby_thylacine.geo.json");


    public ThylacineHandkerchiefLayer(GeoRenderer<ThylacineEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, ThylacineEntity entityLivingBaseIn, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {

        if (entityLivingBaseIn.hasHandkerchief()) {

            RenderType cameo;

             switch (entityLivingBaseIn.getHandkerchiefColor()) {
                case 1 -> cameo = RenderType.entityCutout(RED);
                case 2 -> cameo = RenderType.entityCutout(ORANGE);
                case 3 -> cameo = RenderType.entityCutout(YELLOW);
                case 4 -> cameo = RenderType.entityCutout(LIME);
                case 5 -> cameo = RenderType.entityCutout(GREEN);
                case 6 -> cameo = RenderType.entityCutout(CYAN);
                case 7 -> cameo = RenderType.entityCutout(LIGHT_BLUE);
                case 8 -> cameo = RenderType.entityCutout(BLUE);
                case 9 -> cameo = RenderType.entityCutout(PURPLE);
                case 10 -> cameo = RenderType.entityCutout(MAGENTA);
                case 11 -> cameo = RenderType.entityCutout(PINK);
                case 12 -> cameo = RenderType.entityCutout(BROWN);
                case 13 -> cameo = RenderType.entityCutout(BLACK);
                case 14 -> cameo = RenderType.entityCutout(GRAY);
                case 15 -> cameo = RenderType.entityCutout(LIGHT_GRAY);
                default -> cameo = RenderType.entityCutout(WHITE);
            };

             if (entityLivingBaseIn.isBaby()){
                 this.getRenderer().reRender(this.getGeoModel().getBakedModel(BABY_MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                         bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
             }else {
                 this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, entityLivingBaseIn, renderType,
                         bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
             }

        }


    }

}
