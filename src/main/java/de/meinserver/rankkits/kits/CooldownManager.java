package de.meinserver.rankkits.kits;

import de.meinserver.rankkits.RankKitsPlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class CooldownManager {

    public static long getCooldown(String kit) {

        FileConfiguration config =
                RankKitsPlugin.getInstance().getConfig();

        return config.getLong(
                "cooldown." + kit.toLowerCase(),
                3600
        );
    }
}
