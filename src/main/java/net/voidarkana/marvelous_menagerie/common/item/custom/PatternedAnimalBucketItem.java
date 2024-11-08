package net.voidarkana.marvelous_menagerie.common.item.custom;

import com.peeko32213.unusualprehistory.common.item.ItemModFishBucket;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.voidarkana.marvelous_menagerie.common.entity.ModEntities;
import net.voidarkana.marvelous_menagerie.common.entity.custom.TrilobiteEntity;
import net.voidarkana.marvelous_menagerie.common.entity.custom.baby.BabyOphthalmoEntity;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class PatternedAnimalBucketItem extends ItemModFishBucket {
    public PatternedAnimalBucketItem(Supplier<? extends EntityType<?>> entityType, Supplier<? extends Fluid> fluid, Item item, boolean hasTooltip, Properties builder) {
        super(entityType, fluid, item, hasTooltip, builder);
    }

    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (getFishType() == ModEntities.TRILOBITE.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("model_variant", 3)) {
                int i = compoundtag.getInt("model_variant");
                int j = compoundtag.getInt("base_color_variant");
                int k = compoundtag.getInt("second_color_variant");

                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                String model = "translatable.marvelous_menagerie.trilo_model." + TrilobiteEntity.getModelName(i);
                String base = "translatable.marvelous_menagerie.trilo_base_color." + TrilobiteEntity.getMainColorName(j);
                String second = "translatable.marvelous_menagerie.trilo_second_color." + TrilobiteEntity.getSecondColorName(k);

                pTooltipComponents.add(Component.translatable(model).withStyle(achatformatting));

                MutableComponent mutablecomponent = Component.translatable(base);

                mutablecomponent.withStyle(achatformatting);
                pTooltipComponents.add(mutablecomponent);

                MutableComponent mutablecomponent2 = Component.translatable(second);

                mutablecomponent2.withStyle(achatformatting);
                pTooltipComponents.add(mutablecomponent2);
            }
        }

        if (getFishType() == ModEntities.BABY_OPHTHALMO.get()) {
            CompoundTag compoundtag = pStack.getTag();
            if (compoundtag != null && compoundtag.contains("BaseColor", 3)) {
                int i = compoundtag.getInt("BaseColor");
                int j = compoundtag.getInt("Pattern");

                ChatFormatting[] achatformatting = new ChatFormatting[]{ChatFormatting.ITALIC, ChatFormatting.GRAY};

                String model = "translatable.marvelous_menagerie.ophthalmo.base" + BabyOphthalmoEntity.getColorName(i);

                pTooltipComponents.add(Component.translatable(model).withStyle(achatformatting));

                if (j!=0){
                    String base = "translatable.marvelous_menagerie.ophthalmo.pattern" + BabyOphthalmoEntity.getPatternName(j);
                    MutableComponent mutablecomponent = Component.translatable(base);
                    mutablecomponent.withStyle(achatformatting);
                    pTooltipComponents.add(mutablecomponent);
                }
            }
        }


    }
}
