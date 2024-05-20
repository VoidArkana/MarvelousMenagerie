package net.voidarkana.marvelous_menagerie.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
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
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class DodoEntity extends EntityBaseDinosaurAnimal implements GeoEntity {

    private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.MELON_SLICE, Items.GLISTERING_MELON_SLICE, Items.MELON, Items.PUMPKIN);

    public DodoEntity(EntityType<? extends EntityBaseDinosaurAnimal> entityType, Level level) {
        super(entityType, level);
    }


    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.dodo.walk");
    protected static final RawAnimation FLAP_ANIM = RawAnimation.begin().thenLoop("animation.dodo.flap");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.dodo.idle");


    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 1.0D, FOOD_ITEMS, false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new DodoEntity.DestroyMelonAndPumpkinGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        super.registerGoals();
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
    }


    //variants
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.INT);

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public void setVariant(int variant) {
        this.entityData.set(VARIANT, variant);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getVariant());
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getInt("Variant"));
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


    //attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.ARMOR, 0.0)
                .add(Attributes.ARMOR_TOUGHNESS, 0.0);
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

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> event) {

        //if it's in the air
        if(!(this.onGround())) {
            event.getController().setAnimation(FLAP_ANIM);
            return PlayState.CONTINUE;
        }
        //if it's walking
        else if(event.isMoving()) {
            event.getController().setAnimation(WALK_ANIM);
            return PlayState.CONTINUE;
        }
        //if it's not doing any of the above, play idle
        else {
            event.getController().setAnimation(IDLE_ANIM);
            return PlayState.CONTINUE;
        }

    }

/*
    @Override
    public DodoVariant getVariant() {
        return DodoVariant.byId(this.getTypeVariant() & 255);
    }

    private int getTypeVariant() {
        return this.entityData.get(DATA_ID_TYPE_VARIANT);
    }

    private void setVariant(DodoVariant variant) {
        this.entityData.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }*/

    static class DestroyMelonAndPumpkinGoal extends MoveToBlockGoal {
        private final DodoEntity dodo;

        public DestroyMelonAndPumpkinGoal(DodoEntity pDodo) {
            super(pDodo, 1F, 16);
            this.dodo = pDodo;
        }

        public boolean canUse() {
            if (this.nextStartTick <= 0) {
                if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.dodo.level(), this.dodo)) {
                    return false;
                }
            }
            return super.canUse();
        }

        public void tick() {
            super.tick();
            this.dodo.getLookControl().setLookAt((double)this.blockPos.getX() + 0.5D, (double)(this.blockPos.getY() + 1), (double)this.blockPos.getZ() + 0.5D, 10.0F, (float)this.dodo.getMaxHeadXRot());
            if (this.isReachedTarget()) {
                Level level = this.dodo.level();
                BlockPos blockpos = this.blockPos;
                BlockState blockstate = level.getBlockState(blockpos);
                Block block = blockstate.getBlock();
                if (block instanceof MelonBlock || block instanceof PumpkinBlock) {

                    level.destroyBlock(blockpos, true, this.dodo);
                }
                this.nextStartTick = 10;
            }
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockstate = pLevel.getBlockState(pPos);
            if (blockstate.is(Blocks.MELON) || blockstate.is(Blocks.PUMPKIN)) {
                return true;
            }
            else {
                return false;
            }
        }

    }
}
