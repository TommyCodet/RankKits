package de.meinserver.rankkits.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RankGUI {

    public static final String TITLE = "§8Rank Kits";

    public static void open(Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, TITLE);

        inv.setItem(10, createItem(
                Material.GRAY_STAINED_GLASS_PANE,
                "§7Default Kit",
                List.of(
                        "§7Immer verfügbar",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        inv.setItem(11, createItem(
                Material.GOLD_INGOT,
                "§ePremium Kit",
                List.of(
                        "§71 Stunde Cooldown",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        inv.setItem(12, createItem(
                Material.DIAMOND,
                "§bVIP Kit",
                List.of(
                        "§71 Stunde Cooldown",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        inv.setItem(14, createItem(
                Material.AMETHYST_SHARD,
                "§5Elite Kit",
                List.of(
                        "§71 Stunde Cooldown",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        inv.setItem(15, createItem(
                Material.TOTEM_OF_UNDYING,
                "§6Champion Kit",
                List.of(
                        "§71 Stunde Cooldown",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        inv.setItem(16, createItem(
                Material.NETHER_STAR,
                "§6§lLegend Kit",
                List.of(
                        "§71 Stunde Cooldown",
                        "",
                        "§aKlicken zum Abholen"
                )
        ));

        player.openInventory(inv);
    }

    private static ItemStack createItem(Material material,
                                        String name,
                                        List<String> lore) {

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            item.setItemMeta(meta);
        }

        return item;
    }
}
