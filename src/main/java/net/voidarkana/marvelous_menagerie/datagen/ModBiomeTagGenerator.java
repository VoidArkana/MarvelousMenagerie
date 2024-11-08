package net.voidarkana.marvelous_menagerie.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagGenerator extends BiomeTagsProvider {
    public ModBiomeTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, pProvider, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.addTags();
    }

    protected void addTags() {
        tag(ModTags.Biomes.DODO_BIOMES).addTags(BiomeTags.IS_JUNGLE).add(Biomes.MUSHROOM_FIELDS);
        tag(ModTags.Biomes.ELEPHANT_BIRD_BIOMES).addTags(BiomeTags.IS_JUNGLE);
        tag(ModTags.Biomes.THYLACINE_BIOMES).addTags(BiomeTags.IS_SAVANNA).add(Biomes.PLAINS).add(Biomes.SUNFLOWER_PLAINS);
        tag(ModTags.Biomes.STELLER_BIOMES).add(Biomes.COLD_OCEAN).add(Biomes.DEEP_COLD_OCEAN)
                .add(Biomes.FROZEN_OCEAN).add(Biomes.DEEP_FROZEN_OCEAN);

        tag(ModTags.Biomes.CARIS_BIOMES).add(Biomes.WARM_OCEAN);
        tag(ModTags.Biomes.TRILO_BIOMES).add(Biomes.WARM_OCEAN);
        tag(ModTags.Biomes.OPHTHALMO_BIOMES).add(Biomes.WARM_OCEAN).add(Biomes.LUKEWARM_OCEAN).add(Biomes.DEEP_LUKEWARM_OCEAN);
        tag(ModTags.Biomes.SACA_BIOMES).addTags(BiomeTags.IS_OCEAN);
        tag(ModTags.Biomes.JOSEPHO_BIOMES).addTags(BiomeTags.IS_JUNGLE).addTags(Tags.Biomes.IS_SWAMP);

        tag(ModTags.Biomes.PIKAIA_BIOMES).add(Biomes.WARM_OCEAN);

        tag(ModTags.Biomes.ARANDASPIS_BIOMES).add(Biomes.WARM_OCEAN);
    }
}
