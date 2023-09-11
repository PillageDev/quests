package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.PlayerRank;
import dev.pillage.quests.Utils.RankManager;
import dev.pillage.quests.Utils.TextBuilder;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TPS implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;
        if (!RankManager.hasRank(player, PlayerRank.ADMIN)) {
            sender.sendMessage(TextBuilder.build("&cYou do not have permission to use this command."));
            return true;
        }
        String message = "";
        message = PlaceholderAPI.setPlaceholders(player, message);
        sender.sendMessage(TextBuilder.build(message));
        return false;
    }
}
