package dev.pillage.quests.Commands;

import dev.pillage.quests.Handlers.Party;
import dev.pillage.quests.Utils.TextBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PL implements CommandExecutor {
    dev.pillage.quests.Handlers.Party party = new dev.pillage.quests.Handlers.Party();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Party party = new Party();
        party.listPlayers((Player) sender);
        return true;
    }
}
