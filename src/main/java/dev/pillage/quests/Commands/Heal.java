package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.PlayerRank;
import dev.pillage.quests.Utils.RankManager;
import dev.pillage.quests.Utils.TextBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Heal implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;

        if (!RankManager.hasRank(player, PlayerRank.ADMIN)) {
            sender.sendMessage(TextBuilder.build("&cYou do not have permission to use this command."));
            return true;
        }
        player.setHealth(20);
        player.setFoodLevel(20);
        player.sendMessage(TextBuilder.build("&aYou have been healed."));
        return true;
    }
}
