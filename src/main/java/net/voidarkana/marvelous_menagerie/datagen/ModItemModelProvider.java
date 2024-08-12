package net.voidarkana.marvelous_menagerie.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        simpleItem(ModItems.TAB_ICON);

        simpleItem(ModItems.DODO_DNA);
        simpleItem(ModItems.ELEPHANT_BIRD_DNA);
        simpleItem(ModItems.STELLER_SEA_COW_DNA);
        simpleItem(ModItems.THYLACINE_DNA);
        simpleItem(ModItems.SIGILLARIA_DNA);
        simpleItem(ModItems.COOKSONIA_DNA);

        simpleItem(ModItems.THYLA_EMBRYO);
        simpleItem(ModItems.STELLER_EMBRYO);

        simpleItem(ModItems.BOILED_ELEPHANT_EGG);
        simpleItem(ModItems.JUMBO_OMELETTE);
        simpleItem(ModItems.CRACKED_ELEPHANT_EGG);
        trimmedArmorItem(ModItems.EGG_SHELLMET);

        simpleItem(ModItems.EGG_SHELL_FRAGMENT);

        simpleItem(ModItems.STELLER_MILK);
        simpleItem(ModItems.STELLER_ICE_CREAM);
        simpleItem(ModItems.STELLER_BUCKET);

        simpleBlockItem(ModBlocks.SIGILLARIA_DOOR);
        trapdoorItem(ModBlocks.SIGILLARIA_TRAPDOOR);

        evenSimplerBlockItem(ModBlocks.SIGILLARIA_STAIRS);
        evenSimplerBlockItem(ModBlocks.SIGILLARIA_MOSAIC_STAIRS);
        evenSimplerBlockItem(ModBlocks.SIGILLARIA_SLAB);
        evenSimplerBlockItem(ModBlocks.SIGILLARIA_MOSAIC_SLAB);
        evenSimplerBlockItem(ModBlocks.SIGILLARIA_FENCE_GATE);
        evenSimplerBlockItem(ModBlocks.SIGILLARIA_PRESSURE_PLATE);

        fenceItem(ModBlocks.SIGILLARIA_FENCE, ModBlocks.SIGILLARIA_PLANKS);
        buttonItem(ModBlocks.SIGILLARIA_BUTTON, ModBlocks.SIGILLARIA_PLANKS);

        simpleItem(ModItems.SIGILLARIA_SIGN);
        simpleItem(ModItems.SIGILLARIA_HANGING_SIGN);

        saplingItem(ModBlocks.SIGILLARIA_SAPLING);
        saplingItem(ModBlocks.COOKSONIA);

        withExistingParent(ModItems.DODO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.THYLA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.ELE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.STELLER_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        withExistingParent(ModItems.TRILOBITE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.TRILO_FLASK);
        simpleItem(ModItems.TRILO_BUCKET);
        simpleItem(ModItems.TRILO_BITE);

        withExistingParent(ModItems.SACA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.SACA_FLASK);
        simpleItem(ModItems.SACA_BUCKET);
        simpleItem(ModItems.SACABAMBASPIS);
        simpleItem(ModItems.COOKED_SACA);
        simpleItem(ModItems.GOLDEN_SACA);

        simpleItem(ModItems.PROTO_FLASK);

        simpleItem(ModItems.DICKINSONIA_FLASK);

        withExistingParent(ModItems.CARIS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.CARIS_BUCKET);
        simpleItem(ModItems.BABY_CARIS_BUCKET);
        simpleItem(ModItems.CARIS_FLASK);
        simpleItem(ModItems.CARIS_SCUTE);
        simpleItem(ModItems.CARIS_LENS);
        simpleItem(ModItems.ANOMALOUS_GOGGLES);
        simpleItem(ModItems.OPHTHALMO_ARMOR);

        withExistingParent(ModItems.OPHTHALMO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.BABY_OPHTHALMO_BUCKET);
        simpleItem(ModItems.OPHTHALMO_FLASK);
        simpleItem(ModItems.OPHTHALMO_EMBRYO);

        withExistingParent(ModItems.JOSEPHO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.JOSEPHO_FLASK);
        simpleItem(ModItems.JOSEPHO_EMBRYO);

        withExistingParent(ModItems.PIKAIA_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.PIKAIA_BUCKET);
        simpleItem(ModItems.PIKAIA_FLASK);
    }



    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MarvelousMenagerie.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(MarvelousMenagerie.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MarvelousMenagerie.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(MarvelousMenagerie.MOD_ID,"block/" + item.getId().getPath()));
    }


    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = MarvelousMenagerie.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
