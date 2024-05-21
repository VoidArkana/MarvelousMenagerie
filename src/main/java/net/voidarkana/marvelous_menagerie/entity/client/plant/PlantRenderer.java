package net.voidarkana.marvelous_menagerie.entity.client.plant;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.PlantEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PlantRenderer extends GeoEntityRenderer<PlantEntity> {

    public PlantRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PlantModel());
    }

    @Override
    public ResourceLocation getTextureLocation(PlantEntity entity) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/entity/plants/sigillaria_sapling.png");
    }

}
