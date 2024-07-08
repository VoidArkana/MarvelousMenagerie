package net.voidarkana.marvelous_menagerie.worldgen.tree;

import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.voidarkana.marvelous_menagerie.worldgen.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class SigillariaTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource pRandom, boolean pHasFlowers) {
        return ModConfiguredFeatures.SIGILLARIA_KEY;
    }
}
