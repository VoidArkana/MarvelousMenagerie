package net.voidarkana.marvelous_menagerie.common.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.voidarkana.marvelous_menagerie.common.worldgen.ModConfiguredFeatures;
import net.voidarkana.marvelous_menagerie.common.worldgen.tree.custom.AbstractHugeTreeGrower;
import org.jetbrains.annotations.Nullable;

public class SigillariaTreeGrower extends AbstractHugeTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return ModConfiguredFeatures.SIGILLARIA_KEY;
    }

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredHugeFeature(RandomSource pRandom) {
        return ModConfiguredFeatures.SIGILLARIA_HUGE;
    }

//    @Override
//    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource pRandom) {
//        return ModConfiguredFeatures.SIGILLARIA_HUGE;
//    }
}
