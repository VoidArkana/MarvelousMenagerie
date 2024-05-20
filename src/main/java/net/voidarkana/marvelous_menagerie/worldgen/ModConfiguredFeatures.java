package net.voidarkana.marvelous_menagerie.worldgen;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.SigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.SigillariaFoliagePlacer;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_KEY = registerKey("sigillaria");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, SIGILLARIA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_STEM.get()),
                new SigillariaTrunkPlacer(7, 1, 1),
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_LEAVES.get()),
                new SigillariaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

}
