package net.voidarkana.marvelous_menagerie.common.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeSigillariaFoliagePlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.SigillariaFoliagePlacer;

public class ModFoliagePlacers {

    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<SigillariaFoliagePlacer>> SIGILLARIA_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("sigillaria_foliage_placer", () -> new FoliagePlacerType<>(SigillariaFoliagePlacer.CODEC));


    public static final RegistryObject<FoliagePlacerType<HugeSigillariaFoliagePlacer>> HUGE_SIGILLARIA_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("huge_sigillaria_foliage_placer", () -> new FoliagePlacerType<>(HugeSigillariaFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus){
        FOLIAGE_PLACERS.register(eventBus);
    }
}
