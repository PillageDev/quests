package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.ChatChannels;
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
            if (args.length == 0) {
                sender.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&e/chat a - All Chat\n&e/chat p - Party Chat") + "\n" + TextUtils.border
                );
                return true;
            }
            if (args[0].equalsIgnoreCase("a")) {
                sender.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in all chat") + "\n" + TextUtils.border
                );
                chat.switchChannel(ChatChannels.ALL, (Player) sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("p")) {
                sender.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in party chat") + "\n" + TextUtils.border
                );
                chat.switchChannel(ChatChannels.PARTY, (Player) sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("s")) {
                sender.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in staff chat") + "\n" + TextUtils.border
                );
                chat.switchChannel(ChatChannels.STAFF, (Player) sender);
                return true;
            }
            if (args[0].equalsIgnoreCase("admin")) {
                sender.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in admin chat") + "\n" + TextUtils.border
                );
                chat.switchChannel(ChatChannels.ADMIN, (Player) sender);
                return true;
            }
        }
        return false;
    }
}
