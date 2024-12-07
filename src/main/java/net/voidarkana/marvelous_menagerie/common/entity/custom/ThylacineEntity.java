package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.ChatFormatting;
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
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
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

    public Player playerWhoFedIt;

    private static final EntityDataAccessor<Integer> HOWLING_TIME = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> HOWLING = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> YAWNING_TIME = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> YAWNING = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_HANDKERCHIEF = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> HANDKERCHIEF_COLOR = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<ItemStack> WOOL_ITEM = SynchedEntityData.defineId(ThylacineEntity.class, EntityDataSerializers.ITEM_STACK);

    public int prevHowlTime;
    public int prevYawnTime;
    public int howlTickDuration = 80;

    protected static final RawAnimation THYLA_WALK = RawAnimation.begin().thenLoop("animation.thylacine.walk");
    protected static final RawAnimation THYLA_IDLE = RawAnimation.begin().thenLoop("animation.thylacine.idle");
    protected static final RawAnimation THYLA_SWIM = RawAnimation.begin().thenLoop("animation.thylacine.swim");
    protected static final RawAnimation THYLA_HOWL = RawAnimation.begin().thenPlayAndHold("animation.thylacine.howl");
    protected static final RawAnimation THYLA_YAWN = RawAnimation.begin().thenPlayAndHold("animation.thylacine.yawn");

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
        this.entityData.define(YAWNING_TIME, 0);
        this.entityData.define(YAWNING, false);
        this.entityData.define(HAS_HANDKERCHIEF, false);
        this.entityData.define(HANDKERCHIEF_COLOR, 0);
        this.getEntityData().define(WOOL_ITEM, ItemStack.EMPTY);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("howlingTime", this.getHowlingTime());
        compound.putBoolean("isHowling", this.getIsHowling());
        compound.putInt("yawningTime", this.getYawningTime());
        compound.putBoolean("isYawning", this.getIsYawning());
        compound.putBoolean("hasHandkerchief", this.getHasHandkerchief());

        if (!this.getWoolItem().isEmpty()) {
            compound.put("woolItem", this.getWoolItem().save(new CompoundTag()));
        }
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        //this.setVariant(compound.getInt("variant"));
        this.setHowlingTime(compound.getInt("howlingTime"));
        this.setIsHowling(compound.getBoolean("isHowling"));
        this.setYawningTime(compound.getInt("yawningTime"));
        this.setIsYawning(compound.getBoolean("isYawning"));
        this.setIsYawning(compound.getBoolean("hasHandkerchief"));
        this.setHandkerchiefColor(compound.getInt("handkerchiefColor"));

        CompoundTag compoundtag = compound.getCompound("woolItem");

        if (compoundtag != null && !compoundtag.isEmpty()) {
            ItemStack itemstack = ItemStack.of(compoundtag);
            this.setWoolItem(itemstack);
        }

    }

    //wool item
    public ItemStack getWoolItem() {
        return this.getEntityData().get(WOOL_ITEM);
    }

    public void setWoolItem(ItemStack pStack) {
        if (!pStack.isEmpty()) {
            pStack = pStack.copyWithCount(1);
        }

        this.getEntityData().set(WOOL_ITEM, pStack);
    }

    //handkerchief
    public int getHandkerchiefColor(){
        return this.entityData.get(HANDKERCHIEF_COLOR);
    }

    public void setHandkerchiefColor(int color){
        this.entityData.set(HANDKERCHIEF_COLOR, color);
    }

    public boolean getHasHandkerchief(){
        return this.entityData.get(HAS_HANDKERCHIEF);
    }

    public boolean hasHandkerchief(){
        return getHasHandkerchief();
    }

    public void setHasHandkerchief(boolean hasHandkerchief){
        this.entityData.set(HAS_HANDKERCHIEF, hasHandkerchief);
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


    //yawning
    public int getYawningTime(){
        return this.entityData.get(YAWNING_TIME);}

    public void setYawningTime(int yawningTicksTime){
        this.entityData.set(YAWNING_TIME, yawningTicksTime);}

    public boolean getIsYawning() {
        return this.entityData.get(YAWNING);}

    public void setIsYawning(boolean yawning) {
        this.entityData.set(YAWNING, yawning);}

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

    //custom name
    public boolean isMetro() {
        String s = ChatFormatting.stripFormatting(this.getName().getString());
        return s != null && s.toLowerCase().contains("metropolitan");
    }

    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasHandkerchief()) {
            this.spawnAtLocation(this.getWoolItem());
        }
    }

    //interactions
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);
        if ((itemstack.is(Items.CHICKEN) || itemstack.is(Items.RABBIT)
                || itemstack.is(Items.COOKED_CHICKEN) || itemstack.is(Items.COOKED_RABBIT))
                && !this.getIsHowling() && this.onGround() && !this.getIsYawning()) {
            this.usePlayerItem(player, hand, itemstack);
            this.setHowlingTime(80);
            this.playerWhoFedIt = player;
            return InteractionResult.SUCCESS;

        } else if (itemstack.is(ItemTags.WOOL_CARPETS) || itemstack.is(ModTags.Items.DYE_DEPOT_WOOL_ITEM)){

            if (this.hasHandkerchief()){
                this.spawnAtLocation(this.getWoolItem());
            }
            else {
                this.setHasHandkerchief(true);
            }

            if (itemstack.is(Items.RED_CARPET)){ this.setHandkerchiefColor(1); }
            else if (itemstack.is(Items.ORANGE_CARPET)){this.setHandkerchiefColor(2);}
            else if (itemstack.is(Items.YELLOW_CARPET)){this.setHandkerchiefColor(3);}
            else if (itemstack.is(Items.LIME_CARPET)){this.setHandkerchiefColor(4);}
            else if (itemstack.is(Items.GREEN_CARPET)){this.setHandkerchiefColor(5);}
            else if (itemstack.is(Items.CYAN_CARPET)){this.setHandkerchiefColor(6);}
            else if (itemstack.is(Items.LIGHT_BLUE_CARPET)){this.setHandkerchiefColor(7);}
            else if (itemstack.is(Items.BLUE_CARPET)){this.setHandkerchiefColor(8);}
            else if (itemstack.is(Items.PURPLE_CARPET)){this.setHandkerchiefColor(9);}
            else if (itemstack.is(Items.MAGENTA_CARPET)){this.setHandkerchiefColor(10);}
            else if (itemstack.is(Items.PINK_CARPET)){this.setHandkerchiefColor(11);}
            else if (itemstack.is(Items.BROWN_CARPET)){this.setHandkerchiefColor(12);}
            else if (itemstack.is(Items.BLACK_CARPET)){this.setHandkerchiefColor(13);}
            else if (itemstack.is(Items.GRAY_CARPET)){this.setHandkerchiefColor(14);}
            else if (itemstack.is(Items.LIGHT_GRAY_CARPET)){this.setHandkerchiefColor(15);
            }
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)){ this.setHandkerchiefColor(16); }
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM )){this.setHandkerchiefColor(17);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM )){this.setHandkerchiefColor(18);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM )){this.setHandkerchiefColor(19);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM )){this.setHandkerchiefColor(20);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM )){this.setHandkerchiefColor(21);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM )){this.setHandkerchiefColor(22);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM )){this.setHandkerchiefColor(23);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_MINT_WOOL_ITEM )){this.setHandkerchiefColor(24);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM )){this.setHandkerchiefColor(25);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM )){this.setHandkerchiefColor(26);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM )){this.setHandkerchiefColor(27);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM )){this.setHandkerchiefColor(28);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_TAN_WOOL_ITEM )){this.setHandkerchiefColor(29);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM )){this.setHandkerchiefColor(30);}
            else if (itemstack.is(ModTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM )){this.setHandkerchiefColor(31);
            }
            else {this.setHandkerchiefColor(32);}

            this.playSound(SoundEvents.LLAMA_SWAG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

            this.setWoolItem(itemstack);

            this.usePlayerItem(player, hand, itemstack);

            return InteractionResult.SUCCESS;

        } else if (itemstack.is(Items.SHEARS) && this.hasHandkerchief()){

            this.setHasHandkerchief(false);

            this.spawnAtLocation(this.getWoolItem());

            this.setWoolItem(ItemStack.EMPTY);

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
            if(!(target instanceof ThylacineEntity) && (target.getType().is(ModTags.EntityTypes.THYLA_ALERT_TARGET) || target instanceof Monster || target instanceof NeutralMob)){
                if (!target.isAlliedTo(this.playerWhoFedIt)){
                    target.addEffect(new MobEffectInstance(MobEffects.GLOWING, 60));
                }
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
            if (this.getHowlingTime()==65 && !this.level().isClientSide){
                this.playSound(ModSounds.THYLACINE_ALERT.get());
            }
            if (this.getHowlingTime()==21 && !this.level().isClientSide){
                this.playSound(SoundEvents.BELL_RESONATE);
            }
            if (this.getHowlingTime()==1 && !this.level().isClientSide){
                alertThreats();
            }
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
                this.playSound(ModSounds.THYLACINE_YAWN.get());
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
        } else if (this.isInWater() && !this.onGround()) {
            event.setAndContinue(THYLA_SWIM);
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

    protected SoundEvent getAmbientSound() {
        return ModSounds.THYLACINE_IDLE.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.THYLACINE_HURT.get();
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.THYLACINE_DEATH.get();
    }

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
