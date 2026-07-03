package de.meinserver.rankkits;

import de.meinserver.rankkits.commands.RanksCommand;
import de.meinserver.rankkits.gui.GUIListener;
import de.meinserver.rankkits.hooks.LuckPermsHook;
import org.bukkit.plugin.java.JavaPlugin;

public class RankKitsPlugin extends JavaPlugin {

    private static RankKitsPlugin instance;
    private LuckPermsHook luckPermsHook;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();

        this.luckPermsHook = new LuckPermsHook();

        if (getCommand("ranks") != null) {
            getCommand("ranks").setExecutor(new RanksCommand(this));
        }

        getServer().getPluginManager().registerEvents(
                new GUIListener(this),
                this
        );

        getLogger().info("RankKits aktiviert.");
    }

    @Override
    public void onDisable() {
        getLogger().info("RankKits deaktiviert.");
    }

    public static RankKitsPlugin getInstance() {
        return instance;
    }

    public LuckPermsHook getLuckPermsHook() {
        return luckPermsHook;
    }
}
