package net.voidarkana.marvelous_menagerie.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    //keep an eye on this, was asked to make public but was protected
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SIGILLARIA_PLANKS.get());
        this.dropSelf(ModBlocks.SIGILLARIA_STAIRS.get());
        this.dropSelf(ModBlocks.SIGILLARIA_FENCE.get());
        this.dropSelf(ModBlocks.SIGILLARIA_FENCE_GATE.get());
        this.dropSelf(ModBlocks.SIGILLARIA_BUTTON.get());
        this.dropSelf(ModBlocks.SIGILLARIA_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.SIGILLARIA_TRAPDOOR.get());

        this.add(ModBlocks.SIGILLARIA_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SIGILLARIA_SLAB.get()));
        this.add(ModBlocks.SIGILLARIA_MOSAIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.SIGILLARIA_MOSAIC_SLAB.get()));
        this.add(ModBlocks.SIGILLARIA_DOOR.get(),
                block -> createDoorTable(ModBlocks.SIGILLARIA_DOOR.get()));

        this.add(ModBlocks.SIGILLARIA_SIGN.get(),
                block -> createSingleItemTable(ModItems.SIGILLARIA_SIGN.get()));
        this.add(ModBlocks.SIGILLARIA_WALL_SIGN.get(),
                block -> createSingleItemTable(ModItems.SIGILLARIA_SIGN.get()));
        this.add(ModBlocks.SIGILLARIA_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.SIGILLARIA_HANGING_SIGN.get()));
        this.add(ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get(),
                block -> createSingleItemTable(ModItems.SIGILLARIA_HANGING_SIGN.get()));

        this.dropSelf(ModBlocks.SIGILLARIA_MOSAIC.get());
        this.dropSelf(ModBlocks.SIGILLARIA_MOSAIC_STAIRS.get());

        this.dropSelf(ModBlocks.SIGILLARIA_STEM.get());
        this.dropSelf(ModBlocks.STRIPPED_SIGILLARIA_STEM.get());
        this.dropSelf(ModBlocks.SIGILLARIA_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_SIGILLARIA_WOOD.get());

        this.dropSelf(ModBlocks.SIGILLARIA_SAPLING.get());
        this.add(ModBlocks.POTTED_SIGILLARIA_SAPLING.get(),
                createPotFlowerItemTable(ModBlocks.SIGILLARIA_SAPLING.get()));

        this.add(ModBlocks.SIGILLARIA_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.SIGILLARIA_LEAVES.get(), ModBlocks.SIGILLARIA_SAPLING.get(),0.15f));

        this.add(ModBlocks.DODO_EGGS.get(),
                createSilkTouchOnlyTable(ModBlocks.DODO_EGGS.get()));
        this.add(ModBlocks.ELE_EGG.get(),
                createSilkTouchOnlyTable(ModBlocks.ELE_EGG.get()));

        this.dropSelf(ModBlocks.COOKSONIA.get());

        this.dropSelf(ModBlocks.TRILO_EGGS.get());
        this.dropSelf(ModBlocks.SACA_EGGS.get());
        this.dropSelf(ModBlocks.CARIS_EGGS.get());

        this.dropSelf(ModBlocks.PROTOTAXITES.get());
        this.dropSelf(ModBlocks.PROTOTAXITES_BLOCK.get());

        this.dropSelf(ModBlocks.DICKINSONIA.get());

        this.dropSelf(ModBlocks.PIKAIA_EGGS.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
