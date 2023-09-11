package dev.pillage.quests.Commands;

import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Party implements CommandExecutor {
    dev.pillage.quests.Handlers.Party party = new dev.pillage.quests.Handlers.Party();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("party")) {
            if (args.length == 0) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&e/party <player> - Add a player to your party\n&e/party remove <player> - Remove a player from your party\n&e/party leave - Leave your party\n&e/party disband - Disband your party\n&e/party list - List all players in your party\n&e/chat p - Switch to party chat\n") + TextUtils.border
                );
                return true;
            }
            if (args[0].equalsIgnoreCase("accept") && args.length == 1) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&cPlease specify a user") + TextUtils.border
                );
                return true;
            }
            if (args[0].equalsIgnoreCase("accept") && Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[1]))) {
                party.handleAccept(Bukkit.getPlayer(args[1]), (org.bukkit.entity.Player) sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("list")) {
                party.listPlayers((org.bukkit.entity.Player) sender);
                return true;
            }
            if (!Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&cCannot find that player! Are they online?") + TextUtils.border
                );
                return true;
            }
            if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&aSent a party request to ") + args[0] + TextUtils.border
                );
                party.inviteUser((org.bukkit.entity.Player) sender, Bukkit.getPlayer(args[0]));
                return true;
            }
        }
        return false;
    }
}
