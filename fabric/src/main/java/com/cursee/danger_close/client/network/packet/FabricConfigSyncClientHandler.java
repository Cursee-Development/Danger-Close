package com.cursee.danger_close.client.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.FabricNetwork;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class FabricConfigSyncClientHandler {

    public static void registerS2CPacketHandler(Minecraft client, ClientPacketListener handler, FriendlyByteBuf data, PacketSender responseSender) {
        CommonConfigValues.shouldDetect = data.readBoolean();
        CommonConfigValues.shouldTorchImmolate = data.readBoolean();
        CommonConfigValues.shouldSoulTorchImmolate = data.readBoolean();
        CommonConfigValues.shouldCampfireImmolate = data.readBoolean();
        CommonConfigValues.shouldSoulCampfireImmolate = data.readBoolean();
        CommonConfigValues.shouldStonecutterCut = data.readBoolean();
        CommonConfigValues.shouldBlazeImmolate = data.readBoolean();
        CommonConfigValues.shouldMagmaBlockImmolate = data.readBoolean();
        CommonConfigValues.shouldMagmaCubeImmolate = data.readBoolean();
    }
}
