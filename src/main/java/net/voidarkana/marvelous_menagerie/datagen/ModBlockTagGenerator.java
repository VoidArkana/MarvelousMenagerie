package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.core.registry.UPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {


    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

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
                ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.PLANKS).add(
                ModBlocks.SIGILLARIA_PLANKS.get(),
                ModBlocks.SIGILLARIA_MOSAIC.get()
        );

        this.tag(ModTags.Blocks.SIGILLARIA_LOG_BLOCK).add(
                ModBlocks.SIGILLARIA_STEM.get(),
                ModBlocks.STRIPPED_SIGILLARIA_STEM.get(),
                ModBlocks.SIGILLARIA_WOOD.get(),
                ModBlocks.STRIPPED_SIGILLARIA_WOOD.get()
        );

        this.tag(BlockTags.LOGS_THAT_BURN).addTag(
                ModTags.Blocks.SIGILLARIA_LOG_BLOCK
        );

        this.tag(BlockTags.MINEABLE_WITH_HOE).add(
                ModBlocks.SIGILLARIA_LEAVES.get()
        );

        this.tag(BlockTags.LEAVES).add(
                ModBlocks.SIGILLARIA_LEAVES.get()
        );
        this.tag(BlockTags.WOODEN_FENCES).add(
                ModBlocks.SIGILLARIA_FENCE.get()
        );
        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.SIGILLARIA_FENCE_GATE.get()
        );

        this.tag(BlockTags.WOODEN_DOORS).add(
                ModBlocks.SIGILLARIA_DOOR.get()
        );

        this.tag(BlockTags.WOODEN_TRAPDOORS).add(
                ModBlocks.SIGILLARIA_TRAPDOOR.get()
        );

        this.tag(BlockTags.WOODEN_STAIRS).add(
                ModBlocks.SIGILLARIA_STAIRS.get(),
                ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get()
        );
        this.tag(BlockTags.WOODEN_SLABS).add(
                ModBlocks.SIGILLARIA_SLAB.get(),
                ModBlocks.SIGILLARIA_MOSAIC_SLAB.get()
        );
        this.tag(BlockTags.WOODEN_BUTTONS).add(
                ModBlocks.SIGILLARIA_BUTTON.get()
        );
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(
                ModBlocks.SIGILLARIA_PRESSURE_PLATE.get()
        );

        this.tag(BlockTags.SIGNS).add(
                ModBlocks.SIGILLARIA_SIGN.get()
        );

        this.tag(BlockTags.WALL_SIGNS).add(
                ModBlocks.SIGILLARIA_WALL_SIGN.get()
        );

        this.tag(BlockTags.CEILING_HANGING_SIGNS).add(
                ModBlocks.SIGILLARIA_HANGING_SIGN.get()
        );

        this.tag(BlockTags.WALL_HANGING_SIGNS).add(
                ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get()
        );

        this.tag(BlockTags.SMALL_FLOWERS).add(
                ModBlocks.COOKSONIA.get()
        );

    }
}
