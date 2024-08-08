package net.voidarkana.marvelous_menagerie.common.worldgen.tree;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.HugeSigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.SigillariaTrunkPlacer;

public class ModTrunkPlacerTypes {
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<SigillariaTrunkPlacer>> SIGILLARIA_TRUNK_PLACER =
            TRUNK_PLACER.register("sigillaria_trunk_placer", ()-> new TrunkPlacerType<>(SigillariaTrunkPlacer.CODEC));

    public static final RegistryObject<TrunkPlacerType<HugeSigillariaTrunkPlacer>> HUGE_SIGILLARIA_TRUNK_PLACER =
            TRUNK_PLACER.register("huge_sigillaria_trunk_placer", ()-> new TrunkPlacerType<>(HugeSigillariaTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        TRUNK_PLACER.register(eventBus);
    }
}
