package net.voidarkana.marvelous_menagerie.common.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<BlockEntityType<ModSignBlockEntity>> MOD_SIGN =
        BLOCK_ENTITIES.register( "mod_sign", () ->
                BlockEntityType.Builder.of(ModSignBlockEntity::new,
                        ModBlocks.PROTOTAXITES_SIGN.get(), ModBlocks.PROTOTAXITES_WALL_SIGN.get(),
                        ModBlocks.ZULOAGAE_SIGN.get(), ModBlocks.ZULOAGAE_WALL_SIGN.get(),
                        ModBlocks.SIGILLARIA_SIGN.get(), ModBlocks.SIGILLARIA_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<ModHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register( "mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(ModHangingSignBlockEntity::new,
                            ModBlocks.PROTOTAXITES_SIGN.get(), ModBlocks.PROTOTAXITES_WALL_SIGN.get(),
                            ModBlocks.ZULOAGAE_SIGN.get(), ModBlocks.ZULOAGAE_WALL_SIGN.get(),
                            ModBlocks.SIGILLARIA_HANGING_SIGN.get(), ModBlocks.SIGILLARIA_WALL_HANGING_SIGN.get()).build(null));


    public static final RegistryObject<BlockEntityType<CharniaBlockEntity>> CHARNIA_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("charnia", () ->
                    BlockEntityType.Builder.of(CharniaBlockEntity::new,
                            ModBlocks.CHARNIA.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
