package dev.pillage.quests.Utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.configcat.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class FlagHandler implements Listener {
    public static final ConfigCatClient client = ConfigCatClient.get("E2LbCH4TcEqLbgMN0dzSqA/bsSxIStVD0iW4P-BIq6nUg", options -> {
        options.logLevel(LogLevel.INFO);
    });

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().hasPlayedBefore()) {
            Bukkit.getLogger().info("Player has logged on before. Skipping user creation...");
        }
        UUID uuid = e.getPlayer().getUniqueId();
        User user = User.newBuilder().build(uuid.toString());
        Bukkit.getLogger().info("User created with UUID: " + uuid.toString());
    }

    public static boolean isFlagEnabled(final String flag) {
        return client.getValue(Boolean.class, flag, false);
    }

}
