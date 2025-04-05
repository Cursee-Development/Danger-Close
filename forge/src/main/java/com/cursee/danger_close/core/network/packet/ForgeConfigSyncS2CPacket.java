package com.cursee.danger_close.core.network.packet;

import com.cursee.danger_close.DangerClose;
import com.cursee.danger_close.client.network.packet.ForgeConfigSyncClientHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.fml.DistExecutor;

public class ForgeConfigSyncS2CPacket implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ForgeConfigSyncS2CPacket> TYPE = new CustomPacketPayload.Type<>(DangerClose.identifier("config_sync"));

    public final boolean shouldDetect;
    public final boolean shouldTorchImmolate;
    public final boolean shouldSoulTorchImmolate;
    public final boolean shouldCampfireImmolate;
    public final boolean shouldSoulCampfireImmolate;
    public final boolean shouldStonecutterCut;
    public final boolean shouldBlazeImmolate;
    public final boolean shouldMagmaBlockImmolate;
    public final boolean shouldMagmaCubeImmolate;

    public ForgeConfigSyncS2CPacket(boolean shouldDetect, boolean shouldTorchImmolate, boolean shouldSoulTorchImmolate, boolean shouldCampfireImmolate, boolean shouldSoulCampfireImmolate, boolean shouldStonecutterCut, boolean shouldBlazeImmolate, boolean shouldMagmaBlockImmolate, boolean shouldMagmaCubeImmolate) {
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

    public ForgeConfigSyncS2CPacket(RegistryFriendlyByteBuf data) {
        this.shouldDetect = data.readBoolean();
        this.shouldTorchImmolate = data.readBoolean();
        this.shouldSoulTorchImmolate = data.readBoolean();
        this.shouldCampfireImmolate = data.readBoolean();
        this.shouldSoulCampfireImmolate = data.readBoolean();
        this.shouldStonecutterCut = data.readBoolean();
        this.shouldBlazeImmolate = data.readBoolean();
        this.shouldMagmaBlockImmolate = data.readBoolean();
        this.shouldMagmaCubeImmolate = data.readBoolean();
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

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ForgeConfigSyncClientHandler.handle(this, context));
        });
        context.setPacketHandled(true);
    }
}
