package com.cursee.danger_close.core.network;

import com.cursee.danger_close.Constants;
import com.cursee.danger_close.core.network.packet.NeoForgeConfigSyncS2CPacket;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.DirectionalPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeNetwork {

    public static final StreamCodec<RegistryFriendlyByteBuf, NeoForgeConfigSyncS2CPacket> CONFIG_SYNC_CODEC = StreamCodec.ofMember(NeoForgeConfigSyncS2CPacket::write, NeoForgeConfigSyncS2CPacket::read);

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {

        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                NeoForgeConfigSyncS2CPacket.TYPE,
                CONFIG_SYNC_CODEC,
                new DirectionalPayloadHandler<>(
                        NeoForgeConfigSyncS2CPacket::handle,
                        NeoForgeConfigSyncS2CPacket::unusedHandle
                ));
    }
}
