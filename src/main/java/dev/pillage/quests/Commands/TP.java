package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.PlayerRank;
import dev.pillage.quests.Utils.RankManager;
import dev.pillage.quests.Utils.TextBuilder;
import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TP implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String labl, String[] args) {
        Player player = (Player) sender;
        if (!RankManager.hasRank(player, PlayerRank.ADMIN)) {
            sender.sendMessage(TextBuilder.build("&cYou do not have permission to use this command."));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(TextBuilder.build("&cInvalid usage, please try again (/tp <player> <player/coords>"));
            return true;
        }
        String target = args[0];
        if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(target)) && args.length == 1) {
            sender.sendMessage(TextBuilder.build("&aTeleported to &e" + target + "&a."));

            ((Player) sender).teleport(Objects.requireNonNull(Bukkit.getPlayer(target)));
            return true;
        }

        if (args[1] == "me") {


        }

        Location loc = new Location(Bukkit.getPlayer(target).getWorld(), Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]));
        Objects.requireNonNull(Bukkit.getPlayer(target)).teleport(loc);
        if (!Bukkit.getPlayer(target).equals(sender)) {
            sender.sendMessage(TextBuilder.build("&aTeleported target to &e" + args[1] + " " + args[2] + " " + args[3] + "&a."));
            Bukkit.getPlayer(target).sendMessage(TextBuilder.build("&aYou have been teleported to &e" + args[1] + " " + args[2] + " " + args[3] + "&a."));
            return true;
        }
        sender.sendMessage(TextBuilder.build("&aYou have been teleported to &e" + args[1] + " " + args[2] + " " + args[3] + "&a."));
        return true;
    }
}
