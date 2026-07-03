package de.meinserver.rankkits.kits;

import de.meinserver.rankkits.RankKitsPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class KitManager {

    private static final Map<String, Kit> KITS = new HashMap<>();

    public static void load() {

        KITS.clear();

        File file = new File(
                RankKitsPlugin.getInstance().getDataFolder(),
                "kits.yml"
        );

        if (!file.exists()) {
            RankKitsPlugin.getInstance().saveResource(
                    "kits.yml",
                    false
            );
        }

        YamlConfiguration config =
                YamlConfiguration.loadConfiguration(file);

        if (!config.contains("kits")) {
            return;
        }

        for (String kitName :
                config.getConfigurationSection("kits").getKeys(false)) {

            List<ItemStack> items = new ArrayList<>();

            List<String> itemStrings =
                    config.getStringList(
                            "kits." + kitName + ".items"
                    );

            for (String entry : itemStrings) {

                String[] split = entry.split(":");

                Material material =
                        Material.valueOf(split[0]);

                int amount =
                        Integer.parseInt(split[1]);

                items.add(
                        new ItemStack(material, amount)
                );
            }

            KITS.put(
                    kitName.toLowerCase(),
                    new Kit(kitName, items)
            );
        }
    }

    public static Kit getKit(String name) {
        return KITS.get(name.toLowerCase());
    }
}
