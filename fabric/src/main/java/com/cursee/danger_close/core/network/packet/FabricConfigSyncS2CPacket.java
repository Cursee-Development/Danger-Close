package com.cursee.danger_close.core.network.packet;

import com.cursee.danger_close.DangerClose;
import com.cursee.danger_close.client.network.packet.FabricConfigSyncClientHandler;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class FabricConfigSyncS2CPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FabricConfigSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(DangerClose.identifier("config_sync"));

    public final boolean shouldDetect;
    public final boolean shouldTorchImmolate;
    public final boolean shouldSoulTorchImmolate;
    public final boolean shouldCampfireImmolate;
    public final boolean shouldSoulCampfireImmolate;
    public final boolean shouldStonecutterCut;
    public final boolean shouldBlazeImmolate;
    public final boolean shouldMagmaBlockImmolate;
    public final boolean shouldMagmaCubeImmolate;

    public FabricConfigSyncS2CPacket(boolean shouldDetect, boolean shouldTorchImmolate, boolean shouldSoulTorchImmolate, boolean shouldCampfireImmolate, boolean shouldSoulCampfireImmolate, boolean shouldStonecutterCut, boolean shouldBlazeImmolate, boolean shouldMagmaBlockImmolate, boolean shouldMagmaCubeImmolate) {
        this.shouldDetect = shouldDetect;
        this.shouldTorchImmolate = shouldTorchImmolate;
        this.shouldSoulTorchImmolate = shouldSoulTorchImmolate;
        this.shouldCampfireImmolate = shouldCampfireImmolate;
        this.shouldSoulCampfireImmolate = shouldSoulCampfireImmolate;
        this.shouldStonecutterCut = shouldStonecutterCut;
        this.shouldBlazeImmolate = shouldBlazeImmolate;
        this.shouldMagmaBlockImmolate = shouldMagmaBlockImmolate;
        this.shouldMagmaCubeImmolate = shouldMagmaCubeImmolate;
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void write(RegistryFriendlyByteBuf data) {
        data.writeBoolean(shouldDetect);
        data.writeBoolean(shouldTorchImmolate);
        data.writeBoolean(shouldSoulTorchImmolate);
        data.writeBoolean(shouldCampfireImmolate);
        data.writeBoolean(shouldSoulCampfireImmolate);
        data.writeBoolean(shouldStonecutterCut);
        data.writeBoolean(shouldBlazeImmolate);
        data.writeBoolean(shouldMagmaBlockImmolate);
        data.writeBoolean(shouldMagmaCubeImmolate);
    }

    public static FabricConfigSyncS2CPacket read(RegistryFriendlyByteBuf data) {
        return new FabricConfigSyncS2CPacket(data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean());
    }

    public void handle(ClientPlayNetworking.Context context) {
        FabricConfigSyncClientHandler.handle(this, context);
    }
}
