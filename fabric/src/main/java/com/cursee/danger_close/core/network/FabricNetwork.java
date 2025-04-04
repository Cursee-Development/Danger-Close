package com.cursee.danger_close.core.network;

import com.cursee.danger_close.core.network.packet.FabricConfigSyncS2CPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class FabricNetwork {
    public static final StreamCodec<RegistryFriendlyByteBuf, FabricConfigSyncS2CPacket> CONFIG_SYNC_CODEC = StreamCodec.ofMember(FabricConfigSyncS2CPacket::write, FabricConfigSyncS2CPacket::read);
}
