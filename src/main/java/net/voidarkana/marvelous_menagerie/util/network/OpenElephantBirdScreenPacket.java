package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.voidarkana.marvelous_menagerie.client.screen.ElephantBirdMenu;
import net.voidarkana.marvelous_menagerie.client.screen.ElephantBirdScreen;
import net.voidarkana.marvelous_menagerie.common.entity.custom.ElephantBirdEntity;

import java.util.function.Supplier;

public record OpenElephantBirdScreenPacket(int containerId, int entityId) {


    public static OpenElephantBirdScreenPacket decode(FriendlyByteBuf buf) {
        return new OpenElephantBirdScreenPacket(buf.readInt(), buf.readInt());
    }

    public static void encode(OpenElephantBirdScreenPacket packet, FriendlyByteBuf buf) {
        buf.writeInt(packet.containerId());
        buf.writeInt(packet.entityId());
    }

    public static class Handler {

        @SuppressWarnings("Convert2Lambda")
        public static void handle(OpenElephantBirdScreenPacket packet, Supplier<NetworkEvent.Context> context) {
            context.get().enqueueWork(new Runnable() {
                @Override
                public void run() {
                    Entity entity = Minecraft.getInstance().level.getEntity(packet.entityId());
                    if (entity instanceof ElephantBirdEntity bird) {
                        LocalPlayer localplayer = Minecraft.getInstance().player;
                        SimpleContainer container = new SimpleContainer(24);
                        ElephantBirdMenu menu = new ElephantBirdMenu(packet.containerId(), localplayer.getInventory(), container, bird);
                        localplayer.containerMenu = menu;
                        Minecraft.getInstance().setScreen(new ElephantBirdScreen(menu, localplayer.getInventory(), bird));
                    }
                }
            });
            context.get().setPacketHandled(true);
        }
    }

}
