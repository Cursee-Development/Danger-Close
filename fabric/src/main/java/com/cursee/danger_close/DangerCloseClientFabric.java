package com.cursee.danger_close;

import net.fabricmc.api.ClientModInitializer;

public class DangerCloseClientFabric implements ClientModInitializer {
    
    @Override
    public void onInitializeClient() {
        DangerCloseClient.init();
    }
}
