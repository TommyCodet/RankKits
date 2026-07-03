package de.meinserver.rankkits.hooks;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.entity.Player;

public class LuckPermsHook {

    private final LuckPerms luckPerms;

    public LuckPermsHook() {
        this.luckPerms = LuckPermsProvider.get();
    }

    public String getPrimaryGroup(Player player) {
        return luckPerms.getUserManager()
                .getUser(player.getUniqueId())
                .getPrimaryGroup()
                .toLowerCase();
    }

    public boolean hasAccess(Player player, String requiredGroup) {

        String group = getPrimaryGroup(player);

        return getWeight(group) >= getWeight(requiredGroup);
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
