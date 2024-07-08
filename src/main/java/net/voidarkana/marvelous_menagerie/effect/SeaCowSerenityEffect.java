package net.voidarkana.marvelous_menagerie.effect;

import net.minecraft.world.effect.*;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.UUID;

public class SeaCowSerenityEffect extends MobEffect {
    protected SeaCowSerenityEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, UUID.randomUUID().toString(), 0.5D, AttributeModifier.Operation.ADDITION);
        this.addAttributeModifier(Attributes.ARMOR, UUID.randomUUID().toString(), 5, AttributeModifier.Operation.ADDITION);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            pLivingEntity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 2, 0, false, false, false));
        }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

}
