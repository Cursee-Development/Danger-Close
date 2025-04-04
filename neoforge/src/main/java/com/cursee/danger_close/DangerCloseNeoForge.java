package com.cursee.danger_close;

import com.cursee.danger_close.core.registry.RegistryNeoForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class DangerCloseNeoForge {

    public static IEventBus EVENT_BUS = null;

    public DangerCloseNeoForge(IEventBus modEventBus) {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        DangerCloseNeoForge.EVENT_BUS = modEventBus;
        RegistryNeoForge.register(DangerCloseNeoForge.EVENT_BUS);
    }
}