package net.voidarkana.marvelous_menagerie.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MARVELOUS_MENAGERIE_TAB =
            CREATIVE_MODE_TABS.register("marvelous_menagerie_tab", ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DODO_DNA.get()))
                    .title(Component.translatable("creativetab.marvelous_menagerie_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DODO_DNA.get());
                        output.accept(ModItems.ELEPHANT_BIRD_DNA.get());
                        output.accept(ModItems.STELLER_SEA_COW_DNA.get());
                        output.accept(ModItems.THYLACINE_DNA.get());
                        output.accept(ModItems.SIGILLARIA_DNA.get());


                        output.accept(ModBlocks.DODO_EGGS.get());
                        output.accept(ModItems.THYLA_EMBRYO.get());
                        output.accept(ModItems.EGG_SHELL_FRAGMENT.get());

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

                        output.accept(ModItems.DODO_SPAWN_EGG.get());
                        output.accept(ModItems.THYLA_SPAWN_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
