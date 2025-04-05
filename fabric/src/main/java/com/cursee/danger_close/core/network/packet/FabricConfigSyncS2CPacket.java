package com.cursee.danger_close.core.network.packet;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.network.FabricNetwork;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class FabricConfigSyncS2CPacket {

    public static void registerS2CPacketSender(Entity entity, Level level) {
        if (!(entity instanceof ServerPlayer player)) return;
        FriendlyByteBuf data = new FriendlyByteBuf(Unpooled.buffer());
        data.writeBoolean(CommonConfigValues.shouldDetect);
        data.writeBoolean(CommonConfigValues.shouldTorchImmolate);
        data.writeBoolean(CommonConfigValues.shouldSoulTorchImmolate);
        data.writeBoolean(CommonConfigValues.shouldCampfireImmolate);
        data.writeBoolean(CommonConfigValues.shouldSoulCampfireImmolate);
        data.writeBoolean(CommonConfigValues.shouldStonecutterCut);
        data.writeBoolean(CommonConfigValues.shouldBlazeImmolate);
        data.writeBoolean(CommonConfigValues.shouldMagmaBlockImmolate);
        data.writeBoolean(CommonConfigValues.shouldMagmaCubeImmolate);
        FabricNetwork.sendToPlayer(data, player, FabricNetwork.Packets.CONFIG_SYNC_S2C);
    }
}
