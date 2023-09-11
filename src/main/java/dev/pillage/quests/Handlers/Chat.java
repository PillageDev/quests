package dev.pillage.quests.Handlers;

import dev.pillage.quests.Enums.ChatChannels;
import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

public class Chat implements Listener {
    List<Player> all = new ArrayList<>();
    List<Player> party = new ArrayList<>();
    List<Player> staff = new ArrayList<>();
    List<Player> admin = new ArrayList<>();
    public void switchChannel(ChatChannels channel, Player player) {
        switch (channel) {
            case ALL -> {
                all.add(player);
                removeFromLists(all, player);
                player.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in all chat") + "\n" + TextUtils.border
                );
            }
            case PARTY -> {
                party.add(player);
                removeFromLists(party, player);
                player.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in party chat") + "\n" + TextUtils.border
                );
            }
            case STAFF -> {
                staff.add(player);
                removeFromLists(staff, player);
                player.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in staff chat") + "\n" + TextUtils.border
                );
            }
            case ADMIN -> {
                admin.add(player);
                removeFromLists(admin, player);
                player.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou are now in admin chat") + "\n" + TextUtils.border
                );
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (all.contains(player)) return;
        for (Player p : e.getRecipients()) {
            if (party.contains(player)) {
                if (!party.contains(p)) {
                    //TODO: Implement logic on sending to specific party members
                }
            }
            if (staff.contains(player)) {
                if (!staff.contains(p)) {
                    e.getRecipients().remove(p);
                }
            }
            if (admin.contains(player)) {
                if (!admin.contains(p)) {
                    e.getRecipients().remove(p);
                }
            }
        }
    }

    public void removeFromLists(List<Player> exclusion, Player player) {
        if (all.contains(player) && !exclusion.contains(player)) {
            try {
                all.remove(player);
            } catch (Exception ignored) {}
        }
        if (party.contains(player) && !exclusion.contains(player)) {
            try {
                party.remove(player);
            } catch (Exception ignored) {
            }
        }
        if (staff.contains(player) && !exclusion.contains(player)) {
            try {
                staff.remove(player);
            } catch (Exception ignored) {
            }
        }
        if (admin.contains(player) && !exclusion.contains(player)) {
            try {
                admin.remove(player);
            } catch (Exception ignored) {
            }
        }
    }
}
