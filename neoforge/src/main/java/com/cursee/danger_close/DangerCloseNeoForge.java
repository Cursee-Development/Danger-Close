package com.cursee.danger_close;

import com.cursee.danger_close.core.Config;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

@Mod(Constants.MOD_ID)
public class DangerCloseNeoForge {

    public DangerCloseNeoForge(IEventBus eventBus) {

        DangerClose.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        Config.initialize();
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
    public static class DangerEventListener {

        @SubscribeEvent
        public static void onServerTick(ServerTickEvent.Pre event) {

            MinecraftServer server = event.getServer();

            if (server.getTickCount() % 2 == 0) {

                server.getAllLevels().forEach(level -> {

                    if (!level.isClientSide) {

                        level.getAllEntities().forEach(entity -> {

                            if (entity instanceof LivingEntity living) {

                                DangerClose.detect(level, living);
                            }
                        });
                    }
                });
            }
        }
    }
}
