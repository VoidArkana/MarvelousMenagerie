package net.voidarkana.marvelous_menagerie.common.entity.custom.baby;

import com.peeko32213.unusualprehistory.common.entity.IHatchableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.entity.custom.OphthalmoEntity;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.EnumSet;

public class BabyOphthalmoEntity extends WaterAnimal implements IHatchableEntity, GeoEntity, Bucketable {

    static final TargetingConditions SWIM_WITH_ADULT_TARGETING = TargetingConditions.forNonCombat().range(15.0D).ignoreLineOfSight();

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public float prevTilt;
    public float tilt;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(BabyOphthalmoEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> BASE_VARIANT = SynchedEntityData.defineId(BabyOphthalmoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PATTERN_VARIANT = SynchedEntityData.defineId(BabyOphthalmoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> CAN_GROW = SynchedEntityData.defineId(BabyOphthalmoEntity.class, EntityDataSerializers.BOOLEAN);

    public static final Ingredient FOOD_ITEMS = Ingredient.of(ItemTags.FISHES);
    public static final int MAX_TADPOLE_AGE = Math.abs(-30000);
    private int age;
    private boolean persistenceRequired;

    protected static final RawAnimation BABY_SWIM = RawAnimation.begin().thenLoop("animation.ophthalmo.baby_swim");
    protected static final RawAnimation BABY_IDLE = RawAnimation.begin().thenLoop("animation.ophthalmo.baby_idle");
    protected static final RawAnimation BABY_FLOP = RawAnimation.begin().thenLoop("animation.ophthalmo.baby_flop");

    public BabyOphthalmoEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(2, new BabyFollowAdultGoal(this, 1.5D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8F)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.2F)
                .add(Attributes.ARMOR, 3F);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BUCKET, false);

        this.entityData.define(BASE_VARIANT, 0);
        this.entityData.define(PATTERN_VARIANT, 0);
        this.entityData.define(CAN_GROW, true);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());

        pCompound.putInt("BaseColor", this.getBaseColor());
        pCompound.putInt("Pattern", this.getPattern());
        pCompound.putBoolean("CanGrow", this.canGrow());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));

        this.setBaseColor(pCompound.getInt("BaseColor"));
        this.setPattern(pCompound.getInt("Pattern"));
        this.setCanGrow(pCompound.getBoolean("CanGrow"));
    }

    @Override
    public void determineVariant(int i) {
        this.setBaseColor(this.random.nextInt(0, 3));
        this.setPattern(this.random.nextInt(0, 4));
    }

    public boolean canGrow() {
        return this.entityData.get(CAN_GROW);
    }

    public void setCanGrow(boolean canGrow) {
        this.entityData.set(CAN_GROW, canGrow);
    }

    @Override
    public boolean fromBucket() {return this.entityData.get(FROM_BUCKET);}

    @Override
    public void setFromBucket(boolean pFromBucket) {this.entityData.set(FROM_BUCKET, pFromBucket);}

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("Age", this.getAge());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }

        compoundnbt.putInt("BaseColor", this.getBaseColor());
        compoundnbt.putInt("Pattern", this.getPattern());

        compoundnbt.putBoolean("CanGrow", this.canGrow());
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.BABY_OPHTHALMO_BUCKET.get());    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
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
        this.setAge(this.age + (seconds * 40));
    }

    private void setAge(int age) {
        this.age = age;
        if (this.age >= MAX_TADPOLE_AGE) {
            this.growUp();
        }
    }


    private void growUp() {
        Level var2 = this.level();
        if (var2 instanceof ServerLevel server) {
            OphthalmoEntity frog = (OphthalmoEntity) ((EntityType) ModEntities.OPHTHALMO.get()).create(this.level());
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

            frog.setBaseColor(this.getBaseColor());
            frog.setPattern(this.getPattern());

            //persistance stuff
            frog.setIsFromEgg(true);

            this.playSound(SoundEvents.PLAYER_LEVELUP, 0.15F, 1.0F);
            server.addFreshEntityWithPassengers(frog);
            this.discard();
        }
    }

    public void setPersistenceRequired() {
        this.persistenceRequired = false;
    }

    //underwater navigation
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public void aiStep() {
        if (!this.level().isClientSide && this.canGrow()) {
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

    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }

    }

    //bucket stuff
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if (itemstack.is(ModItems.GOLDEN_SACA.get())){
            this.decrementItem(pPlayer, itemstack);
            this.eatFood();
            this.playSound(SoundEvents.DOLPHIN_EAT);
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }else if (itemstack.is(ModTags.Items.FINTASTIC_BAD_FEED) && this.canGrow()) {

            this.decrementItem(pPlayer, itemstack);
            this.setCanGrow(false);
            this.playSound(SoundEvents.DOLPHIN_EAT);

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.5), this.getRandomY() + 0.5, this.getRandomZ(1.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);

        } else{
            return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 10, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {

        if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.isInWater()) {
            event.setAndContinue(BABY_SWIM);
            event.getController().setAnimationSpeed(1.25f);
        } else if (this.isInWater()){
            event.setAndContinue(BABY_IDLE);
        }
        else{
            event.setAndContinue(BABY_FLOP);
        }
        return PlayState.CONTINUE;

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {}

    @Override
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.5F;
    }

    protected SoundEvent getFlopSound() {
        return ModSounds.CREATURE_FLOPS.get();
    }

    protected SoundEvent getSwimSound() {
        return ModSounds.CREATURE_SWIM.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return isInWater() ? ModSounds.OPHTHALMO_IDLE.get() : ModSounds.DOLPHIN_BLOWHOLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.OPHTHALMO_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.OPHTHALMO_DEATH.get();
    }

    //persistance stuff, only babies don't despawn because they can't spawn naturally
    public void checkDespawn() {
        this.noActionTime = 0;
    }

    public boolean shouldDropExperience() {
        return false;
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    static class BabyFollowAdultGoal extends Goal {
        private final BabyOphthalmoEntity dolphin;
        private final double speedModifier;
        @javax.annotation.Nullable
        private OphthalmoEntity adult;

        BabyFollowAdultGoal(BabyOphthalmoEntity pDolphin, double pSpeedModifier) {
            this.dolphin = pDolphin;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            this.adult = this.dolphin.level().getNearestEntity(OphthalmoEntity.class, BabyOphthalmoEntity.SWIM_WITH_ADULT_TARGETING, this.dolphin, this.dolphin.getX(), this.dolphin.getY(), this.dolphin.getZ(), this.dolphin.getBoundingBox().inflate(6.0D, 2.0D, 6.0D));
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

    public void setBaseColor(int color) {
        this.entityData.set(BASE_VARIANT, color);
    }

    public int getBaseColor() {
        return this.entityData.get(BASE_VARIANT);
    }

    public void setPattern(int pattern) {
        this.entityData.set(PATTERN_VARIANT, pattern);
    }

    public int getPattern() {
        return this.entityData.get(PATTERN_VARIANT);
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("BaseColor", 3)) {
            this.setBaseColor(dataTag.getInt("BaseColor"));
            this.setPattern(dataTag.getInt("Pattern"));
            this.setCanGrow(dataTag.getBoolean("CanGrow"));
            this.setFromBucket(true);
        }else{
            this.setBaseColor(this.random.nextInt(0, 3));
            this.setPattern(this.random.nextInt(0, 4));
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static String getColorName(int color){
        return switch (color){
            case 1 -> "_black";
            case 2-> "_green";
            default -> "_blue";
        };
    }

    public static String getPatternName(int color){
        return switch (color){
            case 2-> "_spots";
            case 3-> "_streak";
            default -> "_stripes";
        };
    }
}
