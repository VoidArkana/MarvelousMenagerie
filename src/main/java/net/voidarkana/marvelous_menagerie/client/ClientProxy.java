package net.voidarkana.marvelous_menagerie.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.client.renderers.plant.*;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.client.events.ModClientEvents;
import net.voidarkana.marvelous_menagerie.client.renderers.*;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyAnomalocarisRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyOphthalmoRenderer;
import net.voidarkana.marvelous_menagerie.client.renderers.baby.BabyStellerRenderer;
import net.voidarkana.marvelous_menagerie.common.CommonProxy;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.util.ModWoodTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientProxy extends CommonProxy {
    public static List<UUID> blockedEntityRenders = new ArrayList<>();

    public void commonInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }

    public void clientInit() {
        MinecraftForge.EVENT_BUS.register(new ModClientEvents());

        Sheets.addWoodType(ModWoodTypes.SIGILLARIA);
        EntityRenderers.register(ModEntities.DODO.get(), DodoRenderer::new);
        EntityRenderers.register(ModEntities.THYLACINE.get(), ThylacineRenderer::new);
        EntityRenderers.register(ModEntities.ELEPHANT_BIRD.get(), ElephantBirdRenderer::new);
        EntityRenderers.register(ModEntities.STELLER_SEA_COW.get(), StellerRenderer::new);
        EntityRenderers.register(ModEntities.BABY_STELLER_SEA_COW.get(), BabyStellerRenderer::new);
        EntityRenderers.register(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), PlantRenderer::new);
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

        EntityRenderers.register(ModEntities.PIKAIA.get(), PikaiaRenderer::new);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PIKAIA_EGGS.get(), RenderType.translucent());

        EntityRenderers.register(ModEntities.WIWAXIA_ENTITY.get(), WiwaxiaRenderer::new);

    }

    public void blockRenderingEntity(UUID id) {
        blockedEntityRenders.add(id);
    }

    public void releaseRenderingEntity(UUID id) {
        blockedEntityRenders.remove(id);
    }

    public boolean isFirstPersonPlayer(Entity entity) {
        return entity.equals(Minecraft.getInstance().cameraEntity) && Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }


}
