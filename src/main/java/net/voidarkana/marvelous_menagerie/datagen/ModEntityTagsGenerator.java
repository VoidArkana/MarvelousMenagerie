package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.core.registry.UPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsGenerator extends EntityTypeTagsProvider {

    public ModEntityTagsGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(UPTags.LAND_MOBS).add(ModEntities.DODO.get());
        this.tag(UPTags.BEELZE_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.DUNK_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.ENCRUSTED_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.SMILODON_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.MAJUNGA_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.RAPTOR_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.REX_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.MEGALANIA_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.HERBIVORES).add(ModEntities.DODO.get());
    }
}
