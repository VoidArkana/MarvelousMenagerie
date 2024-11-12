package net.voidarkana.marvelous_menagerie.client.renderers.block;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.voidarkana.marvelous_menagerie.client.models.block.CharniaBlockModel;
import net.voidarkana.marvelous_menagerie.common.block.entity.CharniaBlockEntity;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class CharniaBlockRenderer extends GeoBlockRenderer<CharniaBlockEntity> {

    public CharniaBlockRenderer(BlockEntityRendererProvider.Context context) {
        super(new CharniaBlockModel());
    }

}
