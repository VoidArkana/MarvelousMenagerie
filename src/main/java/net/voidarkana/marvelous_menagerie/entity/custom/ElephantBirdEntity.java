package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class ElephantBirdEntity extends EntityBaseDinosaurAnimal implements GeoEntity {

    public ElephantBirdEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<Integer> TIME_TO_EAT = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.INT);
    int prevEatenTime;

    protected static final RawAnimation ELE_WALK = RawAnimation.begin().thenLoop("animation.elephant_bird.walk");
    protected static final RawAnimation ELE_IDLE = RawAnimation.begin().thenLoop("animation.elephant_bird.idle");

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIME_TO_EAT, 0);
    }


    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("timeSinceEaten", this.getEatenTime());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setEatenTime(compound.getInt("timeSinceEaten"));
    }

    //has eaten
    public int getEatenTime(){
        return this.entityData.get(TIME_TO_EAT);}

    public void setEatenTime(int timeToEatTicks){
        this.entityData.set(TIME_TO_EAT, timeToEatTicks);}


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if ((itemstack.is(Items.GOLDEN_CARROT)) && this.onGround() && this.getEatenTime()<=0 && !this.isBaby()) {
            this.usePlayerItem(player, hand, itemstack);
            this.playSound(SoundEvents.HORSE_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setEatenTime(600);
            return InteractionResult.SUCCESS;
        } else {
            return type;
        }
    }


    public void tick (){
        super.tick();

        if (this.getEatenTime()>0){
            if (this.getEatenTime()==590){
                this.playSound(SoundEvents.SNIFFER_EGG_PLOP, 1.0F, ((this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) - 1.0F));
                this.spawnAtLocation(ModItems.CRACKED_ELEPHANT_EGG.get());
            }
            if (this.getEatenTime()==588){
                this.playSound(SoundEvents.SNIFFER_EGG_CRACK, 1.0F, ((this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) - 1.0F));
            }
            prevEatenTime = this.getEatenTime();
            this.setEatenTime(prevEatenTime-1);
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 20, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.onGround()) {
            event.setAndContinue(ELE_WALK);
            if (this.isBaby()){
                event.getController().setAnimationSpeed(1.25f);
            }
            else {
                event.getController().setAnimationSpeed(1f);
            }
        } else if (this.onGround()){
            event.setAndContinue(ELE_IDLE);
        }
        return PlayState.CONTINUE;
    }

    @Override
    protected SoundEvent getAttackSound() {return null;}
    @Override
    protected int getKillHealAmount() {return 0;}
    @Override
    protected boolean canGetHungry() {return false;}
    @Override
    protected boolean hasTargets() {return false;}
    @Override
    protected boolean hasAvoidEntity() {return false;}
    @Override
    protected boolean hasCustomNavigation() {return false;}
    @Override
    protected boolean hasMakeStuckInBlock() {return false;}
    @Override
    protected boolean customMakeStuckInBlockCheck(BlockState blockState) {return false;}
    @Override
    protected TagKey<EntityType<?>> getTargetTag() {return null;}
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {return null;}
}
