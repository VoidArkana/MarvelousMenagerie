package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModWoodTypes {
    public static final WoodType SIGILLARIA = WoodType.register(new WoodType(MarvelousMenagerie.MOD_ID + ":sigillaria", BlockSetType.OAK));

    public static final WoodType PROTOTAXITES = WoodType.register(new WoodType(MarvelousMenagerie.MOD_ID + ":prototaxites", BlockSetType.OAK));

    public static final WoodType ZULOAGAE = WoodType.register(new WoodType(MarvelousMenagerie.MOD_ID + ":zuloagae", BlockSetType.BAMBOO));

}
