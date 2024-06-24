package com.cursee.danger_close;

import com.cursee.danger_close.core.Config;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.world.entity.LivingEntity;

public class DangerCloseFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        DangerClose.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        Config.initialize();

        ServerTickEvents.START_SERVER_TICK.register(server -> {

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
        });
    }
}
