package de.meinserver.rankkits.hooks;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class LuckPermsHook {

    private final LuckPerms luckPerms;

    public LuckPermsHook() {
        this.luckPerms = LuckPermsProvider.get();
    }

    public String getPrimaryGroup(Player player) {

        User user = luckPerms.getUserManager()
                .getUser(player.getUniqueId());

        if (user == null) {
            return "default";
        }

        return user.getPrimaryGroup().toLowerCase();
    }

    public boolean hasAccess(Player player, String requiredGroup) {

        String playerGroup = getPrimaryGroup(player);

        return getWeight(playerGroup)
                >= getWeight(requiredGroup);
    }

    private int getWeight(String group) {

        return switch (group.toLowerCase()) {

            case "legend" -> 6;

            case "champion" -> 5;

            case "elite" -> 4;

            case "vip" -> 3;

            case "premium" -> 2;

            default -> 1;
        };
    }
}
