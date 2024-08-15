package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.core.registry.UPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(Tags.Items.EGGS).add(
                ModBlocks.DODO_EGGS.get().asItem(),
                ModBlocks.ELE_EGG.get().asItem(),
                ModItems.CRACKED_ELEPHANT_EGG.get(),
                ModBlocks.SACA_EGGS.get().asItem(),
                ModBlocks.TRILO_EGGS.get().asItem(),
                ModBlocks.CARIS_EGGS.get().asItem(),
                ModBlocks.PIKAIA_EGGS.get().asItem());

        this.tag(Tags.Items.ARMORS_HELMETS).add(
                ModItems.EGG_SHELLMET.get(),
                ModItems.ANOMALOUS_GOGGLES.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.EGG_SHELLMET.get());

        this.copy(ModTags.Blocks.SIGILLARIA_LOG_BLOCK, ModTags.Items.SIGILLARIA_LOG_ITEM);

        this.copy(ModTags.Blocks.DYE_DEPOT_AMBER_WOOL, ModTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_AQUA_WOOL, ModTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_BEIGE_WOOL, ModTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_CORAL_WOOL, ModTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_FOREST_WOOL, ModTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_GINGER_WOOL, ModTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_INDIGO_WOOL, ModTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_MAROON_WOOL, ModTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_MINT_WOOL, ModTags.Items.DYE_DEPOT_MINT_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_NAVY_WOOL, ModTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_OLIVE_WOOL, ModTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_ROSE_WOOL, ModTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_SLATE_WOOL, ModTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_TAN_WOOL, ModTags.Items.DYE_DEPOT_TAN_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_TEAL_WOOL, ModTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM);
        this.copy(ModTags.Blocks.DYE_DEPOT_VERDANT_WOOL, ModTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);

        this.tag(ModTags.Items.DYE_DEPOT_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_MINT_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_TAN_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM)
                .addTag(ModTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM);

        this.tag(ItemTags.LOGS_THAT_BURN).addTag(
                ModTags.Items.SIGILLARIA_LOG_ITEM);

        this.tag(ItemTags.PLANKS).add(
                ModBlocks.SIGILLARIA_PLANKS.get().asItem(),
                ModBlocks.SIGILLARIA_MOSAIC.get().asItem()
        );

        this.tag(ItemTags.LEAVES).add(
                ModBlocks.SIGILLARIA_LEAVES.get().asItem()
        );
        this.tag(ItemTags.WOODEN_FENCES).add(
                ModBlocks.SIGILLARIA_FENCE.get().asItem()
        );
        this.tag(ItemTags.FENCE_GATES).add(
                ModBlocks.SIGILLARIA_FENCE_GATE.get().asItem()
        );

        this.tag(ItemTags.WOODEN_DOORS).add(
                ModBlocks.SIGILLARIA_DOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_TRAPDOORS).add(
                ModBlocks.SIGILLARIA_TRAPDOOR.get().asItem()
        );

        this.tag(ItemTags.WOODEN_STAIRS).add(
                ModBlocks.SIGILLARIA_STAIRS.get().asItem(),
                ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get().asItem()
        );
        this.tag(ItemTags.WOODEN_SLABS).add(
                ModBlocks.SIGILLARIA_SLAB.get().asItem(),
                ModBlocks.SIGILLARIA_MOSAIC_SLAB.get().asItem()
        );
        this.tag(ItemTags.WOODEN_BUTTONS).add(
                ModBlocks.SIGILLARIA_BUTTON.get().asItem()
        );
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.SIGILLARIA_PRESSURE_PLATE.get().asItem()
        );

        this.tag(ItemTags.SIGNS).add(
                ModItems.SIGILLARIA_SIGN.get()
        );

        this.tag(ItemTags.HANGING_SIGNS).add(
                ModItems.SIGILLARIA_HANGING_SIGN.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_OUTPUT_PLANT).add(
                ModItems.SIGILLARIA_DNA.get(),
                ModItems.COOKSONIA_DNA.get(),
                ModItems.PROTO_FLASK.get(),
                ModItems.DICKINSONIA_FLASK.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_OUTPUT_TREE).add(
                ModItems.SIGILLARIA_DNA.get(),
                ModItems.PROTO_FLASK.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_OUTPUT_TAR).add(
                ModItems.DODO_DNA.get(),
                ModItems.ELEPHANT_BIRD_DNA.get(),
                ModItems.THYLACINE_DNA.get(),
                ModItems.JOSEPHO_FLASK.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_OUTPUT_OPAL).add(
                ModItems.STELLER_SEA_COW_DNA.get(),
                ModItems.OPHTHALMO_FLASK.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_OUTPUT_PALEO).add(
                ModItems.TRILO_FLASK.get(),
                ModItems.SACA_FLASK.get(),
                ModItems.CARIS_FLASK.get(),
                ModItems.PIKAIA_FLASK.get()
        );

        this.tag(UPTags.ANALYZER_ITEMS_INPUT).add(
                ModItems.CRACKED_ELEPHANT_EGG.get(),
                ModItems.SACABAMBASPIS.get(),
                ModItems.TRILO_BITE.get(),
                ModItems.STELLER_MILK.get()
        );

        this.tag(ItemTags.FISHES).add(
                ModItems.SACABAMBASPIS.get(),
                ModItems.TRILO_BITE.get()
        );

        this.tag(ItemTags.PIGLIN_LOVED).add(
                ModItems.GOLDEN_SACA.get()
        );

        this.tag(ItemTags.AXOLOTL_TEMPT_ITEMS).add(
                ModItems.SACA_BUCKET.get(),
                ModItems.PIKAIA_BUCKET.get()
        );


        this.tag(UPTags.DNA_FLASKS).add(
                ModItems.DODO_DNA.get(),
                ModItems.ELEPHANT_BIRD_DNA.get(),
                ModItems.STELLER_SEA_COW_DNA.get(),
                ModItems.THYLACINE_DNA.get(),
                ModItems.SIGILLARIA_DNA.get(),
                ModItems.COOKSONIA_DNA.get(),
                ModItems.TRILO_FLASK.get(),
                ModItems.SACA_FLASK.get(),
                ModItems.PROTO_FLASK.get(),
                ModItems.DICKINSONIA_FLASK.get(),
                ModItems.CARIS_FLASK.get(),
                ModItems.OPHTHALMO_FLASK.get(),
                ModItems.JOSEPHO_FLASK.get(),
                ModItems.PIKAIA_FLASK.get()
        );
    }
}
