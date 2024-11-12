package net.voidarkana.marvelous_menagerie;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.block.entity.ModBlockEntities;
import net.voidarkana.marvelous_menagerie.client.ClientProxy;
import net.voidarkana.marvelous_menagerie.common.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.common.effect.potion.ModPotionRecipes;
import net.voidarkana.marvelous_menagerie.common.effect.potion.ModPotions;
import net.voidarkana.marvelous_menagerie.common.enchantment.ModEnchantmentsClass;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.event.ModEventBusEvents;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
import net.voidarkana.marvelous_menagerie.common.CommonProxy;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModFoliagePlacers;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.ModTrunkPlacerTypes;
import net.voidarkana.marvelous_menagerie.common.entity.MarvelousEntityPlacement;
import net.voidarkana.marvelous_menagerie.util.config.CommonConfig;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(MarvelousMenagerie.MOD_ID)
public class MarvelousMenagerie {

    public static CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    public static final String MOD_ID = "marvelous_menagerie";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MarvelousMenagerie() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);

        GeckoLib.initialize();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModEntities.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEffects.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModEnchantmentsClass.register(modEventBus);

        ModPotions.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC,
                "marvelous_menagerie.toml");

        MinecraftForge.EVENT_BUS.register(new ModEventBusEvents());

        PROXY.commonInit();
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{

            BrewingRecipeRegistry.addRecipe(new ModPotionRecipes(Potions.AWKWARD,
                    ModItems.HALLUCIGENIC_SLIME.get(), ModPotions.HALLUCIGENIA_EXTRACT.get()));

            MarvelousEntityPlacement.entityPlacement();

            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.SIGILLARIA_SAPLING.getId(), ModBlocks.POTTED_SIGILLARIA_SAPLING);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SIGILLARIA_SAPLING.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.SIGILLARIA_LEAVES.get().asItem(), 0.4F);

            ComposterBlock.COMPOSTABLES.put(ModBlocks.COOKSONIA.get().asItem(), 0.4F);

            ComposterBlock.COMPOSTABLES.put(ModBlocks.PROTOTAXITES.get().asItem(), 0.4F);
            ComposterBlock.COMPOSTABLES.put(ModBlocks.PROTOTAXITES_BLOCK.get().asItem(), 0.8F);
        });


    }

    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) { }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

    }
}
