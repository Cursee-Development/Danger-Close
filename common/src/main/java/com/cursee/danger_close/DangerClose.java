package com.cursee.danger_close;

import com.cursee.monolib.core.sailing.Sailing;

public class DangerClose {

    public static void init() {

        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
    }
}