package net.voidarkana.marvelous_menagerie.common.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.entity.custom.*;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyAnomalocarisEntity;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyStellerEntity;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyOphthalmoEntity;

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
                            .sized(0.7f, 0.7f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "thylacine").toString()));

    public static final RegistryObject<EntityType<ElephantBirdEntity>> ELEPHANT_BIRD =
            ENTITY_TYPES.register("elephant_bird",
                    () -> EntityType.Builder.of(ElephantBirdEntity::new, MobCategory.CREATURE)
                            .sized(1.2f, 3.2f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "elephant_bird").toString()));

    public static final RegistryObject<EntityType<StellerEntity>> STELLER_SEA_COW =
            ENTITY_TYPES.register("steller_sea_cow",
                    () -> EntityType.Builder.of(StellerEntity::new, MobCategory.WATER_CREATURE)
                            .sized(4f, 2.25f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "steller_sea_cow").toString()));

    public static final RegistryObject<EntityType<BabyStellerEntity>> BABY_STELLER_SEA_COW =
            ENTITY_TYPES.register("baby_steller_sea_cow",
                    () -> EntityType.Builder.of(BabyStellerEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.9f, 0.6f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "baby_steller_sea_cow").toString()));

    //plants
    public static final RegistryObject<EntityType<PlantEntity>> SIGILLARIA_SAPLING_ENTITY =
            ENTITY_TYPES.register("sigillaria_sapling_entity",
                    () -> EntityType.Builder.of(PlantEntity::new, MobCategory.MISC)
                            .noSummon()
                            .fireImmune()
                            .sized(1,1F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "sigillaria_sapling").toString()));

    public static final RegistryObject<EntityType<PlantEntity>> COOKSONIA_ENTITY =
            ENTITY_TYPES.register("cooksonia_entity",
                    () -> EntityType.Builder.of(PlantEntity::new, MobCategory.MISC)
                            .noSummon()
                            .fireImmune()
                            .sized(1,1F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "cooksonia").toString()));


    public static final RegistryObject<EntityType<TrilobiteEntity>> TRILOBITE =
            ENTITY_TYPES.register("trilobite",
                    () -> EntityType.Builder.of(TrilobiteEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.1f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "trilobite").toString()));

    public static final RegistryObject<EntityType<SacabambaspisEntity>> SACABAMBASPIS =
            ENTITY_TYPES.register("sacabambaspis",
                    () -> EntityType.Builder.of(SacabambaspisEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.8f, 0.5f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "sacabambaspis").toString()));

    public static final RegistryObject<EntityType<PlantEntity>> PROTOTAXITES_ENTITY =
            ENTITY_TYPES.register("prototaxites_entity",
                    () -> EntityType.Builder.of(PlantEntity::new, MobCategory.MISC)
                            .noSummon()
                            .fireImmune()
                            .sized(1,4F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "prototaxites").toString()));

    public static final RegistryObject<EntityType<PlantEntity>> DICKINSONIA_ENTITY =
            ENTITY_TYPES.register("dickinsonia_entity",
                    () -> EntityType.Builder.of(PlantEntity::new, MobCategory.MISC)
                            .noSummon()
                            .fireImmune()
                            .sized(1,0.2F)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "dickinsonia").toString()));

    public static final RegistryObject<EntityType<AnomalocarisEntity>> ANOMALOCARIS =
            ENTITY_TYPES.register("anomalocaris",
                    () -> EntityType.Builder.of(AnomalocarisEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.6f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "anomalocaris").toString()));

    public static final RegistryObject<EntityType<BabyAnomalocarisEntity>> BABY_ANOMALOCARIS =
            ENTITY_TYPES.register("baby_anomalocaris",
                    () -> EntityType.Builder.of(BabyAnomalocarisEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.6f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "baby_anomalocaris").toString()));


    public static final RegistryObject<EntityType<OphthalmoEntity>> OPHTHALMO =
            ENTITY_TYPES.register("ophthalmo",
                    () -> EntityType.Builder.of(OphthalmoEntity::new, MobCategory.WATER_CREATURE)
                            .sized(1.5f, 1.3f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "ophthalmo").toString()));

    public static final RegistryObject<EntityType<BabyOphthalmoEntity>> BABY_OPHTHALMO =
            ENTITY_TYPES.register("baby_ophthalmo",
                    () -> EntityType.Builder.of(BabyOphthalmoEntity::new, MobCategory.WATER_CREATURE)
                            .sized(0.75f, 0.75f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "ophthalmo").toString()));


    public static final RegistryObject<EntityType<JosephoEntity>> JOSEPHO =
            ENTITY_TYPES.register("josepho",
                    () -> EntityType.Builder.of(JosephoEntity::new, MobCategory.CREATURE)
                            .sized(1.5f, 1.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "josepho").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
