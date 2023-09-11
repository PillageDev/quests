package dev.pillage.quests.Utils;

import dev.pillage.quests.Enums.PlayerRank;

import net.luckperms.api.model.user.User;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.luckperms.api.LuckPerms;

import java.util.UUID;

public class RankManager {

    static LuckPerms api;

    public static final RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);

    public static void init() {
        if (provider != null) {
            api = provider.getProvider();
        }
    }

    public static void setRank(final Player player, final PlayerRank rank) {
        UUID uuid = player.getUniqueId();
        User user = api.getUserManager().getUser(uuid);
        user.setPrimaryGroup(rank.toString());
    }

    public static String getRank(final Player player) {
        UUID uuid = player.getUniqueId();
        User user = api.getUserManager().getUser(uuid);
        return user.getPrimaryGroup();
    }


    public static boolean hasRank(final Player player, final PlayerRank rank) {
        UUID uuid = player.getUniqueId();
        User user = api.getUserManager().getUser(uuid);
        return user.getPrimaryGroup().equals(rank.toString().toLowerCase());
    }

}
