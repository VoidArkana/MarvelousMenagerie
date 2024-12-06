package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.CustomFollower;
import com.peeko32213.unusualprehistory.common.entity.msc.util.CustomRandomStrollGoal;
import com.peeko32213.unusualprehistory.common.entity.msc.util.CustomRideGoal;
import com.peeko32213.unusualprehistory.common.entity.msc.util.TameableFollowOwner;
import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityTameableBaseDinosaurAnimal;
import com.peeko32213.unusualprehistory.core.registry.UPItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class JosephoEntity extends EntityTameableBaseDinosaurAnimal implements CustomFollower {

    public Vec3 movement;

    static final TargetingConditions ADULT_TO_RIDE = TargetingConditions.forNonCombat().range(15.0D).ignoreLineOfSight();

    //80
    private static final EntityDataAccessor<Integer> SITTING_TIME = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);
    //140
    private static final EntityDataAccessor<Integer> STANDING_TIME = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> SITTING_LAG = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> WIGGLING_TIME = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> WIGGLING_TYPE = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> COMMAND = SynchedEntityData.defineId(JosephoEntity.class, EntityDataSerializers.INT);

    public JosephoEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.0F);
    }

    protected static final RawAnimation JOSEPHO_WALK = RawAnimation.begin().thenLoop("animation.josepho.walk");
    protected static final RawAnimation JOSEPHO_SWIM = RawAnimation.begin().thenLoop("animation.josepho.swim");

    protected static final RawAnimation JOSEPHO_IDLE = RawAnimation.begin().thenLoop("animation.josepho.idle");
    protected static final RawAnimation JOSEPHO_IDLE_SIT = RawAnimation.begin().thenLoop("animation.josepho.idle_sitting");

    protected static final RawAnimation JOSEPHO_SITTING = RawAnimation.begin().thenPlayAndHold("animation.josepho.sit_start");
    protected static final RawAnimation JOSEPHO_STANDING_UP = RawAnimation.begin().thenPlayAndHold("animation.josepho.sit_end");

    protected static final RawAnimation JOSEPHO_EARS_WIGGLE = RawAnimation.begin().thenPlay("animation.josepho.ear_wiggle_both");
    protected static final RawAnimation JOSEPHO_R_EAR_WIGGLE = RawAnimation.begin().thenPlay("animation.josepho.ear_wiggle_right");
    protected static final RawAnimation JOSEPHO_L_EAR_WIGGLE = RawAnimation.begin().thenPlay("animation.josepho.ear_wiggle_left");

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 20.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(1, new DismountGoal(this));
        this.goalSelector.addGoal(1, new MountAdultGoal(this, 1.2D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(1, new CustomRideGoal(this, 1));
        this.goalSelector.addGoal(3, new TameableFollowOwner(this, 1.2, 5.0F, 2.0F, false));
        //this.goalSelector.addGoal(3, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        //this.goalSelector.addGoal(3, new CustomRandomStrollGoal(this, 30, 1.0, 100, 34));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F){
            @Override
            public boolean canUse() {
                return super.canUse() && !JosephoEntity.this.isVehicle();
            }
        });
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this){
            @Override
            public boolean canUse() {
                return super.canUse() && (!JosephoEntity.this.isVehicle() || !JosephoEntity.this.isPassenger());
            }
        });
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SADDLED, false);
        this.entityData.define(COMMAND, 0);
        this.entityData.define(SITTING_TIME, 0);
        this.entityData.define(SITTING_LAG, 0);
        this.entityData.define(STANDING_TIME, 0);
        this.entityData.define(WIGGLING_TIME, 0);
        this.entityData.define(WIGGLING_TYPE, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("Saddle", this.isSaddled());
        compound.putInt("Command", this.getCommand());
        compound.putInt("StandingTime", this.getStandingTime());
        compound.putInt("SittingTime", this.getSittingTime());
        compound.putInt("SittingLag", this.getSittingLag());
        compound.putInt("WigglingTime", this.getWigglingTime());
        compound.putInt("WigglingType", this.getWigglingType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setSaddled(compound.getBoolean("Saddle"));
        this.setCommand(compound.getInt("Command"));
        this.setStandingTime(compound.getInt("StandingTime"));
        this.setSittingTime(compound.getInt("SittingTime"));
        this.setSittingLag(compound.getInt("SittingLag"));
        this.setWigglingTime(compound.getInt("WigglingTime"));
        this.setWigglingType(compound.getInt("WigglingType"));
    }

    public int getCommand() {
        return this.entityData.get(COMMAND);
    }

    public void setCommand(int command) {
        this.entityData.set(COMMAND, command);
    }

    public int getWigglingTime() {
        return this.entityData.get(WIGGLING_TIME);
    }

    public void setWigglingTime(int command) {
        this.entityData.set(WIGGLING_TIME, command);
    }

    public int getWigglingType() {
        return this.entityData.get(WIGGLING_TYPE);
    }

    public void setWigglingType(int command) {
        this.entityData.set(WIGGLING_TYPE, command);
    }

    public boolean isSaddled() {
        return this.entityData.get(SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(SADDLED, saddled);
    }

    public int getStandingTime() {
        return this.entityData.get(STANDING_TIME);
    }

    public void setStandingTime(int command) {
        this.entityData.set(STANDING_TIME, command);
    }

    public int getSittingTime() {
        return this.entityData.get(SITTING_TIME);
    }

    public void setSittingTime(int command) {
        this.entityData.set(SITTING_TIME, command);
    }

    public int getSittingLag() {
        return this.entityData.get(SITTING_LAG);
    }

    public void setSittingLag(int command) {
        this.entityData.set(SITTING_LAG, command);
    }

    public boolean shouldFollow() {
        return this.getCommand() == 1;
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.isSaddled()) {
            this.spawnAtLocation(Items.SADDLE);
        }
    }

    public boolean isAlliedTo(Entity entityIn) {
        if (this.isTame()) {
            LivingEntity livingentity = this.getOwner();
            if (entityIn == livingentity) {
                return true;
            }

            if (entityIn instanceof TamableAnimal) {
                return ((TamableAnimal)entityIn).isOwnedBy(livingentity);
            }

            if (livingentity != null) {
                return livingentity.isAlliedTo(entityIn);
            }
        }

        return entityIn.is(this);
    }

    @Override
    public boolean dismountsUnderwater() {
        return true;
    }

    public void travel(Vec3 pos) {
        if (this.isAlive()) {
            LivingEntity livingentity = this.getControllingPassenger();
            if (this.isVehicle() && livingentity instanceof Player) {

                float f = livingentity.xxa * 0.5F;
                float f1 = livingentity.zza;
                if (f1 <= 0.0F) {
                    f1 *= 0.25F;
                }
                double moveY = pos.y;

                if (this.isControlledByLocalInstance() && this.isInWater()){
                    moveY = Minecraft.getInstance().options.keyJump.isDown() ? 5F : 0F;
                }

                this.setSpeed(0.1F);

                Vec3 vec = new Vec3(f, moveY, f1);

                super.travel(vec);

            } else {
                super.travel(pos);
            }
        }
    }

    @Override
    public boolean canBeCollidedWith() {
        return this.isInSittingPose() || this.getStandingTime()>0 || this.getSittingTime()>0;
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return !this.isInSittingPose() && !(this.getSittingTime()>0 || this.getStandingTime()>0) && !this.isVehicle();
    }

    public boolean isFood(ItemStack stack) {
        return stack.is(Items.APPLE);
    }


    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.is(UPItems.ENCYLOPEDIA.get()) || itemstack.is(ModItems.JOSEPHO_EMBRYO.get())) {
            return super.mobInteract(player, hand);
        } else if (hand == InteractionHand.MAIN_HAND && this.isFood(itemstack) && !this.isTame() && !this.isBaby()) {

            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            this.playSound(SoundEvents.HORSE_EAT);

            if (this.random.nextInt(5) == 0) {
                this.tame(player);
                this.navigation.stop();
                this.level().broadcastEntityEvent(this, (byte)7);
            }else{
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;

        } else if (hand == InteractionHand.MAIN_HAND && !this.level().isClientSide && this.isTame() && this.isOwnedBy(player)
            && this.getStandingTime()==0 && this.getSittingTime()==0) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                this.gameEvent(GameEvent.EAT, this);
            } else if (itemstack.getItem() == Items.SADDLE && !this.isSaddled()) {
                this.usePlayerItem(player, hand, itemstack);
                this.playSound(SoundEvents.HORSE_SADDLE);
                this.setSaddled(true);
            } else if (itemstack.getItem() == Items.SHEARS && this.isSaddled()) {
                this.setSaddled(false);
                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.spawnAtLocation(Items.SADDLE);
            } else if (!player.isShiftKeyDown() && !this.isBaby() && this.isSaddled() && !this.isInSittingPose() &&
                    this.getStandingTime() == 0 && this.getSittingTime() == 0 && !this.isInWater()) {
                player.startRiding(this);
            } else {
                this.setCommand((this.getCommand() + 1) % 3);
                if (this.getCommand() == 3) {
                    this.setCommand(0);
                }

                int var10001 = this.getCommand();
                player.displayClientMessage(Component.translatable("entity.unusualprehistory.all.command_" + var10001, new Object[]{this.getName()}), true);
                boolean sit = this.getCommand() == 2;
                if (sit) {
                    this.setOrderedToSit(true);
                    if (!this.isInSittingPose() && this.onGround()){
                        this.setSittingTime(60);
                    }
                } else {
                    if (this.isInSittingPose() && this.onGround()){
                        this.setStandingTime(70);
                    }
                    this.setOrderedToSit(false);
                }
            }
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.isInSittingPose()) {
            return super.getDimensions(pPose).scale(1.0F, 0.575F);
        } else {
            return super.getDimensions(pPose);
        }
    }


    @Override
    public void tick() {

        super.tick();

        if (this.isVehicle()){
            movement = new Vec3(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        }

        if (this.isTame() && this.getSittingTime()==0 && this.getStandingTime()==0){
            refreshDimensions();
        }

        if (this.getWigglingTime() > 0){
            int prevWig = this.getWigglingTime();
            this.setWigglingTime(prevWig-1);
        }

        if ((this.getRandom().nextInt(0,500) == 0) && this.getWigglingTime()==0){
            this.setWigglingTime(20);
            this.setWigglingType(this.getRandom().nextInt(0, 100));
        }

        if (this.getSittingTime()>0){
            if (!this.getNavigation().isDone()){
                this.getNavigation().stop();
            }
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            int prev = this.getSittingTime();

            if (this.getSittingTime()<6)
                this.setSittingLag(getSittingTime()+5);

            this.setSittingTime(prev-1);
        } else if (this.getSittingLag()>0) {
            if(this.getSittingLag()==1){
                this.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
            }
            int prev = this.getSittingLag();
            this.setSittingLag(prev-1);
        }

        if (this.isInSittingPose()){
            this.getNavigation().stop();
        }

        if (this.getStandingTime()>0){
            if (!this.getNavigation().isDone()){
                this.getNavigation().stop();
            }
            this.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
            int prev = this.getStandingTime();
            this.setStandingTime(prev-1);
        }


    }

    @Override
    protected void tickRidden(Player pPlayer, Vec3 pTravelVector) {

        super.tickRidden(pPlayer, pTravelVector);

        this.setRot(pPlayer.getYRot(),pPlayer.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!level().isClientSide && level().getGameTime() % 20 == 0) {
            for (LivingEntity living : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20.0D, 10.0D, 20.0D))) {
                    living.addEffect(new MobEffectInstance(ModEffects.PACIFIED.get(), 100, 0, false, false));
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
        controllers.add(new AnimationController[]{new AnimationController(this, "EarController", 0, this::EarController)});
        controllers.add(new AnimationController[]{new AnimationController(this, "SitController", 5, this::SitController)});
    }

    private <E extends JosephoEntity> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook() || this.isInSittingPose() || this.getSittingTime() > 0 || this.getStandingTime() > 0 || (this.getSittingLag() < 7 && this.getSittingLag() > 0)){
            return PlayState.STOP;
        } else {
            if (this.isPassenger()) {
                event.setAndContinue(JOSEPHO_IDLE);
            } else if (this.isInWater() && !this.onGround()) {
                event.setAndContinue(JOSEPHO_SWIM);
            } else if (!this.isInSittingPose() && this.getSittingTime() <= 0 && this.getStandingTime() <= 0) {

                if (this.isVehicle() && this.movement != null){
                    if (this.movement.horizontalDistanceSqr()>0.0001){
                        event.setAndContinue(JOSEPHO_WALK);
                    }
                }

                if (!this.isInSittingPose() &&  this.getDeltaMovement().horizontalDistanceSqr() > 0.000001 && this.getSittingTime() <= 0 && this.getStandingTime() <= 0) {

                    event.setAndContinue(JOSEPHO_WALK);
                    event.getController().setAnimationSpeed(this.isBaby() ? 1.5 : this.isVehicle() ? this.isInWater() && this.onGround() ? 0.6 : 1.1 : 0.8);

                } else if (this.onGround()) {
                    event.setAndContinue(JOSEPHO_IDLE);
                }
            }

            return PlayState.CONTINUE;
        }
    }

    protected <E extends JosephoEntity> PlayState EarController(AnimationState<E> event) {
        if (!(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) && this.getSittingTime() == 0
                && this.getStandingTime()==0 && this.getWigglingTime()>0) {
            if (this.getWigglingType() < 50){
                event.setAndContinue(JOSEPHO_EARS_WIGGLE);
            } else if (this.getWigglingType() < 75) {
                event.setAndContinue(JOSEPHO_R_EAR_WIGGLE);
            }else {
                event.setAndContinue(JOSEPHO_L_EAR_WIGGLE);
            }
            return PlayState.CONTINUE;
        } else {
            event.getController().forceAnimationReset();
            return PlayState.STOP;
        }
    }

    protected <E extends JosephoEntity> PlayState SitController(AnimationState<E> event) {
        if (this.onGround() && (this.isInSittingPose() || (this.getSittingLag() < 7 && this.getSittingLag() > 0))){
            event.setAndContinue(JOSEPHO_IDLE_SIT);
            return PlayState.CONTINUE;
        }else if (this.getSittingTime()>0) {
            event.setAndContinue(JOSEPHO_SITTING);
            return PlayState.CONTINUE;
        } else if (this.getStandingTime()>0) {
            event.setAndContinue(JOSEPHO_STANDING_UP);
            return PlayState.CONTINUE;
        } else {
            event.getController().forceAnimationReset();
            return PlayState.STOP;
        }
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.JOSEPHO_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.JOSEPHO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.JOSEPHO_DEATH.get();
    }

    @Override
    protected void performAttack() {}

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

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(ModSounds.LARGE_STEPS.get());
    }

    public static class MountAdultGoal extends Goal {

        private final JosephoEntity childAnimal;
        private final double moveSpeed;
        @javax.annotation.Nullable
        private JosephoEntity josepho;

        public MountAdultGoal(JosephoEntity child, double speed) {
            this.childAnimal = child;
            this.moveSpeed = speed;
        }

        @Override
        public boolean canUse() {

            if(!this.childAnimal.isPassenger() && this.childAnimal.isBaby()) {
                this.josepho = this.childAnimal.level().getNearestEntity(JosephoEntity.class, JosephoEntity.ADULT_TO_RIDE, this.childAnimal, this.childAnimal.getX(), this.childAnimal.getY(), this.childAnimal.getZ(), this.childAnimal.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
                if (josepho != null && !josepho.isBaby() && !josepho.isVehicle() && josepho.isInSittingPose()) {
                    this.childAnimal.getNavigation().moveTo(this.childAnimal.getNavigation().createPath(josepho, 0), this.moveSpeed);
                    return true;
                }
            }

            return false;
        }


        @Override
        public void tick() {
            JosephoEntity adult = this.childAnimal.level().getNearestEntity(JosephoEntity.class, JosephoEntity.ADULT_TO_RIDE, this.childAnimal, this.childAnimal.getX(), this.childAnimal.getY(), this.childAnimal.getZ(), this.childAnimal.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));

            if (adult!= null){
                if (!adult.equals(this.childAnimal) && !adult.isBaby() && !adult.isVehicle()) {
                    this.childAnimal.startRiding(adult);
                }
            }

        }

        public boolean canContinueToUse() {
            return !this.childAnimal.isPassenger();
        }

    }
    
    public int getMaxHeadYRot() {
        return 30;
    }

    static class DismountGoal extends Goal {

        private final Animal childAnimal;

        public DismountGoal(Animal child) {
            this.childAnimal = child;
        }

        @Override
        public boolean canUse() {
            Entity entity = this.childAnimal.getVehicle();
            if (entity instanceof JosephoEntity josepho){
                if ((this.childAnimal.getRandom().nextInt(250) == 0 || (this.childAnimal.isPassenger() && !this.childAnimal.isBaby())) || !josepho.isInSittingPose()) {
                    return true;
                }
            }

            return false;

        }

        @Override
        public void tick() {
            if (this.childAnimal.isPassenger()) {
                this.childAnimal.stopRiding();
            }
        }
    }

}
