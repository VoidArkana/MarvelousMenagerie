package net.voidarkana.marvelous_menagerie.common.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.voidarkana.marvelous_menagerie.common.item.ModItems;

public class ModEnchantmentsClass {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =  DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, MarvelousMenagerie.MOD_ID);

    public static final EnchantmentCategory ANOMALOUS_GOGGLES = EnchantmentCategory.create("anomalous_goggles", (item -> item == ModItems.ANOMALOUS_GOGGLES.get()));

    public static final RegistryObject<Enchantment> CAMBRIAN_VISION = ENCHANTMENTS.register("cambrian_vision",
            ()-> new EquipmentEnchantment("cambrian_vision",Enchantment.Rarity.RARE, ANOMALOUS_GOGGLES, 1, EquipmentSlot.HEAD));

    public static final RegistryObject<Enchantment> INFERNAL_VISION = ENCHANTMENTS.register("infernal_vision",
            ()-> new EquipmentEnchantment("infernal_vision",Enchantment.Rarity.VERY_RARE, ANOMALOUS_GOGGLES, 1, EquipmentSlot.HEAD));

    public static boolean areCompatible(EquipmentEnchantment enchantment1, Enchantment enchantment2) {
        if(enchantment1 == CAMBRIAN_VISION.get() && enchantment2 == INFERNAL_VISION.get())
            return false;
        return true;
    }

    public static void register (IEventBus eventBus){
        ENCHANTMENTS.register(eventBus);
    }
}
