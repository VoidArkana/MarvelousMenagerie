package net.voidarkana.marvelous_menagerie.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.custom.*;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import net.voidarkana.marvelous_menagerie.util.ModWoodTypes;
import net.voidarkana.marvelous_menagerie.worldgen.tree.SigillariaTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MarvelousMenagerie.MOD_ID);

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
