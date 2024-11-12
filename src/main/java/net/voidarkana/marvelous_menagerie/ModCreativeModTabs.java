package net.voidarkana.marvelous_menagerie;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_TAB =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CARIS_BUCKET.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CARIS_FLASK.get());
                        output.accept(ModItems.ARANDASPIS_FLASK.get());
                        output.accept(ModItems.DODO_DNA.get());
                        output.accept(ModItems.ELEPHANT_BIRD_DNA.get());
                        output.accept(ModItems.HALLUCIGENIA_FLASK.get());
                        output.accept(ModItems.JOSEPHO_FLASK.get());
                        output.accept(ModItems.OPHTHALMO_FLASK.get());
                        output.accept(ModItems.PIKAIA_FLASK.get());
                        output.accept(ModItems.SACA_FLASK.get());
                        output.accept(ModItems.STELLER_SEA_COW_DNA.get());
                        output.accept(ModItems.THYLACINE_DNA.get());
                        output.accept(ModItems.TRILO_FLASK.get());
                        output.accept(ModItems.COOKSONIA_DNA.get());
                        output.accept(ModItems.DICKINSONIA_FLASK.get());
                        output.accept(ModItems.PROTO_FLASK.get());
                        output.accept(ModItems.SIGILLARIA_DNA.get());
                        output.accept(ModItems.WIWAXIA_FLASK.get());

                        output.accept(ModBlocks.CARIS_EGGS.get());
                        output.accept(ModBlocks.ARANDASPIS_EGGS.get());
                        output.accept(ModBlocks.DODO_EGGS.get());
                        output.accept(ModBlocks.ELE_EGG.get());
                        output.accept(ModBlocks.HALLU_EGGS.get());
                        output.accept(ModBlocks.PIKAIA_EGGS.get());
                        output.accept(ModBlocks.SACA_EGGS.get());
                        output.accept(ModBlocks.TRILO_EGGS.get());
                        output.accept(ModItems.JOSEPHO_EMBRYO.get());
                        output.accept(ModItems.OPHTHALMO_EMBRYO.get());
                        output.accept(ModItems.THYLA_EMBRYO.get());
                        output.accept(ModItems.STELLER_EMBRYO.get());

                        output.accept(ModItems.CARIS_BUCKET.get());
                        output.accept(ModItems.CARIS_LENS.get());
                        output.accept(ModItems.CARIS_SCUTE.get());
                        output.accept(ModItems.ANOMALOUS_GOGGLES.get());
                        output.accept(ModItems.OPHTHALMO_ARMOR.get());

                        output.accept(ModItems.ARANDASPIS_BUCKET.get());

                        output.accept(ModItems.CRACKED_ELEPHANT_EGG.get());
                        output.accept(ModItems.BOILED_ELEPHANT_EGG.get());
                        output.accept(ModItems.JUMBO_OMELETTE.get());
                        output.accept(ModItems.EGG_SHELL_FRAGMENT.get());
                        output.accept(ModItems.EGG_SHELLMET.get());

                        output.accept(ModItems.HALLUCIGENIA_BUCKET.get());
                        output.accept(ModItems.MAGIC_ROLL.get());

                        output.accept(ModItems.BABY_OPHTHALMO_BUCKET.get());

                        output.accept(ModItems.PIKAIA_BUCKET.get());

                        output.accept(ModItems.SACA_BUCKET.get());
                        output.accept(ModItems.SACABAMBASPIS.get());
                        output.accept(ModItems.COOKED_SACA.get());
                        output.accept(ModItems.GOLDEN_SACA.get());

                        output.accept(ModItems.STELLER_BUCKET.get());
                        output.accept(ModItems.STELLER_MILK.get());
                        output.accept(ModItems.STELLER_ICE_CREAM.get());

                        output.accept(ModItems.TRILO_BUCKET.get());
                        output.accept(ModItems.TRILO_BITE.get());


                        output.accept(ModBlocks.CHARNIA.get());

                        output.accept(ModBlocks.COOKSONIA.get());

                        output.accept(ModBlocks.DICKINSONIA.get());

                        output.accept(ModBlocks.WIWAXIA.get());


                        output.accept(ModBlocks.PROTOTAXITES_PLANKS.get());

                        output.accept(ModBlocks.PROTOTAXITES_BLOCK.get());

                        output.accept(ModBlocks.PROTOTAXITES_STAIRS.get());
                        output.accept(ModBlocks.PROTOTAXITES_SLAB.get());
                        output.accept(ModBlocks.PROTOTAXITES_FENCE.get());
                        output.accept(ModBlocks.PROTOTAXITES_FENCE_GATE.get());
                        output.accept(ModBlocks.PROTOTAXITES_DOOR.get());
                        output.accept(ModBlocks.PROTOTAXITES_TRAPDOOR.get());
                        output.accept(ModBlocks.PROTOTAXITES_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.PROTOTAXITES_BUTTON.get());

                        output.accept(ModBlocks.PROTOTAXITES_MOSAIC.get());
                        output.accept(ModBlocks.PROTOTAXITES_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.PROTOTAXITES_MOSAIC_SLAB.get());
                        output.accept(ModItems.PROTOTAXITES_SIGN.get());
                        output.accept(ModItems.PROTOTAXITES_HANGING_SIGN.get());

                        output.accept(ModBlocks.PROTOTAXITES.get());



                        output.accept(ModBlocks.SIGILLARIA_PLANKS.get());

                        output.accept(ModBlocks.SIGILLARIA_STEM.get());
                        output.accept(ModBlocks.STRIPPED_SIGILLARIA_STEM.get());
                        output.accept(ModBlocks.SIGILLARIA_WOOD.get());
                        output.accept(ModBlocks.STRIPPED_SIGILLARIA_WOOD.get());

                        output.accept(ModBlocks.SIGILLARIA_STAIRS.get());
                        output.accept(ModBlocks.SIGILLARIA_SLAB.get());
                        output.accept(ModBlocks.SIGILLARIA_FENCE.get());
                        output.accept(ModBlocks.SIGILLARIA_FENCE_GATE.get());
                        output.accept(ModBlocks.SIGILLARIA_DOOR.get());
                        output.accept(ModBlocks.SIGILLARIA_TRAPDOOR.get());
                        output.accept(ModBlocks.SIGILLARIA_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.SIGILLARIA_BUTTON.get());

                        output.accept(ModBlocks.SIGILLARIA_MOSAIC.get());
                        output.accept(ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.SIGILLARIA_MOSAIC_SLAB.get());
                        output.accept(ModItems.SIGILLARIA_SIGN.get());
                        output.accept(ModItems.SIGILLARIA_HANGING_SIGN.get());
                        output.accept(ModBlocks.SIGILLARIA_LEAVES.get());
                        output.accept(ModBlocks.SIGILLARIA_SAPLING.get());



                        output.accept(ModBlocks.ZULOAGAE_PLANKS.get());

                        output.accept(ModBlocks.ZULOAGAE_BLOCK.get());
                        output.accept(ModBlocks.STRIPPED_ZULOAGAE_BLOCK.get());

                        output.accept(ModBlocks.ZULOAGAE_STAIRS.get());
                        output.accept(ModBlocks.ZULOAGAE_SLAB.get());
                        output.accept(ModBlocks.ZULOAGAE_FENCE.get());
                        output.accept(ModBlocks.ZULOAGAE_FENCE_GATE.get());
                        output.accept(ModBlocks.ZULOAGAE_DOOR.get());
                        output.accept(ModBlocks.ZULOAGAE_TRAPDOOR.get());
                        output.accept(ModBlocks.ZULOAGAE_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.ZULOAGAE_BUTTON.get());

                        output.accept(ModBlocks.ZULOAGAE_MOSAIC.get());
                        output.accept(ModBlocks.ZULOAGAE_MOSAIC_STAIRS.get());
                        output.accept(ModBlocks.ZULOAGAE_MOSAIC_SLAB.get());
                        output.accept(ModItems.ZULOAGAE_SIGN.get());
                        output.accept(ModItems.ZULOAGAE_HANGING_SIGN.get());



                        output.accept(ModItems.CARIS_SPAWN_EGG.get());
                        output.accept(ModItems.ARANDASPIS_SPAWN_EGG.get());
                        output.accept(ModItems.DODO_SPAWN_EGG.get());
                        output.accept(ModItems.ELE_SPAWN_EGG.get());
                        output.accept(ModItems.HALLUCIGENIA_SPAWN_EGG.get());
                        output.accept(ModItems.JOSEPHO_SPAWN_EGG.get());
                        output.accept(ModItems.OPHTHALMO_SPAWN_EGG.get());
                        output.accept(ModItems.PIKAIA_SPAWN_EGG.get());
                        output.accept(ModItems.SACA_SPAWN_EGG.get());
                        output.accept(ModItems.STELLER_SPAWN_EGG.get());
                        output.accept(ModItems.THYLA_SPAWN_EGG.get());
                        output.accept(ModItems.TRILOBITE_SPAWN_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
