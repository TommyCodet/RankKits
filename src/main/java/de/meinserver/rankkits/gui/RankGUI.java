package de.meinserver.rankkits.gui;

import de.meinserver.rankkits.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class RankGUI {

    public static final String TITLE = "§8Rank Kits";

    public static void open(Player player) {

        Inventory inv = Bukkit.createInventory(
                null,
                27,
                TITLE
        );

        for (int i = 0; i < 27; i++) {

            inv.setItem(
                    i,
                    new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .name(" ")
                            .build()
            );
        }

        inv.setItem(
                10,
                new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                        .name("§7Default")
                        .lore(List.of(
                                "§7Standard Kit",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        inv.setItem(
                11,
                new ItemBuilder(Material.GOLD_INGOT)
                        .name("§ePremium")
                        .lore(List.of(
                                "§71 Stunde Cooldown",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        inv.setItem(
                12,
                new ItemBuilder(Material.DIAMOND)
                        .name("§bVIP")
                        .lore(List.of(
                                "§71 Stunde Cooldown",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        inv.setItem(
                14,
                new ItemBuilder(Material.AMETHYST_SHARD)
                        .name("§5Elite")
                        .lore(List.of(
                                "§71 Stunde Cooldown",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        inv.setItem(
                15,
                new ItemBuilder(Material.GOLD_BLOCK)
                        .name("§6Champion")
                        .lore(List.of(
                                "§71 Stunde Cooldown",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        inv.setItem(
                16,
                new ItemBuilder(Material.NETHER_STAR)
                        .name("§6§lLegend")
                        .lore(List.of(
                                "§x§F§F§A§A§0§0Gold-Orange Rang",
                                "§71 Stunde Cooldown",
                                "",
                                "§aKlicken zum Abholen"
                        ))
                        .build()
        );

        player.openInventory(inv);
    }
}
