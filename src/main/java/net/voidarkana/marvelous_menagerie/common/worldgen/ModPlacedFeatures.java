package net.voidarkana.marvelous_menagerie.common.worldgen;

import com.peeko32213.unusualprehistory.common.config.UnusualPrehistoryConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.common.block.ModBlocks;

import java.util.List;

public class ModPlacedFeatures {


//    public static final ResourceKey<PlacedFeature> PROTOTAXITES_KEY = registerKey("prototaxites_place");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

//        register(context, PROTOTAXITES_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PROTOTAXITES_KEY),
//                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.1f, 2),
//                        ModBlocks.PROTOTAXITES.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

}
