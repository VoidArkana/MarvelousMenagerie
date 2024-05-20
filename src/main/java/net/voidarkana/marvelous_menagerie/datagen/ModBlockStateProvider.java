package net.voidarkana.marvelous_menagerie.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MarvelousMenagerie.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
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

}
