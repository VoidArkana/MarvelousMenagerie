package net.voidarkana.marvelous_menagerie.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.voidarkana.marvelous_menagerie.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.effect.SeaCowSerenityEffect;

public class ModFoods {
    public static final FoodProperties BOILED_ELEPHANT_EGG = (new FoodProperties.Builder())
            .nutrition(6)
            .saturationMod(0.6F).build();
    public static final FoodProperties JUMBO_OMELETTE = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationMod(1.5F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200, 1), 1.0F).alwaysEat().build();
    public static final FoodProperties STELLER_ICE_CREAM = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationMod(0.2F)
            .effect(new MobEffectInstance(ModEffects.SEA_COW_SERENITY.get(), 200, 0, true, true, true,
                    new MobEffectInstance(MobEffects.WATER_BREATHING, 200,0, false, false, false,
                            new MobEffectInstance(MobEffects.WATER_BREATHING, 200,0, false, false), ModEffects.SEA_COW_SERENITY.get().createFactorData()), ModEffects.SEA_COW_SERENITY.get().createFactorData()),
                    1.0F).alwaysEat().build();
}
