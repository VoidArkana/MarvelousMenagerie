package net.voidarkana.marvelous_menagerie.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.voidarkana.marvelous_menagerie.item.ModItems;

public class EggInShellFoodItem extends Item {
    public EggInShellFoodItem(Properties pProperties) {
        super(pProperties);
    }
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pEntityLiving);
        return pEntityLiving instanceof Player && ((Player)pEntityLiving).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.EGG_SHELL_FRAGMENT.get());
    }
}
