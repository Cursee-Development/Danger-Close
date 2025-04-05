package com.cursee.danger_close;

import com.cursee.danger_close.core.FabricCommonConfigHandler;
import com.cursee.danger_close.core.event.LivingEntityTickEvent;
import com.cursee.danger_close.core.network.FabricNetwork;
import com.cursee.danger_close.core.network.packet.FabricConfigSyncS2CPacket;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;

public class DangerCloseFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        FabricCommonConfigHandler.onLoad();
        // ServerTickEvents.START_SERVER_TICK.register(server -> onServerTick(server));
        LivingEntityTickEvent.ON_TICK.register(entity -> onLivingTick(entity));

        FabricNetwork.Packets.registerPacketIDs();
        ServerEntityEvents.ENTITY_LOAD.register(FabricConfigSyncS2CPacket::registerS2CPacketSender);
    }

//    public static void onServerTick(MinecraftServer server) {
//        if (server.getTickCount() % 2 != 0) return;
//        server.getAllLevels().forEach(level -> level.getAllEntities().forEach(entity -> {
//            // operate here
//        }));
//    }

    public static void onLivingTick(LivingEntity entity) {
        MinecraftServer server = entity.getServer();
        if (server == null || server.getTickCount() % 2 != 0) return;
        // operate here
        DangerClose.detect(entity);
    }
}
