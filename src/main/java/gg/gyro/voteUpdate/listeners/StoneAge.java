package gg.gyro.voteUpdate.listeners;

import gg.gyro.localeAPI.Locales;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class StoneAge implements Listener {
    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        Component playername = Component.text(Locales.get(Locales.get(new gg.gyro.voteUpdate.votes.StoneAge().getLocaleRoot()+"name")+"playername"));
        Player player = event.getPlayer();
        player.playerListName(playername);
        player.displayName(playername);
    }
}