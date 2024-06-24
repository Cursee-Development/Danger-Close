package com.cursee.danger_close;

import com.cursee.danger_close.core.Config;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class DangerCloseForge {
    
    public DangerCloseForge() {
    
        DangerClose.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        Config.initialize();
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class DangerEventListener {

        @SubscribeEvent
        public static void onServerTick(TickEvent.ServerTickEvent event) {

            if (event.phase == TickEvent.Phase.START) {

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
}
