package net.voidarkana.marvelous_menagerie.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MarvelousMenagerie.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        this.createEggDefaultMedium(ModBlocks.DODO_EGGS.get());
        this.createSingleEgg(ModBlocks.ELE_EGG.get());

        blockWithItem(ModBlocks.SIGILLARIA_PLANKS);
        stairsBlock(((StairBlock) ModBlocks.SIGILLARIA_STAIRS.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        slabBlock(((SlabBlock) ModBlocks.SIGILLARIA_SLAB.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        buttonBlock(((ButtonBlock) ModBlocks.SIGILLARIA_BUTTON.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.SIGILLARIA_PRESSURE_PLATE.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        fenceBlock(((FenceBlock) ModBlocks.SIGILLARIA_FENCE.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.SIGILLARIA_FENCE_GATE.get()), blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.SIGILLARIA_DOOR.get()), modLoc("block/sigillaria_door_bottom"), modLoc("block/sigillaria_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.SIGILLARIA_TRAPDOOR.get()), modLoc("block/sigillaria_trapdoor"), true, "cutout");

        blockWithItem(ModBlocks.SIGILLARIA_MOSAIC);
        stairsBlock(((StairBlock) ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get()), blockTexture(ModBlocks.SIGILLARIA_MOSAIC.get()));
        slabBlock(((SlabBlock) ModBlocks.SIGILLARIA_MOSAIC_SLAB.get()), blockTexture(ModBlocks.SIGILLARIA_MOSAIC.get()), blockTexture(ModBlocks.SIGILLARIA_MOSAIC.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.SIGILLARIA_STEM.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SIGILLARIA_WOOD.get()), blockTexture(ModBlocks.SIGILLARIA_STEM.get()), blockTexture(ModBlocks.SIGILLARIA_STEM.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SIGILLARIA_STEM.get()), blockTexture(ModBlocks.STRIPPED_SIGILLARIA_STEM.get()), new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/stripped_sigillaria_stem_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SIGILLARIA_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SIGILLARIA_STEM.get()), blockTexture(ModBlocks.STRIPPED_SIGILLARIA_STEM.get()));
        blockItem(ModBlocks.SIGILLARIA_STEM);
        blockItem(ModBlocks.STRIPPED_SIGILLARIA_STEM);
        blockItem(ModBlocks.SIGILLARIA_WOOD);
        blockItem(ModBlocks.STRIPPED_SIGILLARIA_WOOD);

        signBlock(((StandingSignBlock) ModBlocks.SIGILLARIA_SIGN.get()), ((WallSignBlock) ModBlocks.SIGILLARIA_WALL_SIGN.get()),
                blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));
        hangingSignBlock(ModBlocks.SIGILLARIA_HANGING_SIGN.get(), ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                blockTexture(ModBlocks.SIGILLARIA_PLANKS.get()));

        leavesBlock(ModBlocks.SIGILLARIA_LEAVES);

        simpleBlockWithItem(ModBlocks.SIGILLARIA_SAPLING.get(), models().cross(blockTexture(ModBlocks.SIGILLARIA_SAPLING.get()).getPath(),
                blockTexture(ModBlocks.SIGILLARIA_SAPLING.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_SIGILLARIA_SAPLING.get(), models().singleTexture("potted_sigillaria_sapling", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.SIGILLARIA_SAPLING.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.COOKSONIA.get(), models().cross(blockTexture(ModBlocks.COOKSONIA.get()).getPath(),
                blockTexture(ModBlocks.COOKSONIA.get())).renderType("cutout"));

        this.createFlatWaterEgg(ModBlocks.TRILO_EGGS.get());

        this.createFlatWaterEgg(ModBlocks.SACA_EGGS.get());

        this.createFlatWaterEgg(ModBlocks.CARIS_EGGS.get());
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(MarvelousMenagerie.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject
                .get()));
    }

    private String getName(Block block) {
        return this.key(block).toString().replace(MarvelousMenagerie.MOD_ID+":", "");
    }

    public void createSingleEgg(Block block) {
        this.createSingleEgg(block, "");
        this.createSlightltyCrackedSingleEgg(block, "");
        this.createVeryCrackedSingleEgg(block, "");
        this.eggBlockSingleVariantY(block);
        this.singleTex(block);
    }

    public ModelFile createSingleEgg(Block block, String modifier) {
        String baseName = this.getName(block);
        return this.models().singleTexture("block/eggs/" + modifier + baseName.replace(MarvelousMenagerie.MOD_ID+":", ""), new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + modifier + baseName), this.blockTextureEggs(block));
    }

    public ModelFile createSlightltyCrackedSingleEgg(Block block, String modifier) {
        String baseName = this.getName(block);
        BlockModelProvider var10000 = this.models();
        String var10001 = "block/eggs/" + modifier + "slightly_cracked_" + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
        ResourceLocation var10002 = new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + baseName);
        String var10006 = this.getName(block);
        return var10000.singleTexture(var10001, var10002, new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/eggs/" + var10006 + "_slightly_cracked"));
    }

    public ModelFile createVeryCrackedSingleEgg(Block block, String modifier) {
        String baseName = this.getName(block);
        BlockModelProvider var10000 = this.models();
        String var10001 = "block/eggs/" + modifier + "very_cracked_" + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
        ResourceLocation var10002 = new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + baseName);
        String var10006 = this.getName(block);
        return var10000.singleTexture(var10001, var10002, new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/eggs/" + var10006 + "_very_cracked"));
    }

    private void eggBlockSingleVariantY(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            int eggs = (Integer)state.getValue(BlockStateProperties.EGGS);
            int hatch = (Integer)state.getValue(BlockStateProperties.HATCH);
            return this.createVariants(this.existingModel(this.createEggModelSingle(eggs, hatch, block)));
        }, new Property[0]);
    }

    private ConfiguredModel[] createVariants(ModelFile model) {
        List<ConfiguredModel> activeModels = new ArrayList();
        Iterator var3 = Arrays.asList(model).iterator();

        while(var3.hasNext()) {
            ModelFile modelFile = (ModelFile)var3.next();
            activeModels.add(new ConfiguredModel(modelFile, 0, 0, false));
            activeModels.add(new ConfiguredModel(modelFile, 0, 90, false));
            activeModels.add(new ConfiguredModel(modelFile, 0, 180, false));
            activeModels.add(new ConfiguredModel(modelFile, 0, 270, false));
        }

        return (ConfiguredModel[])Arrays.copyOfRange((ConfiguredModel[])activeModels.toArray(new ConfiguredModel[0]), 0, 4);
    }

    private String createEggModelSingle(int pHatchAmount, String pVariantName, String baseName) {
        String s = "eggs/" + pVariantName + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
        String var10000;
        switch (pHatchAmount) {
            case 1 -> var10000 = s;
            case 2 -> var10000 = s;
            case 3 -> var10000 = s;
            case 4 -> var10000 = s;
            default -> throw new UnsupportedOperationException();
        }

        return var10000;
    }

    private String createEggModelSingle(Integer pEgg, Integer pVariantId, Block block) {
        String var10000;
        switch (pVariantId) {
            case 0 -> var10000 = this.createEggModelSingle(pEgg, "", this.key(block).toString());
            case 1 -> var10000 = this.createEggModelSingle(pEgg, "slightly_cracked_", this.key(block).toString());
            case 2 -> var10000 = this.createEggModelSingle(pEgg, "very_cracked_", this.key(block).toString());
            default -> throw new UnsupportedOperationException();
        }

        return var10000;
    }

    public ModelFile existingModel(String path) {
        return new ModelFile.ExistingModelFile(this.resourceBlock(path), this.models().existingFileHelper);
    }

    public ResourceLocation resourceBlock(String path) {
        return new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/" + path);
    }
    public ResourceLocation blockTextureEggs(Block block) {
        ResourceLocation name = this.key(block);
        return new ResourceLocation(name.getNamespace(), "block/eggs/" + name.getPath());
    }

    private BlockModelBuilder singleTex(Block block) {
        String var10001 = this.getName(block);
        ResourceLocation[] var10002 = new ResourceLocation[1];
        String var10008 = this.getName(block);
        var10002[0] = new ResourceLocation(MarvelousMenagerie.MOD_ID, "item/" + var10008.replace("eggs", "egg"));
        return this.generated(var10001, var10002);
    }

    private BlockModelBuilder generated(String name, ResourceLocation... layers) {
        BlockModelBuilder builder = this.models().withExistingParent("item/" + name, "item/generated");

        for(int i = 0; i < layers.length; ++i) {
            builder = builder.texture("layer" + i, layers[i]);
        }

        return builder;
    }

    public void createEggDefaultMedium(Block block) {
        this.createEggDefaultMedium(block, "");
        this.createEggDefaultMedium(block, "two_");
        this.createEggDefaultMedium(block, "three_");
        this.createEggDefaultMedium(block, "four_");
        this.eggBlockVariantY(block);
        this.singleTex(block);
    }

    public void createEggDefaultMedium(Block block, String modifier) {
        this.createSingleEggDefault(block, modifier, "medium");
        this.createSlightltyCrackedSingleEggDefault(block, modifier, "medium");
        this.createVeryCrackedSingleEggDefault(block, modifier, "medium");
    }


    public ModelFile createSingleEggDefault(Block block, String modifier, String modifier2) {
        String baseName = this.getName(block);
        return this.models().singleTexture("block/eggs/" + modifier + baseName.replace(MarvelousMenagerie.MOD_ID+":", ""), new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + modifier + modifier2 + "_eggs"), this.blockTextureEggs(block));
    }

    public ModelFile createSlightltyCrackedSingleEggDefault(Block block, String modifier, String modifier2) {
        String baseName = this.getName(block);
        BlockModelProvider var10000 = this.models();
        String var10001 = "block/eggs/" + modifier + "slightly_cracked_" + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
        ResourceLocation var10002 = new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + modifier + modifier2 + "_eggs");
        String var10006 = this.getName(block);
        return var10000.singleTexture(var10001, var10002, new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/eggs/" + var10006 + "_slightly_cracked"));
    }

    public ModelFile createVeryCrackedSingleEggDefault(Block block, String modifier, String modifier2) {
        String baseName = this.getName(block);
        BlockModelProvider var10000 = this.models();
        String var10001 = "block/eggs/" + modifier + "very_cracked_" + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
        ResourceLocation var10002 = new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/template_eggs/template_" + modifier + modifier2 + "_eggs");
        String var10006 = this.getName(block);
        return var10000.singleTexture(var10001, var10002, new ResourceLocation(MarvelousMenagerie.MOD_ID, "block/eggs/" + var10006 + "_very_cracked"));
    }

    private void eggBlockVariantY(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            int eggs = (Integer)state.getValue(BlockStateProperties.EGGS);
            int hatch = (Integer)state.getValue(BlockStateProperties.HATCH);
            return this.createVariants(this.existingModel(this.createEggModel(eggs, hatch, block)));
        }, new Property[0]);
    }

    private String createEggModel(Integer pEgg, Integer pVariantId, Block block) {
        String var10000;
        switch (pVariantId) {
            case 0 -> var10000 = this.createEggModel(pEgg, "", this.key(block).toString());
            case 1 -> var10000 = this.createEggModel(pEgg, "slightly_cracked_", this.key(block).toString());
            case 2 -> var10000 = this.createEggModel(pEgg, "very_cracked_", this.key(block).toString());
            default -> throw new UnsupportedOperationException();
        }

        return var10000;
    }

    private String createEggModel(int pHatchAmount, String pVariantName, String baseName) {
        String var10000;
        switch (pHatchAmount) {
            case 1 -> var10000 = "eggs/" + pVariantName + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
            case 2 -> var10000 = "eggs/two_" + pVariantName + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
            case 3 -> var10000 = "eggs/three_" + pVariantName + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
            case 4 -> var10000 = "eggs/four_" + pVariantName + baseName.replace(MarvelousMenagerie.MOD_ID+":", "");
            default -> throw new UnsupportedOperationException();
        }
        return var10000;
    }

    public void createFlatWaterEgg(Block block) {
        this.createFlatWaterEgg(block, "");
        this.flatWaterEgg(block);
        this.singleTexWaterEgg(block);
    }

    public ModelFile createFlatWaterEgg(Block block, String modifier) {
        String baseName = this.getName(block);
        return this.models().singleTexture("block/eggs/" + modifier + baseName.replace("marvelous_menagerie:", ""), new ResourceLocation("marvelous_menagerie", "block/template_eggs/template_flat_water_egg"), this.blockTextureEggs(block));
    }

    private void flatWaterEgg(Block block) {
        this.getVariantBuilder(block).forAllStatesExcept((state) -> {
            ConfiguredModel.Builder var10000 = ConfiguredModel.builder();
            String var10002 = this.getName(block);
            return var10000.modelFile(this.existingModel("eggs/" + var10002)).build();
        }, new Property[0]);
    }

    private BlockModelBuilder singleTexWaterEgg(Block block) {
        String var10001 = this.getName(block);
        ResourceLocation[] var10002 = new ResourceLocation[1];
        String var10008 = this.getName(block);
        var10002[0] = new ResourceLocation("marvelous_menagerie", "item/" + var10008);
        return this.generated(var10001, var10002);
    }

    /*
    private void createPointedDripstone() {
        this.skipAutoItemBlock(Blocks.POINTED_DRIPSTONE);
        PropertyDispatch.C2<Direction, DripstoneThickness> c2 = PropertyDispatch.properties(BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS);

        for(DripstoneThickness dripstonethickness : DripstoneThickness.values()) {
            c2.select(Direction.UP, dripstonethickness, this.createPointedDripstoneVariant(Direction.UP, dripstonethickness));
        }

        for(DripstoneThickness dripstonethickness1 : DripstoneThickness.values()) {
            c2.select(Direction.DOWN, dripstonethickness1, this.createPointedDripstoneVariant(Direction.DOWN, dripstonethickness1));
        }

        this.blockStateOutput.accept(MultiVariantGenerator.multiVariant(Blocks.POINTED_DRIPSTONE).with(c2));
    }

    private Variant createPointedDripstoneVariant(Direction pDirection, DripstoneThickness pDripstoneThickness) {
        String s = "_" + pDirection.getSerializedName() + "_" + pDripstoneThickness.getSerializedName();
        TextureMapping texturemapping = TextureMapping.cross(TextureMapping.getBlockTexture(Blocks.POINTED_DRIPSTONE, s));
        return Variant.variant().with(VariantProperties.MODEL, ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(Blocks.POINTED_DRIPSTONE, s, texturemapping, this.modelOutput));
    }

    void skipAutoItemBlock(Block pBlock) {
        this.skippedAutoModelsOutput.accept(pBlock.asItem());
    }*/


}
