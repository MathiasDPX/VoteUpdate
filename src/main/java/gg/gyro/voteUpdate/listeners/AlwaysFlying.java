package gg.gyro.voteUpdate.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AlwaysFlying implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        event.getPlayer().setGliding(true);
    }
}
