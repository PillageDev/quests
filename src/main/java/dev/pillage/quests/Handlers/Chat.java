package dev.pillage.quests.Handlers;

import dev.pillage.quests.Enums.ChatChannels;
import dev.pillage.quests.Utils.TextBuilder;
import dev.pillage.quests.Utils.TextUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.Array;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Chat implements Listener {
    public HashMap<Player, ChatChannels> playerChannels = new HashMap<>();
    public HashMap<ChatChannels, ArrayList<Player>> channelPlayers = new HashMap<>();

    public void joinChannel(Player player, ChatChannels channel) {
        if (playerChannels.get(player) != null) {
            ChatChannels oldChannel = playerChannels.get(player);
            leaveChannel(player, oldChannel);
        }

        ArrayList<Player> players = channelPlayers.get(channel);
        if (players == null) {
            players = new ArrayList<>();
        }
        players.add(player);
        channelPlayers.put(channel, players);
        playerChannels.put(player, channel);
        player.sendMessage(TextBuilder.build("&aYou have joined the " + channel.toString().toLowerCase() + " channel."));
    }

    public void leaveChannel(Player player, ChatChannels channel) {
        ArrayList<Player> players = channelPlayers.get(channel);
        players.remove(player);
        channelPlayers.put(channel, players);
        playerChannels.remove(player);
    }

    public ArrayList<Player> getChannel(Player player) {
        ChatChannels channel = playerChannels.get(player);
        return channelPlayers.get(channel);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        e.getRecipients().clear();
        e.setFormat(TextUtils.color("&7[&a" + playerChannels.get(p).toString().toLowerCase() + "&7] &r" + e.getFormat()));
        getChannel(p).forEach(player -> e.getRecipients().add(player));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        joinChannel(e.getPlayer(), ChatChannels.ALL);
    }
}
