package net.voidarkana.marvelous_menagerie.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModTags {

    public static class Blocks {

        public static final TagKey<Block> SIGILLARIA_LOG_BLOCK = tag("sigillaria_log_block");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> SIGILLARIA_LOG_ITEM = tag("sigillaria_log_item");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(MarvelousMenagerie.MOD_ID, name));
        }
    }

}
