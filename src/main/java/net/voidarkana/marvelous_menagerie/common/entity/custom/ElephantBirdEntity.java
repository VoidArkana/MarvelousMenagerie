package net.voidarkana.marvelous_menagerie.common.entity.custom;

import com.peeko32213.unusualprehistory.common.entity.msc.util.dino.EntityBaseDinosaurAnimal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.network.PacketDistributor;
import net.voidarkana.marvelous_menagerie.client.screen.ElephantBirdMenu;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ai.ElephantBirdFollowCaravanGoal;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;
import net.voidarkana.marvelous_menagerie.client.sound.ModSounds;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import net.voidarkana.marvelous_menagerie.util.network.ModNetworkHandler;
import net.voidarkana.marvelous_menagerie.util.network.OpenElephantBirdScreenPacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Iterator;


public class ElephantBirdEntity extends EntityBaseDinosaurAnimal implements GeoEntity, ContainerListener, HasCustomInventoryScreen {

    public ElephantBirdEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
        this.setMaxUpStep(1.5F);
        GroundPathNavigation groundpathnavigation = (GroundPathNavigation)this.getNavigation();
        groundpathnavigation.setCanWalkOverFences(true);
        this.createInventory();
    }

    private static final EntityDataAccessor<Integer> TIME_TO_EAT = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.INT);
    int prevEatenTime;
    private static final EntityDataAccessor<Integer> SMH_TIME = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.INT);
    int prevSMHTime;
    private static final EntityDataAccessor<Boolean> IS_SMH = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<ItemStack> WOOL_ITEM = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.ITEM_STACK);
    @javax.annotation.Nullable
    private ElephantBirdEntity caravanHead;
    @javax.annotation.Nullable
    private ElephantBirdEntity caravanTail;
    private static final EntityDataAccessor<Boolean> DATA_ID_CHEST = SynchedEntityData.defineId(ElephantBirdEntity.class, EntityDataSerializers.BOOLEAN);


    protected static final RawAnimation BABY_WALK = RawAnimation.begin().thenLoop("animation.elephant_bird.baby_walk");
    protected static final RawAnimation ELE_WALK = RawAnimation.begin().thenLoop("animation.elephant_bird.walk");
    protected static final RawAnimation ELE_IDLE = RawAnimation.begin().thenLoop("animation.elephant_bird.idle");
    protected static final RawAnimation ELE_SWIM = RawAnimation.begin().thenLoop("animation.elephant_bird.swim");
    protected static final RawAnimation BABY_SWIM = RawAnimation.begin().thenLoop("animation.elephant_bird.baby_swim");

    protected static final RawAnimation ELE_SMH = RawAnimation.begin().thenPlay("animation.elephant_bird.smh");


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.15)
                .add(Attributes.FOLLOW_RANGE, 40.0D);
    }

    @javax.annotation.Nullable
    public LivingEntity getControllingPassenger() {
        return null;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new ElephantBirdFollowCaravanGoal(this, (double)2.1F));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0D));
        this.goalSelector.addGoal(2, new FollowParentGoal(this, 1.1D){
            @Override
            public boolean canUse() {
                return super.canUse() && !ElephantBirdEntity.this.inCaravan();
            }
            @Override
            public boolean canContinueToUse() {
                return super.canContinueToUse() && !ElephantBirdEntity.this.inCaravan();
            }
        });
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
        super.registerGoals();
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIME_TO_EAT, 0);
        this.entityData.define(SMH_TIME, 0);
        this.entityData.define(IS_SMH, false);
        this.entityData.define(WOOL_ITEM, ItemStack.EMPTY);
        this.entityData.define(DATA_ID_CHEST, false);
    }

    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("timeSinceEaten", this.getEatenTime());

        if (this.hasSwag()) {
            compound.put("woolItem", this.getWoolItem().save(new CompoundTag()));
        }


        compound.putBoolean("chestedBird", this.hasChest());

        if (this.hasChest()) {

            ListTag listTag = new ListTag();
            for (int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemStack = this.inventory.getItem(i);
                if (itemStack.isEmpty()) continue;
                CompoundTag compoundTag2 = new CompoundTag();
                compoundTag2.putByte("Slot", (byte)i);
                itemStack.save(compoundTag2);
                listTag.add(compoundTag2);
            }
            compound.put("Items", listTag);
        }
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setEatenTime(compound.getInt("timeSinceEaten"));

        if (compound != null && !compound.isEmpty()) {
            ItemStack itemstack = ItemStack.of(compound);
            this.setWoolItemData(itemstack);
        }

        this.setChest(compound.getBoolean("chestedBird"));
        this.createInventory();
        if (this.hasChest()) {
            ListTag listtag = compound.getList("Items", 10);


            for (int i = 0; i < listtag.size(); ++i) {
                CompoundTag compoundTag2 = listtag.getCompound(i);
                int j = compoundTag2.getByte("Slot") & 0xFF;
                if (j >= this.inventory.getContainerSize()) continue;
                this.inventory.setItem(j, ItemStack.of(compoundTag2));
            }
        }

        this.updateContainerEquipment();
    }

    public ItemStack getWoolItem() {
        return this.getEntityData().get(WOOL_ITEM);
    }

    public void setWoolItemData(ItemStack pStack) {

        if (!pStack.isEmpty()) {
            pStack = pStack.copyWithCount(1);
        }

        this.getEntityData().set(WOOL_ITEM, pStack);
    }

    public void setWoolItem(ItemStack pStack, Player player) {
        if (this.hasSwag() && !player.getAbilities().instabuild){
            spawnAtLocation(this.getWoolItem());
        }

        if (!pStack.isEmpty()) {
            pStack = pStack.copyWithCount(1);
        }

        this.getEntityData().set(WOOL_ITEM, pStack);
    }

    //handkerchief
    public String getSwagColor(){
        ItemStack itemstack = this.getWoolItem();

        if (itemstack.is(Items.RED_CARPET)){ return "red"; }
        else if (itemstack.is(Items.ORANGE_CARPET)){return("orange");}
        else if (itemstack.is(Items.YELLOW_CARPET)){return("yellow");}
        else if (itemstack.is(Items.LIME_CARPET)){return("lime");}
        else if (itemstack.is(Items.GREEN_CARPET)){return("green");}
        else if (itemstack.is(Items.CYAN_CARPET)){return("cyan");}
        else if (itemstack.is(Items.LIGHT_BLUE_CARPET)){return("light_blue");}
        else if (itemstack.is(Items.BLUE_CARPET)){return("blue");}
        else if (itemstack.is(Items.PURPLE_CARPET)){return("purple");}
        else if (itemstack.is(Items.MAGENTA_CARPET)){return("magenta");}
        else if (itemstack.is(Items.PINK_CARPET)){return("pink");}
        else if (itemstack.is(Items.BROWN_CARPET)){return("brown");}
        else if (itemstack.is(Items.BLACK_CARPET)){return("black");}
        else if (itemstack.is(Items.GRAY_CARPET)){return("gray");}
        else if (itemstack.is(Items.LIGHT_GRAY_CARPET)){return("light_gray");
        }
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_AMBER_WOOL_ITEM)){ return("amber"); }
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_AQUA_WOOL_ITEM )){return("aqua");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_BEIGE_WOOL_ITEM )){return("beige");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_CORAL_WOOL_ITEM )){return("coral");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_FOREST_WOOL_ITEM )){return("forest");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_GINGER_WOOL_ITEM )){return("ginger");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_INDIGO_WOOL_ITEM )){return("indigo");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_MAROON_WOOL_ITEM )){return("maroon");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_MINT_WOOL_ITEM )){return("mint");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_NAVY_WOOL_ITEM )){return("navy");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_OLIVE_WOOL_ITEM )){return("olive");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_ROSE_WOOL_ITEM )){return("rose");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_SLATE_WOOL_ITEM )){return("slate");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_TAN_WOOL_ITEM )){return("tan");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_TEAL_WOOL_ITEM )){return("teal");}
        else if (itemstack.is(ModTags.Items.DYE_DEPOT_VERDANT_WOOL_ITEM )){return("verdant");
        }
        else {return("white");}
    }

    public boolean hasSwag(){
        return !this.getWoolItem().isEmpty();
    }

    //has eaten
    public int getEatenTime(){
        return this.entityData.get(TIME_TO_EAT);}

    public void setEatenTime(int timeToEatTicks){
        this.entityData.set(TIME_TO_EAT, timeToEatTicks);}

    //SMH
    public int getSMH(){
        return this.entityData.get(SMH_TIME);}

    public void setSMH(int smhTime){
        this.entityData.set(SMH_TIME, smhTime);}

    //is SMHing
    public boolean getIsSMH(){
        return this.entityData.get(IS_SMH);}

    public void setIsSMH(boolean isSMH){
        this.entityData.set(IS_SMH, isSMH);}


    //caravan
    public void leaveCaravan() {
        if (this.caravanHead != null) {
            this.caravanHead.caravanTail = null;
        }

        this.caravanHead = null;
    }

    public void joinCaravan(ElephantBirdEntity pCaravanHead) {
        this.caravanHead = pCaravanHead;
        this.caravanHead.caravanTail = this;
    }

    public boolean hasCaravanTail() {
        return this.caravanTail != null;
    }

    public boolean inCaravan() {
        return this.caravanHead != null;
    }

    @javax.annotation.Nullable
    public ElephantBirdEntity getCaravanHead() {
        return this.caravanHead;
    }

    protected double followLeashSpeed() {
        return 2.0D;
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        InteractionResult type = super.mobInteract(player, hand);

        if ((itemstack.is(Items.GOLDEN_CARROT)) && this.onGround() && this.getEatenTime()<=0 && !this.isBaby()) {
            this.usePlayerItem(player, hand, itemstack);
            this.playSound(SoundEvents.HORSE_EAT, 1.0F, (this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) + 1.0F);
            this.setEatenTime(600);
            return InteractionResult.SUCCESS;
        } else if ((itemstack.is(Items.GOLDEN_CARROT)) && this.onGround() && this.getEatenTime()>0 && !this.isBaby() && this.getSMH()<=0){
            this.setSMH(60);
            this.setIsSMH(true);
            this.playSound(ModSounds.ELE_GRUMBLE.get());

            for(int j = 0; j < 5; ++j) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(2.0), this.getRandomY() + 0.5, this.getRandomZ(2.0), 0.0, 0.0, 0.0);
            }

            return InteractionResult.SUCCESS;
        }else if (!this.isVehicle() && !this.isBaby()) {

            if (!this.hasChest() && itemstack.is(Items.CHEST)) {

                this.equipChest(player, itemstack);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if (itemstack.is(ItemTags.WOOL_CARPETS)) {

                this.playSound(SoundEvents.LLAMA_SWAG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
                this.setWoolItem(itemstack, player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if (itemstack.is(Items.SHEARS) && (this.hasSwag() || this.hasChest())) {

                this.playSound(SoundEvents.SHEEP_SHEAR, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);

                if (this.hasSwag()) {

                    this.setWoolItem(ItemStack.EMPTY, player);

                }else if (this.hasChest()){
                    this.spawnAtLocation(new ItemStack(Items.CHEST));
                    if (this.inventory != null) {
                        for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                            ItemStack inventoryItem = this.inventory.getItem(i);
                            if (!inventoryItem.isEmpty() && !EnchantmentHelper.hasVanishingCurse(inventoryItem)) {
                                this.spawnAtLocation(inventoryItem);
                            }
                        }
                    }
                    this.setChest(false);
                }

                return InteractionResult.sidedSuccess(this.level().isClientSide);

            }else if (player.isSecondaryUseActive() && this.hasChest()) {

                this.openCustomInventoryScreen(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);

            } else {
                if (!itemstack.isEmpty()) {
                    InteractionResult interactionresult = itemstack.interactLivingEntity(player, this, hand);
                    if (interactionresult.consumesAction()) {
                        return interactionresult;
                    }
                }

                this.doPlayerRide(player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }

        } else {
            return type;
        }
    }

    private void equipChest(Player pPlayer, ItemStack pChestStack) {
        this.setChest(true);
        this.playChestEquipsSound();
        if (!pPlayer.getAbilities().instabuild) {
            pChestStack.shrink(1);
        }

        this.createInventory();
    }

    protected void doPlayerRide(Player pPlayer) {
        if (!this.level().isClientSide) {
            pPlayer.setYRot(this.getYRot());
            pPlayer.setXRot(this.getXRot());
            pPlayer.startRiding(this);
        }

    }

    public void tick (){
        super.tick();

        if (this.getEatenTime()>0){
            if (this.getEatenTime()==590){
                this.playSound(SoundEvents.SNIFFER_EGG_PLOP, 1.0F, ((this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) - 1.0F));
                this.spawnAtLocation(ModItems.CRACKED_ELEPHANT_EGG.get());
            }
            if (this.getEatenTime()==588){
                this.playSound(SoundEvents.SNIFFER_EGG_CRACK, 1.0F, ((this.random.nextFloat() - (this.random.nextFloat()) * 0.2F) - 1.0F));
            }
            prevEatenTime = this.getEatenTime();
            this.setEatenTime(prevEatenTime-1);
        }
        if (this.getSMH()>0){
            prevSMHTime = this.getSMH();
            this.setSMH(prevSMHTime-1);

            if (this.getSMH()==0){
                this.setIsSMH(false);
            }
        }
    }


    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "Normal", 10, this::Controller)});
        controllerRegistrar.add(new AnimationController[]{new AnimationController(this, "SMH", 10, this::SMHController)});
    }

    protected <E extends GeoAnimatable> PlayState Controller(AnimationState<E> event) {
        if (this.isFromBook()){
            return PlayState.STOP;
        }else if (this.isInWater() && !this.onGround()){
            if (this.isBaby()){
                event.setAndContinue(BABY_SWIM);
            }else{
                event.setAndContinue(ELE_SWIM);
            }
        } else if(this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6 && this.onGround()) {
            if (this.isBaby()){
                event.setAndContinue(BABY_WALK);
                //event.getController().setAnimationSpeed(1.25f);
            }
            else {
                event.setAndContinue(ELE_WALK);
                //event.getController().setAnimationSpeed(1f);
            }
        } else if (this.onGround()){
            event.setAndContinue(ELE_IDLE);
        }
        return PlayState.CONTINUE;
    }

    protected <E extends GeoAnimatable> PlayState SMHController(AnimationState<E> event) {
        if (this.getIsSMH()){
            event.setAndContinue(ELE_SMH);
            return PlayState.CONTINUE;
        } else {
            event.getController().forceAnimationReset();
            return PlayState.STOP;
        }
    }

    protected void playStepSound(BlockPos p_28301_, BlockState p_28302_) {
        if(this.isBaby()){
            this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
        }
        else{
            this.playSound(ModSounds.LARGE_STEPS.get(), 0.25F, 1.0F);
        }
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        if (pFallDistance > 1.0F) {
            if (this.isBaby()){
                this.playSound(SoundEvents.HORSE_LAND, 0F, 1.0F);
            }
            else {
                this.playSound(SoundEvents.HORSE_LAND, 0.15F, 1.0F);
            }
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
    public float getVoicePitch() {
        return (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F;
    }

    protected SoundEvent getAmbientSound() {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_IDLE.get();
        }else {
            return ModSounds.ELE_IDLE.get();
        }
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_HURT.get();
        }else {
            return ModSounds.ELE_HURT.get();
        }
    }

    protected SoundEvent getDeathSound() {
        if (this.isBaby()){
            return ModSounds.BABY_ELE_DEATH.get();
        }else {
            return ModSounds.ELE_DEATH.get();
        }
    }

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

    public SimpleContainer inventory;

    private net.minecraftforge.common.util.LazyOptional<?> itemHandler = null;

    protected void createInventory() {
        SimpleContainer simplecontainer = this.inventory;
        this.inventory = new SimpleContainer(this.getInventorySize());
        if (simplecontainer != null) {
            simplecontainer.removeListener(this);
            int i = Math.min(simplecontainer.getContainerSize(), this.inventory.getContainerSize());

            //int i = 24;

            for(int j = 0; j < i; ++j) {
                ItemStack itemstack = simplecontainer.getItem(j);
                if (!itemstack.isEmpty()) {
                    this.inventory.setItem(j, itemstack.copy());
                }
            }
        }

        this.inventory.addListener(this);
        this.updateContainerEquipment();
        this.itemHandler = net.minecraftforge.common.util.LazyOptional.of(() -> new net.minecraftforge.items.wrapper.InvWrapper(this.inventory));
    }

    public void containerChanged(Container pInvBasic) {
        this.updateContainerEquipment();
    }

    protected void updateContainerEquipment() {
    }

    protected void dropEquipment() {
        super.dropEquipment();

        if (this.hasChest()) {
            this.spawnAtLocation(new ItemStack(Items.CHEST));
        }
        if (this.inventory != null) {
            for(int i = 0; i < this.inventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.inventory.getItem(i);
                if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                    this.spawnAtLocation(itemstack);
                }
            }

        }
        if (this.hasSwag()) {
            this.spawnAtLocation(this.getWoolItem());
        }
    }

    public void openCustomInventoryScreen(Player pPlayer) {
        if (!this.level().isClientSide && (!this.isVehicle() || this.hasPassenger(pPlayer)) && this.hasChest()) {

            ServerPlayer sp = (ServerPlayer) pPlayer;
            if (sp.containerMenu != sp.inventoryMenu) {
                sp.closeContainer();
            }

            sp.nextContainerCounter();
            ModNetworkHandler.CHANNEL.send(PacketDistributor.PLAYER.with(() -> sp), new OpenElephantBirdScreenPacket(sp.containerCounter, this.getId()));
            sp.containerMenu = new ElephantBirdMenu(sp.containerCounter, sp.getInventory(), this.getInventory(), this);
            sp.initMenu(sp.containerMenu);
            //this.inventoryOpen = true;
            MinecraftForge.EVENT_BUS.post(new PlayerContainerEvent.Open(sp, sp.containerMenu));
        }

    }

    @javax.annotation.Nullable
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    public boolean hasChest() {
        return this.entityData.get(DATA_ID_CHEST);
    }

    public void setChest(boolean pChested) {
        this.entityData.set(DATA_ID_CHEST, pChested);
    }

    public int getInventorySize() {
        return this.hasChest() ? 24 : 0;
    }

    protected void playChestEquipsSound() {
        this.playSound(SoundEvents.DONKEY_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

//    public SlotAccess getSlot(int pSlot) {
//
//        if(pSlot == 499) {
//            return new SlotAccess() {
//                public ItemStack get() {
//                    return ElephantBirdEntity.this.hasChest() ? new ItemStack(Items.CHEST) : ItemStack.EMPTY;
//                }
//
//                public boolean set(ItemStack p_149485_) {
//                    if (p_149485_.isEmpty()) {
//                        if (ElephantBirdEntity.this.hasChest()) {
//                            ElephantBirdEntity.this.setChest(false);
//                            ElephantBirdEntity.this.createInventory();
//                        }
//
//                        return true;
//                    } else if (p_149485_.is(Items.CHEST)) {
//                        if (!ElephantBirdEntity.this.hasChest()) {
//                            ElephantBirdEntity.this.setChest(true);
//                            ElephantBirdEntity.this.createInventory();
//                        }
//
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//            };
//        }else{
//            int j = pSlot - 500 + 2;
//            return j >= 0 && j < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, j) : super.getSlot(pSlot);
//        }
//    }

//    private SlotAccess createEquipmentSlotAccess(final int pSlot, final Predicate<ItemStack> pStackFilter) {
//        return new SlotAccess() {
//            public ItemStack get() {
//                return ElephantBirdEntity.this.inventory.getItem(pSlot);
//            }
//
//            public boolean set(ItemStack p_149528_) {
//                if (!pStackFilter.test(p_149528_)) {
//                    return false;
//                } else {
//                    ElephantBirdEntity.this.inventory.setItem(pSlot, p_149528_);
//                    ElephantBirdEntity.this.updateContainerEquipment();
//                    return true;
//                }
//            }
//        };
//    }

    public boolean hasInventoryChanged(Container pInventory) {
        return this.inventory != pInventory;
    }
}
