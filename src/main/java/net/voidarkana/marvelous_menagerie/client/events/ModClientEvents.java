package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.ClientProxy;
import net.voidarkana.marvelous_menagerie.enchantment.ModEnchantmentsClass;
import net.voidarkana.marvelous_menagerie.item.custom.AnomalousGogglesItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @SubscribeEvent
    public static void preRenderLiving(RenderLivingEvent.Pre<Player, HumanoidModel<Player>> event) {
        if (ClientProxy.blockedEntityRenders.contains(event.getEntity().getUUID())) {
            MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post<>(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
            event.setCanceled(true);
            ClientProxy.blockedEntityRenders.remove(event.getEntity().getUUID());
        }
    }

    public boolean isFirstPersonPlayer(LivingEntity entity) {
        return entity.equals(Minecraft.getInstance().cameraEntity) && Minecraft.getInstance().options.getCameraType().isFirstPerson();
    }

}
