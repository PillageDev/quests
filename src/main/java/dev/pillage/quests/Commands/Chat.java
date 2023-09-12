package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.ChatChannels;
import dev.pillage.quests.Enums.PlayerRank;
import dev.pillage.quests.Utils.RankManager;
import dev.pillage.quests.Utils.TextBuilder;
import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Chat implements CommandExecutor {
    dev.pillage.quests.Handlers.Chat chat = new dev.pillage.quests.Handlers.Chat();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("chat")) {
            dev.pillage.quests.Handlers.Chat chat = new dev.pillage.quests.Handlers.Chat();
            if (args.length == 0) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&e/chat <channel> - Switch to a chat channel\n&e/chat list - List all chat channels\n") + TextUtils.border
                );
                return true;
            }
            if (args[0].equalsIgnoreCase("list")) {
                sender.sendMessage(
                        TextUtils.border + TextUtils.color("\n&6Chat Channels:\n&6All\n&6Party\n&6Guild\n") + TextUtils.border
                );
                return true;
            }
            if (args[0].equalsIgnoreCase("a") || args[0].equalsIgnoreCase("all")) {
                chat.joinChannel((Player) sender, ChatChannels.ALL);
                return true;
            }
            if (args[0].equalsIgnoreCase("p") || args[0].equalsIgnoreCase("party")) {
                //TODO: Handle party chat
                return true;
            }
            if (args[0].equalsIgnoreCase("g") || args[0].equalsIgnoreCase("guild")) {
                chat.joinChannel((Player) sender, ChatChannels.GUILD);
            }
            if (args[0].equalsIgnoreCase("admin")) {
                if (RankManager.hasRank((Player) sender, PlayerRank.ADMIN)) {
                    chat.joinChannel((Player) sender, ChatChannels.ADMIN);
                    return true;
                }
                sender.sendMessage(TextBuilder.build("&cYou do not have permission to use this command!"));
                return true;
            }
            if (args[0].equalsIgnoreCase("s") || args[0].equalsIgnoreCase("staff")) {
                if (RankManager.hasRank((Player) sender, PlayerRank.MODERATOR)) {
                    chat.joinChannel((Player) sender, ChatChannels.STAFF);
                    return true;
                }
                if (RankManager.hasRank((Player) sender, PlayerRank.ADMIN)) {
                    chat.joinChannel((Player) sender, ChatChannels.STAFF);
                    return true;
                }
                sender.sendMessage(TextBuilder.build("&cYou do not have permission to use this command!"));
                return true;
            }
        }
        return false;
    }
}
