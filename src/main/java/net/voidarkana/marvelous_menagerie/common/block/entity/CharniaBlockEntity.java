package net.voidarkana.marvelous_menagerie.common.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.voidarkana.marvelous_menagerie.common.block.custom.animal_block.CharniaBlock;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class CharniaBlockEntity extends BlockEntity implements GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    protected static final RawAnimation IDLE_NORTH = RawAnimation.begin().thenLoop("animation.charnia.idle_north");
    protected static final RawAnimation IDLE_SOUTH = RawAnimation.begin().thenLoop("animation.charnia.idle_south");
    protected static final RawAnimation IDLE_EAST = RawAnimation.begin().thenLoop("animation.charnia.idle_east");
    protected static final RawAnimation IDLE_WEST = RawAnimation.begin().thenLoop("animation.charnia.idle_west");

    protected static final RawAnimation IDLE_NORTHEAST = RawAnimation.begin().thenLoop("animation.charnia.idle_northeast");
    protected static final RawAnimation IDLE_NORTHWEST = RawAnimation.begin().thenLoop("animation.charnia.idle_northwest");
    protected static final RawAnimation IDLE_SOUTHEAST = RawAnimation.begin().thenLoop("animation.charnia.idle_southeast");
    protected static final RawAnimation IDLE_SOUTHWEST = RawAnimation.begin().thenLoop("animation.charnia.idle_southwest");


    public CharniaBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHARNIA_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        switch (this.getBlockState().getValue(CharniaBlock.ROTATION_8)){
            case 1:
                tAnimationState.setAndContinue(IDLE_NORTHEAST);
                break;
            case 2:
                tAnimationState.setAndContinue(IDLE_EAST);
                break;
            case 3:
                tAnimationState.setAndContinue(IDLE_SOUTHEAST);
                break;
            case 4:
                tAnimationState.setAndContinue(IDLE_SOUTH);
                break;
            case 5:
                tAnimationState.setAndContinue(IDLE_SOUTHWEST);
                break;
            case 6:
                tAnimationState.setAndContinue(IDLE_WEST);
                break;
            case 7:
                tAnimationState.setAndContinue(IDLE_NORTHWEST);
                break;
            default:
                tAnimationState.setAndContinue(IDLE_NORTH);
        }

        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

}
