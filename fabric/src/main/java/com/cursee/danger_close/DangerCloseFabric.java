package com.cursee.danger_close;

import com.cursee.danger_close.core.Config;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;

public class DangerCloseFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        DangerClose.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        Config.initialize();
    }
}
