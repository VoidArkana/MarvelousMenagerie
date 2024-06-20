package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.IBookEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.item.ModItems;
import net.voidarkana.marvelous_menagerie.sound.ModSounds;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class StellerEntity extends WaterAnimal implements GeoEntity, IBookEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    static final TargetingConditions SWIM_WITH_PLAYER_TARGETING = TargetingConditions.forNonCombat().range(10.0D).ignoreLineOfSight();
    private static final EntityDataAccessor<Boolean> FROM_BOOK = SynchedEntityData.defineId(StellerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(StellerEntity.class, EntityDataSerializers.INT);

    private boolean persistenceRequired;

    protected static final RawAnimation STELLER_SWIM = RawAnimation.begin().thenLoop("animation.steller_sea_cow.swim");
    protected static final RawAnimation STELLER_IDLE = RawAnimation.begin().thenLoop("animation.steller_sea_cow.idle");
    protected static final RawAnimation STELLER_BEACHED = RawAnimation.begin().thenLoop("animation.steller_sea_cow.beached");

    public StellerEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
        this.moveControl = new StellerSwimmingController(this, 85, 10, 0.02F, 0.01F, true);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 50.0)
                .add(Attributes.ARMOR, 10.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.MOVEMENT_SPEED, (double)0.8F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1));
        this.goalSelector.addGoal(2, new StellerEntity.SeaCowSwimWithPlayerGoal(this, 2D));
        this.goalSelector.addGoal(3, new StellerEntity.RandomShallowSwimmingGoal(this, 1, 10));
        //this.goalSelector.addGoal(3, new SeaCowEatKelpGoal(this);
        this.goalSelector.addGoal(4, new StellerEntity.SeaCowBreachGoal(this)); //rework into tick
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F, 0.02F, true));
    }

    //data
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BOOK, false);
        this.entityData.define(VARIANT, 0);
        //this.entityData.define(ROTATION, 0F);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("variant", this.getVariant());
        //pCompound.putFloat("rotation", this.getRotation());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("variant"));
        //this.setRotation(pCompound.getInt("rotation"));
    }

    /*public boolean isStellar() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("stellar");
    }*/

    /*rotation
    public void setRotation(float rotation) {
        this.entityData.set(ROTATION, rotation);
    }

    public Float getRotation() {
        return this.entityData.get(ROTATION);
    }*/

    //variant
    public void determineVariant(int variantChange) {
        this.setVariant(0);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public int getVariant() {
        return (Integer)this.entityData.get(VARIANT);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        this.setXRot(0.0F);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    //underwater navigation
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public void tick() {
        super.tick();

        //if it gets too deep it goes up
        if ( this.isInWater() && this.getFluidHeight(FluidTags.WATER) > 3 || this.isInFluidType((fluidType, height) -> this.canSwimInFluidType(fluidType) && height > 3)){
            this.findAirPosition();
            this.moveRelative(0.02F, new Vec3(this.xxa, this.yya, this.zza));
            this.move(MoverType.SELF, this.getDeltaMovement());
        }

    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.BUCKET)) {
            pPlayer.playSound(SoundEvents.COW_MILK, 1.0F, 1.0F);
            ItemStack itemstack1 = ItemUtils.createFilledResult(itemstack, pPlayer, ModItems.STELLER_MILK.get().getDefaultInstance());
            pPlayer.setItemInHand(pHand, itemstack1);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(pPlayer, pHand);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 20, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.isInWater()) {
            event.setAndContinue(STELLER_SWIM);
            event.getController().setAnimationSpeed(1f);
        } else if (this.onGround() && !this.isInWater()){
            event.setAndContinue(STELLER_BEACHED);
        }
        else if (this.isInWater()){
            event.setAndContinue(STELLER_IDLE);
        }
        return PlayState.CONTINUE;
    }


    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public boolean isFromBook() {
        return (Boolean)this.entityData.get(FROM_BOOK);
    }

    public void setFromBook(boolean fromBook) {
        this.entityData.set(FROM_BOOK, fromBook);
    }

    public void checkDespawn() {
        this.noActionTime = 0;
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0, -0.006, 0.0));
            }
        } else {
            super.travel(travelVector);
        }

    }

    public class StellerSwimmingController extends MoveControl {
        private static final float FULL_SPEED_TURN_THRESHOLD = 10.0F;
        private static final float STOP_TURN_THRESHOLD = 60.0F;
        private final int maxTurnX;
        private final int maxTurnY;
        private final float inWaterSpeedModifier;
        private final float outsideWaterSpeedModifier;
        private final boolean applyGravity;
        private final StellerEntity stellerEntity;

        public StellerSwimmingController(StellerEntity stellerEntityIn, int pMaxTurnX, int pMaxTurnY, float pInWaterSpeedModifier, float pOutsideWaterSpeedModifier, boolean pApplyGravity) {
            super(stellerEntityIn);
            this.stellerEntity = stellerEntityIn;
            this.maxTurnX = pMaxTurnX;
            this.maxTurnY = pMaxTurnY;
            this.inWaterSpeedModifier = pInWaterSpeedModifier;
            this.outsideWaterSpeedModifier = pOutsideWaterSpeedModifier;
            this.applyGravity = pApplyGravity;
        }

        public void tick() {

            if (this.applyGravity && this.stellerEntity.isInWater() && this.stellerEntity.getFluidHeight(FluidTags.WATER)>0.1F) {
                this.stellerEntity.setDeltaMovement(this.stellerEntity.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
            } else if (this.stellerEntity.isInWater() && this.stellerEntity.getNavigation().isDone()) {
                this.stellerEntity.setDeltaMovement(this.stellerEntity.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            } else if (this.stellerEntity.isInWater() && this.stellerEntity.getFluidHeight(FluidTags.WATER)>3F){
                this.stellerEntity.setDeltaMovement(this.stellerEntity.getDeltaMovement().add(0.0D, 0.5D, 0.0D));
            }

            if (this.operation == MoveControl.Operation.MOVE_TO && !this.stellerEntity.getNavigation().isDone()) {
                double d0 = this.wantedX - this.stellerEntity.getX();
                double d1 = this.wantedY - this.stellerEntity.getY();
                double d2 = this.wantedZ - this.stellerEntity.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                if (d3 < (double)2.5000003E-7F) {
                    this.stellerEntity.setZza(0.0F);
                } else {
                    float f = (float)(Mth.atan2(d2, d0) * (double)(180F / (float)Math.PI)) - 90.0F;
                    this.stellerEntity.setYRot(this.rotlerp(this.stellerEntity.getYRot(), f, (float)this.maxTurnY));

                    this.stellerEntity.yBodyRot = this.stellerEntity.getYRot();
                    this.stellerEntity.yHeadRot = this.stellerEntity.getYRot();


                    float f1 = (float)(this.speedModifier * this.stellerEntity.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    if (this.stellerEntity.isInWater()) {
                        this.stellerEntity.setSpeed(f1 * this.inWaterSpeedModifier);
                        double d4 = Math.sqrt(d0 * d0 + d2 * d2);
                        if (Math.abs(d1) > (double)1.0E-5F || Math.abs(d4) > (double)1.0E-5F) {
                            float f3 = -((float)(Mth.atan2(d1, d4) * (double)(180F / (float)Math.PI)));
                            f3 = Mth.clamp(Mth.wrapDegrees(f3), (float)(-this.maxTurnX), (float)this.maxTurnX);
                            this.stellerEntity.setXRot(this.rotlerp(this.stellerEntity.getXRot(), f3, 5.0F));
                        }

                        float f6 = Mth.cos(this.stellerEntity.getXRot() * ((float)Math.PI / 180F));
                        float f4 = Mth.sin(this.stellerEntity.getXRot() * ((float)Math.PI / 180F));

                        this.stellerEntity.zza = f6 * f1;
                        this.stellerEntity.yya = -f4 * f1;

                    } else {
                        float f5 = Math.abs(Mth.wrapDegrees(this.stellerEntity.getYRot() - f));
                        float f2 = getTurningSpeedFactor(f5);
                        this.stellerEntity.setSpeed(f1 * this.outsideWaterSpeedModifier * f2);
                    }
                }
            } else {
                this.stellerEntity.setSpeed(0.0F);
                this.stellerEntity.setXxa(0.0F);
                this.stellerEntity.setYya(0.0F);
                this.stellerEntity.setZza(0.0F);
            }


        }

        private static float getTurningSpeedFactor(float p_249853_) {
            return 1.0F - Mth.clamp((p_249853_ - 10.0F) / 50.0F, 0.0F, 1.0F);
        }
    }

    static class SeaCowSwimWithPlayerGoal extends Goal {
        private final StellerEntity dolphin;
        private final double speedModifier;
        @Nullable
        private Player player;

        SeaCowSwimWithPlayerGoal(StellerEntity pDolphin, double pSpeedModifier) {
            this.dolphin = pDolphin;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.player = this.dolphin.level().getNearestPlayer(StellerEntity.SWIM_WITH_PLAYER_TARGETING, this.dolphin);
            if (this.player == null) {
                return false;
            } else {
                return this.player.isSwimming() && this.dolphin.getTarget() != this.player && this.player.getFluidHeight(FluidTags.WATER) < 5;
            }
        }

        public boolean canContinueToUse() {
            return this.player != null && this.player.isSwimming() && this.dolphin.distanceToSqr(this.player) < 256.0D && this.player.getFluidHeight(FluidTags.WATER) < 5;
        }

        public void start() {
            this.player.addEffect(new MobEffectInstance(ModEffects.SEA_COW_SERENITY.get(), 50), this.dolphin);
        }

        public void stop() {
            this.player = null;
            this.dolphin.getNavigation().stop();
        }

        public void tick() {
            this.dolphin.getLookControl().setLookAt(this.player, (float)(this.dolphin.getMaxHeadYRot() + 20), (float)this.dolphin.getMaxHeadXRot());
            if (this.dolphin.distanceToSqr(this.player) < 8.25D) {
                this.dolphin.getNavigation().stop();
            } else {
                this.dolphin.getNavigation().moveTo(this.player, this.speedModifier);
            }

            if (this.player.isSwimming() && this.player.level().random.nextInt(6) == 0) {
                this.player.addEffect(new MobEffectInstance(ModEffects.SEA_COW_SERENITY.get(), 50), this.dolphin);
            }
        }
    }

    public class RandomShallowSwimmingGoal extends RandomStrollGoal {
        public RandomShallowSwimmingGoal(PathfinderMob mob, double speedMod, int interval) {
            super(mob, speedMod, interval);}

        @Nullable
        protected Vec3 getPosition() {
            return BehaviorUtils.getRandomSwimmablePos(this.mob, 7, 1);
        }
    }

    public class SeaCowBreachGoal extends Goal {
        private final StellerEntity mob;

        public SeaCowBreachGoal(StellerEntity pMob) {
            this.mob = pMob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return this.mob.isInWater() && this.mob.getRandom().nextInt( 5000) == 0;
        }

        public boolean canContinueToUse() {
            return this.mob.isInWater() && !(this.mob.getLastHurtByMob() != null || this.mob.isFreezing() || this.mob.isOnFire());
        }

        public boolean isInterruptable() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void start() {
            this.mob.findAirPosition();
        }

        public void tick() {
            this.mob.findAirPosition();
            this.mob.moveRelative(0.02F, new Vec3((double)this.mob.xxa, (double)this.mob.yya, (double)this.mob.zza));
            this.mob.move(MoverType.SELF, this.mob.getDeltaMovement());
            if (this.mob.getFluidHeight(FluidTags.WATER) <= 0.5){
                int i;
                for (i=0; i<60; i++){
                    this.mob.getNavigation().stop();
                    this.mob.goalSelector.getRunningGoals().forEach(WrappedGoal::stop);
                }
                if (i==60){
                    this.mob.goalSelector.getRunningGoals().forEach(WrappedGoal::start);
                    this.stop();
                }
            }
        }
    }

    private void findAirPosition() {
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.getX() - 1.0D), this.getBlockY(), Mth.floor(this.getZ() - 1.0D),
                Mth.floor(this.getX() + 1.0D), Mth.floor(this.getY() + 8.0D), Mth.floor(this.getZ() + 1.0D));
        BlockPos blockpos = null;

        for(BlockPos blockpos1 : iterable) {
            if (this.givesAir(this.level(), blockpos1)) {
                blockpos = blockpos1;
                break;
            }
        }

        if (blockpos == null) {
            blockpos = BlockPos.containing(this.getX(), this.getY() + 8.0D, this.getZ());
        }
        this.getNavigation().moveTo((double)blockpos.getX(), (double)(blockpos.getY() + 1), (double)blockpos.getZ(), 0.8D);
    }

    private boolean givesAir(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (pLevel.getFluidState(pPos).isEmpty() && blockstate.isPathfindable(pLevel, pPos, PathComputationType.LAND));
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.DOLPHIN_SWIM;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0F, 1.0F);
    }

    public boolean canBeCollidedWith() {
        return !this.isEyeInFluid(FluidTags.WATER) && !this.onGround();
    }

    public void setPersistenceRequired() {
        this.persistenceRequired = false;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public void aiStep() {
        super.aiStep();
    }

    protected SoundEvent getAmbientSound() {
        if (this.isEyeInFluid(FluidTags.WATER)){
            return ModSounds.STELLER_IDLE.get();
        }else {
            return ModSounds.DOLPHIN_BLOWHOLE.get();
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.isEyeInFluid(FluidTags.WATER)){
            return ModSounds.STELLER_HURT.get();
        }else {
            return ModSounds.STELLER_LAND_HURT.get();
        }
    }

    protected SoundEvent getDeathSound() {
            return ModSounds.STELLER_DEATH.get();
    }

}
