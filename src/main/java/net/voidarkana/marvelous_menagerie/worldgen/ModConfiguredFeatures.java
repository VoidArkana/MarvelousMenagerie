package net.voidarkana.marvelous_menagerie.worldgen;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.levelgen.feature.foliageplacers.PineFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.SpruceFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AlterGroundDecorator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.block.ModBlocks;
import net.voidarkana.marvelous_menagerie.worldgen.features.HugePrototaxitesFeature;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.HugeSigillariaFoliagePlacer;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.HugeSigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.SigillariaTrunkPlacer;
import net.voidarkana.marvelous_menagerie.worldgen.tree.custom.SigillariaFoliagePlacer;
import net.voidarkana.marvelous_menagerie.worldgen.util.HugePrototaxitesFeatureConfiguration;

import java.util.function.Supplier;

public class ModConfiguredFeatures {

    public static final DeferredRegister<Feature<?>> MOD_FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, MarvelousMenagerie.MOD_ID);

    public static final RegistryObject<Feature<HugePrototaxitesFeatureConfiguration>> PROTOTAXITES_FEATURE =
            register_feature("prototaxites_feature", () -> new HugePrototaxitesFeature(HugePrototaxitesFeatureConfiguration.CODEC));

    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_KEY = registerKey("sigillaria");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SIGILLARIA_HUGE = registerKey("sigillaria_huge");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PROTOTAXITES_KEY = registerKey("prototaxites");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {

        register(context, SIGILLARIA_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_STEM.get()),
                new SigillariaTrunkPlacer(7, 1, 1),
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_LEAVES.get()),
                new SigillariaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 2),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, SIGILLARIA_HUGE, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_STEM.get()),
                new HugeSigillariaTrunkPlacer(20, 1, 1),
                BlockStateProvider.simple(ModBlocks.SIGILLARIA_LEAVES.get()),
                new HugeSigillariaFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 5),
                new TwoLayersFeatureSize(1, 0, 1)).ignoreVines().build());

        register(context, PROTOTAXITES_KEY, ModConfiguredFeatures.PROTOTAXITES_FEATURE.get(), new HugePrototaxitesFeatureConfiguration(
                BlockStateProvider.simple(ModBlocks.PROTOTAXITES_BLOCK.get()), 3));

    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }

    public static <T extends FeatureConfiguration> RegistryObject<Feature<T>> register_feature(String name, Supplier<Feature<T>> featureSupplier) {
        return MOD_FEATURES.register(name, featureSupplier);
    }

    public static void register(IEventBus eventBus){
        MOD_FEATURES.register(eventBus);
    }

}
