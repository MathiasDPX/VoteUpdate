package gg.gyro.voteUpdate.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Minime implements Listener {
    @EventHandler
    void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        gg.gyro.voteUpdate.votes.Minime.minimizePlayer(player);
    }
}