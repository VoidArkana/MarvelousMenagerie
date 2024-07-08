package net.voidarkana.marvelous_menagerie.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class EquipmentEnchantment extends Enchantment {

    private String registryName;
    private int levels;

    protected EquipmentEnchantment(String name, Rarity pRarity, EnchantmentCategory pCategory,int levels, EquipmentSlot... pApplicableSlots) {
        super(pRarity, pCategory, pApplicableSlots);
        this.registryName = name;
        this.levels = levels;
    }

    protected boolean checkCompatibility(Enchantment enchantment) {
        return this != enchantment && ModEnchantmentsClass.areCompatible(this, enchantment);
    }

    public int getMaxLevel() {
        return levels;
    }

    public String getName(){
        return registryName;
    }
}
