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
    public void onClick(InventoryClickEvent event) {

        if (event.getView().getTitle() == null) {
            return;
        }

        if (!event.getView().getTitle().equals(RankGUI.TITLE)) {
            return;
        }

        event.setCancelled(true);

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }

        switch (event.getSlot()) {

            case 10 -> KitService.claimKit(player, "default");

            case 11 -> KitService.claimKit(player, "premium");

            case 12 -> KitService.claimKit(player, "vip");

            case 14 -> KitService.claimKit(player, "elite");

            case 15 -> KitService.claimKit(player, "champion");

            case 16 -> KitService.claimKit(player, "legend");
        }
    }
}
