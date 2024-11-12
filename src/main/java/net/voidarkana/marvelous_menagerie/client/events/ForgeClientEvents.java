package net.voidarkana.marvelous_menagerie.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;
import net.voidarkana.marvelous_menagerie.client.ClientProxy;
import net.voidarkana.marvelous_menagerie.client.events.custom.ModelRotationEvent;
import net.voidarkana.marvelous_menagerie.client.events.custom.PlayerPoseEvent;
import net.voidarkana.marvelous_menagerie.client.renderers.util.ICustomPlayerRidePose;
import net.voidarkana.marvelous_menagerie.common.effect.ModEffects;
import net.voidarkana.marvelous_menagerie.util.RenderUtil;

@Mod.EventBusSubscriber(modid = MarvelousMenagerie.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents {


    @SubscribeEvent
    public static void preRenderLiving(RenderLivingEvent.Pre event) {
        if (ClientProxy.blockedEntityRenders.contains(event.getEntity().getUUID())) {
            if (!MarvelousMenagerie.PROXY.isFirstPersonPlayer(event.getEntity())){
                MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Post(event.getEntity(), event.getRenderer(), event.getPartialTick(), event.getPoseStack(), event.getMultiBufferSource(), event.getPackedLight()));
                event.setCanceled(true);
            }
            ClientProxy.blockedEntityRenders.remove(event.getEntity().getUUID());
        }
    }


    @SubscribeEvent
    public static <T extends LivingEntity> void onModelRotation(ModelRotationEvent<T> pEvent) {
        T entity = pEvent.getEntity();
        if (entity instanceof Player) {
            pEvent.setCanceled(RenderUtil.getEntityRenderer(entity.getVehicle()) instanceof ICustomPlayerRidePose);
        }
    }

    @SubscribeEvent
    public static <T extends LivingEntity> void onPlayerPose(PlayerPoseEvent<T> pEvent) {
        T entity = pEvent.getEntity();
        if (entity instanceof Player) {
            if (RenderUtil.getEntityRenderer(entity.getVehicle()) instanceof ICustomPlayerRidePose customRidePos) {
                customRidePos.applyRiderPose(pEvent.getHumanoidModel(), entity);
            }
        }
    }

}
