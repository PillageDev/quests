package dev.pillage.quests;

import dev.pillage.quests.Commands.Gamemode;
import dev.pillage.quests.Commands.Heal;
import dev.pillage.quests.Commands.PL;
import dev.pillage.quests.Commands.TP;
import dev.pillage.quests.Handlers.Chat;
import dev.pillage.quests.Utils.FlagHandler;
import dev.pillage.quests.Utils.RankManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public final class Main extends JavaPlugin {

    public final File dataFolder = this.getDataFolder();

    @Override
    public void onEnable() {
        RankManager.init();
        getCommand("gamemode").setExecutor(new Gamemode());
        getServer().getPluginManager().registerEvents(new Chat(), this);
        getServer().getPluginManager().registerEvents(new FlagHandler(), this);
        getCommand("chat").setExecutor(new dev.pillage.quests.Commands.Chat());
        getCommand("party").setExecutor(new dev.pillage.quests.Commands.Party());
        getCommand("tp").setExecutor(new TP());
        getCommand("heal").setExecutor(new Heal());
        getCommand("pl").setExecutor(new PL());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
