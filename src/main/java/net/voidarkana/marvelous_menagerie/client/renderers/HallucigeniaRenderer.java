package net.voidarkana.marvelous_menagerie.client.renderers;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.voidarkana.marvelous_menagerie.client.models.HallucigeniaModel;
import net.voidarkana.marvelous_menagerie.common.entity.custom.HallucigeniaEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class HallucigeniaRenderer extends GeoEntityRenderer<HallucigeniaEntity> {

    public HallucigeniaRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new HallucigeniaModel());
    }
}
