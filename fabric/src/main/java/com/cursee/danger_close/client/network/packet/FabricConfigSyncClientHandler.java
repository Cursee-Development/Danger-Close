package com.cursee.danger_close.client.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.packet.FabricConfigSyncS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class FabricConfigSyncClientHandler {

    public static void handle(FabricConfigSyncS2CPacket packet, ClientPlayNetworking.Context context) {
        CommonConfigValues.shouldDetect = packet.shouldDetect;
        CommonConfigValues.shouldTorchImmolate = packet.shouldTorchImmolate;
        CommonConfigValues.shouldSoulTorchImmolate = packet.shouldSoulTorchImmolate;
        CommonConfigValues.shouldCampfireImmolate = packet.shouldCampfireImmolate;
        CommonConfigValues.shouldSoulCampfireImmolate = packet.shouldSoulCampfireImmolate;
        CommonConfigValues.shouldStonecutterCut = packet.shouldStonecutterCut;
        CommonConfigValues.shouldBlazeImmolate = packet.shouldBlazeImmolate;
        CommonConfigValues.shouldMagmaBlockImmolate = packet.shouldMagmaBlockImmolate;
        CommonConfigValues.shouldMagmaCubeImmolate = packet.shouldMagmaCubeImmolate;
    }
}
