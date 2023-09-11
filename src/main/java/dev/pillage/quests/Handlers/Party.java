package dev.pillage.quests.Handlers;

import dev.pillage.quests.Enums.PartyRoles;
import dev.pillage.quests.Utils.TextBuilder;
import dev.pillage.quests.Utils.TextUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                    String memberList = "";
                    for (UUID uuid : parties.get(i).keySet()) {
                        memberList = memberList + Bukkit.getPlayer(uuid).getName() + ", ";
                        memberList.replace(", " + uuid, "");
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
                String memberList = "";
                for (UUID uuid : parties.get(i).keySet()) {
                    //TODO: List specific to party rank
                    memberList = memberList + "- " + Bukkit.getPlayer(uuid).getName() + "\n";
                }
                partyMember.sendMessage(
                        TextUtils.border + "\n" + TextUtils.color("&6Party Members(" + partySize + ") \n" + memberList) + "\n" + TextUtils.border
                );
                return;
            }
        }
    }
}

