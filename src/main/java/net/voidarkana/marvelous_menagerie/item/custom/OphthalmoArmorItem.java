package net.voidarkana.marvelous_menagerie.item.custom;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class OphthalmoArmorItem extends Item {

    private final ItemStack material;
    public OphthalmoArmorItem(Properties pProperties, ItemStack material) {
        super(pProperties);
        this.material = material;
    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return material == ingredient;
    }

}
