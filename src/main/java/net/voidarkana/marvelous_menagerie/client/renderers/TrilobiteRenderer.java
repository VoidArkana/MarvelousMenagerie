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

import java.util.Objects;

public class TrilobiteRenderer extends GeoEntityRenderer<TrilobiteEntity> {

//    private static final ResourceLocation CURLY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_black.png");
//    private static final ResourceLocation CURLY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_blue.png");
//    private static final ResourceLocation CURLY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_brown.png");
//    private static final ResourceLocation CURLY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_green.png");
//    private static final ResourceLocation CURLY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_pink.png");
//    private static final ResourceLocation CURLY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_red.png");
//    private static final ResourceLocation CURLY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_curly_base/trilobite_curly_base_teal.png");
//
//    private static final ResourceLocation TRIDENT_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_black.png");
//    private static final ResourceLocation TRIDENT_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_blue.png");
//    private static final ResourceLocation TRIDENT_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_brown.png");
//    private static final ResourceLocation TRIDENT_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_green.png");
//    private static final ResourceLocation TRIDENT_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_pink.png");
//    private static final ResourceLocation TRIDENT_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_red.png");
//    private static final ResourceLocation TRIDENT_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_trident_base/trilobite_trident_base_teal.png");
//
//    private static final ResourceLocation MOON_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_black.png");
//    private static final ResourceLocation MOON_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_blue.png");
//    private static final ResourceLocation MOON_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_brown.png");
//    private static final ResourceLocation MOON_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_green.png");
//    private static final ResourceLocation MOON_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_pink.png");
//    private static final ResourceLocation MOON_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_red.png");
//    private static final ResourceLocation MOON_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_moon_base/trilobite_moon_base_teal.png");
//
//    private static final ResourceLocation SPIKY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_black.png");
//    private static final ResourceLocation SPIKY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_blue.png");
//    private static final ResourceLocation SPIKY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_brown.png");
//    private static final ResourceLocation SPIKY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_green.png");
//    private static final ResourceLocation SPIKY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_pink.png");
//    private static final ResourceLocation SPIKY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_red.png");
//    private static final ResourceLocation SPIKY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_spiky_base/trilobite_spiky_base_teal.png");
//
//    private static final ResourceLocation ITTY_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_black.png");
//    private static final ResourceLocation ITTY_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_blue.png");
//    private static final ResourceLocation ITTY_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_brown.png");
//    private static final ResourceLocation ITTY_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_green.png");
//    private static final ResourceLocation ITTY_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_pink.png");
//    private static final ResourceLocation ITTY_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_red.png");
//    private static final ResourceLocation ITTY_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_itty_base/trilobite_itty_base_teal.png");
//
//    private static final ResourceLocation FAT_BASE_BLACK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_black.png");
//    private static final ResourceLocation FAT_BASE_BLUE = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_blue.png");
//    private static final ResourceLocation FAT_BASE_BROWN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_brown.png");
//    private static final ResourceLocation FAT_BASE_GREEN = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_green.png");
//    private static final ResourceLocation FAT_BASE_PINK = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_pink.png");
//    private static final ResourceLocation FAT_BASE_RED = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_red.png");
//    private static final ResourceLocation FAT_BASE_TEAL = new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_fat_base/trilobite_fat_base_teal.png");

    public TrilobiteRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new TrilobiteModel());
        this.addRenderLayer(new TrilobiteLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(TrilobiteEntity entity) {
        if (entity.isLGBTrilo()){
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/lgbtrilos/"+entity.getModelName()+"/trilobite_"+entity.getModelName()+"_"+entity.getTriloName()+".png");
        }
        else{
            return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/trilobite/trilobite_"+entity.getModelName()+"_base/trilobite_"+entity.getModelName()+"_base_"+entity.getMainColorName()+".png");
        }
    }

    @Override
    public void render(TrilobiteEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLightIn);
    }
}
