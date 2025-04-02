package com.cursee.danger_close;

import com.cursee.danger_close.platform.Services;
import net.minecraft.resources.ResourceLocation;

public class DangerClose {

    public static boolean SOUL_FIRE_D_INSTALLED = false;

    public static void init() {
        SOUL_FIRE_D_INSTALLED = Services.PLATFORM.isModLoaded("soul_fire_d");
    }

    public static ResourceLocation identifier(String value) {
        return new ResourceLocation(Constants.MOD_ID, value);
    }
}