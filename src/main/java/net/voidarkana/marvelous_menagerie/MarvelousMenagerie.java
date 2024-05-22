package net.voidarkana.marvelous_menagerie;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.block.entity.ModBlockEntities;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.entity.client.DodoRenderer;
import net.voidarkana.marvelous_menagerie.entity.client.ThylacineRenderer;
import net.voidarkana.marvelous_menagerie.entity.client.plant.PlantRenderer;
import net.voidarkana.marvelous_menagerie.item.ModCreativeModTabs;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import net.voidarkana.marvelous_menagerie.util.ModWoodTypes;
import net.voidarkana.marvelous_menagerie.worldgen.tree.ModFoliagePlacers;
import net.voidarkana.marvelous_menagerie.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MarvelousMenagerie.MOD_ID)
public class MarvelousMenagerie {
    public static final String MOD_ID = "marvelous_menagerie";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MarvelousMenagerie() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
;

    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SIGILLARIA_SAPLING.getId(), ModBlocks.POTTED_SIGILLARIA_SAPLING);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SIGILLARIA_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SIGILLARIA_LEAVES.get().asItem(), 0.4F);
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.DODO_DNA);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.SIGILLARIA);
            EntityRenderers.register(ModEntities.DODO.get(), DodoRenderer::new);
            EntityRenderers.register(ModEntities.THYLACINE.get(), ThylacineRenderer::new);
            EntityRenderers.register(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), PlantRenderer::new);
        }
    }
}
