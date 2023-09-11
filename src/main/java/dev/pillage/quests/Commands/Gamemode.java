package dev.pillage.quests.Commands;

import dev.pillage.quests.Enums.PlayerRank;
import dev.pillage.quests.Utils.RankManager;
import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Gamemode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if(!RankManager.hasRank(player, PlayerRank.ADMIN)) {
            sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&cYou do not have permission to run this command.\n" + TextUtils.border));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&cInvalid usage, please try again (/gamemode <gamemode> <target>).\n" + TextUtils.border));
            return true;
        } else if (args.length == 1) {
            String gamemode = args[0];
            switch (gamemode) {
                case "0" -> {
                    player.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eSURVIVAL&a.\n" + TextUtils.border));
                    return true;
                }
                case "1" -> {
                    player.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eCREATIVE&a.\n" + TextUtils.border));
                    return true;
                }
                case "2" -> {
                    player.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eADVENTURE&a.\n" + TextUtils.border));
                    return true;
                }
                case "3" -> {
                    player.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eSPECTATOR&a.\n" + TextUtils.border));
                    return true;
                }
                default -> sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&cInvalid usage, please try again (/gamemode <gamemode> <target>).\n" + TextUtils.border));
            }

        }
        else if (args.length == 2) {
            Player target = player.getServer().getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&cInvalid usage, please try again (/gamemode <gamemode> <target>).\n" + TextUtils.border));
                return true;
            }
            String gamemode = args[0];
            switch (gamemode) {
                case "0" -> {
                    target.setGameMode(GameMode.SURVIVAL);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aSet target game mode to &eSURVIVAL&a.\n" + TextUtils.border));
                    target.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eSURVIVAL&a.\n" + TextUtils.border));
                    return true;
                }
                case "1" -> {
                    target.setGameMode(GameMode.CREATIVE);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aSet target game mode to &eCREATIVE&a.\n" + TextUtils.border));
                    target.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eCREATIVE&a.\n" + TextUtils.border));

                    return true;
                }
                case "2" -> {
                    target.setGameMode(GameMode.ADVENTURE);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aSet target game mode to &eADVENTURE&a.\n" + TextUtils.border));
                    target.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eADVENTURE&a.\n" + TextUtils.border));
                    return true;
                }
                case "3" -> {
                    target.setGameMode(GameMode.SPECTATOR);
                    sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aSet target game mode to &eSPECTATOR&a.\n" + TextUtils.border));
                    target.sendMessage(TextUtils.border + "\n" + TextUtils.color("&aYour game mode has been set to &eSPECTATOR&a.\n" + TextUtils.border));
                    return true;
                }
                default -> sender.sendMessage(TextUtils.border + "\n" + TextUtils.color("&cInvalid usage, please try again (/gamemode <gamemode> <target>).\n" + TextUtils.border));
            }

        }
        return false;
    }
}
