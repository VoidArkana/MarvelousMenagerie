package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.datagen.UPRecipeProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
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

        //Egg Shellmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.EGG_SHELLMET.get(), 1)
                .pattern("SSS")
                .pattern("S S")
                .define('S', ModItems.EGG_SHELL_FRAGMENT.get())
                .unlockedBy(getHasName(ModItems.EGG_SHELL_FRAGMENT.get()), has(ModItems.EGG_SHELL_FRAGMENT.get()))
                .save(consumer);

        //Jumbo Omelette
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.JUMBO_OMELETTE.get(), 1)
                .requires(ModItems.BOILED_ELEPHANT_EGG.get())
                .requires(Items.CARROT)
                .requires(Ingredient.of(Items.POTATO, Items.BAKED_POTATO))
                .unlockedBy(getHasName(ModItems.CRACKED_ELEPHANT_EGG.get()), has(ModItems.CRACKED_ELEPHANT_EGG.get()))
                .save(consumer);

        //Steller Ice Cream
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.STELLER_ICE_CREAM.get(), 3)
                .pattern(" S ")
                .pattern("SMS")
                .pattern("BBB")
                .define('S', Items.SNOWBALL)
                .define('M', ModItems.STELLER_MILK.get())
                .define('B', Items.BOWL)
                .unlockedBy(getHasName(ModItems.STELLER_MILK.get()), has(ModItems.STELLER_MILK.get()))
                .save(consumer);

        //Lime Pigment From Cooksonia
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.LIME_DYE, 1)
//                .requires(ModBlocks.COOKSONIA.get())
//                .unlockedBy(getHasName(ModBlocks.COOKSONIA.get()), has(ModBlocks.COOKSONIA.get()))
//                .save(consumer);

        //Golden Sacabambaspis
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.GOLDEN_SACA.get(), 1)
                .pattern("GGG")
                .pattern("GSG")
                .pattern("GGG")
                .define('G', Items.GOLD_INGOT)
                .define('S', ModItems.SACABAMBASPIS.get())
                .unlockedBy(getHasName(ModItems.SACABAMBASPIS.get()), has(ModItems.SACABAMBASPIS.get()))
                .save(consumer);

        //Mycelium from Prototaxites
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.MYCELIUM, 2)
                .pattern("PP")
                .pattern("DD")
                .define('P', ModBlocks.PROTOTAXITES.get())
                .define('D', Items.DIRT)
                .unlockedBy(getHasName(ModBlocks.PROTOTAXITES.get()), has(ModBlocks.PROTOTAXITES.get()))
                .save(consumer);

        //Prototaxites Block From Prototaxites
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PROTOTAXITES_BLOCK.get(), 1)
                .pattern("PP")
                .pattern("PP")
                .define('P', ModBlocks.PROTOTAXITES.get())
                .unlockedBy(getHasName(ModBlocks.PROTOTAXITES.get()), has(ModBlocks.PROTOTAXITES.get()))
                .save(consumer);

        //Prototaxites From Prototaxites Block
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PROTOTAXITES.get(), 4)
                .requires(ModBlocks.PROTOTAXITES_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PROTOTAXITES.get()), has(ModBlocks.PROTOTAXITES.get()))
                .save(consumer);

        //Mushroom Stew From Prototaxites
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MUSHROOM_STEW, 1)
//                .requires(ModBlocks.PROTOTAXITES.get(), 2)
//                .requires(Items.BOWL)
//                .unlockedBy(getHasName(ModBlocks.PROTOTAXITES.get()), has(ModBlocks.PROTOTAXITES.get()))
//                .save(consumer);

        //Anomalous Goggles
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.ANOMALOUS_GOGGLES.get(), 1)
                .pattern("LSL")
                .define('L', ModItems.CARIS_LENS.get())
                .define('S', ModItems.CARIS_SCUTE.get())
                .unlockedBy(getHasName(ModItems.CARIS_SCUTE.get()), has(ModItems.CARIS_SCUTE.get()))
                .unlockedBy(getHasName(ModItems.CARIS_LENS.get()), has(ModItems.CARIS_LENS.get()))
                .save(consumer);


        //Ophthalmo Armor
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.OPHTHALMO_ARMOR.get(), 1)
                .pattern("S  ")
                .pattern("SSS")
                .pattern("S S")
                .define('S', ModItems.CARIS_SCUTE.get())
                .unlockedBy(getHasName(ModItems.CARIS_SCUTE.get()), has(ModItems.CARIS_SCUTE.get()))
                .save(consumer);
    }
}
