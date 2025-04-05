package com.cursee.danger_close;

import com.cursee.danger_close.core.CommonConfigValues;
import com.cursee.danger_close.core.ForgeCommonConfigHandler;
import com.cursee.danger_close.core.network.ForgeNetwork;
import com.cursee.danger_close.core.network.packet.ForgeConfigSyncS2CPacket;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class DangerCloseForge {

    public static IEventBus EVENT_BUS = null;
    
    public DangerCloseForge(FMLJavaModLoadingContext context) {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        ForgeCommonConfigHandler.onLoad();
        DangerCloseForge.EVENT_BUS = context.getModEventBus();
        // MinecraftForge.EVENT_BUS.addListener((Consumer<TickEvent.ServerTickEvent>)event -> onServerTick(event));
        MinecraftForge.EVENT_BUS.addListener((Consumer<LivingEvent.LivingTickEvent>) event -> onLivingTick(event));

        MinecraftForge.EVENT_BUS.addListener((Consumer<EntityJoinLevelEvent>) event -> {
            if (!(event.getEntity() instanceof ServerPlayer player)) return;
            ForgeNetwork.sendToPlayer(new ForgeConfigSyncS2CPacket(
                    CommonConfigValues.shouldDetect, CommonConfigValues.shouldTorchImmolate, CommonConfigValues.shouldSoulTorchImmolate,
                    CommonConfigValues.shouldCampfireImmolate, CommonConfigValues.shouldSoulCampfireImmolate, CommonConfigValues.shouldStonecutterCut,
                    CommonConfigValues.shouldBlazeImmolate, CommonConfigValues.shouldMagmaBlockImmolate, CommonConfigValues.shouldMagmaCubeImmolate), player);
        });
    }

//    public static void onServerTick(TickEvent.ServerTickEvent event) {
//        if (event.phase != TickEvent.Phase.START) return;
//        MinecraftServer server = event.getServer();
//        if (server.getTickCount() % 2 != 0) return;
//        server.getAllLevels().forEach(level -> level.getAllEntities().forEach(entity -> {
//            // operate here
//        }));
//    }

    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        MinecraftServer server = entity.getServer();
        if (server == null || server.getTickCount() % 2 != 0) return;
        // operate here
        DangerClose.detect(entity);
    }
}