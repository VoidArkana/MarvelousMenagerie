package net.voidarkana.marvelous_menagerie.common.entity.custom.ai;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.phys.Vec3;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;

import java.util.EnumSet;
import java.util.List;

public class ElephantBirdFollowCaravanGoal extends Goal {
    public final ElephantBirdEntity bird;
    private double speedModifier;
    private static final int CARAVAN_LIMIT = 8;
    private int distCheckCounter;

    public ElephantBirdFollowCaravanGoal(ElephantBirdEntity pBird, double pSpeedModifier) {
        this.bird = pBird;
        this.speedModifier = pSpeedModifier;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!this.bird.isLeashed() && !this.bird.inCaravan()) {
            List<Entity> list = this.bird.level().getEntities(this.bird, this.bird.getBoundingBox().inflate(15.0D, 4.0D, 15.0D), (entity) -> {
                EntityType<?> entitytype = entity.getType();
                return entitytype == ModEntities.ELEPHANT_BIRD.get();
            });
            ElephantBirdEntity llama = null;
            double d0 = Double.MAX_VALUE;

            for(Entity entity : list) {
                ElephantBirdEntity llama1 = (ElephantBirdEntity)entity;
                if (llama1.inCaravan() && !llama1.hasCaravanTail()) {
                    double d1 = this.bird.distanceToSqr(llama1);
                    if (!(d1 > d0)) {
                        d0 = d1;
                        llama = llama1;
                    }
                }
            }

            if (llama == null) {
                for(Entity entity1 : list) {
                    ElephantBirdEntity llama2 = (ElephantBirdEntity)entity1;
                    if (llama2.isLeashed() && !llama2.hasCaravanTail()) {
                        double d2 = this.bird.distanceToSqr(llama2);
                        if (!(d2 > d0)) {
                            d0 = d2;
                            llama = llama2;
                        }
                    }
                }
            }

            if (llama == null) {
                return false;
            } else if (d0 < 4.0D) {
                return false;
            } else if (!llama.isLeashed() && !this.firstIsLeashed(llama, 1)) {
                return false;
            } else {
                this.bird.joinCaravan(llama);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        if (this.bird.inCaravan() && this.bird.getCaravanHead().isAlive() && this.firstIsLeashed(this.bird, 0)) {
            double d0 = this.bird.distanceToSqr(this.bird.getCaravanHead());
            if (d0 > 676.0D) {
                if (this.speedModifier <= 3.0D) {
                    this.speedModifier *= 1.2D;
                    this.distCheckCounter = reducedTickDelay(40);
                    return true;
                }

                if (this.distCheckCounter == 0) {
                    return false;
                }
            }

            if (this.distCheckCounter > 0) {
                --this.distCheckCounter;
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.bird.leaveCaravan();
        this.speedModifier = 2.1D;
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        if (this.bird.inCaravan()) {
            if (!(this.bird.getLeashHolder() instanceof LeashFenceKnotEntity)) {
                ElephantBirdEntity llama = this.bird.getCaravanHead();
                double d0 = (double)this.bird.distanceTo(llama);
                float f = 2.0F;
                Vec3 vec3 = (new Vec3(llama.getX() - this.bird.getX(), llama.getY() - this.bird.getY(), llama.getZ() - this.bird.getZ())).normalize().scale(Math.max(d0 - 2.0D, 0.0D));
                this.bird.getNavigation().moveTo(this.bird.getX() + vec3.x, this.bird.getY() + vec3.y, this.bird.getZ() + vec3.z, this.speedModifier);
            }
        }
    }

    private boolean firstIsLeashed(ElephantBirdEntity pLlama, int pLeashedQueuePosition) {
        if (pLeashedQueuePosition > 8) {
            return false;
        } else if (pLlama.inCaravan()) {
            if (pLlama.getCaravanHead().isLeashed()) {
                return true;
            } else {
                ElephantBirdEntity llama = pLlama.getCaravanHead();
                ++pLeashedQueuePosition;
                return this.firstIsLeashed(llama, pLeashedQueuePosition);
            }
        } else {
            return false;
        }
    }
}
