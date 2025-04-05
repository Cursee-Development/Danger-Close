package com.cursee.danger_close;

import com.cursee.danger_close.core.network.FabricNetwork;
import com.cursee.danger_close.core.network.packet.FabricConfigSyncS2CPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class DangerCloseClientFabric implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        DangerCloseClient.init();

        ClientPlayNetworking.registerGlobalReceiver(FabricConfigSyncS2CPacket.TYPE, FabricConfigSyncS2CPacket::handle);
    }
}
