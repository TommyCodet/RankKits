package de.meinserver.rankkits.gui;

import de.meinserver.rankkits.kits.KitService;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class GUIListener implements Listener {

    public GUIListener(Object plugin) {
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!event.getView().getTitle().equals(RankGUI.TITLE)) {
            return;
        }

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        switch (event.getSlot()) {

            case 10 -> {
                KitService.claimKit(player, "default");
                player.closeInventory();
            }

            case 11 -> {
                KitService.claimKit(player, "premium");
                player.closeInventory();
            }

            case 12 -> {
                KitService.claimKit(player, "vip");
                player.closeInventory();
            }

            case 14 -> {
                KitService.claimKit(player, "elite");
                player.closeInventory();
            }

            case 15 -> {
                KitService.claimKit(player, "champion");
                player.closeInventory();
            }

            case 16 -> {
                KitService.claimKit(player, "legend");
                player.closeInventory();
            }
        }
    }
}
