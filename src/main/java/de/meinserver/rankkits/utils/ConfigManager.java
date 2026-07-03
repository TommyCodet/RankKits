package de.meinserver.rankkits.utils;

import de.meinserver.rankkits.RankKitsPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {

    private ConfigManager() {
    }

    public static FileConfiguration config() {
        return RankKitsPlugin.getInstance().getConfig();
    }

    public static String getString(String path) {
        return config().getString(path);
    }

    public static int getInt(String path) {
        return config().getInt(path);
    }

    public static long getLong(String path) {
        return config().getLong(path);
    }

    public static boolean getBoolean(String path) {
        return config().getBoolean(path);
    }
}
