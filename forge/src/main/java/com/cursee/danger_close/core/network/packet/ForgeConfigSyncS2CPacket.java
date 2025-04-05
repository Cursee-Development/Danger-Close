package com.cursee.danger_close.core.network.packet;

import com.cursee.danger_close.client.network.packet.ForgeConfigSyncClientHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ForgeConfigSyncS2CPacket {

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

    public void encode(FriendlyByteBuf data) {
        data.writeBoolean(this.shouldDetect);
        data.writeBoolean(this.shouldTorchImmolate);
        data.writeBoolean(this.shouldSoulTorchImmolate);
        data.writeBoolean(this.shouldCampfireImmolate);
        data.writeBoolean(this.shouldSoulCampfireImmolate);
        data.writeBoolean(this.shouldStonecutterCut);
        data.writeBoolean(this.shouldBlazeImmolate);
        data.writeBoolean(this.shouldMagmaBlockImmolate);
        data.writeBoolean(this.shouldMagmaCubeImmolate);
    }

    public static ForgeConfigSyncS2CPacket decode(FriendlyByteBuf data) {
        return new ForgeConfigSyncS2CPacket(data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean(), data.readBoolean());
    }

    public static void handle(ForgeConfigSyncS2CPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        contextSupplier.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ForgeConfigSyncClientHandler.registerS2CPacketHandler(packet, contextSupplier))
        );
        contextSupplier.get().setPacketHandled(true);
    }
}
