package dev.pillage.quests.Handlers;

import dev.pillage.quests.Enums.PartyRoles;
import dev.pillage.quests.Utils.TextBuilder;
import dev.pillage.quests.Utils.TextUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class Party {
    public Map<Integer, Map<UUID, PartyRoles>> parties = new HashMap<>();

    /**
     * Map<Integer, Map<UUID, PartyRoles>> parties = new HashMap<>();
     * Integer = Party ID
     * Map<UUID, PartyRoles> = Party Roles
     * PartyRoles = Party Roles
     * UUID = Player UUID associated with role
     */

    public void inviteUser(Player requester, Player target) {
        Map<UUID, PartyRoles> newParty = new HashMap<>();
        newParty.put(requester.getUniqueId(), PartyRoles.LEADER);
        //TODO: Start invite timer
        parties.put(parties.size() + 1, newParty);
        TextComponent acceptMessage = new TextComponent(TextBuilder.build(TextUtils.color("&eYou have been invited to a party by " + requester.getName() + "&e. &aClick to accept")));
        acceptMessage.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party accept " + requester.getName()));
        target.spigot().sendMessage(acceptMessage);
        requester.sendMessage(
                TextUtils.border + "\n" + TextUtils.color("&aYou have invited " + //TODO: Color name
                        target.getName() + " &ato your party. They have &c60 &aseconds to accept") + "\n" + TextUtils.border
        );
    }

    public void handleAccept(Player requester, Player target) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(target.getUniqueId())) {
                target.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&cYou are already in a party. Leave it before joining another one") + "\n" + TextUtils.border
                );
                return;
            }
            if (existingParties.containsKey(requester.getUniqueId())) {
                existingParties.put(target.getUniqueId(), PartyRoles.MEMBER);
                parties.put(i, existingParties);
                requester.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&a" + target.getName() + " &ahas joined your party") + "\n" + TextUtils.border
                );
                if (parties.get(i).size() > 2) {
                    StringBuilder memberList = new StringBuilder();
                    for (UUID uuid : parties.get(i).keySet()) {
                        memberList.append(Objects.requireNonNull(Bukkit.getPlayer(uuid)).getName()).append(", ");
                    }
                    target.sendMessage(
                            TextUtils.border + "\n" + TextUtils.color("&aYou have joined " + requester.getName() + "'s &aparty. You will be partying with " + memberList) + "\n" + TextUtils.border
                    );
                }
                target.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou have joined " + requester.getName() + "'s &aparty") + "\n" + TextUtils.border
                );
                return;
            } else {
                target.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&cYou have not been invited to a party by that user") + "\n" + TextUtils.border
                );
            }
        }
    }

    public void listPlayers(Player partyMember) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(partyMember.getUniqueId())) {
                int partySize = parties.get(i).size();
                StringBuilder memberList = new StringBuilder();
                StringBuilder leaderList = new StringBuilder();
                StringBuilder moderatorList = new StringBuilder();
                for (UUID uuid : parties.get(i).keySet()) {
                    Player player = Bukkit.getPlayer(uuid);
                    if (parties.get(i).get(uuid).equals(PartyRoles.LEADER)) {
                        assert player != null;
                        leaderList.insert(0, "&eParty Leader: " + "%luckperms_prefix%" + player.getName() + "\n");
                        leaderList = new StringBuilder(PlaceholderAPI.setPlaceholders(player, leaderList.toString()));
                    } else if (parties.get(i).get(uuid).equals(PartyRoles.MODERATOR)) {
                        moderatorList.insert(0, "&eParty Moderators: ");
                        moderatorList.append("%luckperms_prexix%").append(player.getName()).append(", ");
                        moderatorList = new StringBuilder(PlaceholderAPI.setPlaceholders(player, moderatorList.toString()));
                        moderatorList.deleteCharAt(moderatorList.length() - 2);
                    } else {
                        memberList.insert(0, "&eMember List: ");
                        memberList.append("%luckperms_prefix%").append(player.getName()).append(", ");
                        memberList = new StringBuilder(PlaceholderAPI.setPlaceholders(player, memberList.toString()));
                        memberList.deleteCharAt(memberList.length() - 2);
                    }
                }
                partyMember.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&6Party Members (" + partySize + ") \n" + leaderList + moderatorList + memberList) + TextUtils.border
                );
                return;
            } else {
                partyMember.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&cYou are not in a party") + "\n" + TextUtils.border
                );
            }
        }
    }

    public boolean isInParty(Player query) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(query.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    public void removeFromParty(Player player) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(player.getUniqueId())) {
                existingParties.remove(player.getUniqueId());
                parties.put(i, existingParties);
                player.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&aYou have left the party") + "\n" + TextUtils.border
                );
                List<Player> playersInOldParty = new ArrayList<>();
                for (UUID uuid : parties.get(i).keySet()) {
                    playersInOldParty.add(Bukkit.getPlayer(uuid));
                }

                for (Player p : playersInOldParty) {
                    String leaveMessage = "%luckperms_prefix%" + player.getName() + " &ahas left the party";
                    leaveMessage = PlaceholderAPI.setPlaceholders(p, leaveMessage);
                    p.sendMessage(
                            TextUtils.border + "\n" + TextUtils.color(leaveMessage) + "\n" + TextUtils.border
                    );
                }

            }
            return;
        }
    }

    public PartyRoles partyRole(Player query) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(query.getUniqueId())) {
                return existingParties.get(query.getUniqueId());
            }
        }
        return null;
    }

    public int partyID(Player query) {
        for (int i : parties.keySet()) {
            Map<UUID, PartyRoles> existingParties = parties.get(i);
            if (existingParties.containsKey(query.getUniqueId())) {
                return i;
            }
        }
        return -1;
    }
}

