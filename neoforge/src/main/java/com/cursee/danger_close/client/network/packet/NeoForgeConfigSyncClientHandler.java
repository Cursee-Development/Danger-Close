package com.cursee.danger_close.client.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.packet.NeoForgeConfigSyncS2CPacket;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class NeoForgeConfigSyncClientHandler {

    public static void handle(NeoForgeConfigSyncS2CPacket packet, IPayloadContext context) {
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
