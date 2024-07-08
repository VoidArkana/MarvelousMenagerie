package net.voidarkana.marvelous_menagerie.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<MobEffect> SEA_COW_SERENITY = MOB_EFFECTS.register("sea_cow_serenity",
            ()-> new SeaCowSerenityEffect(MobEffectCategory.BENEFICIAL, 796861));

    public static final RegistryObject<MobEffect> PACIFIED = MOB_EFFECTS.register("pacified",
            ()-> new ChillingEffect(MobEffectCategory.BENEFICIAL, 16774105));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
