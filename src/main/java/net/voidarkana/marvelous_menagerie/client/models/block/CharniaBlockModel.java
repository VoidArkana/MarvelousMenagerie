package net.voidarkana.marvelous_menagerie.client.models.block;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.animal_block.CharniaBlock;
import net.voidarkana.marvelous_menagerie.common.block.entity.CharniaBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class CharniaBlockModel extends GeoModel<CharniaBlockEntity> {

    @Override
    public ResourceLocation getModelResource(CharniaBlockEntity animatable) {
        return switch (animatable.getBlockState().getValue(CharniaBlock.PICKLES)){
            default -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia_1.geo.json");
            case 2 -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia_2.geo.json");
            case 3 -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia_3.geo.json");
            case 4 -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "geo/plant/charnia_4.geo.json");
        };
    }

    @Override
    public ResourceLocation getTextureResource(CharniaBlockEntity animatable) {
        return switch (animatable.getBlockState().getValue(CharniaBlock.PICKLES)){
            case 2, 3, 4 -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/charnia_2.png");
            default -> new ResourceLocation(MarvelousMenagerie.MOD_ID, "textures/block/charnia.png");
        };
    }

    @Override
    public ResourceLocation getAnimationResource(CharniaBlockEntity animatable) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "animations/charnia.animation.json");
    }

    @Override
    public RenderType getRenderType(CharniaBlockEntity animatable, ResourceLocation texture) {
        return RenderType.entityCutout(texture);
    }
}
