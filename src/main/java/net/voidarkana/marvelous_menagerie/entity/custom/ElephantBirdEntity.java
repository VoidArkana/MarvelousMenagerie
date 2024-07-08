package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
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
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import net.voidarkana.marvelous_menagerie.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Iterator;

public class ElephantBirdEntity extends EntityBaseDinosaurAnimal implements GeoEntity {

    public ElephantBirdEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.5F);
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation)this.getNavigation();
        groundpathnavigation.setCanWalkOverFences(true);
    }

    private static final EntityDataAccessor<Integer> TIME_TO_EAT = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.INT);
    int prevEatenTime;
    private static final EntityDataAccessor<Integer> SMH_TIME = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.INT);
    int prevSMHTime;
    private static final EntityDataAccessor<Boolean> IS_SMH = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.BOOLEAN);


    protected static final RawAnimation BABY_WALK = RawAnimation.begin().thenLoop("animation.elephant_bird.baby_walk");
    protected static final RawAnimation ELE_WALK = RawAnimation.begin().thenLoop("animation.elephant_bird.walk");
    protected static final RawAnimation ELE_IDLE = RawAnimation.begin().thenLoop("animation.elephant_bird.idle");
    protected static final RawAnimation ELE_SWIM = RawAnimation.begin().thenLoop("animation.elephant_bird.swim");
    protected static final RawAnimation BABY_SWIM = RawAnimation.begin().thenLoop("animation.elephant_bird.baby_swim");

    protected static final RawAnimation ELE_SMH = RawAnimation.begin().thenPlay("animation.elephant_bird.smh");

    
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
        this.entityData.define(SMH_TIME, 0);
        this.entityData.define(IS_SMH, false);
    }


    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("timeSinceEaten", this.getEatenTime());
        compound.putInt("smhTime", this.getSMH());
        compound.putBoolean("isSMH", this.getIsSMH());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setEatenTime(compound.getInt("timeSinceEaten"));
        this.setSMH(compound.getInt("smhTime"));
        this.setIsSMH(compound.getBoolean("isSMH"));
    }

    //has eaten
    public int getEatenTime(){
        return this.entityData.get(TIME_TO_EAT);}

    public void setEatenTime(int timeToEatTicks){
        this.entityData.set(TIME_TO_EAT, timeToEatTicks);}

    //SMH
    public int getSMH(){
        return this.entityData.get(SMH_TIME);}

    public void setSMH(int smhTime){
        this.entityData.set(SMH_TIME, smhTime);}

    //is SMHing
    public boolean getIsSMH(){
        return this.entityData.get(IS_SMH);}

    public void setIsSMH(boolean isSMH){
        this.entityData.set(IS_SMH, isSMH);}

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if ((itemstack.is(Items.GOLDEN_CARROT)) && this.onGround() && this.getEatenTime()<=0 && !this.isBaby()) {
            this.usePlayerItem(player, hand, itemstack);
            this.playSound(SoundEvents.HORSE_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setEatenTime(600);
            return InteractionResult.SUCCESS;
        } else if ((itemstack.is(Items.GOLDEN_CARROT)) && this.onGround() && this.getEatenTime()>0 && !this.isBaby() && this.getSMH()<=0){
            this.setSMH(60);
            this.setIsSMH(true);
            this.playSound(ModSounds.ELE_GRUMBLE.get());

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(2.0), this.getRandomY() + 0.5, this.getRandomZ(2.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }
        else {
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
        if (this.getSMH()>0){
            prevSMHTime = this.getSMH();
            this.setSMH(prevSMHTime-1);

            if (this.getSMH()==0){
                this.setIsSMH(false);
            }
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 10, this::Controller)});
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "SMH", 10, this::SMHController)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if (this.isInWater() && !this.onGround()){
            if (this.isBaby()){
                event.setAndContinue(BABY_SWIM);
            }else{
                event.setAndContinue(ELE_SWIM);
            }
        } else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.onGround()) {
            if (this.isBaby()){
                event.setAndContinue(BABY_WALK);
                //event.getController().setAnimationSpeed(1.25f);
            }
            else {
                event.setAndContinue(ELE_WALK);
                //event.getController().setAnimationSpeed(1f);
            }
        } else if (this.onGround()){
            event.setAndContinue(ELE_IDLE);
        }
        return PlayState.CONTINUE;
    }

    protected <E extends GeoAnimatable> PlayState SMHController(AnimationState<E> event) {
        if (this.getIsSMH()){
            event.setAndContinue(ELE_SMH);
            return PlayState.CONTINUE;
        } else {
            event.getController().forceAnimationReset();
            return PlayState.STOP;
        }
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        if(this.isBaby()){
            this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
        }
        else{
            this.playSound(ModSounds.LARGE_STEPS.get(), 0.25F, 1.0F);
        }
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        if (pFallDistance > 1.0F) {
            if (this.isBaby()){
                this.playSound(SoundEvents.HORSE_LAND, 0F, 1.0F);
            }
            else {
                this.playSound(SoundEvents.HORSE_LAND, 0.15F, 1.0F);
            }
        }

        int i = this.calculateFallDamage(pFallDistance, pMultiplier);
        if (i <= 0) {
            return false;
        } else {
            this.hurt(pSource, (float)i);
            if (this.isVehicle()) {
                Iterator var5 = this.getIndirectPassengers().iterator();

                while(var5.hasNext()) {
                    Entity entity = (Entity)var5.next();
                    entity.hurt(pSource, (float)i);
                }
            }

            return true;
        }
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    protected SoundEvent getAmbientSound() {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_IDLE.get();
        }else {
            return ModSounds.ELE_IDLE.get();
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_HURT.get();
        }else {
            return ModSounds.ELE_HURT.get();
        }
    }

    protected SoundEvent getDeathSound() {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_DEATH.get();
        }else {
            return ModSounds.ELE_DEATH.get();
        }
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
