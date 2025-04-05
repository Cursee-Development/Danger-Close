package com.cursee.danger_close.core.network;

import com.cursee.danger_close.Constants;
import com.cursee.danger_close.DangerClose;
import com.cursee.danger_close.core.network.packet.ForgeConfigSyncS2CPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ForgeNetwork {

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            DangerClose.identifier(Constants.MOD_ID),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        ForgeNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    private static int packetID = 0;
    private static int createNewPacketID() {
        return packetID = packetID + 1;
    }

    public static void registerS2CPackets() {
        ForgeNetwork.INSTANCE.registerMessage(createNewPacketID(), ForgeConfigSyncS2CPacket.class, ForgeConfigSyncS2CPacket::encode, ForgeConfigSyncS2CPacket::decode, ForgeConfigSyncS2CPacket::handle);
    }
}
