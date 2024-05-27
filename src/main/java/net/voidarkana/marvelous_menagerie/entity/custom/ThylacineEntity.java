package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Iterator;
import java.util.List;

public class ThylacineEntity extends EntityBaseDinosaurAnimal implements GeoEntity {

    public ThylacineEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> HOWLING_TIME = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HOWLING = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> YAWNING_TIME = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> YAWNING = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.BOOLEAN);
    public int prevHowlTime;
    public int prevYawnTime;
    public int howlTickDuration = 120;

    protected static final RawAnimation THYLA_WALK = RawAnimation.begin().thenLoop("animation.thylacine.walk");
    protected static final RawAnimation THYLA_IDLE = RawAnimation.begin().thenLoop("animation.thylacine.idle");
    protected static final RawAnimation THYLA_HOWL = RawAnimation.begin().thenPlay("animation.thylacine.howl");
    protected static final RawAnimation THYLA_YAWN = RawAnimation.begin().thenPlay("animation.thylacine.yawn");

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HOWLING_TIME, 0);
        this.entityData.define(HOWLING, false);
        this.entityData.define(VARIANT, 0);
        this.entityData.define(YAWNING_TIME, 0);
        this.entityData.define(YAWNING, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("howlingTime", this.getHowlingTime());
        compound.putBoolean("isHowling", this.getIsHowling());
        compound.putInt("variant", this.getVariant());
        compound.putInt("yawningTime", this.getYawningTime());
        compound.putBoolean("isYawning", this.getIsYawning());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setHowlingTime(compound.getInt("howlingTime"));
        this.setIsHowling(compound.getBoolean("isHowling"));
        this.setVariant(compound.getInt("variant"));
        this.setYawningTime(compound.getInt("yawningTime"));
        this.setIsYawning(compound.getBoolean("isYawning"));
    }

    //howling
    public int getHowlingTime(){
        return this.entityData.get(HOWLING_TIME);}

    public void setHowlingTime(int howlingTicksTime){
        this.entityData.set(HOWLING_TIME, howlingTicksTime);}

    public boolean getIsHowling() {
        return this.entityData.get(HOWLING);}

    public void setIsHowling(boolean howling) {
        this.entityData.set(HOWLING, howling);}

    //variant stuff
    public void determineVariant(int variantChange){
        this.setVariant(0);}

    public int getVariant() {
        return this.entityData.get(VARIANT);}

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);}

    //yawning
    public int getYawningTime(){
        return this.entityData.get(YAWNING_TIME);}

    public void setYawningTime(int yawningTicksTime){
        this.entityData.set(YAWNING_TIME, yawningTicksTime);}

    public boolean getIsYawning() {
        return this.entityData.get(YAWNING);}

    public void setIsYawning(boolean yawning) {
        this.entityData.set(YAWNING, yawning);}

    public Item getWoolType(int variantColor){
        return switch(variantColor){
            case 1 ->  Items.RED_WOOL;
            case 2 ->  Items.ORANGE_WOOL;
            case 3 ->  Items.YELLOW_WOOL;
            case 4 ->  Items.LIME_WOOL;
            case 5 ->  Items.GREEN_WOOL;
            case 6 ->  Items.CYAN_WOOL;
            case 7 ->  Items.LIGHT_BLUE_WOOL;
            case 8 ->  Items.BLUE_WOOL;
            case 9 ->  Items.PURPLE_WOOL;
            case 10 ->  Items.MAGENTA_WOOL;
            case 11 ->  Items.PINK_WOOL;
            case 12 ->  Items.BROWN_WOOL;
            case 13 ->  Items.BLACK_WOOL;
            case 14 ->  Items.GRAY_WOOL;
            case 15 ->  Items.LIGHT_GRAY_WOOL;
            case 16 ->  Items.WHITE_WOOL;
            default -> Items.AIR;
        };
    }

    public void travel(Vec3 pTravelVector) {
        if (this.getIsHowling() || this.getIsYawning()) {
            if (this.getNavigation().getPath() != null) {
                this.getNavigation().stop();
            }
            pTravelVector = Vec3.ZERO;
            super.travel(pTravelVector);
        } else {
            super.travel(pTravelVector);
        }
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if ((itemstack.is(Items.CHICKEN) || itemstack.is(Items.RABBIT) || itemstack.is(Items.COOKED_CHICKEN) || itemstack.is(Items.COOKED_RABBIT))
                && !this.getIsHowling() && this.onGround() && !this.getIsYawning()) {
            this.usePlayerItem(player, hand, itemstack);
            this.setHowlingTime(howlTickDuration);
            return InteractionResult.SUCCESS;
        } else if (itemstack.is(ItemTags.WOOL)){

            if (this.getVariant()!=0){this.spawnAtLocation(getWoolType(this.getVariant()));}
            if (itemstack.is(Items.RED_WOOL)){ this.setVariant(1); }
            else if (itemstack.is(Items.ORANGE_WOOL)){this.setVariant(2);}
            else if (itemstack.is(Items.YELLOW_WOOL)){this.setVariant(3);}
            else if (itemstack.is(Items.LIME_WOOL)){this.setVariant(4);}
            else if (itemstack.is(Items.GREEN_WOOL)){this.setVariant(5);}
            else if (itemstack.is(Items.CYAN_WOOL)){this.setVariant(6);}
            else if (itemstack.is(Items.LIGHT_BLUE_WOOL)){this.setVariant(7);}
            else if (itemstack.is(Items.BLUE_WOOL)){this.setVariant(8);}
            else if (itemstack.is(Items.PURPLE_WOOL)){this.setVariant(9);}
            else if (itemstack.is(Items.MAGENTA_WOOL)){this.setVariant(10);}
            else if (itemstack.is(Items.PINK_WOOL)){this.setVariant(11);}
            else if (itemstack.is(Items.BROWN_WOOL)){this.setVariant(12);}
            else if (itemstack.is(Items.BLACK_WOOL)){this.setVariant(13);}
            else if (itemstack.is(Items.GRAY_WOOL)){this.setVariant(14);}
            else if (itemstack.is(Items.LIGHT_GRAY_WOOL)){this.setVariant(15);}
            else {this.setVariant(16);}

            this.playSound(SoundEvents.LLAMA_SWAG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            return InteractionResult.SUCCESS;

        } else if (itemstack.is(Items.SHEARS) && getVariant()!= 0){

            this.spawnAtLocation(this.getWoolType(this.getVariant()));
            this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.setVariant(0);
            return InteractionResult.SUCCESS;

        } else {
            return type;
        }

    }

    private void alertThreats() {
        List<LivingEntity> list = this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(24, 12, 24));
        for (LivingEntity target : list) {
            if(!(target instanceof ThylacineEntity) && target.getType().is(ModTags.EntityTypes.THYLA_ALERT_TARGET)){
                target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 45));
            }
        }
    }

    public void tick (){
        super.tick();

        //handles howling
        if (this.getHowlingTime() > 0){
            if (this.getIsHowling()){
                this.getNavigation().stop();
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            } else {
                this.setIsHowling(true);
            }
            if (this.getHowlingTime()==30 && !this.level().isClientSide){
                alertThreats();}
            prevHowlTime = this.getHowlingTime();
            this.setHowlingTime(prevHowlTime - 1);
        } else if (getIsHowling()){
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
            this.setIsHowling(false);
        }

        //handles yawning
        if (this.getRandom().nextInt(5000) == 0 && !this.getIsYawning() && this.onGround() && !this.getIsHowling()){
            this.setYawningTime(60);
        }
        if (this.getYawningTime()>0){

            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);

            if (this.getIsYawning()){
                this.getNavigation().stop();
            } else {
                this.setIsYawning(true);
            }
            prevYawnTime = this.getYawningTime();
            this.setYawningTime(prevYawnTime - 1);
        }
        else if (getIsYawning()){
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
            this.setIsYawning(false);
        }

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 10, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        } else if (this.getIsHowling() && this.onGround() && !this.getIsYawning()){
            event.setAndContinue(THYLA_HOWL);
        } else if (this.getIsYawning() && this.onGround() && !this.getIsHowling()){
            event.setAndContinue(THYLA_YAWN);
        }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.onGround()) {
            event.setAndContinue(THYLA_WALK);
            if (this.isBaby()){
                event.getController().setAnimationSpeed(1.65);
            }
            else {
                event.getController().setAnimationSpeed(1.15);
            }
        } else if (this.onGround() && !this.getIsHowling()){
                event.setAndContinue(THYLA_IDLE);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        if (pFallDistance > 1.0F) {
            this.playSound(SoundEvents.HORSE_LAND, 0F, 1.0F);
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

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
