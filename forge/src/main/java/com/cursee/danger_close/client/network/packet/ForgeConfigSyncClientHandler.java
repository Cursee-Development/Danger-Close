package com.cursee.danger_close.client.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.packet.ForgeConfigSyncS2CPacket;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeConfigSyncClientHandler {

    public static void registerS2CPacketHandler(ForgeConfigSyncS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() -> {
            CommonConfigValues.shouldDetect = packet.shouldDetect;
            CommonConfigValues.shouldTorchImmolate = packet.shouldTorchImmolate;
            CommonConfigValues.shouldSoulTorchImmolate = packet.shouldSoulTorchImmolate;
            CommonConfigValues.shouldCampfireImmolate = packet.shouldCampfireImmolate;
            CommonConfigValues.shouldSoulCampfireImmolate = packet.shouldSoulCampfireImmolate;
            CommonConfigValues.shouldStonecutterCut = packet.shouldStonecutterCut;
            CommonConfigValues.shouldBlazeImmolate = packet.shouldBlazeImmolate;
            CommonConfigValues.shouldMagmaBlockImmolate = packet.shouldMagmaBlockImmolate;
            CommonConfigValues.shouldMagmaCubeImmolate = packet.shouldMagmaCubeImmolate;
        });
    }
}
