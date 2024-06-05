package net.voidarkana.marvelous_menagerie.item.custom;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.item.ModItems;

public class StackableBowlFoodItem extends Item {
    public StackableBowlFoodItem(Properties pProperties) {
        super(pProperties);
    }
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);

        if (pStack.isEmpty()) {
            return new ItemStack(Items.BOWL);
        } else {
            if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild) {
                ItemStack itemstack = new ItemStack(Items.BOWL);
                Player player = (Player)pEntityLiving;
                if (!player.getInventory().add(itemstack)) {
                    player.drop(itemstack, false);
                }
            }

            return pStack;
        }
    }
}
