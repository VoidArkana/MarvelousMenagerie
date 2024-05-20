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

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<EntityType<DodoEntity>> DODO =
            ENTITY_TYPES.register("dodo",
                    () -> EntityType.Builder.of(DodoEntity::new, MobCategory.CREATURE)
                            .sized(0.7f, 0.9f)
                            .build(new ResourceLocation(MarvelousMenagerie.MOD_ID, "dodo").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
