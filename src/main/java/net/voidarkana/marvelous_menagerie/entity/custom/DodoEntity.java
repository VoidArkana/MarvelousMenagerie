package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.MelonBlock;
import net.minecraft.world.level.block.PumpkinBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
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

import java.util.EnumSet;


public class DodoEntity extends EntityBaseDinosaurAnimal implements GeoEntity {


    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE, Items.MELON, Items.PUMPKIN);

    public DodoEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    private static final int PECK_ANIMATION_TICKS = 36;
    public boolean IsFalling;
    protected static final RawAnimation DODO_WALK;
    protected static final RawAnimation DODO_FLAP;
    protected static final RawAnimation DODO_IDLE;
    protected static final RawAnimation DODO_PECK;

    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new DodoEntity.DestroyMelonAndPumpkinGoal(this));
        this.goalSelector.addGoal(5, new DodoLookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new DodoEntity.DodoRandomLookAroundGoal(this));
        super.registerGoals();
    }

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> PECKING_TIME = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> PECKING = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
        this.entityData.define(PECKING_TIME, 0);
        this.entityData.define(PECKING, false);
    }

    //variants
    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
        compound.putBoolean("isPecking", this.getIsPecking());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
        this.setIsPecking(compound.getBoolean("isPecking"));
    }

    //determines variant based on the number determined at spawn
    public void determineVariant(int variantChange){
        /*if(variantChange <= 33){
            this.setVariant(2);
        }else */
        if(variantChange <= 50){
            this.setVariant(1);
        }else{
            this.setVariant(0);
        }
    }

    //determines a number when spawning
    @Override
    @org.jetbrains.annotations.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn,
                                        DifficultyInstance difficultyIn,
                                        MobSpawnType reason, @org.jetbrains.annotations.Nullable SpawnGroupData spawnDataIn,
                                        @org.jetbrains.annotations.Nullable CompoundTag dataTag) {
        int variantChange = this.random.nextInt(0, 100);
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.determineVariant(variantChange);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }


    public boolean getIsPecking() {
        return (Boolean)this.entityData.get(PECKING);
    }

    public void setIsPecking(boolean pecking) {
        this.entityData.set(PECKING, pecking);
    }


    //eye height
    @Override
    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? pSize.height * 0.85F : pSize.height * 0.92F;
    }

    public void aiStep() {
        super.aiStep();
        //makes it fall slowly when it's falling
        Vec3 vec3 = this.getDeltaMovement();
        if (!this.onGround() && vec3.y < (-0.1D)) {
            this.setDeltaMovement(vec3.multiply(1.0D, 0.6D, 1.0D));
            IsFalling = true;
        }
        else {
            IsFalling = false;
        }
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        if (pFallDistance > 1.0F) {
            this.playSound(SoundEvents.CHICKEN_STEP, 0F, 1.0F);
        }

        return false;
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
        /*DodoEntity baby = ModEntities.DODO.get().create(pLevel);
        DodoVariant variant = Util.getRandom(DodoVariant.values(), this.random);
        baby.setVariant(variant);*/
        return null;
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }
        else {
            if(this.getIsPecking()) {
                event.setAndContinue(DODO_PECK);
            } else if(IsFalling && !this.getIsPecking()) {
                event.setAndContinue(DODO_FLAP);
            } else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.onGround()) {
                event.setAndContinue(DODO_WALK);
                if (this.isBaby()){
                    event.getController().setAnimationSpeed(1.5);
                }
            } else if (this.onGround() && !this.getIsPecking()){
                event.setAndContinue(DODO_IDLE);
            }
                return PlayState.CONTINUE;
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController[]{new AnimationController(this, "Normal", 5, this::Controller)});
    }

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    static {
        DODO_WALK = RawAnimation.begin().thenLoop("animation.dodo.walk");
        DODO_IDLE = RawAnimation.begin().thenLoop("animation.dodo.idle");
        DODO_FLAP = RawAnimation.begin().thenLoop("animation.dodo.flap");
        DODO_PECK = RawAnimation.begin().thenPlay("animation.dodo.peck");
    }

    static class DestroyMelonAndPumpkinGoal extends MoveToBlockGoal {
        private final DodoEntity dodo;
        private int eatAnimationTick;

        public void start() {
            this.eatAnimationTick = PECK_ANIMATION_TICKS;
            this.dodo.level().broadcastEntityEvent(this.dodo, (byte)10);
        }

        public DestroyMelonAndPumpkinGoal(DodoEntity pDodo) {
            super(pDodo, 1F, 16);
            this.dodo = pDodo;
        }

        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.dodo.level(), this.dodo) || this.dodo.isBaby()) {
                    return false;
                }
            }
            return super.canUse();
        }

        public void tick() {
            super.tick();
            //this.dodo.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.dodo.getMaxHeadXRot());
            if (this.isReachedTarget()) {

                if (!this.dodo.getIsPecking()){
                    this.dodo.setIsPecking(true);
                }

                if (this.dodo.getNavigation().getPath() != null) {
                    this.dodo.getNavigation().stop();
                }

                if(this.eatAnimationTick == 12){
                    Level level = this.dodo.level();
                    BlockPos blockpos = this.blockPos;
                    BlockState blockstate = level.getBlockState(blockpos);
                    Block block = blockstate.getBlock();
                    if (block instanceof MelonBlock || block instanceof PumpkinBlock) {
                        level.destroyBlock(blockpos, true, this.dodo);
                    }

                    this.nextStartTick = 10;
                    this.dodo.setIsPecking(false);
                } else if (this.eatAnimationTick == 0) {
                    this.dodo.setIsPecking(false);
                }

                this.eatAnimationTick--;
            }
        }

        public void stop() {
            this.nextStartTick = 10;
            this.eatAnimationTick = 0;
            this.dodo.setIsPecking(false);
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            return (blockstate.is(Blocks.MELON) || blockstate.is(Blocks.PUMPKIN)) && pLevel.isEmptyBlock(pPos.above());
        }

    }

    public class DodoRandomLookAroundGoal extends Goal {
        private final Mob mob;
        private double relX;
        private double relZ;
        private int lookTime;

        public DodoRandomLookAroundGoal(Mob mob) {
            this.mob = mob;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }

        public boolean canUse() {
            return !DodoEntity.this.getIsPecking() && this.mob.getRandom().nextFloat() < 0.02F;
        }

        public boolean canContinueToUse() {
            return !DodoEntity.this.getIsPecking() && this.lookTime >= 0;
        }

        public void start() {
            double d0 = (Math.PI * 2D) * this.mob.getRandom().nextDouble();
            this.relX = Math.cos(d0);
            this.relZ = Math.sin(d0);
            this.lookTime = 20 + this.mob.getRandom().nextInt(20);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            --this.lookTime;
            this.mob.getLookControl().setLookAt(this.mob.getX() + this.relX, this.mob.getEyeY(), this.mob.getZ() + this.relZ);
        }
    }


    public class DodoLookAtPlayerGoal extends Goal {
        public static final float DEFAULT_PROBABILITY = 0.02F;
        protected final Mob mob;
        @javax.annotation.Nullable
        protected Entity lookAt;
        protected final float lookDistance;
        private int lookTime;
        protected final float probability;
        private final boolean onlyHorizontal;
        protected final Class<? extends LivingEntity> lookAtType;
        protected final TargetingConditions lookAtContext;

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance) {
            this(pMob, pLookAtType, pLookDistance, 0.02F);
        }

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability) {
            this(pMob, pLookAtType, pLookDistance, pProbability, false);
        }

        public DodoLookAtPlayerGoal(Mob pMob, Class<? extends LivingEntity> pLookAtType, float pLookDistance, float pProbability, boolean pOnlyHorizontal) {
            this.mob = pMob;
            this.lookAtType = pLookAtType;
            this.lookDistance = pLookDistance;
            this.probability = pProbability;
            this.onlyHorizontal = pOnlyHorizontal;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
            if (pLookAtType == Player.class) {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance).selector((p_25531_) -> {
                    return EntitySelector.notRiding(pMob).test(p_25531_);
                });
            } else {
                this.lookAtContext = TargetingConditions.forNonCombat().range((double)pLookDistance);
            }

        }

        public boolean canUse() {

            if (DodoEntity.this.getIsPecking()){
                return false;
            }
            if (this.mob.getRandom().nextFloat() >= this.probability) {
                return false;
            } else {
                if (this.mob.getTarget() != null) {
                    this.lookAt = this.mob.getTarget();
                }

                if (this.lookAtType == Player.class) {
                    this.lookAt = this.mob.level().getNearestPlayer(this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                } else {
                    this.lookAt = this.mob.level().getNearestEntity(this.mob.level().getEntitiesOfClass(this.lookAtType, this.mob.getBoundingBox().inflate((double)this.lookDistance, 3.0D, (double)this.lookDistance), (p_148124_) -> {
                        return true;
                    }), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
                }

                return this.lookAt != null;
            }
        }

        public boolean canContinueToUse() {
            if (DodoEntity.this.getIsPecking()){
                return false;
            }
            if (!this.lookAt.isAlive()) {
                return false;
            } else if (this.mob.distanceToSqr(this.lookAt) > (double)(this.lookDistance * this.lookDistance)) {
                return false;
            } else {
                return this.lookTime > 0;
            }
        }

        public void start() {
            this.lookTime = this.adjustedTickDelay(40 + this.mob.getRandom().nextInt(40));
        }

        public void stop() {
            this.lookAt = null;
        }

        public void tick() {
            if (this.lookAt.isAlive()) {
                double d0 = this.onlyHorizontal ? this.mob.getEyeY() : this.lookAt.getEyeY();
                this.mob.getLookControl().setLookAt(this.lookAt.getX(), d0, this.lookAt.getZ());
                --this.lookTime;
            }
        }
    }
}
