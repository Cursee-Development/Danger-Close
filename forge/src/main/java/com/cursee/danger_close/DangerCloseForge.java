package com.cursee.danger_close;

import com.cursee.danger_close.core.registry.RegistryForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Constants.MOD_ID)
public class DangerCloseForge {

    public static IEventBus EVENT_BUS = null;
    
    public DangerCloseForge(FMLJavaModLoadingContext context) {
        DangerClose.init();
        Sailing.register(Constants.MOD_ID, Constants.MOD_NAME, Constants.MOD_VERSION, Constants.MOD_PUBLISHER, Constants.MOD_URL);
        DangerCloseForge.EVENT_BUS = context.getModEventBus();
        RegistryForge.register(DangerCloseForge.EVENT_BUS);
    }
}