package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModTags {

    public static class EntityTypes {
        public static final TagKey<EntityType<?>> THYLA_ALERT_TARGET = tag("thyla_alert_target");
        public static final TagKey<EntityType<?>> THYLA_EMBRYO_ATTACH_TO = tag("thyla_embryo_attach_to");
        public static final TagKey<EntityType<?>> STELLER_EMBRYO_ATTACH_TO = tag("steller_embryo_attach_to");
        public static final TagKey<EntityType<?>> OPHTHALMO_EMBRYO_ATTACH_TO = tag("ophthalmo_embryo_attach_to");
        public static final TagKey<EntityType<?>> JOSEPHO_EMBRYO_ATTACH_TO = tag("josepho_embryo_attach_to");

        private static TagKey<EntityType<?>> tag(String name){
            return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
        }
    }

    public static class Biomes {

        public static final TagKey<Biome> DODO_BIOMES = create("is_dodo_biome");
        public static final TagKey<Biome> ELEPHANT_BIRD_BIOMES = create("is_elephant_bird_biome");
        public static final TagKey<Biome> THYLACINE_BIOMES = create("is_thylacine_biome");
        public static final TagKey<Biome> STELLER_BIOMES = create("is_steller_biome");

        public static final TagKey<Biome> JOSEPHO_BIOMES = create("is_josepho_biome");
        public static final TagKey<Biome> TRILO_BIOMES = create("is_trilo_biome");
        public static final TagKey<Biome> CARIS_BIOMES = create("is_caris_biome");
        public static final TagKey<Biome> SACA_BIOMES = create("is_saca_biome");
        public static final TagKey<Biome> OPHTHALMO_BIOMES = create("is_ophthalmo_biome");

        public static final TagKey<Biome> PIKAIA_BIOMES = create("is_pikaia_biome");

        public static final TagKey<Biome> ARANDASPIS_BIOMES = create("is_arandaspis_biome");

        private static TagKey<Biome> create(String pName) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(MarvelousMenagerie.MOD_ID, pName));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> SIGILLARIA_LOG_BLOCK = tag("sigillaria_log_block");
        public static final TagKey<Block> ZULOAGAE_LOG_BLOCK = tag("zuloagae_log_block");

        public static final TagKey<Block> DYE_DEPOT_WOOL = tag("dye_depot_wool");

        public static final TagKey<Block> DYE_DEPOT_MAROON_WOOL = tag("dye_depot_maroon_wool");
        public static final TagKey<Block> DYE_DEPOT_ROSE_WOOL = tag("dye_depot_rose_wool");
        public static final TagKey<Block> DYE_DEPOT_CORAL_WOOL = tag("dye_depot_coral_wool");
        public static final TagKey<Block> DYE_DEPOT_INDIGO_WOOL = tag("dye_depot_indigo_wool");
        public static final TagKey<Block> DYE_DEPOT_NAVY_WOOL = tag("dye_depot_navy_wool");
        public static final TagKey<Block> DYE_DEPOT_SLATE_WOOL = tag("dye_depot_slate_wool");
        public static final TagKey<Block> DYE_DEPOT_OLIVE_WOOL = tag("dye_depot_olive_wool");
        public static final TagKey<Block> DYE_DEPOT_AMBER_WOOL = tag("dye_depot_amber_wool");
        public static final TagKey<Block> DYE_DEPOT_BEIGE_WOOL = tag("dye_depot_beige_wool");
        public static final TagKey<Block> DYE_DEPOT_TEAL_WOOL = tag("dye_depot_teal_wool");
        public static final TagKey<Block> DYE_DEPOT_MINT_WOOL = tag("dye_depot_mint_wool");
        public static final TagKey<Block> DYE_DEPOT_AQUA_WOOL = tag("dye_depot_aqua_wool");
        public static final TagKey<Block> DYE_DEPOT_VERDANT_WOOL = tag("dye_depot_verdant_wool");
        public static final TagKey<Block> DYE_DEPOT_FOREST_WOOL = tag("dye_depot_forest_wool");
        public static final TagKey<Block> DYE_DEPOT_GINGER_WOOL = tag("dye_depot_ginger_wool");
        public static final TagKey<Block> DYE_DEPOT_TAN_WOOL = tag("dye_depot_tan_wool");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> SIGILLARIA_LOG_ITEM = tag("sigillaria_log_item");
        public static final TagKey<Item> ZULOAGAE_LOG_ITEM = tag("zuloagae_log_item");

        public static final TagKey<Item> DYE_DEPOT_WOOL_ITEM = tag("dye_depot_wool_item");

        public static final TagKey<Item> DYE_DEPOT_MAROON_WOOL_ITEM = tag("dye_depot_maroon_wool_item");
        public static final TagKey<Item> DYE_DEPOT_ROSE_WOOL_ITEM = tag("dye_depot_rose_wool_item");
        public static final TagKey<Item> DYE_DEPOT_CORAL_WOOL_ITEM = tag("dye_depot_coral_wool_item");
        public static final TagKey<Item> DYE_DEPOT_INDIGO_WOOL_ITEM = tag("dye_depot_indigo_wool_item");
        public static final TagKey<Item> DYE_DEPOT_NAVY_WOOL_ITEM = tag("dye_depot_navy_wool_item");
        public static final TagKey<Item> DYE_DEPOT_SLATE_WOOL_ITEM = tag("dye_depot_slate_wool_item");
        public static final TagKey<Item> DYE_DEPOT_OLIVE_WOOL_ITEM = tag("dye_depot_olive_wool_item");
        public static final TagKey<Item> DYE_DEPOT_AMBER_WOOL_ITEM = tag("dye_depot_amber_wool_item");
        public static final TagKey<Item> DYE_DEPOT_BEIGE_WOOL_ITEM = tag("dye_depot_beige_wool_item");
        public static final TagKey<Item> DYE_DEPOT_TEAL_WOOL_ITEM = tag("dye_depot_teal_wool_item");
        public static final TagKey<Item> DYE_DEPOT_MINT_WOOL_ITEM = tag("dye_depot_mint_wool_item");
        public static final TagKey<Item> DYE_DEPOT_AQUA_WOOL_ITEM = tag("dye_depot_aqua_wool_item");
        public static final TagKey<Item> DYE_DEPOT_VERDANT_WOOL_ITEM = tag("dye_depot_verdant_wool_item");
        public static final TagKey<Item> DYE_DEPOT_FOREST_WOOL_ITEM = tag("dye_depot_forest_wool_item");
        public static final TagKey<Item> DYE_DEPOT_GINGER_WOOL_ITEM = tag("dye_depot_ginger_wool_item");
        public static final TagKey<Item> DYE_DEPOT_TAN_WOOL_ITEM = tag("dye_depot_tan_wool_item");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
        }
    }

}
