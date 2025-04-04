package com.cursee.danger_close.core;

import com.cursee.danger_close.Constants;
import com.cursee.danger_close.platform.Services;
import com.cursee.monolib.util.toml.Toml;
import com.cursee.monolib.util.toml.TomlWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NeoForgeCommonConfigHandler {

    public static final String CONFIG_DIR = Services.PLATFORM.getGameDirectory() + File.separator + "config" + File.separator;
    public static final String CONFIG_FILEPATH = CONFIG_DIR + Constants.MOD_ID + "-common.toml";

    public static void onLoad() {
        File configDirectory = new File(CONFIG_DIR);
        if (!configDirectory.isDirectory()) configDirectory.mkdirs();
        File configFile = new File(CONFIG_FILEPATH);

        if (!configFile.isFile()) createConfigurationWithDefaultValues(configFile);
        else readConfigurationAndGetValues(configFile);
    }

    private static void createConfigurationWithDefaultValues(File configFile) {

        populateDefaults();

        try {
            new TomlWriter().write(defaults, configFile);
        }
        catch (IOException ignored) {}
    }

    private static void readConfigurationAndGetValues(File configFile) {
        Toml toml = new Toml().read(configFile);
        CommonConfigValues.shouldDetect = toml.getBoolean("enabled");
        CommonConfigValues.shouldTorchImmolate = toml.getBoolean("torches_burn");
        CommonConfigValues.shouldSoulTorchImmolate = toml.getBoolean("soul_torches_burn");
        CommonConfigValues.shouldCampfireImmolate = toml.getBoolean("campfires_burn");
        CommonConfigValues.shouldSoulCampfireImmolate = toml.getBoolean("soul_campfires_burn");
        CommonConfigValues.shouldStonecutterCut = toml.getBoolean("stonecutters_cut");
        CommonConfigValues.shouldBlazeImmolate = toml.getBoolean("enable_blaze_damage");
        CommonConfigValues.shouldMagmaCubeImmolate = toml.getBoolean("enable_magma_cube_damage");
        CommonConfigValues.shouldMagmaBlockImmolate = toml.getBoolean("enable_magma_block_damage");
    }

    public static final Map<String, Object> defaults = new HashMap<>();

    private static void populateDefaults() {
        defaults.put("enabled", CommonConfigValues.shouldDetect);
        defaults.put("torches_burn", CommonConfigValues.shouldTorchImmolate);
        defaults.put("soul_torches_burn", CommonConfigValues.shouldSoulTorchImmolate);
        defaults.put("campfires_burn", CommonConfigValues.shouldCampfireImmolate);
        defaults.put("soul_campfires_burn", CommonConfigValues.shouldSoulCampfireImmolate);
        defaults.put("stonecutters_cut", CommonConfigValues.shouldStonecutterCut);
        defaults.put("enable_blaze_damage", CommonConfigValues.shouldBlazeImmolate);
        defaults.put("enable_magma_cube_damage", CommonConfigValues.shouldMagmaCubeImmolate);
        defaults.put("enable_magma_block_damage", CommonConfigValues.shouldMagmaBlockImmolate);
    }
}
