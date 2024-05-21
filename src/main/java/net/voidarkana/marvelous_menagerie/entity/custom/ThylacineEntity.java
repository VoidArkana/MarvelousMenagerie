package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animation.AnimatableManager;

public class ThylacineEntity extends EntityBaseDinosaurAnimal implements GeoEntity {

    protected ThylacineEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
    }
    @Override
    protected SoundEvent getAttackSound() {
        return null;
    }
    @Override
    protected int getKillHealAmount() {
        return 0;
    }
    @Override
    protected boolean canGetHungry() {
        return false;
    }
    @Override
    protected boolean hasTargets() {
        return false;
    }
    @Override
    protected boolean hasAvoidEntity() {
        return false;
    }
    @Override
    protected boolean hasCustomNavigation() {
        return false;
    }
    @Override
    protected boolean hasMakeStuckInBlock() {
        return false;
    }
    @Override
    protected boolean customMakeStuckInBlockCheck(BlockState blockState) {
        return false;
    }
    @Override
    protected TagKey<EntityType<?>> getTargetTag() {
        return null;
    }
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
