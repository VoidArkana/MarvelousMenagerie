package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.peeko32213.unusualprehistory.common.config.UnusualPrehistoryConfig;
import com.peeko32213.unusualprehistory.common.entity.IBookEntity;
import com.peeko32213.unusualprehistory.common.entity.IHatchableEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Bucketable;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
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

import java.util.Objects;

public class TrilobiteEntity extends WaterAnimal implements GeoEntity, IBookEntity, Bucketable, IHatchableEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public TrilobiteEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.refreshDimensions();
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1, 1, 1, 0.1F, true);
        this.setMaxUpStep(1.0F);
    }

    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BOOK = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> VARIANT_MODEL = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT_BASE_COLOR = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> VARIANT_SECOND_COLOR = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.INT);

    protected static final RawAnimation TRILOBITE_CURLY_WALK = RawAnimation.begin().thenLoop("animation.trilobite.walk_curly");
    protected static final RawAnimation TRILOBITE_TRIDENT_WALK = RawAnimation.begin().thenLoop("animation.trilobite.walk_trident");
    protected static final RawAnimation TRILOBITE_RIGID_WALK = RawAnimation.begin().thenLoop("animation.trilobite.walk_rigid");
    protected static final RawAnimation TRILOBITE_ITTY_WALK = RawAnimation.begin().thenLoop("animation.trilobite.walk_itty");
    protected static final RawAnimation TRILOBITE_FAT_WALK = RawAnimation.begin().thenLoop("animation.trilobite.walk_fat");

    protected static final RawAnimation TRILOBITE_FLOP = RawAnimation.begin().thenLoop("animation.trilobite.flop");

    protected static final RawAnimation IDLE_CURLY = RawAnimation.begin().thenLoop("animation.trilobite.idle_curly");
    protected static final RawAnimation IDLE_RIGID = RawAnimation.begin().thenLoop("animation.trilobite.idle_rigid");
    protected static final RawAnimation IDLE_TRIDENT = RawAnimation.begin().thenLoop("animation.trilobite.idle_trident");
    protected static final RawAnimation IDLE_ITTY = RawAnimation.begin().thenLoop("animation.trilobite.idle_itty");
    protected static final RawAnimation IDLE_FAT = RawAnimation.begin().thenLoop("animation.trilobite.idle_fat");

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 4.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5F);
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
        this.entityData.define(VARIANT_MODEL, 0);
        this.entityData.define(VARIANT_BASE_COLOR, 0);
        this.entityData.define(VARIANT_SECOND_COLOR, 0);
        this.entityData.define(FROM_EGG, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        pCompound.putInt("model_variant", this.getVariantModel());
        pCompound.putInt("base_color_variant", this.getVariantBaseColor());
        pCompound.putInt("second_color_variant", this.getVariantSecondColor());
        pCompound.putBoolean("FromEgg", this.isFromEgg());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
        this.setVariantModel(pCompound.getInt("model_variant"));
        this.setVariantBaseColor(pCompound.getInt("base_color_variant"));
        this.setVariantSecondColor(pCompound.getInt("second_color_variant"));
        this.setIsFromEgg(pCompound.getBoolean("FromEgg"));
    }

    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    public void aiStep() {
        super.aiStep();

        if (!this.isInWater() && this.onGround() && this.verticalCollision) {
            this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2.0F - 1.0F) * 0.05F, 0.4F, (this.random.nextFloat() * 2.0F - 1.0F) * 0.05F));
            this.setOnGround(false);
            this.hasImpulse = true;
            this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
        }else {
            if(this.isInWater() && !this.onGround()){
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

    protected SoundEvent getFlopSound() {
        return ModSounds.CREATURE_FLOPS.get();
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        this.refreshDimensions();
        super.onSyncedDataUpdated(pKey);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        if (this.getVariantModel() == 5) {
            return super.getDimensions(pPose).scale(2F, 2.5F);
        } else if (this.getVariantModel() == 4){
            return super.getDimensions(pPose).scale(0.8F, 1F);
        }else {
            return super.getDimensions(pPose);
        }
    }

    //variant model
    public void setVariantModel(int variant) {
        this.entityData.set(VARIANT_MODEL, variant);}

    public int getVariantModel() {
        return this.entityData.get(VARIANT_MODEL);}

    //variant model
    public void setVariantBaseColor(int variant) {
        this.entityData.set(VARIANT_BASE_COLOR, variant);}

    public int getVariantBaseColor() {
        return this.entityData.get(VARIANT_BASE_COLOR);}

    //variant model
    public void setVariantSecondColor(int variant) {
        this.entityData.set(VARIANT_SECOND_COLOR, variant);}

    public int getVariantSecondColor() {
        return this.entityData.get(VARIANT_SECOND_COLOR);}


    //determines variant based on the number determined at spawn
    public void determineVariant(int variant){
        int variantModelChange = this.random.nextInt(0, 6);
        int variantColorBaseChange = this.random.nextInt(0, 7);
        int variantColorSecondChange = this.random.nextInt(0, 11);

        this.setVariantModel(variantModelChange);
        this.setVariantBaseColor(variantColorBaseChange);
        this.setVariantSecondColor(variantColorSecondChange);

        //persistence stuff, only called on hatching
        this.setIsFromEgg(true);
    }

    //determines a number when spawning
    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {

        if (reason == MobSpawnType.BUCKET && dataTag != null && dataTag.contains("model_variant", 3)) {
            this.setVariantModel(dataTag.getInt("model_variant"));
            this.setVariantBaseColor(dataTag.getInt("base_color_variant"));
            this.setVariantSecondColor(dataTag.getInt("second_color_variant"));
        }else{
            int variantModelChange = this.random.nextInt(0, 6);
            int variantColorBaseChange = this.random.nextInt(0, 7);
            int variantColorSecondChange = this.random.nextInt(0, 11);

            this.setVariantModel(variantModelChange);
            this.setVariantBaseColor(variantColorBaseChange);
            this.setVariantSecondColor(variantColorSecondChange);
        }


        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        Bucketable.saveDefaultDataToBucketTag(this, bucket);

        CompoundTag compoundnbt = bucket.getOrCreateTag();

        compoundnbt.putFloat("Health", this.getHealth());
        compoundnbt.putInt("model_variant", this.getVariantModel());
        compoundnbt.putInt("base_color_variant", this.getVariantBaseColor());
        compoundnbt.putInt("second_color_variant", this.getVariantSecondColor());
        if (this.hasCustomName()) {
            bucket.setHoverName(this.getCustomName());
        }
    }

    @Override
    public void loadFromBucketTag(CompoundTag pTag) {
        Bucketable.loadDefaultDataFromBucketTag(this, pTag);

//        this.setVariantModel(pTag.getInt("model_variant"));
//        this.setVariantBaseColor(pTag.getInt("base_color_variant"));
//        this.setVariantSecondColor(pTag.getInt("second_color_variant"));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.TRILO_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    //ANIMATION CONTROLLERS
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else {
            if (!this.isInWater()){
                event.setAndContinue(TRILOBITE_FLOP);
            }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
                switch(this.getVariantModel()){
                    case 1 -> event.setAndContinue(TRILOBITE_TRIDENT_WALK);
                    case 2, 3 -> event.setAndContinue(TRILOBITE_RIGID_WALK);
                    case 4 -> event.setAndContinue(TRILOBITE_ITTY_WALK);
                    case 5 -> event.setAndContinue(TRILOBITE_FAT_WALK);
                    default -> event.setAndContinue(TRILOBITE_CURLY_WALK);
                }
            } else {
                switch(this.getVariantModel()){
                    case 1 -> event.setAndContinue(IDLE_TRIDENT);
                    case 2, 3 -> event.setAndContinue(IDLE_RIGID);
                    case 4 -> event.setAndContinue(IDLE_ITTY);
                    case 5 -> event.setAndContinue(IDLE_FAT);
                    default -> event.setAndContinue(IDLE_CURLY);
                }
            }

            return PlayState.CONTINUE;
        }
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public MobType getMobType() {
        return MobType.ARTHROPOD;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.ARTHROPOD_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.ARTHROPOD_DEATH.get();
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.SPIDER_STEP, 0.005F, 1.25F);
    }

    @Override
    public boolean canBeLeashed(Player pPlayer) {
        return true;
    }

    public boolean isLGBTrilo(){
        return this.isLGBT() || this.isAroAce() || this.isAro() || this.isAce() || this.isBi() || this.isPan() || this.isGay() || this.isLesbian()
                || this.isEnby() || this.isAgender() || this.isGenderfluid() || this.isTrans();
    }

    public boolean isLGBT(){
        return Objects.equals(getTriloName(), "lgbt");
    }

    public boolean isAro(){
        return Objects.equals(getTriloName(), "aro");
    }

    public boolean isAce(){
        return Objects.equals(getTriloName(), "ace");
    }

    public boolean isAroAce(){
        return Objects.equals(getTriloName(), "aroace");
    }

    public boolean isBi(){
        return Objects.equals(getTriloName(), "bi");
    }

    public boolean isGay(){
        return Objects.equals(getTriloName(), "gay");
    }

    public boolean isLesbian(){
        return Objects.equals(getTriloName(), "lesbian");
    }

    public boolean isEnby(){
        return Objects.equals(getTriloName(), "enby");
    }

    public boolean isAgender(){
        return Objects.equals(getTriloName(), "agender");
    }

    public boolean isGenderfluid(){
        return Objects.equals(getTriloName(), "genderfluid");
    }

    public boolean isPan(){
        return Objects.equals(getTriloName(), "pan");
    }

    public boolean isTrans(){
        return Objects.equals(getTriloName(), "trans");
    }

    public String getTriloName(){
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s.toLowerCase();
    }

    public static String getModelName(int i){
        return switch (i){
            case 1 -> "trident";
            case 2 -> "moon";
            case 3 -> "spiky";
            case 4 -> "itty";
            case 5 -> "fat";
            default -> "curly";
        };
    }

    public static String getMainColorName(int i){
        return switch (i){
            case 1 -> "black";
            case 2 -> "blue";
            case 3 -> "green";
            case 4 -> "pink";
            case 5 -> "red";
            case 6 -> "teal";
            default -> "brown";
        };
    }

    public static String getSecondColorName(int i){
        return switch (i){
            case 1 -> "blue";
            case 2 -> "green";
            case 3 -> "light_blue";
            case 4 -> "orange";
            case 5 -> "pink";
            case 6 -> "purple";
            case 7 -> "red";
            case 8 -> "teal";
            case 9 -> "white";
            case 10 -> "yellow";
            default -> "brown";
        };
    }


    public static boolean checkSurfaceWaterDinoSpawnRules(EntityType<? extends TrilobiteEntity> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER) && UnusualPrehistoryConfig.DINO_NATURAL_SPAWNING.get();
    }

    //persistance stuff
    private static final EntityDataAccessor<Boolean> FROM_EGG = SynchedEntityData.defineId(TrilobiteEntity.class, EntityDataSerializers.BOOLEAN);

    public void setIsFromEgg(boolean pTamed) {
        this.entityData.set(FROM_EGG, pTamed);
    }

    public boolean isFromEgg() {
        return this.entityData.get(FROM_EGG);
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.hasCustomName() && !this.fromBucket() && !this.isFromEgg();
    }
}
