package net.voidarkana.marvelous_menagerie.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.entity.custom.DodoEntity;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.DODO.get(), DodoEntity.createAttributes().build());
        event.put(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), DodoEntity.createAttributes().build());
    }


}
