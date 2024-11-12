package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.peeko32213.unusualprehistory.common.config.UnusualPrehistoryConfig;
import com.peeko32213.unusualprehistory.common.entity.IBookEntity;
import com.peeko32213.unusualprehistory.common.entity.IHatchableEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.voidarkana.marvelous_menagerie.common.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.common.effect.potion.ModPotions;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
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

import java.util.function.Predicate;

public class HallucigeniaEntity extends WaterAnimal implements GeoEntity, IBookEntity, Bucketable, IHatchableEntity {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    private static final Predicate<LivingEntity> SCARY_MOB = (p_289442_) -> {
        if (p_289442_ instanceof Player && ((Player)p_289442_).isCreative()) {
            return false;
        } else {
            return p_289442_.getType() == EntityType.AXOLOTL || p_289442_.getMobType() != MobType.WATER;
        }
    };
    static final TargetingConditions targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(HallucigeniaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BOOK = SynchedEntityData.defineId(HallucigeniaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_EGG = SynchedEntityData.defineId(HallucigeniaEntity.class, EntityDataSerializers.BOOLEAN);

    protected static final RawAnimation HALLU_WALK = RawAnimation.begin().thenLoop("animation.hallu.walk");
    protected static final RawAnimation HALLU_IDLE = RawAnimation.begin().thenLoop("animation.hallu.idle");
    protected static final RawAnimation HALLU_BEACHED = RawAnimation.begin().thenLoop("animation.hallu.beached");

    public HallucigeniaEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1, 1, 1, 0.1F, true);
        this.setMaxUpStep(1.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.1));
        this.goalSelector.addGoal(3, new RandomStrollGoal(this, 1.0D));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BOOK, false);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(FROM_EGG, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("FromBucket", this.fromBucket());
        pCompound.putBoolean("FromEgg", this.isFromEgg());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setIsFromEgg(pCompound.getBoolean("FromEgg"));
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public void aiStep() {
        super.aiStep();

        if (this.isAlive()) {
            for(Mob mob : this.level().getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(0.3D), (p_149013_) -> {
                return targetingConditions.test(this, p_149013_);
            })) {
                if (mob.isAlive()) {
                    this.touch(mob);
                }
            }
        }

        if (this.isInWater()) {
            if(!this.onGround()){
                this.setDeltaMovement(this.getDeltaMovement().add(0, -0.08, 0));
            }

            BlockPos pos = this.blockPosition();
            BlockState block = this.level().getBlockState(pos.above());
            if (this.getStepHeight() >= 1 && block.getFluidState().is(Fluids.EMPTY)){
                this.setMaxUpStep(0);
            }else if (this.isInWater() && block.getFluidState().is(Fluids.WATER)){
                this.setMaxUpStep(1);
            }
        }

    }

    private void touch(Mob pMob) {

        if (pMob.hurt(this.damageSources().mobAttack(this), (float)(1))) {

            if (this.getRandom().nextInt(100)>75){
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.0F);

                this.spawnAtLocation(ModItems.HALLUCIGENIC_SLIME.get());
            }

            pMob.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 0), this);
            pMob.addEffect(new MobEffectInstance(ModEffects.HALLUCINATING.get(), 60, 0), this);
            pMob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
            this.playSound(SoundEvents.PUFFER_FISH_STING, 1.0F, 1.0F);
        }

    }

    public void playerTouch(Player pEntity) {

        if (pEntity instanceof ServerPlayer && pEntity.hurt(this.damageSources().mobAttack(this), (float)(1))) {
            if (!this.isSilent()) {
                ((ServerPlayer)pEntity).connection.send(new ClientboundGameEventPacket(ClientboundGameEventPacket.PUFFER_FISH_STING, 0.0F));
            }

            if (this.getRandom().nextInt(100)>75){
                this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, 1.0F);

                this.spawnAtLocation(ModItems.HALLUCIGENIC_SLIME.get());
            }

            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
            pEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 60, 0), this);
            pEntity.addEffect(new MobEffectInstance(ModEffects.HALLUCINATING.get(), 60, 0), this);
        }

    }

    public boolean isFromBook() {
        return this.entityData.get(FROM_BOOK);
    }

    public void setFromBook(boolean fromBook) {
        this.entityData.set(FROM_BOOK, fromBook);
    }

    @Override
    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    @Override
    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    public void setIsFromEgg(boolean pTamed) {
        this.entityData.set(FROM_EGG, pTamed);
    }

    public boolean isFromEgg() {
        return this.entityData.get(FROM_EGG);
    }

    @Override
    public void determineVariant(int i) {
        this.setIsFromEgg(true);
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.005F, 1.25F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putBoolean("FromEgg", this.isFromEgg());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.HALLUCIGENIA_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("FromEgg", 3)) {
            this.setIsFromEgg(dataTag.getBoolean("FromEgg"));
            this.setFromBucket(true);
        }

        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});

    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else {
            if (this.isInWater()){
                if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
                    event.setAndContinue(HALLU_WALK);
                } else {
                    event.setAndContinue(HALLU_IDLE);
                }
            }else {
                event.setAndContinue(HALLU_BEACHED);
            }
            return PlayState.CONTINUE;
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.hasCustomName() && !this.fromBucket() && !this.isFromEgg();
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    public static boolean checkSurfaceWaterDinoSpawnRules(EntityType<? extends HallucigeniaEntity> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER) && UnusualPrehistoryConfig.DINO_NATURAL_SPAWNING.get();
    }
}
