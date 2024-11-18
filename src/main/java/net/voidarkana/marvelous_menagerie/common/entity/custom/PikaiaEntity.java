package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.google.common.collect.Maps;
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
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
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
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.data.codec.PikaiaCoralManager;
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
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

public class PikaiaEntity extends WaterAnimal implements IBookEntity, IHatchableEntity, GeoEntity, Bucketable {

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public float prevTilt;
    public float tilt;

    @javax.annotation.Nullable
    BlockPos savedFlowerPos;
    //PikaiaEntity.PollinateCoralGoal pollinateGoal;
    int remainingCooldownBeforeLocatingNewFlower = Mth.nextInt(this.random, 20, 60);

    public PikaiaEntity(EntityType<? extends WaterAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new PikaiaLookControl(this, 10);
    }

    class PikaiaLookControl extends SmoothSwimmingLookControl {
        PikaiaLookControl(Mob pMob, int pMaxYRotFromCenter) {
            super(pMob, pMaxYRotFromCenter);
        }

//        protected boolean resetXRotOnTick() {
//            return !PikaiaEntity.this.pollinateGoal.isPollinating();
//        }
    }

    private static final EntityDataAccessor<Boolean> IS_POLLINATING = SynchedEntityData.defineId(PikaiaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BUCKET = SynchedEntityData.defineId(PikaiaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_BOOK = SynchedEntityData.defineId(PikaiaEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FROM_EGG = SynchedEntityData.defineId(PikaiaEntity.class, EntityDataSerializers.BOOLEAN);

    protected static final RawAnimation PIKAIA_POLLINATE = RawAnimation.begin().thenLoop("animation.pikaia.pollinate");
    protected static final RawAnimation PIKAIA_SWIM = RawAnimation.begin().thenLoop("animation.pikaia.swim");
    protected static final RawAnimation PIKAIA_IDLE = RawAnimation.begin().thenLoop("animation.pikaia.idle");
    protected static final RawAnimation PIKAIA_FLOP = RawAnimation.begin().thenLoop("animation.pikaia.flop");

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
        this.goalSelector.addGoal(4, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(4, new PikaiaEntity.ReviveCoralGoal(this));

        //this.pollinateGoal = new PikaiaEntity.PollinateCoralGoal();
       // this.goalSelector.addGoal(4, this.pollinateGoal);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.8F);
    }
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FROM_BOOK, false);
        this.entityData.define(FROM_BUCKET, false);
        this.entityData.define(FROM_EGG, false);
        this.entityData.define(IS_POLLINATING, false);
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

    //custom name
    public boolean isPikachu() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("pikachu");
    }

    public boolean isPollinating() {
        return this.entityData.get(IS_POLLINATING);
    }

    public void setPollinating(boolean fromBook) {
        this.entityData.set(IS_POLLINATING, fromBook);
    }

    public boolean isFromBook() {
        return this.entityData.get(FROM_BOOK);
    }

    public void setFromBook(boolean fromBook) {
        this.entityData.set(FROM_BOOK, fromBook);
    }

    public void setIsFromEgg(boolean pTamed) {
        this.entityData.set(FROM_EGG, pTamed);
    }

    public boolean isFromEgg() {
        return this.entityData.get(FROM_EGG);
    }

    //persistence stuff, called when hatching only
    @Override
    public void determineVariant(int i) {
        this.setIsFromEgg(true);
    }

    public boolean fromBucket() {
        return this.entityData.get(FROM_BUCKET);
    }

    public void setFromBucket(boolean pFromBucket) {
        this.entityData.set(FROM_BUCKET, pFromBucket);
    }

    @Override
    public void saveToBucketTag(ItemStack bucket) {
        CompoundTag compoundnbt = bucket.getOrCreateTag();
        Bucketable.saveDefaultDataToBucketTag(this, bucket);
        compoundnbt.putFloat("Health", this.getHealth());
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
        return new ItemStack(ModItems.PIKAIA_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_FISH;
    }

    public void aiStep() {
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

    protected PathNavigation createNavigation(Level pLevel) {
       // WaterBoundPathNavigation flyingpathnavigation = new WaterBoundPathNavigation(this, pLevel){
//            public void tick() {
//                if (!PikaiaEntity.this.pollinateGoal.isPollinating()) {
//                    super.tick();
//                }
//            }
//        };
        return new WaterBoundPathNavigation(this, pLevel);
    }

//    public boolean hurt(DamageSource pSource, float pAmount) {
//        if (this.isInvulnerableTo(pSource)) {
//            return false;
//        } else {
//            if (!this.level().isClientSide) {
//                this.pollinateGoal.stopPollinating();
//            }
//
//            return super.hurt(pSource, pAmount);
//        }
//    }

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

    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        return Bucketable.bucketMobPickup(pPlayer, pHand, this).orElse(super.mobInteract(pPlayer, pHand));
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 10, this::Controller)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if(this.isPollinating()){
            event.setAndContinue(PIKAIA_POLLINATE);
            event.getController().setAnimationSpeed(1.75f);
        }else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.isInWater()) {
            event.setAndContinue(PIKAIA_SWIM);
        } else if (this.isInWater()){
            event.setAndContinue(PIKAIA_IDLE);
        }
        else{
            event.setAndContinue(PIKAIA_FLOP);
        }
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public static boolean checkSurfaceWaterDinoSpawnRules(EntityType<? extends PikaiaEntity> pWaterAnimal, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER) && UnusualPrehistoryConfig.DINO_NATURAL_SPAWNING.get();
    }

    public boolean removeWhenFarAway(double p_213397_1_) {
        return !this.hasCustomName() && !this.fromBucket() && !this.isFromEgg();
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {}

    protected SoundEvent getAmbientSound() {
        return SoundEvents.COD_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.COD_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.COD_FLOP;
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.FISH_SWIM;
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

    class ReviveCoralGoal extends MoveToBlockGoal{
        private final PikaiaEntity pikaiia;
        private int eatAnimationTick;

        public void start() {
            this.eatAnimationTick = 10;
            this.pikaiia.level().broadcastEntityEvent(this.pikaiia, (byte)10);
        }

        public ReviveCoralGoal(PikaiaEntity pikaia) {
            super(pikaia, 1F, 16, 10);
            this.pikaiia = pikaia;
        }

        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.pikaiia.level(), this.pikaiia) || this.pikaiia.isBaby()) {
                    return false;
                }
            }
            return super.canUse();
        }

        @Override
        public double acceptedDistance() {
            return 1.5D;
        }

        public void tick() {
            super.tick();
            if (this.isReachedTarget()) {

                if (!this.pikaiia.isPollinating()){
                    this.pikaiia.setPollinating(true);
                }

                if (this.pikaiia.getNavigation().getPath() != null) {
                    this.pikaiia.getNavigation().stop();
                }

                Level level = this.pikaiia.level();
                BlockPos blockpos = this.blockPos;
                BlockState blockstate = level.getBlockState(blockpos);

                Map<BlockPos, BlockState> posBlockStateMap = Maps.newHashMap();

                if (blockstate.is(ModTags.Blocks.PIKAIA_REVIVING_TARGET) && !this.pikaiia.level().isClientSide) {
                    for (PikaiaCoralManager.PikaiaCoralData data : PikaiaCoralManager.DATA) {

                        Block input = data.input();
                        Block output = data.output();

                        if (blockstate != null){
                            if (blockstate.is(input)) {
                                if (blockstate.getBlock() instanceof BaseCoralPlantTypeBlock){
                                    posBlockStateMap.put(blockpos, output.defaultBlockState().trySetValue(BaseCoralPlantBlock.WATERLOGGED, true));
                                }else {
                                    posBlockStateMap.put(blockpos, output.defaultBlockState());
                                }
                            }
                        }
                    }
                    for (BlockPos position : posBlockStateMap.keySet()) {
                        this.pikaiia.level().levelEvent(2005, position, 0);
                        BlockState output = posBlockStateMap.get(blockpos);
                        this.pikaiia.level().setBlock(blockpos, output, 2);
                    }
                }

                this.nextStartTick = 1000;
                this.eatAnimationTick--;

            } else if (this.eatAnimationTick == 0) {
                this.pikaiia.setPollinating(false);
            }

        }

        public void stop() {
            this.nextStartTick = 10;
            this.eatAnimationTick = 0;
            this.pikaiia.setPollinating(false);
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return (blockstate.is(ModTags.Blocks.PIKAIA_REVIVING_TARGET) &&
                    (blockstate.getBlock() instanceof BaseCoralPlantTypeBlock == blockstate.getFluidState().is(FluidTags.WATER)));
                    //&& pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
        }

    }

    class PollinateCoralGoal extends Goal {

        private final Predicate<BlockState> VALID_POLLINATION_BLOCKS = (p_28074_) -> p_28074_.is(BlockTags.CORALS);

        private int successfulPollinatingTicks;
        private int lastSoundPlayedTick;
        private boolean pollinating;
        @javax.annotation.Nullable
        private Vec3 hoverPos;
        private int pollinatingTicks;

        PollinateCoralGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            Optional<BlockPos> optional = this.findNearbyFlower();
            if (optional.isPresent()) {
                PikaiaEntity.this.savedFlowerPos = optional.get();
                PikaiaEntity.this.navigation.moveTo((double)PikaiaEntity.this.savedFlowerPos.getX() + (0.01 * random.nextInt(-5, 5)),
                        (double)PikaiaEntity.this.savedFlowerPos.getY() + (0.01 * random.nextInt(-5, 5)),
                        (double)PikaiaEntity.this.savedFlowerPos.getZ() + (0.01 * random.nextInt(-5, 5)), 1.2F);
                return true;
            } else {
                PikaiaEntity.this.remainingCooldownBeforeLocatingNewFlower = Mth.nextInt(PikaiaEntity.this.random, 300, 400);
                return false;
            }

        }

        public boolean canContinueToUse() {
            if (!isPollinating()) {
                return false;
            } else if (!PikaiaEntity.this.hasSavedFlowerPos()) {
                return false;
            } else if (this.hasPollinatedLongEnough()) {
                return PikaiaEntity.this.random.nextFloat() < 0.2F;
            } else if (PikaiaEntity.this.tickCount % 20 == 0 && !PikaiaEntity.this.isFlowerValid(PikaiaEntity.this.savedFlowerPos)) {
                PikaiaEntity.this.savedFlowerPos = null;
                return false;
            } else {
                return true;
            }
        }

        private boolean hasPollinatedLongEnough() {
            return this.successfulPollinatingTicks > 100;
        }

        boolean isPollinating() {
            return this.pollinating;
        }

        void stopPollinating() {
            this.pollinating = false;
        }

        public void start() {
            this.successfulPollinatingTicks = 0;
            this.pollinatingTicks = 0;
            this.lastSoundPlayedTick = 0;
            this.pollinating = true;
        }

        public void stop() {
            this.stopPollinating();
            PikaiaEntity.this.navigation.stop();
            PikaiaEntity.this.remainingCooldownBeforeLocatingNewFlower = 400;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            ++this.pollinatingTicks;
            if (this.pollinatingTicks > 600) {
                PikaiaEntity.this.savedFlowerPos = null;
            } else {
                Vec3 vec3 = Vec3.atBottomCenterOf(PikaiaEntity.this.savedFlowerPos).add(0.0D, (double)0.6F, 0.0D);
                if (vec3.distanceTo(PikaiaEntity.this.position()) > 1.0D) {
                    this.hoverPos = vec3;
                    this.setWantedPos();
                } else {
                    if (this.hoverPos == null) {
                        this.hoverPos = vec3;
                    }

                    boolean flag = PikaiaEntity.this.position().distanceTo(this.hoverPos) <= 0.1D;
                    boolean flag1 = true;
                    if (!flag && this.pollinatingTicks > 600) {
                        PikaiaEntity.this.savedFlowerPos = null;
                    } else {
                        if (flag) {
                            boolean flag2 = PikaiaEntity.this.random.nextInt(25) == 0;
                            if (flag2) {
                                this.hoverPos = new Vec3(vec3.x() + (double)this.getOffset(), vec3.y(), vec3.z() + (double)this.getOffset());
                                PikaiaEntity.this.navigation.stop();
                            } else {
                                flag1 = false;
                            }

                            PikaiaEntity.this.getLookControl().setLookAt(vec3.x(), vec3.y(), vec3.z());
                        }

                        if (flag1) {
                            this.setWantedPos();
                        }

                        ++this.successfulPollinatingTicks;
                        if (PikaiaEntity.this.random.nextFloat() < 0.05F && this.successfulPollinatingTicks > this.lastSoundPlayedTick + 60) {
                            this.lastSoundPlayedTick = this.successfulPollinatingTicks;
                            PikaiaEntity.this.playSound(SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, 1.0F, 1.0F);
                        }

                    }
                }
            }
        }

        private void setWantedPos() {
            if (this.hoverPos != null){
                PikaiaEntity.this.getMoveControl().setWantedPosition(this.hoverPos.x(), this.hoverPos.y(), this.hoverPos.z(), 0.35F);
            }
        }

        private float getOffset() {
            return (PikaiaEntity.this.random.nextFloat() * 2.0F - 1.0F) * 0.33333334F;
        }

        private Optional<BlockPos> findNearbyFlower() {
            return this.findNearestBlock(this.VALID_POLLINATION_BLOCKS, 5.0D);
        }

        private Optional<BlockPos> findNearestBlock(Predicate<BlockState> pPredicate, double pDistance) {
            BlockPos blockpos = PikaiaEntity.this.blockPosition();
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for(int i = 0; (double)i <= pDistance; i = i > 0 ? -i : 1 - i) {
                for(int j = 0; (double)j < pDistance; ++j) {
                    for(int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
                        for(int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
                            blockpos$mutableblockpos.setWithOffset(blockpos, k, i - 1, l);
                            if (blockpos.closerThan(blockpos$mutableblockpos, pDistance) && pPredicate.test(PikaiaEntity.this.level().getBlockState(blockpos$mutableblockpos))) {
                                return Optional.of(blockpos$mutableblockpos);
                            }
                        }
                    }
                }
            }

            return Optional.empty();
        }
    }

    public boolean hasSavedFlowerPos() {
        return this.savedFlowerPos != null;
    }

    boolean isFlowerValid(BlockPos pPos) {
        return this.level().isLoaded(pPos) && this.level().getBlockState(pPos).is(BlockTags.CORALS);
    }

}
