package net.voidarkana.marvelous_menagerie.item;

import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<Item> DODO_DNA = ITEMS.register("dodo_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.DODO, 0x7f929a, 0x374454, new Item.Properties()));

    public static final RegistryObject<Item> ELEPHANT_BIRD_DNA = ITEMS.register("elephant_bird_dna",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STELLER_SEA_COW_DNA = ITEMS.register("steller_sea_cow_dna",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> THYLACINE_DNA = ITEMS.register("thylacine_dna",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EGG_SHELL_FRAGMENT = ITEMS.register("egg_shell_fragment",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIGILLARIA_DNA = ITEMS.register("sigillaria_dna",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SIGILLARIA_SIGN = ITEMS.register("sigillaria_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.SIGILLARIA_SIGN.get(), ModBlocks.SIGILLARIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> SIGILLARIA_HANGING_SIGN = ITEMS.register("sigillaria_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.SIGILLARIA_HANGING_SIGN.get(), ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
