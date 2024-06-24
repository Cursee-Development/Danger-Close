package com.cursee.danger_close.core;

import com.cursee.danger_close.Constants;
import com.cursee.danger_close.DangerClose;
import com.cursee.monolib.platform.Services;
import com.cursee.monolib.util.toml.Toml;
import com.cursee.monolib.util.toml.TomlWriter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Config {

    public static final File CONFIG_DIRECTORY = new File(Services.PLATFORM.getGameDirectory() + File.separator + "config");
    public static final String CONFIG_FILEPATH = CONFIG_DIRECTORY + File.separator + Constants.MOD_ID + ".toml";

    public static boolean enabled = true;

    public static boolean torches_burn = false;
    public static boolean soul_torches_burn = false;
    public static boolean campfires_burn = true;
    public static boolean soul_campfires_burn = true;
    public static boolean stonecutters_cut = true;
    public static boolean enable_blaze_damage = true;
    public static boolean enable_magma_cube_damage = false;
    public static boolean enable_magma_block_damage = true;

    public static final Map<String, Object> defaults = new HashMap<String, Object>();

    public static void initialize() {

        defaults.put("enabled", enabled);
        defaults.put("torches_burn", torches_burn);
        defaults.put("soul_torches_burn", soul_torches_burn);
        defaults.put("campfires_burn", campfires_burn);
        defaults.put("soul_campfires_burn", soul_campfires_burn);
        defaults.put("stonecutters_cut", stonecutters_cut);
        defaults.put("enable_blaze_damage", enable_blaze_damage);
        defaults.put("enable_magma_cube_damage", enable_magma_cube_damage);
        defaults.put("enable_magma_block_damage", enable_magma_block_damage);

        // laziness. sue me.
        DangerClose.shouldDetect =  enabled;
        DangerClose.shouldTorchImmolate = torches_burn;
        DangerClose.shouldSoulTorchImmolate = soul_torches_burn;
        DangerClose.shouldCampfireImmolate = campfires_burn;
        DangerClose.shouldSoulCampfireImmolate = soul_campfires_burn;
        DangerClose.shouldStonecutterCut = stonecutters_cut;
        DangerClose.shouldBlazeImmolate = enable_blaze_damage;
        DangerClose.shouldMagmaCubeImmolate = enable_magma_cube_damage;
        DangerClose.shouldMagmaBlockImmolate = enable_magma_block_damage;

        if (!CONFIG_DIRECTORY.isDirectory()) {
            CONFIG_DIRECTORY.mkdir();
        }

        final File CONFIG_FILE = new File(CONFIG_FILEPATH);

        Config.handle(CONFIG_FILE);
    }

    public static void handle(File file) {

        final boolean FILE_NOT_FOUND = !file.isFile();

        if (FILE_NOT_FOUND) {
            try {
                TomlWriter writer = new TomlWriter();
                writer.write(defaults, file);
            }
            catch (IOException exception) {
                Constants.LOG.error("Fatal error occurred while attempting to write " + Constants.MOD_ID + ".toml");
                Constants.LOG.error("Did another process delete the config directory during writing?");
                Constants.LOG.error(exception.getMessage());
            }
        }
        else {
            try {

                Toml toml = new Toml().read(file);

                enabled = toml.getBoolean("enabled");

                torches_burn = toml.getBoolean("torches_burn");
                soul_torches_burn = toml.getBoolean("soul_torches_burn");
                campfires_burn = toml.getBoolean("campfires_burn");
                soul_campfires_burn = toml.getBoolean("soul_campfires_burn");
                stonecutters_cut = toml.getBoolean("stonecutters_cut");
                enable_blaze_damage = toml.getBoolean("enable_blaze_damage");
                enable_magma_cube_damage = toml.getBoolean("enable_magma_cube_damage");
                enable_magma_block_damage = toml.getBoolean("enable_magma_block_damage");

                // laziness. sue me.
                DangerClose.shouldDetect =  enabled;
                DangerClose.shouldTorchImmolate = torches_burn;
                DangerClose.shouldSoulTorchImmolate = soul_torches_burn;
                DangerClose.shouldCampfireImmolate = campfires_burn;
                DangerClose.shouldSoulCampfireImmolate = soul_campfires_burn;
                DangerClose.shouldStonecutterCut = stonecutters_cut;
                DangerClose.shouldBlazeImmolate = enable_blaze_damage;
                DangerClose.shouldMagmaCubeImmolate = enable_magma_cube_damage;
                DangerClose.shouldMagmaBlockImmolate = enable_magma_block_damage;

            }
            catch (IllegalStateException exception) {
                Constants.LOG.error("Fatal error occurred while attempting to read " + Constants.MOD_ID + ".toml");
                Constants.LOG.error("Did another process delete the file during reading?");
                Constants.LOG.error(exception.getMessage());
            }
        }
    }
}
