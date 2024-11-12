package net.voidarkana.marvelous_menagerie.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.effect.custom.ChillingEffect;
import net.voidarkana.marvelous_menagerie.common.effect.custom.HallucinatingEffect;
import net.voidarkana.marvelous_menagerie.common.effect.custom.SeaCowSerenityEffect;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<MobEffect> SEA_COW_SERENITY = MOB_EFFECTS.register("sea_cow_serenity",
            ()-> new SeaCowSerenityEffect(MobEffectCategory.BENEFICIAL, 796861));

    public static final RegistryObject<MobEffect> PACIFIED = MOB_EFFECTS.register("pacified",
            ()-> new ChillingEffect(MobEffectCategory.BENEFICIAL, 0xf787ff));

    public static final RegistryObject<MobEffect> HALLUCINATING = MOB_EFFECTS.register("hallucinating",
            ()-> new HallucinatingEffect(MobEffectCategory.HARMFUL, 0xd084db));

    public static void register(IEventBus eventBus){
        MOB_EFFECTS.register(eventBus);
    }
}
