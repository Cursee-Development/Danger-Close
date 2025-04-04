package com.cursee.danger_close;

import com.cursee.danger_close.core.NeoForgeCommonConfigHandler;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.EntityTickEvent;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class DangerCloseNeoForge {

    public static IEventBus EVENT_BUS = null;

    public DangerCloseNeoForge(IEventBus modEventBus) {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        DangerCloseNeoForge.EVENT_BUS = modEventBus;
        NeoForgeCommonConfigHandler.onLoad();
        // NeoForge.EVENT_BUS.addListener((Consumer<TickEvent.ServerTickEvent>)event -> onServerTick(event));
        NeoForge.EVENT_BUS.addListener((Consumer<EntityTickEvent.Pre>) event -> onLivingTick(event));
    }

//    public static void onServerTick(TickEvent.ServerTickEvent event) {
//        if (event.phase != TickEvent.Phase.START) return;
//        MinecraftServer server = event.getServer();
//        if (server.getTickCount() % 2 != 0) return;
//        server.getAllLevels().forEach(level -> level.getAllEntities().forEach(entity -> {
//            // operate here
//        }));
//    }

    public static void onLivingTick(EntityTickEvent.Pre event) {
        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity entity = (LivingEntity) event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server == null || server.getTickCount() % 2 != 0) return;
        // operate here
        DangerClose.detect(entity);
    }
}