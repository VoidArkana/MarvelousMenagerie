package net.voidarkana.marvelous_menagerie.item;

import com.peeko32213.unusualprehistory.common.item.AnimalAttacherItem;
import com.peeko32213.unusualprehistory.common.item.ItemModFishBucket;
import com.peeko32213.unusualprehistory.core.registry.UPEntities;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.item.custom.EggInShellFoodItem;
import net.voidarkana.marvelous_menagerie.item.custom.SeaCowMilkBucketItem;
import net.voidarkana.marvelous_menagerie.item.custom.SinglePieceEffectArmorItem;
import net.voidarkana.marvelous_menagerie.item.custom.StackableBowlFoodItem;
import net.voidarkana.marvelous_menagerie.util.ModTags;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MOD_ID);

    //dodo items
    public static final RegistryObject<Item> DODO_DNA = ITEMS.register("dodo_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DODO_SPAWN_EGG = ITEMS.register("dodo_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.DODO, 0x7f929a, 0x374454, new Item.Properties()));

    //thylacine items
    public static final RegistryObject<Item> THYLACINE_DNA = ITEMS.register("thylacine_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> THYLA_EMBRYO = ITEMS.register("thylacine_embryo",
            ()-> new AnimalAttacherItem(new Item.Properties().stacksTo(16), ModTags.EntityTypes.THYLA_EMBRYO_ATTACH_TO,
                    ModEntities.THYLACINE, 1000));
    public static final RegistryObject<Item> THYLA_SPAWN_EGG = ITEMS.register("thylacine_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.THYLACINE, 0xe6a25f, 0x443429, new Item.Properties()));

    //elephant bird items
    public static final RegistryObject<Item> ELEPHANT_BIRD_DNA = ITEMS.register("elephant_bird_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ELE_SPAWN_EGG = ITEMS.register("elephant_bird_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.ELEPHANT_BIRD, 0x53423c, 0x322820, new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_ELEPHANT_EGG = ITEMS.register("cracked_elephant_egg",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EGG_SHELL_FRAGMENT = ITEMS.register("egg_shell_fragment",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BOILED_ELEPHANT_EGG = ITEMS.register("boiled_elephant_egg",
            ()-> new EggInShellFoodItem(new Item.Properties().craftRemainder(ModItems.EGG_SHELL_FRAGMENT.get()).food(ModFoods.BOILED_ELEPHANT_EGG)));
    public static final RegistryObject<Item> JUMBO_OMELETTE = ITEMS.register("jumbo_omelette",
            ()-> new Item(new Item.Properties().food(ModFoods.JUMBO_OMELETTE)));
    public static final RegistryObject<Item> EGG_SHELLMET = ITEMS.register("egg_shellmet",
            ()-> new SinglePieceEffectArmorItem(ModArmorMaterials.EGGSHELL, ArmorItem.Type.HELMET, new Item.Properties()));

    //steller's sea cow items
    public static final RegistryObject<Item> STELLER_SEA_COW_DNA = ITEMS.register("steller_sea_cow_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STELLER_SPAWN_EGG = ITEMS.register("steller_sea_cow_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.STELLER_SEA_COW, 0x2F292B, 0x1F191D, new Item.Properties()));
    public static final RegistryObject<Item> STELLER_EMBRYO = ITEMS.register("steller_sea_cow_embryo",
            ()-> new AnimalAttacherItem(new Item.Properties().stacksTo(16), ModTags.EntityTypes.STELLER_EMBRYO_ATTACH_TO,
                    ModEntities.BABY_STELLER_SEA_COW, 1000));
    public static final RegistryObject<Item> STELLER_MILK = ITEMS.register("steller_sea_cow_milk_bucket",
            ()-> new SeaCowMilkBucketItem((new Item.Properties()).craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> STELLER_ICE_CREAM = ITEMS.register("steller_ice_cream",
            ()-> new StackableBowlFoodItem(new Item.Properties().food(ModFoods.STELLER_ICE_CREAM).stacksTo(16)));
    public static final RegistryObject<Item> STELLER_BUCKET = ITEMS.register("steller_bucket", () -> {
        return new ItemModFishBucket(ModEntities.BABY_STELLER_SEA_COW, () -> {
            return Fluids.WATER;
        }, Items.BUCKET, false, (new Item.Properties()).stacksTo(1));
    });


    //sigillaria items
    public static final RegistryObject<Item> SIGILLARIA_DNA = ITEMS.register("sigillaria_dna",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SIGILLARIA_SIGN = ITEMS.register("sigillaria_sign",
            ()-> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.SIGILLARIA_SIGN.get(), ModBlocks.SIGILLARIA_WALL_SIGN.get()));
    public static final RegistryObject<Item> SIGILLARIA_HANGING_SIGN = ITEMS.register("sigillaria_hanging_sign",
            ()-> new HangingSignItem(ModBlocks.SIGILLARIA_HANGING_SIGN.get(), ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),new Item.Properties().stacksTo(16)));

    //cooksonia

    public static final RegistryObject<Item> COOKSONIA_DNA = ITEMS.register("cooksonia_dna",
            ()-> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
