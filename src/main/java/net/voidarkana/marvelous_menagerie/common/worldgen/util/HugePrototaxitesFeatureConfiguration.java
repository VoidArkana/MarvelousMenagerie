package net.voidarkana.marvelous_menagerie.common.worldgen.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.stream.Stream;

public class HugePrototaxitesFeatureConfiguration implements FeatureConfiguration {

    public static final Codec<HugePrototaxitesFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(
                    BlockStateProvider.CODEC.fieldOf("stem_provider").forGetter((HugePrototaxitesFeatureConfiguration) -> HugePrototaxitesFeatureConfiguration.stemProvider),
                    Codec.intRange(0, 16).fieldOf("height").orElse(2).forGetter((HugePrototaxitesFeatureConfiguration) -> HugePrototaxitesFeatureConfiguration.height))
            .apply(instance, HugePrototaxitesFeatureConfiguration::new));

    public final BlockStateProvider stemProvider;
    public int height;

    public HugePrototaxitesFeatureConfiguration(BlockStateProvider stemProvider, int height) {
        this.stemProvider = stemProvider;
        this.height = height;
    }

    @Override
    public Stream<ConfiguredFeature<?, ?>> getFeatures() {
        return FeatureConfiguration.super.getFeatures();
    }

}
