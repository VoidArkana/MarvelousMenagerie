package net.voidarkana.marvelous_menagerie.data.datagen;

import com.peeko32213.unusualprehistory.core.registry.UPBlocks;
import com.peeko32213.unusualprehistory.core.registry.UPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {


    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(UPTags.DINO_NATURAL_SPAWNABLE).add(Blocks.MYCELIUM);

        this.tag(BlockTags.MINEABLE_WITH_AXE).add(
                ModBlocks.SIGILLARIA_PLANKS.get(),
                ModBlocks.SIGILLARIA_STAIRS.get(),
                ModBlocks.SIGILLARIA_SLAB.get(),
                ModBlocks.SIGILLARIA_BUTTON.get(),
                ModBlocks.SIGILLARIA_PRESSURE_PLATE.get(),
                ModBlocks.SIGILLARIA_FENCE.get(),
                ModBlocks.SIGILLARIA_FENCE_GATE.get(),
                ModBlocks.SIGILLARIA_MOSAIC.get(),
                ModBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get(),
                ModBlocks.SIGILLARIA_DOOR.get(),
                ModBlocks.SIGILLARIA_TRAPDOOR.get(),
                ModBlocks.SIGILLARIA_STEM.get(),
                ModBlocks.STRIPPED_SIGILLARIA_STEM.get(),
                ModBlocks.SIGILLARIA_WOOD.get(),
                ModBlocks.STRIPPED_SIGILLARIA_WOOD.get(),
                ModBlocks.SIGILLARIA_SIGN.get(),
                ModBlocks.SIGILLARIA_WALL_SIGN.get(),
                ModBlocks.SIGILLARIA_HANGING_SIGN.get(),
                ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),

                ModBlocks.PROTOTAXITES.get(),
                ModBlocks.PROTOTAXITES_BLOCK.get(),
                ModBlocks.PROTOTAXITES_PLANKS.get(),
                ModBlocks.PROTOTAXITES_STAIRS.get(),
                ModBlocks.PROTOTAXITES_SLAB.get(),
                ModBlocks.PROTOTAXITES_BUTTON.get(),
                ModBlocks.PROTOTAXITES_PRESSURE_PLATE.get(),
                ModBlocks.PROTOTAXITES_FENCE.get(),
                ModBlocks.PROTOTAXITES_FENCE_GATE.get(),
                ModBlocks.PROTOTAXITES_MOSAIC.get(),
                ModBlocks.PROTOTAXITES_MOSAIC_SLAB.get(),
                ModBlocks.PROTOTAXITES_MOSAIC_STAIRS.get(),
                ModBlocks.PROTOTAXITES_DOOR.get(),
                ModBlocks.PROTOTAXITES_TRAPDOOR.get(),
                ModBlocks.PROTOTAXITES_SIGN.get(),
                ModBlocks.PROTOTAXITES_WALL_SIGN.get(),
                ModBlocks.PROTOTAXITES_HANGING_SIGN.get(),
                ModBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),

                ModBlocks.ZULOAGAE_PLANKS.get(),
                ModBlocks.ZULOAGAE_STAIRS.get(),
                ModBlocks.ZULOAGAE_SLAB.get(),
                ModBlocks.ZULOAGAE_BUTTON.get(),
                ModBlocks.ZULOAGAE_PRESSURE_PLATE.get(),
                ModBlocks.ZULOAGAE_FENCE.get(),
                ModBlocks.ZULOAGAE_FENCE_GATE.get(),
                ModBlocks.ZULOAGAE_MOSAIC.get(),
                ModBlocks.ZULOAGAE_MOSAIC_SLAB.get(),
                ModBlocks.ZULOAGAE_MOSAIC_STAIRS.get(),
                ModBlocks.ZULOAGAE_DOOR.get(),
                ModBlocks.ZULOAGAE_TRAPDOOR.get(),
                ModBlocks.ZULOAGAE_BLOCK.get(),
                ModBlocks.STRIPPED_ZULOAGAE_BLOCK.get(),
                ModBlocks.ZULOAGAE_SIGN.get(),
                ModBlocks.ZULOAGAE_WALL_SIGN.get(),
                ModBlocks.ZULOAGAE_HANGING_SIGN.get(),
                ModBlocks.ZULOAGAE_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.PLANKS).add(
                ModBlocks.SIGILLARIA_PLANKS.get(),
                ModBlocks.SIGILLARIA_MOSAIC.get(),
                ModBlocks.PROTOTAXITES_PLANKS.get(),
                ModBlocks.PROTOTAXITES_MOSAIC.get(),
                ModBlocks.ZULOAGAE_PLANKS.get(),
                ModBlocks.ZULOAGAE_MOSAIC.get()
        );

        this.tag(ModTags.Blocks.SIGILLARIA_LOG_BLOCK).add(
                ModBlocks.SIGILLARIA_STEM.get(),
                ModBlocks.STRIPPED_SIGILLARIA_STEM.get(),
                ModBlocks.SIGILLARIA_WOOD.get(),
                ModBlocks.STRIPPED_SIGILLARIA_WOOD.get()
        );

        this.tag(ModTags.Blocks.ZULOAGAE_LOG_BLOCK).add(
                ModBlocks.ZULOAGAE_BLOCK.get(),
                ModBlocks.STRIPPED_ZULOAGAE_BLOCK.get()
        );

        this.tag(BlockTags.LOGS_THAT_BURN)
                .addTag(ModTags.Blocks.ZULOAGAE_LOG_BLOCK)
                .addTag(ModTags.Blocks.SIGILLARIA_LOG_BLOCK);

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.SIGILLARIA_LEAVES.get()
        );

        this.tag(BlockTags.LEAVES).add(
                ModBlocks.SIGILLARIA_LEAVES.get()
        );
        this.tag(BlockTags.WOODEN_FENCES).add(
                ModBlocks.SIGILLARIA_FENCE.get(),
                ModBlocks.PROTOTAXITES_FENCE.get(),
                ModBlocks.ZULOAGAE_FENCE.get()
        );
        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.SIGILLARIA_FENCE_GATE.get(),
                ModBlocks.PROTOTAXITES_FENCE_GATE.get(),
                ModBlocks.ZULOAGAE_FENCE_GATE.get()
        );

        this.tag(BlockTags.WOODEN_DOORS).add(
                ModBlocks.SIGILLARIA_DOOR.get(),
                ModBlocks.PROTOTAXITES_DOOR.get(),
                ModBlocks.ZULOAGAE_DOOR.get()
        );

        this.tag(BlockTags.WOODEN_TRAPDOORS).add(
                ModBlocks.SIGILLARIA_TRAPDOOR.get(),
                ModBlocks.PROTOTAXITES_TRAPDOOR.get(),
                ModBlocks.ZULOAGAE_TRAPDOOR.get()
        );

        this.tag(BlockTags.WOODEN_STAIRS).add(
                ModBlocks.SIGILLARIA_STAIRS.get(),
                ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get(),
                ModBlocks.PROTOTAXITES_STAIRS.get(),
                ModBlocks.PROTOTAXITES_MOSAIC_STAIRS.get(),
                ModBlocks.ZULOAGAE_STAIRS.get(),
                ModBlocks.ZULOAGAE_MOSAIC_STAIRS.get()
        );
        this.tag(BlockTags.WOODEN_SLABS).add(
                ModBlocks.SIGILLARIA_SLAB.get(),
                ModBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                ModBlocks.PROTOTAXITES_SLAB.get(),
                ModBlocks.PROTOTAXITES_MOSAIC_SLAB.get(),
                ModBlocks.ZULOAGAE_SLAB.get(),
                ModBlocks.ZULOAGAE_MOSAIC_SLAB.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(
                ModBlocks.SIGILLARIA_BUTTON.get(),
                ModBlocks.PROTOTAXITES_BUTTON.get(),
                ModBlocks.ZULOAGAE_BUTTON.get()
        );
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.SIGILLARIA_PRESSURE_PLATE.get(),
                ModBlocks.PROTOTAXITES_PRESSURE_PLATE.get(),
                ModBlocks.ZULOAGAE_PRESSURE_PLATE.get()
        );

        this.tag(BlockTags.SIGNS).add(
                ModBlocks.SIGILLARIA_SIGN.get(),
                ModBlocks.PROTOTAXITES_SIGN.get(),
                ModBlocks.ZULOAGAE_SIGN.get()
        );

        this.tag(BlockTags.WALL_SIGNS).add(
                ModBlocks.SIGILLARIA_WALL_SIGN.get(),
                ModBlocks.PROTOTAXITES_WALL_SIGN.get(),
                ModBlocks.ZULOAGAE_WALL_SIGN.get()
        );

        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
                ModBlocks.SIGILLARIA_HANGING_SIGN.get(),
                ModBlocks.PROTOTAXITES_HANGING_SIGN.get(),
                ModBlocks.ZULOAGAE_HANGING_SIGN.get()
        );

        this.tag(BlockTags.WALL_HANGING_SIGNS).add(
                ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                ModBlocks.PROTOTAXITES_WALL_HANGING_SIGN.get(),
                ModBlocks.ZULOAGAE_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.COOKSONIA.get()
        );

        this.tag(ModTags.Blocks.DYE_DEPOT_MAROON_WOOL).addOptional(new ResourceLocation("dye_depot:maroon_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_ROSE_WOOL).addOptional(new ResourceLocation("dye_depot:rose_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_CORAL_WOOL).addOptional(new ResourceLocation("dye_depot:coral_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_INDIGO_WOOL).addOptional(new ResourceLocation("dye_depot:indigo_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_NAVY_WOOL).addOptional(new ResourceLocation("dye_depot:navy_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_SLATE_WOOL).addOptional(new ResourceLocation("dye_depot:slate_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_OLIVE_WOOL).addOptional(new ResourceLocation("dye_depot:olive_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_AMBER_WOOL).addOptional(new ResourceLocation("dye_depot:amber_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_BEIGE_WOOL).addOptional(new ResourceLocation("dye_depot:beige_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_TEAL_WOOL).addOptional(new ResourceLocation("dye_depot:teal_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_MINT_WOOL).addOptional(new ResourceLocation("dye_depot:mint_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_AQUA_WOOL).addOptional(new ResourceLocation("dye_depot:aqua_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_VERDANT_WOOL).addOptional(new ResourceLocation("dye_depot:verdant_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_FOREST_WOOL).addOptional(new ResourceLocation("dye_depot:forest_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_GINGER_WOOL).addOptional(new ResourceLocation("dye_depot:ginger_wool"));
        this.tag(ModTags.Blocks.DYE_DEPOT_TAN_WOOL).addOptional(new ResourceLocation("dye_depot:tan_wool"));

        this.tag(ModTags.Blocks.DYE_DEPOT_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_MAROON_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_ROSE_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_CORAL_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_INDIGO_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_NAVY_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_SLATE_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_OLIVE_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_AMBER_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_BEIGE_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_TEAL_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_MINT_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_AQUA_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_VERDANT_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_FOREST_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_GINGER_WOOL)
                .addTag(ModTags.Blocks.DYE_DEPOT_TAN_WOOL);

        this.tag(ModTags.Blocks.PIKAIA_REVIVING_TARGET).add(

                Blocks.DEAD_HORN_CORAL_BLOCK,
                Blocks.DEAD_BRAIN_CORAL_BLOCK,
                Blocks.DEAD_FIRE_CORAL_BLOCK,
                Blocks.DEAD_BUBBLE_CORAL_BLOCK,
                Blocks.DEAD_TUBE_CORAL_BLOCK,
                UPBlocks.DEAD_CLATHRODICTYON_BLOCK.get(),

                Blocks.DEAD_BRAIN_CORAL,
                Blocks.DEAD_BRAIN_CORAL_FAN,
                Blocks.DEAD_BRAIN_CORAL_WALL_FAN,

                Blocks.DEAD_BUBBLE_CORAL,
                Blocks.DEAD_BUBBLE_CORAL_FAN,
                Blocks.DEAD_BUBBLE_CORAL_WALL_FAN,

                Blocks.DEAD_FIRE_CORAL,
                Blocks.DEAD_FIRE_CORAL_FAN,
                Blocks.DEAD_FIRE_CORAL_WALL_FAN,

                Blocks.DEAD_HORN_CORAL,
                Blocks.DEAD_HORN_CORAL_FAN,
                Blocks.DEAD_HORN_CORAL_WALL_FAN,

                Blocks.DEAD_TUBE_CORAL,
                Blocks.DEAD_TUBE_CORAL_FAN,
                Blocks.DEAD_TUBE_CORAL_WALL_FAN,

                UPBlocks.DEAD_CLATHRODICTYON.get(),
                UPBlocks.DEAD_CLATHRODICTYON_FAN.get(),
                UPBlocks.DEAD_CLATHRODICTYON_WALL_FAN.get()
        );

    }
}
