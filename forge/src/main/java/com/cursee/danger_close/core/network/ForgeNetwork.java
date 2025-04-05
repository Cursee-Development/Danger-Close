package com.cursee.danger_close.core.network;

import com.cursee.danger_close.Constants;
import com.cursee.danger_close.DangerClose;
import com.cursee.danger_close.core.network.packet.ForgeConfigSyncS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;

public class ForgeNetwork {

    private static SimpleChannel INSTANCE;
    private static int packetID = 0;
    private static int id() {
        return packetID++;
    }

    public static void register() {
        SimpleChannel net = ChannelBuilder
                .named(DangerClose.identifier(Constants.MOD_ID))
                .networkProtocolVersion(1)
                .clientAcceptedVersions(Channel.VersionTest.exact(1))
                .serverAcceptedVersions(Channel.VersionTest.exact(1))
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(ForgeConfigSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ForgeConfigSyncS2CPacket::new)
                .encoder(ForgeConfigSyncS2CPacket::write)
                .consumerMainThread(ForgeConfigSyncS2CPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(message, player.connection.getConnection());
    }
}
