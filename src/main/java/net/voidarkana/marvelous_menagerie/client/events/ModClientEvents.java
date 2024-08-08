package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.common.enchantment.ModEnchantmentsClass;
import net.voidarkana.marvelous_menagerie.common.item.custom.AnomalousGogglesItem;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void getFogDensity(ViewportEvent.RenderFog event){
        Camera camera = event.getCamera();
        Level level = Minecraft.getInstance().level;
        BlockPos blockPos = camera.getBlockPosition();
        FluidState fluidState = level.getFluidState(blockPos);

        if (camera.getPosition().y() >= blockPos.getY() + fluidState.getHeight(level, blockPos))
            return;

        Fluid fluid = fluidState.getType();
        Entity player = camera.getEntity();

        if (player.isSpectator())
            return;

        ItemStack helmet = AnomalousGogglesItem.getWornItem(player);
        if (!helmet.isEmpty() && helmet.getItem() instanceof AnomalousGogglesItem){
            if ((fluid == Fluids.LAVA || fluid == Fluids.FLOWING_LAVA) && helmet.getEnchantmentLevel(ModEnchantmentsClass.INFERNAL_VISION.get())>0){
                event.scaleFarPlaneDistance(10f);
                event.setCanceled(true);
                return;
            }else if ((fluid == Fluids.WATER || fluid == Fluids.FLOWING_WATER)){
                event.scaleFarPlaneDistance(10f);
                event.setCanceled(true);
                return;
            }
        }
    }

}
