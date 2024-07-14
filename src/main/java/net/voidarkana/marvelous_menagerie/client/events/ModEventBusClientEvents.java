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

//    @SubscribeEvent
//    public static void onClientSetup(FMLClientSetupEvent event) {
//
//    }

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
