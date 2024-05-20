package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.datagen.UPRecipeProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import net.voidarkana.marvelous_menagerie.util.ModTags;

import java.util.function.Consumer;

public class ModRecipeProvider extends UPRecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Sigillaria woodset
        makePlanks(ModBlocks.SIGILLARIA_PLANKS, ModTags.Items.SIGILLARIA_LOG_ITEM).save(consumer);
        makeWood(ModBlocks.SIGILLARIA_WOOD, ModBlocks.SIGILLARIA_STEM).save(consumer);
        makeWood(ModBlocks.STRIPPED_SIGILLARIA_WOOD, ModBlocks.STRIPPED_SIGILLARIA_STEM).save(consumer);
        makeStairs(ModBlocks.SIGILLARIA_STAIRS, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeStairs(ModBlocks.SIGILLARIA_MOSAIC_STAIRS, ModBlocks.SIGILLARIA_MOSAIC).save(consumer);
        makeSlab(ModBlocks.SIGILLARIA_SLAB, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeSlab(ModBlocks.SIGILLARIA_MOSAIC_SLAB, ModBlocks.SIGILLARIA_MOSAIC).save(consumer);
        makeFence(ModBlocks.SIGILLARIA_FENCE, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeFenceGate(ModBlocks.SIGILLARIA_FENCE_GATE, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeDoor(ModBlocks.SIGILLARIA_DOOR, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeTrapdoor(ModBlocks.SIGILLARIA_TRAPDOOR, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makeButton(ModBlocks.SIGILLARIA_BUTTON, ModBlocks.SIGILLARIA_PLANKS).save(consumer);
        makePressurePlate(ModBlocks.SIGILLARIA_PRESSURE_PLATE, ModBlocks.SIGILLARIA_PLANKS).save(consumer);

        //Sigillaria mosaic
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SIGILLARIA_MOSAIC.get(), 4)
                .pattern("S")
                .pattern("S")
                .define('S', ModBlocks.SIGILLARIA_SLAB.get())
                .unlockedBy(getHasName(ModBlocks.SIGILLARIA_STEM.get()), has(ModBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);

        //Sigillaria sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.SIGILLARIA_SIGN.get(), 3)
                .pattern("SSS")
                .pattern("SSS")
                .pattern(" # ")
                .define('S', ModBlocks.SIGILLARIA_PLANKS.get())
                .define('#', Tags.Items.RODS_WOODEN)
                .unlockedBy(getHasName(ModBlocks.SIGILLARIA_STEM.get()), has(ModBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);

        //Sigillaria hanging sign
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.SIGILLARIA_HANGING_SIGN.get(), 6)
                .pattern("# #")
                .pattern("SSS")
                .pattern("SSS")
                .define('#', Items.CHAIN)
                .define('S', ModTags.Items.SIGILLARIA_LOG_ITEM)
                .unlockedBy(getHasName(ModBlocks.SIGILLARIA_STEM.get()), has(ModBlocks.SIGILLARIA_STEM.get()))
                .save(consumer);
    }
}
