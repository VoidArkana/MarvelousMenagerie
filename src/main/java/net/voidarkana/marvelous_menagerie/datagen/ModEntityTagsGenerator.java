package net.voidarkana.marvelous_menagerie.datagen;

import com.peeko32213.unusualprehistory.core.registry.UPEntities;
import com.peeko32213.unusualprehistory.core.registry.UPTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEntityTagsGenerator extends EntityTypeTagsProvider {

    public ModEntityTagsGenerator(PackOutput p_256095_, CompletableFuture<HolderLookup.Provider> p_256572_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_256095_, p_256572_, MarvelousMenagerie.MOD_ID, existingFileHelper);
    }

    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(ModTags.EntityTypes.THYLA_ALERT_TARGET)
                .addTag(EntityTypeTags.SKELETONS).addTag(EntityTypeTags.RAIDERS).addTag(EntityTypeTags.AXOLOTL_ALWAYS_HOSTILES)
                .add(EntityType.ZOMBIE).add(EntityType.ZOMBIE_VILLAGER).add(EntityType.ZOMBIFIED_PIGLIN).add(EntityType.PIGLIN)
                .add(EntityType.PIGLIN_BRUTE).add(EntityType.HOGLIN).add(EntityType.CREEPER).add(EntityType.SPIDER).add(EntityType.CAVE_SPIDER)
                .add(EntityType.WOLF).add(EntityType.POLAR_BEAR).add(EntityType.SHULKER).add(EntityType.SILVERFISH).add(EntityType.PIGLIN)
                .add(EntityType.PHANTOM).add(EntityType.SLIME).add(EntityType.MAGMA_CUBE).add(EntityType.GHAST).add(EntityType.ENDERMITE)
                .add(EntityType.ENDERMAN).add(EntityType.WITHER).add(EntityType.ENDER_DRAGON).add(EntityType.HUSK)
                .add(UPEntities.REX.get()).add(UPEntities.SMILODON.get()).add(UPEntities.HWACHA.get()).add(UPEntities.ULUG.get())
                .add(UPEntities.AUSTRO.get()).add(UPEntities.VELOCI.get()).add(UPEntities.REX.get()).add(UPEntities.PARACERATHERIUM.get())
                .add(UPEntities.MEGALANIA.get()).add(UPEntities.SLUDGE.get()).add(UPEntities.BARINASUCHUS.get());

        this.tag(ModTags.EntityTypes.THYLA_EMBRYO_ATTACH_TO).add(ModEntities.THYLACINE.get()).addOptional(new ResourceLocation("alexsmobs:tasmanian_devil"));
        this.tag(ModTags.EntityTypes.STELLER_EMBRYO_ATTACH_TO).add(ModEntities.STELLER_SEA_COW.get());

        this.tag(UPTags.LAND_MOBS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.BEELZE_TARGETS).add(ModEntities.DODO.get());
        this.tag(UPTags.DUNK_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.BABY_STELLER_SEA_COW.get()).add(ModEntities.STELLER_SEA_COW.get()).add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.ENCRUSTED_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.BABY_STELLER_SEA_COW.get()).add(ModEntities.STELLER_SEA_COW.get()).add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.MAJUNGA_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get());
        this.tag(UPTags.RAPTOR_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.BABY_STELLER_SEA_COW.get());
        this.tag(UPTags.REX_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.BABY_STELLER_SEA_COW.get()).add(ModEntities.STELLER_SEA_COW.get()).add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.MEGALANIA_TARGETS).add(ModEntities.DODO.get()).add(ModEntities.THYLACINE.get()).add(ModEntities.BABY_STELLER_SEA_COW.get()).add(ModEntities.STELLER_SEA_COW.get()).add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.HERBIVORES)
                .add(ModEntities.DODO.get())
                .add(ModEntities.BABY_STELLER_SEA_COW.get())
                .add(ModEntities.STELLER_SEA_COW.get())
                .add(ModEntities.ELEPHANT_BIRD.get());
        this.tag(UPTags.CARNIVORES).add(ModEntities.THYLACINE.get());
    }
}
