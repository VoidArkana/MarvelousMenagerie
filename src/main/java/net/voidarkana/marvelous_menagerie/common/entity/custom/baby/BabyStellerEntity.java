package net.voidarkana.marvelous_menagerie.common.entity.custom.baby;

import com.peeko32213.unusualprehistory.common.entity.IBookEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.custom.StellerEntity;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
import net.voidarkana.marvelous_menagerie.util.ModTags;
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

public class BabyStellerEntity extends WaterAnimal implements GeoEntity, IBookEntity, Bucketable {
    static final TargetingConditions SWIM_WITH_ADULT_TARGETING = TargetingConditions.forNonCombat().range(15.0D).ignoreLineOfSight();

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public float prevTilt;
    public float tilt;

    public static final Ingredient FOOD_ITEMS = Ingredient.of(Items.KELP);
    public static final int MAX_TADPOLE_AGE = Math.abs(-30000);
    private int age;
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(BabyStellerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BOOK = SynchedEntityData.defineId(BabyStellerEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> CAN_GROW = SynchedEntityData.defineId(BabyStellerEntity.class, EntityDataSerializers.BOOLEAN);

    protected static final RawAnimation BABY_STELLER_SWIM = RawAnimation.begin().thenLoop("animation.baby_steller_sea_cow.swim");
    protected static final RawAnimation BABY_STELLER_IDLE = RawAnimation.begin().thenLoop("animation.baby_steller_sea_cow.idle");
    protected static final RawAnimation BABY_STELLER_BEACHED = RawAnimation.begin().thenLoop("animation.baby_steller_sea_cow.flop");

    public BabyStellerEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.lookControl = new SmoothSwimmingLookControl(this, 5);
        this.moveControl = new StellerSwimmingController(this, 85, 10, 0.02F, 0.8F, true);

        WaterBoundPathNavigation waterpathnavigation = (WaterBoundPathNavigation)this.getNavigation();
        waterpathnavigation.setCanFloat(false);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ARMOR, 0.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.6)
                .add(Attributes.MOVEMENT_SPEED, (double)0.8F);
    }

    public void aiStep() {

        if (!this.level().isClientSide && this.canGrow()){
            this.setAge(this.age + 1);
        }
        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F), (double)0.4F, (double)((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F)));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }

        super.aiStep();

        prevTilt = tilt;
        if (this.isInWater()) {
            final float v = Mth.degreesDifference(this.getYRot(), yRotO);
            if (Math.abs(v) > 1) {
                if (Math.abs(tilt) < 25) {
                    tilt -= Math.signum(v);
                }
            } else {
                if (Math.abs(tilt) > 0) {
                    final float tiltSign = Math.signum(tilt);
                    tilt -= tiltSign * 0.85F;
                    if (tilt * tiltSign < 0) {
                        tilt = 0;
                    }
                }
            }
        } else {
            tilt = 0;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1));
        this.goalSelector.addGoal(2, new BabyFollowAdultGoal(this, 1.1));
        this.goalSelector.addGoal(3, new RandomShallowSwimmingGoal(this, 1, 10));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F, 0.02F, true));
    }

    //data
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BOOK, false);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(CAN_GROW, true);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putBoolean("CanGrow", this.canGrow());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setCanGrow(pCompound.getBoolean("CanGrow"));
    }

    public boolean canGrow() {
        return this.entityData.get(CAN_GROW);
    }

    public void setCanGrow(boolean canGrow) {
        this.entityData.set(CAN_GROW, canGrow);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
        compoundnbt.putBoolean("CanGrow", this.canGrow());
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
        if (pTag.contains("Age")) {
            this.setAge(pTag.getInt("Age"));
        }
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.STELLER_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }


    //bucket stuff
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(Items.KELP)){

            this.decrementItem(pPlayer, itemstack);
            this.eatFood();
            this.playSound(SoundEvents.GENERIC_EAT);
            return InteractionResult.sidedSuccess(this.level().isClientSide);

        } else if (itemstack.is(ModTags.Items.FINTASTIC_BAD_FEED) && this.canGrow()) {

            this.decrementItem(pPlayer, itemstack);
            this.setCanGrow(false);
            this.playSound(SoundEvents.GENERIC_EAT);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.5), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);

        } else{
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
        }
    }

    //food
    private void eatFood() {
        if (!this.canGrow()){
            this.setCanGrow(true);
        }
        this.increaseAge((int)((float)(this.getTicksUntilGrowth() / 20) * 0.1F));
        for(int j = 0; j < 5; ++j) {
            this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.5), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
        }
    }

    private void decrementItem(Player player, ItemStack stack) {
        if (!player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }

    //age
    private int getTicksUntilGrowth() {
        return Math.max(0, MAX_TADPOLE_AGE - this.age);
    }

    private int getAge() {
        return this.age;
    }

    private void increaseAge(int seconds) {
        this.setAge(this.age + (seconds * 20));
    }

    private void setAge(int age) {
        this.age = age;
        if (this.age >= MAX_TADPOLE_AGE) {
            this.growUp();
        }
    }


    //grow up
    private void growUp() {
        Level var2 = this.level();
        if (var2 instanceof ServerLevel server) {
            StellerEntity frog = (StellerEntity)((EntityType) ModEntities.STELLER_SEA_COW.get()).create(this.level());
            if (frog == null) {
                return;
            }

            frog.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
            frog.finalizeSpawn(server, this.level().getCurrentDifficultyAt(frog.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData)null, (CompoundTag)null);
            frog.setNoAi(this.isNoAi());
            if (this.hasCustomName()) {
                frog.setCustomName(this.getCustomName());
                frog.setCustomNameVisible(this.isCustomNameVisible());
            }

            frog.setIsFromEgg(true);

            this.playSound(SoundEvents.PLAYER_LEVELUP, 0.15F, 1.0F);
            server.addFreshEntityWithPassengers(frog);
            this.discard();
        }
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {

        if (pReason == MobSpawnType.BUCKET && pDataTag != null && pDataTag.contains("CanGrow", 3)) {
            this.setCanGrow(pDataTag.getBoolean("CanGrow"));
            this.setFromBucket(true);
        }

        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    //underwater navigation
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    //air breathing
    public boolean canBreatheUnderwater() {
        return true;
    }

    public void tick() {

        if (!this.level().isClientSide) {

            //if it gets too deep it goes up
            if ( this.isInWater() && this.getFluidHeight(FluidTags.WATER) > 5F || this.isInFluidType((fluidType, height) -> this.canSwimInFluidType(fluidType) && height > 5F)){
                this.findAirPosition();
                this.moveRelative(0.02F, new Vec3(this.xxa, this.yya, this.zza));
                this.move(MoverType.SELF, this.getDeltaMovement());
            }
        }

        super.tick();
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 20, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.isInWater()) {
            event.setAndContinue(BABY_STELLER_SWIM);
            event.getController().setAnimationSpeed(1.25f);
        } else if (this.onGround() && !this.isInWater()){
            event.setAndContinue(BABY_STELLER_BEACHED);
        }
        else if (this.isInWater()){
            event.setAndContinue(BABY_STELLER_IDLE);
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

    public void setIsFromBook(boolean fromBook) {
        this.entityData.set(FROM_BOOK, fromBook);
    }

    public void setFromBook(boolean fromBook) {
        this.entityData.set(FROM_BOOK, fromBook);
    }

    //persistance stuff, only babies don't despawn because they can't spawn naturally
    public void checkDespawn() {
        this.noActionTime = 0;
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9));
        } else {
            super.travel(travelVector);
        }

    }

    protected SoundEvent getFlopSound() {
        return ModSounds.CREATURE_FLOPS.get();
    }

    protected SoundEvent getSwimSound() {
        return ModSounds.CREATURE_SWIM.get();
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0F, 1.0F);
    }

    public static class StellerSwimmingController extends MoveControl {
        private static final float FULL_SPEED_TURN_THRESHOLD = 10.0F;
        private static final float STOP_TURN_THRESHOLD = 60.0F;
        private final int maxTurnX;
        private final int maxTurnY;
        private final float inWaterSpeedModifier;
        private final float outsideWaterSpeedModifier;
        private final boolean applyGravity;
        private final BabyStellerEntity stellerEntity;

        public StellerSwimmingController(BabyStellerEntity stellerEntityIn, int pMaxTurnX, int pMaxTurnY, float pInWaterSpeedModifier, float pOutsideWaterSpeedModifier, boolean pApplyGravity) {
            super(stellerEntityIn);
            this.stellerEntity = stellerEntityIn;
            this.maxTurnX = pMaxTurnX;
            this.maxTurnY = pMaxTurnY;
            this.inWaterSpeedModifier = pInWaterSpeedModifier;
            this.outsideWaterSpeedModifier = pOutsideWaterSpeedModifier;
            this.applyGravity = pApplyGravity;
        }

        public void tick() {

            if (this.stellerEntity.isInWater() && this.stellerEntity.getNavigation().isDone() && this.stellerEntity.getFluidHeight(FluidTags.WATER) > 0.5F) {
                this.stellerEntity.setDeltaMovement(this.stellerEntity.getDeltaMovement().add(0.0D, 0.025D, 0.0D));
            } else if (this.stellerEntity.isInWater() && this.stellerEntity.getFluidHeight(FluidTags.WATER) > 3F){
                //this.stellerEntity.setDeltaMovement(this.stellerEntity.getDeltaMovement().add(0.0D, 0.25D, 0.0D));
                this.stellerEntity.findAirPosition();
            }

            if (this.operation == Operation.MOVE_TO && !this.stellerEntity.getNavigation().isDone()) {
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

    public static class RandomShallowSwimmingGoal extends RandomStrollGoal {
        public RandomShallowSwimmingGoal(PathfinderMob mob, double speedMod, int interval) {
            super(mob, speedMod, interval);}

        @Nullable
        protected Vec3 getPosition() {
            return BehaviorUtils.getRandomSwimmablePos(this.mob, 7, 1);
        }
    }

    private void findAirPosition() {
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.getX() - 1.0D), this.getBlockY(), Mth.floor(this.getZ() - 1.0D),
                Mth.floor(this.getX() + 1.0D), Mth.floor(this.getY() + 10.0D), Mth.floor(this.getZ() + 1.0D));
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

        this.getNavigation().moveTo((double)blockpos.getX(), (double)(blockpos.getY() + 2), (double)blockpos.getZ(), 1.1D);
    }

    private boolean givesAir(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (pLevel.getFluidState(pPos.above()).isEmpty() && blockstate.isPathfindable(pLevel, pPos, PathComputationType.LAND));
    }

    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
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

    static class BabyFollowAdultGoal extends Goal {
        private final BabyStellerEntity dolphin;
        private final double speedModifier;
        @javax.annotation.Nullable
        private StellerEntity adult;

        BabyFollowAdultGoal(BabyStellerEntity pDolphin, double pSpeedModifier) {
            this.dolphin = pDolphin;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.adult = this.dolphin.level().getNearestEntity(StellerEntity.class, BabyStellerEntity.SWIM_WITH_ADULT_TARGETING, this.dolphin, this.dolphin.getX(), this.dolphin.getY(), this.dolphin.getZ(), this.dolphin.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
            return this.adult != null;
        }

        public boolean canContinueToUse() {
            return this.adult != null && this.adult.isInWater() && this.dolphin.distanceToSqr(this.adult) < 256.0D;
        }

        public void stop() {
            this.adult = null;
            this.dolphin.getNavigation().stop();
        }

        public void tick() {
            this.dolphin.getLookControl().setLookAt(this.adult, (float)(this.dolphin.getMaxHeadYRot() + 20), (float)this.dolphin.getMaxHeadXRot());
            if (this.dolphin.distanceToSqr(this.adult) < 6.25D) {
                this.dolphin.getNavigation().stop();
            } else {
                this.dolphin.getNavigation().moveTo(this.adult, this.speedModifier);
            }

        }
    }

}
