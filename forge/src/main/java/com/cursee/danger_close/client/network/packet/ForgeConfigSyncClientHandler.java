package com.cursee.danger_close.client.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.packet.ForgeConfigSyncS2CPacket;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class ForgeConfigSyncClientHandler {

    public static void handle(ForgeConfigSyncS2CPacket packet, CustomPayloadEvent.Context context) {
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
