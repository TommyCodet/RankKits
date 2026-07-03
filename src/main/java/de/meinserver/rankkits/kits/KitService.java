package de.meinserver.rankkits.kits;

import de.meinserver.rankkits.RankKitsPlugin;
import de.meinserver.rankkits.storage.DatabaseManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitService {

    public static void claimKit(Player player, String kit) {

        var hook = RankKitsPlugin.getInstance().getLuckPermsHook();

        if (!hook.hasAccess(player, kit)) {

            player.sendMessage("§cDu hast keinen Zugriff auf dieses Kit.");
            return;
        }

        if (!kit.equalsIgnoreCase("default")) {

            long remaining =
                    DatabaseManager.getRemainingCooldown(
                            player.getUniqueId(),
                            kit
                    );

            if (remaining > 0) {

                long minutes = remaining / 60;

                player.sendMessage(
                        "§cCooldown aktiv: "
                                + minutes
                                + " Minuten."
                );

                return;
            }
        }

        giveItems(player, kit);

        DatabaseManager.updateClaim(
                player.getUniqueId(),
                kit
        );

        player.sendMessage(
                "§aDu hast das "
                        + kit
                        + " Kit erhalten."
        );
    }

    private static void giveItems(Player player, String kit) {

        switch (kit.toLowerCase()) {

            case "default" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.STONE_SWORD)
                );

                player.getInventory().addItem(
                        new ItemStack(Material.BREAD, 16)
                );
            }

            case "premium" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.IRON_SWORD)
                );

                player.getInventory().addItem(
                        new ItemStack(Material.COOKED_BEEF, 32)
                );
            }

            case "vip" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.DIAMOND_SWORD)
                );

                player.getInventory().addItem(
                        new ItemStack(Material.GOLDEN_APPLE, 5)
                );
            }

            case "elite" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.NETHERITE_HELMET)
                );
            }

            case "champion" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.NETHERITE_CHESTPLATE)
                );
            }

            case "legend" -> {

                player.getInventory().addItem(
                        new ItemStack(Material.NETHERITE_SWORD)
                );

                player.getInventory().addItem(
                        new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 2)
                );
            }
        }
    }
}
