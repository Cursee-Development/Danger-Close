package com.cursee.danger_close;

import com.cursee.danger_close.client.network.packet.FabricConfigSyncClientHandler;
import com.cursee.danger_close.core.network.FabricNetwork;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class DangerCloseClientFabric implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        DangerCloseClient.init();

        ClientPlayNetworking.registerGlobalReceiver(FabricNetwork.Packets.CONFIG_SYNC_S2C, FabricConfigSyncClientHandler::registerS2CPacketHandler);
    }
}
