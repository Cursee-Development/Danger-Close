package com.cursee.danger_close;

import com.cursee.danger_close.core.registry.RegistryFabric;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;

public class DangerCloseFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        RegistryFabric.register();
    }
}
