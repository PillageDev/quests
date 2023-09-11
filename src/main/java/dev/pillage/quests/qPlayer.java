package dev.pillage.quests;

import dev.pillage.quests.Utils.RankManager;
import org.bukkit.entity.Player;

public class qPlayer {
    private Player player;

    public qPlayer(Player player) {
        this.player = player;
    }

    public void sendTranslated(String message) {
        player.sendMessage(message.replace("%%red%%", "§c").replace("%%green%%", "§a").replace("%%yellow%%", "§e").replace("%%blue%%", "§9").replace("%%purple%%", "§5").replace("%%aqua%%", "§b").replace("%%white%%", "§f").replace("%%black%%", "§0").replace("%%bold%%", "§l").replace("%%italic%%", "§o").replace("%%underline%%", "§n").replace("%%strike%%", "§m").replace("%%reset%%", "§r").replace("%%gray%%", "§7").replace("%%gold%%", "§6").replace("%%darkgreen%%", "§2").replace("%%darkaqua%%", "§3").replace("%%darkred%%", "§4").replace("%%darkpurple%%", "§5").replace("%%darkblue%%", "§1").replace("%%darkgray%%", "§8".replace("%%magic%%", "§k")));
    }

    public String getRank() {
        return RankManager.getRank(player);
    }
}
