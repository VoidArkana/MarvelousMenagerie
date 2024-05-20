package net.voidarkana.marvelous_menagerie.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.DODO_DNA);
        simpleItem(ModItems.ELEPHANT_BIRD_DNA);
        simpleItem(ModItems.STELLER_SEA_COW_DNA);
        simpleItem(ModItems.THYLACINE_DNA);
        simpleItem(ModItems.SIGILLARIA_DNA);
        simpleItem(ModItems.EGG_SHELL_FRAGMENT);

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

        withExistingParent(ModItems.DODO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
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
}
