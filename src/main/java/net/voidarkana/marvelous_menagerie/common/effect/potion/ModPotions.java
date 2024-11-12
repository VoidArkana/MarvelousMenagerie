package net.voidarkana.marvelous_menagerie.common.effect.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS
            = DeferredRegister.create(ForgeRegistries.POTIONS, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<Potion> HALLUCIGENIA_EXTRACT = POTIONS.register("hallu_extract",
            () -> new Potion(new MobEffectInstance(ModEffects.HALLUCINATING.get(), 1800, 0),
                    new MobEffectInstance(MobEffects.CONFUSION, 1800, 0),
                    new MobEffectInstance(MobEffects.POISON, 1800, 1)));

    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
