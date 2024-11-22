package net.voidarkana.marvelous_menagerie.util.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.voidarkana.marvelous_menagerie.MarvelousMenagerie;

public class ModNetworkHandler {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MarvelousMenagerie.MOD_ID, "channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    @SuppressWarnings("UnusedAssignment")
    public static void init() {
        int id = 0;
        CHANNEL.registerMessage(id++, OpenElephantBirdScreenPacket.class, OpenElephantBirdScreenPacket::encode, OpenElephantBirdScreenPacket::decode, OpenElephantBirdScreenPacket.Handler::handle);
    }

}
