package net.voidarkana.marvelous_menagerie.common.block;

import com.peeko32213.unusualprehistory.common.block.BlockDinosaurLandEggs;
import com.peeko32213.unusualprehistory.common.block.BlockDinosaurWaterEggs;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.custom.*;
import net.voidarkana.marvelous_menagerie.common.block.custom.animal_block.CharniaBlock;
import net.voidarkana.marvelous_menagerie.common.block.custom.animal_block.DickinsoniaBlock;
import net.voidarkana.marvelous_menagerie.common.block.custom.animal_block.WiwaxiaBlock;
import net.voidarkana.marvelous_menagerie.common.block.custom.flammable.FlammableWoodLogBlock;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.util.ModWoodTypes;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.SigillariaTreeGrower;

import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MarvelousMenagerie.MOD_ID);

    //eggs
    public static final RegistryObject<Block> DODO_EGGS = registerBlock("dodo_eggs",
            ()-> new BlockDinosaurLandEggs(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion(),
                    ModEntities.DODO, 2, Block.box(4.0, 0.0, 8.0, 11.0, 9.0, 15.0), Block.box(2.0, 0.0, 1.0, 14.0, 10.0, 15.0)));
    public static final RegistryObject<Block> ELE_EGG = registerBlock("elephant_bird_egg",
            ()-> new BlockDinosaurLandEggs(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG).strength(0.5F).sound(SoundType.METAL).randomTicks().noOcclusion(),
                    ModEntities.ELEPHANT_BIRD, 1, Block.box(3.0, 0.0, 3.0, 13.0, 12.0, 13.0)));

    //if adding another tree, make custom block classes for each thing to make flammable
    //Sigillaria plank blocks
    public static final RegistryObject<Block> SIGILLARIA_PLANKS = registerBlock("sigillaria_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_STAIRS = registerBlock("sigillaria_stairs",
            ()-> new StairBlock(() -> ModBlocks.SIGILLARIA_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_SLAB = registerBlock("sigillaria_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_BUTTON = registerBlock("sigillaria_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.NETHER_WOOD), BlockSetType.CRIMSON, 25, true){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_PRESSURE_PLATE = registerBlock("sigillaria_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.NETHER_WOOD), BlockSetType.CRIMSON){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_FENCE = registerBlock("sigillaria_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_FENCE_GATE = registerBlock("sigillaria_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.NETHER_WOOD), SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });


    //Sigillaria Door and Trapdoor
    public static final RegistryObject<Block> SIGILLARIA_DOOR = registerBlock("sigillaria_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));
    public static final RegistryObject<Block> SIGILLARIA_TRAPDOOR = registerBlock("sigillaria_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(),BlockSetType.CRIMSON));

    //Sigillaria signs
    public static final RegistryObject<Block> SIGILLARIA_SIGN = BLOCKS.register("sigillaria_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), ModWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_WALL_SIGN = BLOCKS.register("sigillaria_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), ModWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_HANGING_SIGN = BLOCKS.register("sigillaria_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), ModWoodTypes.SIGILLARIA));
    public static final RegistryObject<Block> SIGILLARIA_WALL_HANGING_SIGN = BLOCKS.register("sigillaria_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), ModWoodTypes.SIGILLARIA));

    //Sigillaria Mosaic Blocks
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC = registerBlock("sigillaria_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC_STAIRS = registerBlock("sigillaria_mosaic_stairs",
            ()-> new StairBlock(() -> ModBlocks.SIGILLARIA_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });
    public static final RegistryObject<Block> SIGILLARIA_MOSAIC_SLAB = registerBlock("sigillaria_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            });

    //Sigillaria logs and wood
    public static final RegistryObject<Block> SIGILLARIA_STEM = registerBlock ("sigillaria_stem",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> SIGILLARIA_WOOD = registerBlock ("sigillaria_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> STRIPPED_SIGILLARIA_STEM = registerBlock ("stripped_sigillaria_stem",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.STEM)));
    public static final RegistryObject<Block> STRIPPED_SIGILLARIA_WOOD = registerBlock ("stripped_sigillaria_wood",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.STEM)));

    //Sigillaria Leaves
    public static final RegistryObject<Block> SIGILLARIA_LEAVES = registerBlock("sigillaria_leaves",
            ()-> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}
                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}
            });

    //Sigillaria Sapling Blocks
    public static final RegistryObject<Block> SIGILLARIA_SAPLING = registerBlock("sigillaria_sapling",
            ()-> new SaplingBlock(new SigillariaTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_SIGILLARIA_SAPLING = registerBlock("potted_sigillaria_sapling",
            ()-> new FlowerPotBlock(()-> ((FlowerPotBlock) Blocks.FLOWER_POT), ModBlocks.SIGILLARIA_SAPLING,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> COOKSONIA = registerBlock("cooksonia",
            ()-> new CooksoniaBlock(BlockBehaviour.Properties.copy(Blocks.SUGAR_CANE).noOcclusion().noCollission()));

    public static final Supplier<Block> TRILO_EGGS = registerBlockWithItem("trilo_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion()
                    .noCollission().randomTicks(), ModEntities.TRILOBITE, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));

    public static final Supplier<Block> SACA_EGGS = registerBlockWithItem("saca_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak()
                    .noOcclusion().noCollission().randomTicks(), ModEntities.SACABAMBASPIS, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));

    public static final Supplier<Block> CARIS_EGGS = registerBlockWithItem("anomalocaris_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak()
                    .noOcclusion().noCollission().randomTicks(), ModEntities.BABY_ANOMALOCARIS, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));



    public static final RegistryObject<Block> PROTOTAXITES = registerBlock("prototaxites",
            ()-> new PrototaxitesBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_GREEN).noOcclusion(),
                    ModConfiguredFeatures.PROTOTAXITES_KEY));

    public static final RegistryObject<Block> PROTOTAXITES_BLOCK = registerBlock("prototaxites_block",
            ()-> new HugeMushroomBlock(BlockBehaviour.Properties.copy(Blocks.MUSHROOM_STEM).mapColor(MapColor.COLOR_GREEN)));

    public static final RegistryObject<Block> DICKINSONIA = registerBlock("dickinsonia",
            ()-> new DickinsoniaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_LIGHT_GREEN).noOcclusion().instabreak().lightLevel((p_152684_) -> {return 6;})));


    public static final Supplier<Block> PIKAIA_EGGS = registerBlockWithItem("pikaia_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion()
                    .noCollission().randomTicks(), ModEntities.PIKAIA, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));


    //prototaxites blocks

    public static final RegistryObject<Block> PROTOTAXITES_PLANKS = registerBlock("prototaxites_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_STAIRS = registerBlock("prototaxites_stairs",
            ()-> new StairBlock(() -> ModBlocks.PROTOTAXITES_PLANKS.get().defaultBlockState(),

                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));
    public static final RegistryObject<Block> PROTOTAXITES_SLAB = registerBlock("prototaxites_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_BUTTON = registerBlock("prototaxites_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON, 25, true));

    public static final RegistryObject<Block> PROTOTAXITES_PRESSURE_PLATE = registerBlock("prototaxites_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON));

    public static final RegistryObject<Block> PROTOTAXITES_FENCE = registerBlock("prototaxites_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_FENCE_GATE = registerBlock("prototaxites_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.NETHER_WOOD).ignitedByLava(),
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE));


    //Prototaxites Door and Trapdoor
    public static final RegistryObject<Block> PROTOTAXITES_DOOR = registerBlock("prototaxites_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(), BlockSetType.CRIMSON));

    public static final RegistryObject<Block> PROTOTAXITES_TRAPDOOR = registerBlock("prototaxites_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).noOcclusion(),BlockSetType.CRIMSON));

    //Prototaxites signs
    public static final RegistryObject<Block> PROTOTAXITES_SIGN = BLOCKS.register("prototaxites_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), ModWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_WALL_SIGN = BLOCKS.register("prototaxites_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), ModWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_HANGING_SIGN = BLOCKS.register("prototaxites_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), ModWoodTypes.PROTOTAXITES));

    public static final RegistryObject<Block> PROTOTAXITES_WALL_HANGING_SIGN = BLOCKS.register("prototaxites_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), ModWoodTypes.PROTOTAXITES));

    //Prototaxites Mosaic Blocks
    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC = registerBlock("prototaxites_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC_STAIRS = registerBlock("prototaxites_mosaic_stairs",
            ()-> new StairBlock(() -> ModBlocks.PROTOTAXITES_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> PROTOTAXITES_MOSAIC_SLAB = registerBlock("prototaxites_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.NETHER_WOOD).ignitedByLava()));



    //zuloagae blocks

    public static final RegistryObject<Block> ZULOAGAE_PLANKS = registerBlock("zuloagae_planks",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_STAIRS = registerBlock("zuloagae_stairs",
            ()-> new StairBlock(() -> ModBlocks.ZULOAGAE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_SLAB = registerBlock("zuloagae_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_BUTTON = registerBlock("zuloagae_button",
            ()-> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON, 25, true));

    public static final RegistryObject<Block> ZULOAGAE_PRESSURE_PLATE = registerBlock("zuloagae_pressure_plate",
            ()-> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,
                    BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    BlockSetType.CRIMSON));

    public static final RegistryObject<Block> ZULOAGAE_FENCE = registerBlock("zuloagae_fence",
            ()-> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_FENCE_GATE = registerBlock("zuloagae_fence_gate",
            ()-> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE).sound(SoundType.BAMBOO_WOOD).ignitedByLava(),
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN, SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE));


    //Zuloagae Door and Trapdoor
    public static final RegistryObject<Block> ZULOAGAE_DOOR = registerBlock("zuloagae_door",
            ()-> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).noOcclusion(), BlockSetType.CRIMSON));

    public static final RegistryObject<Block> ZULOAGAE_TRAPDOOR = registerBlock("zuloagae_trapdoor",
            ()-> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).noOcclusion(),BlockSetType.CRIMSON));

    //Zuloagae signs
    public static final RegistryObject<Block> ZULOAGAE_SIGN = BLOCKS.register("zuloagae_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_SIGN), ModWoodTypes.ZULOAGAE));

    public static final RegistryObject<Block> ZULOAGAE_WALL_SIGN = BLOCKS.register("zuloagae_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_SIGN), ModWoodTypes.ZULOAGAE));

    public static final RegistryObject<Block> ZULOAGAE_HANGING_SIGN = BLOCKS.register("zuloagae_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_HANGING_SIGN), ModWoodTypes.ZULOAGAE));

    public static final RegistryObject<Block> ZULOAGAE_WALL_HANGING_SIGN = BLOCKS.register("zuloagae_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_WALL_HANGING_SIGN), ModWoodTypes.ZULOAGAE));

    //Zuloagae Mosaic Blocks
    public static final RegistryObject<Block> ZULOAGAE_MOSAIC = registerBlock("zuloagae_mosaic",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_MOSAIC_STAIRS = registerBlock("zuloagae_mosaic_stairs",
            ()-> new StairBlock(() -> ModBlocks.ZULOAGAE_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));

    public static final RegistryObject<Block> ZULOAGAE_MOSAIC_SLAB = registerBlock("zuloagae_mosaic_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BAMBOO_WOOD).ignitedByLava()));


    //Zuloagae logs and wood
    public static final RegistryObject<Block> ZULOAGAE_BLOCK = registerBlock ("zuloagae_block",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).sound(SoundType.BAMBOO_WOOD)));
    public static final RegistryObject<Block> STRIPPED_ZULOAGAE_BLOCK = registerBlock ("stripped_zuloagae_block",
            () -> new FlammableWoodLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.BAMBOO_WOOD)));


    //Wiwaxia
    public static final RegistryObject<Block> WIWAXIA = registerBlock("wiwaxia",
            ()-> new WiwaxiaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_PURPLE).noOcclusion().instabreak().lightLevel((p_152684_) -> {return 6;})));


    //Arandaspis
    public static final Supplier<Block> ARANDASPIS_EGGS = registerBlockWithItem("arandaspis_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion()
                    .noCollission().randomTicks(), ModEntities.ARANDASPIS, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));

    //Hallucigenia
    public static final Supplier<Block> HALLU_EGGS = registerBlockWithItem("hallu_eggs",
            () -> new BlockDinosaurWaterEggs(BlockBehaviour.Properties.copy(Blocks.FROGSPAWN).instabreak().noOcclusion()
                    .noCollission().randomTicks(), ModEntities.HALLUCIGENIA, false),
            (entry) -> new PlaceOnWaterBlockItem(entry.get(), new Item.Properties()));

    //Charnia
    public static final RegistryObject<Block> CHARNIA = registerBlock("charnia",
            ()-> new CharniaBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).mapColor(MapColor.COLOR_ORANGE)
                    .noOcclusion().sound(SoundType.MOSS).instabreak().noCollission()
                    .lightLevel((pState) -> {return 3 + 3 * pState.getValue(CharniaBlock.PICKLES);})));


    private static <T extends Block> Supplier<T> registerBlockWithItem(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        ModItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name,RegistryObject<T> block){
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
