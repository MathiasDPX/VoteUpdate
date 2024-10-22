package gg.gyro.voteUpdate.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GodOfLightning implements Listener {

    private void spawnLightning(Location loc) {
        loc.getWorld().strikeLightning(loc);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (!(e.getAction().equals(Action.LEFT_CLICK_BLOCK))) return;
        Block block = e.getClickedBlock();
        if (block == null) return;
        spawnLightning(block.getLocation());
    }

    @EventHandler
    public void onInteractAtEntity(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        spawnLightning(e.getEntity().getLocation());
    }
}
