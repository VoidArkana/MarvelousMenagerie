package net.voidarkana.marvelous_menagerie.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;
import net.voidarkana.marvelous_menagerie.entity.custom.PlantEntity;
import net.voidarkana.marvelous_menagerie.entity.custom.ThylacineEntity;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<EntityType<DodoEntity>> DODO =
            ENTITY_TYPES.register("dodo",
                    () -> EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "dodo").toString()));

    public static final RegistryObject<EntityType<ThylacineEntity>> THYLACINE =
            ENTITY_TYPES.register("thylacine",
                    () -> EntityType.Builder.of(ThylacineEntity::new, MobCategory.CREATURE)
                            .sized(1.0f, 1.0f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "thylacine").toString()));

    //plants
    public static final RegistryObject<EntityType<PlantEntity>> SIGILLARIA_SAPLING_ENTITY =
            ENTITY_TYPES.register("sigillaria_sapling_entity",
                    () -> EntityType.Builder.of(PlantEntity::new, MobCategory.MISC)
                            .noSummon()
                            .fireImmune()
                            .sized(1,0.8F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "sigillaria_sapling").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
