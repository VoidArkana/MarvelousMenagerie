package net.voidarkana.marvelous_menagerie.common.item;

import com.peeko32213.unusualprehistory.common.item.AnimalAttacherItem;
import com.peeko32213.unusualprehistory.common.item.ItemModFishBucket;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.custom.*;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.util.ModTags;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MarvelousMenagerie.MOD_ID);


    public static final RegistryObject<Item> TAB_ICON = ITEMS.register("tab_icon",
            ()-> new Item(new Item.Properties()));

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
            ()-> new EggShellmetItem(ModArmorMaterials.EGGSHELL, ArmorItem.Type.HELMET, new Item.Properties()));


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

    //trilobite
    public static final RegistryObject<Item> TRILO_FLASK = ITEMS.register("trilo_flask",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRILOBITE_SPAWN_EGG = ITEMS.register("trilobite_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.TRILOBITE, 0x4F281E, 0x745750, new Item.Properties()));
    public static final RegistryObject<Item> TRILO_BUCKET = ITEMS.register("trilo_bucket",
            () -> new TriloBucketItem(ModEntities.TRILOBITE, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> TRILO_BITE = ITEMS.register("trilo_bite",
            ()-> new Item(new Item.Properties().food(ModFoods.TRILO_BITE)));

    //sacabambaspis

    public static final RegistryObject<Item> SACA_SPAWN_EGG = ITEMS.register("saca_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.SACABAMBASPIS, 0xDFD5B7, 0xAE9C79, new Item.Properties()));
    public static final RegistryObject<Item> SACA_BUCKET = ITEMS.register("saca_bucket",
            () -> new ItemModFishBucket(ModEntities.SACABAMBASPIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> SACABAMBASPIS = ITEMS.register("sacabambaspis",
            ()-> new Item(new Item.Properties().food(ModFoods.RAW_SACA)));
    public static final RegistryObject<Item> COOKED_SACA = ITEMS.register("cooked_saca",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKED_SACA)));
    public static final RegistryObject<Item> GOLDEN_SACA = ITEMS.register("golden_saca",
            ()-> new Item(new Item.Properties().food(ModFoods.GOLDEN_SACA)));
    public static final RegistryObject<Item> SACA_FLASK = ITEMS.register("saca_flask",
            ()-> new Item(new Item.Properties()));

    //prototaxites
    public static final RegistryObject<Item> PROTO_FLASK = ITEMS.register("proto_flask",
            ()-> new Item(new Item.Properties()));

    //dickinsonia
    public static final RegistryObject<Item> DICKINSONIA_FLASK = ITEMS.register("dickinsonia_flask",
            ()-> new Item(new Item.Properties()));

    //anomalocaris
    public static final RegistryObject<Item> CARIS_SPAWN_EGG = ITEMS.register("anomalocaris_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.ANOMALOCARIS, 0x984B4B, 0x722430, new Item.Properties()));
    public static final RegistryObject<Item> CARIS_BUCKET = ITEMS.register("anomalocaris_bucket",
            () -> new ItemModFishBucket(ModEntities.ANOMALOCARIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> BABY_CARIS_BUCKET = ITEMS.register("baby_anomalocaris_bucket",
            () -> new ItemModFishBucket(ModEntities.BABY_ANOMALOCARIS, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> CARIS_FLASK = ITEMS.register("anomalocaris_flask",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARIS_LENS = ITEMS.register("anomalocaris_lens",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARIS_SCUTE = ITEMS.register("anomalocaris_scute",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ANOMALOUS_GOGGLES = ITEMS.register("anomalous_goggles",
            ()-> new AnomalousGogglesItem(ModArmorMaterials.CARIS_SCUTE, ArmorItem.Type.HELMET, new Item.Properties()));

    //Ophthalmosaurus
    public static final RegistryObject<Item> OPHTHALMO_FLASK = ITEMS.register("ophthalmo_flask",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OPHTHALMO_SPAWN_EGG = ITEMS.register("ophthalmo_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.OPHTHALMO, 0x485060, 0x262F42, new Item.Properties()));
    public static final RegistryObject<Item> OPHTHALMO_ARMOR = ITEMS.register("ophthalmo_armor",
            ()-> new OphthalmoArmorItem(new Item.Properties().durability(300),
                    new ItemStack(ModItems.CARIS_SCUTE.get())));
    public static final RegistryObject<Item> BABY_OPHTHALMO_BUCKET = ITEMS.register("baby_ophthalmo_bucket",
            () -> new ItemModFishBucket(ModEntities.BABY_OPHTHALMO, () -> Fluids.WATER, Items.BUCKET, false,
                    (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> OPHTHALMO_EMBRYO = ITEMS.register("ophthalmo_embryo",
            ()-> new AnimalAttacherItem(new Item.Properties().stacksTo(16), ModTags.EntityTypes.OPHTHALMO_EMBRYO_ATTACH_TO,
                    ModEntities.BABY_OPHTHALMO, 1000));

    //Josephoartigasia
    public static final RegistryObject<Item> JOSEPHO_FLASK = ITEMS.register("josepho_flask",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JOSEPHO_SPAWN_EGG = ITEMS.register("josepho_spawn_egg",
            ()-> new ForgeSpawnEggItem(ModEntities.JOSEPHO, 0x8d6047, 0x31191b, new Item.Properties()));
    public static final RegistryObject<Item> JOSEPHO_EMBRYO = ITEMS.register("josepho_embryo",
            ()-> new AnimalAttacherItem(new Item.Properties().stacksTo(16), ModTags.EntityTypes.JOSEPHO_EMBRYO_ATTACH_TO,
                    ModEntities.JOSEPHO, 1000));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

}
