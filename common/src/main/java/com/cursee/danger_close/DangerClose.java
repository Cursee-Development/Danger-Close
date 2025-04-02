package com.cursee.danger_close;

import net.minecraft.resources.ResourceLocation;

public class DangerClose {

    public static void init() {}

    public static ResourceLocation identifier(String value) {
        return new ResourceLocation(Constants.MOD_ID, value);
    }
}