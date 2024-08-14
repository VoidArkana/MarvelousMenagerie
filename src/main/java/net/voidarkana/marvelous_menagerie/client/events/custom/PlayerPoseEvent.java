package net.voidarkana.marvelous_menagerie.client.events.custom;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.eventbus.api.Event;

public class PlayerPoseEvent <T extends LivingEntity> extends Event {
    private final T entity;
    private final HumanoidModel<T> humanoidModel;

    public PlayerPoseEvent(T pEntity, HumanoidModel<T> pHumanoidModel) {
        this.entity = pEntity;
        this.humanoidModel = pHumanoidModel;
    }

    public T getEntity() {
        return this.entity;
    }

    public HumanoidModel<T> getHumanoidModel() {
        return this.humanoidModel;
    }
}
