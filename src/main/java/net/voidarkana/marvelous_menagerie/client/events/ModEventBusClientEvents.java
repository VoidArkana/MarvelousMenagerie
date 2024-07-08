package net.voidarkana.marvelous_menagerie.client.events;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.block.entity.ModBlockEntities;
import net.voidarkana.marvelous_menagerie.client.renderers.*;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyAnomalocarisRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyOphthalmoRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyStellerRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.plant.CooksoniaRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.plant.DickinsoniaRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.plant.PrototaxitesRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.plant.SigillariaRenderer;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.util.ModWoodTypes;

import java.awt.event.KeyEvent;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        Sheets.addWoodType(ModWoodTypes.SIGILLARIA);
        EntityRenderers.register(ModEntities.DODO.get(), DodoRenderer::new);
        EntityRenderers.register(ModEntities.THYLACINE.get(), ThylacineRenderer::new);
        EntityRenderers.register(ModEntities.ELEPHANT_BIRD.get(), ElephantBirdRenderer::new);
        EntityRenderers.register(ModEntities.STELLER_SEA_COW.get(), StellerRenderer::new);
        EntityRenderers.register(ModEntities.BABY_STELLER_SEA_COW.get(), BabyStellerRenderer::new);
        EntityRenderers.register(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), SigillariaRenderer::new);
        EntityRenderers.register(ModEntities.COOKSONIA_ENTITY.get(), CooksoniaRenderer::new);

        EntityRenderers.register(ModEntities.TRILOBITE.get(), TrilobiteRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.TRILO_EGGS.get(), RenderType.cutout());

        EntityRenderers.register(ModEntities.SACABAMBASPIS.get(), SacaRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.SACA_EGGS.get(), RenderType.cutout());

        EntityRenderers.register(ModEntities.PROTOTAXITES_ENTITY.get(), PrototaxitesRenderer::new);

        EntityRenderers.register(ModEntities.DICKINSONIA_ENTITY.get(), DickinsoniaRenderer::new);

        EntityRenderers.register(ModEntities.ANOMALOCARIS.get(), AnomalocarisRenderer::new);
        EntityRenderers.register(ModEntities.BABY_ANOMALOCARIS.get(), BabyAnomalocarisRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CARIS_EGGS.get(), RenderType.cutout());

        EntityRenderers.register(ModEntities.OPHTHALMO.get(), OphthalmoRenderer::new);
        EntityRenderers.register(ModEntities.BABY_OPHTHALMO.get(), BabyOphthalmoRenderer::new);

        EntityRenderers.register(ModEntities.JOSEPHO.get(), JosephoRenderer::new);
    }

    /*@SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(HippogryphModel.LAYER_LOCATION, HippogryphModel::createBodyLayer);
    }*/

    public static KeyMapping descendKey;

    @SubscribeEvent
    public static void registerKeyMappings(final RegisterKeyMappingsEvent event) {
        descendKey = create("ophthalmoSwimDown", KeyEvent.VK_X);

        event.register(descendKey);
    }

    private static KeyMapping create(String name, int key) {
        return new KeyMapping("key." + MarvelousMenagerie.MOD_ID + "." + name, key, "key.category." + MarvelousMenagerie.MOD_ID);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
