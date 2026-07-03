package de.meinserver.rankkits.kits;

import de.meinserver.rankkits.RankKitsPlugin;
import de.meinserver.rankkits.storage.DatabaseManager;
import de.meinserver.rankkits.utils.TimeUtil;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitService {

    public static void claimKit(Player player, String kitName) {

        var hook = RankKitsPlugin.getInstance().getLuckPermsHook();

        if (!hook.hasAccess(player, kitName)) {

            player.sendMessage("§cDu hast keinen Zugriff auf dieses Kit.");
            return;
        }

        long cooldown =
                CooldownManager.getCooldown(kitName);

        if (cooldown > 0) {

            long remaining =
                    DatabaseManager.getRemainingCooldown(
                            player.getUniqueId(),
                            kitName
                    );

            if (remaining > 0) {

                player.sendMessage(
                        "§cCooldown: "
                                + TimeUtil.format(remaining)
                );

                return;
            }
        }

        Kit kit = KitManager.getKit(kitName);

        if (kit == null) {

            player.sendMessage("§cKit nicht gefunden.");
            return;
        }

        for (ItemStack item : kit.getItems()) {
            player.getInventory().addItem(item);
        }

        DatabaseManager.updateClaim(
                player.getUniqueId(),
                kitName
        );

        player.sendMessage(
                "§aDu hast das "
                        + kit.getName()
                        + " Kit erhalten."
        );
    }
}
