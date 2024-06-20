package net.voidarkana.marvelous_menagerie.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.entity.custom.*;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event){
        event.put(ModEntities.DODO.get(), DodoEntity.createAttributes().build());
        event.put(ModEntities.THYLACINE.get(), ThylacineEntity.createAttributes().build());
        event.put(ModEntities.ELEPHANT_BIRD.get(), ElephantBirdEntity.createAttributes().build());
        event.put(ModEntities.STELLER_SEA_COW.get(), StellerEntity.createAttributes().build());
        event.put(ModEntities.BABY_STELLER_SEA_COW.get(), BabyStellerEntity.createAttributes().build());
        event.put(ModEntities.SIGILLARIA_SAPLING_ENTITY.get(), PlantEntity.createAttributes().build());
        event.put(ModEntities.COOKSONIA_ENTITY.get(), PlantEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void setTargetEvent(LivingChangeTargetEvent event){

        if (event.getNewTarget() != null){
            if (event.getNewTarget().hasEffect(ModEffects.CHILLING.get())) {
                event.setCanceled(true);
                return;
            }
        }

    }

}
