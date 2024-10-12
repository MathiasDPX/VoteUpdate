package gg.gyro.voteUpdate.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AlwaysFlying implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!player.isOnGround()) {
            player.setGliding(true);
        } else {
            player.setGliding(false);
        }
    }
}
