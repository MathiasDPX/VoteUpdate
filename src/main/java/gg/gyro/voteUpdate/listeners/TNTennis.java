package gg.gyro.voteUpdate.listeners;

import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.util.Vector;

public class TNTennis implements Listener {
    @EventHandler
    void onInteract(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof TNTPrimed tnt)) return;
        Player player = event.getPlayer();

        Vector direction = player.getLocation().toVector()
                .subtract(tnt.getLocation().toVector())
                .normalize()
                .multiply(-1); // Knockback-like velocity

        tnt.setVelocity(direction);
    }
}
