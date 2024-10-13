package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class BedPVP implements Listener {

    public boolean isBed(Material material) {
        return material.name().toLowerCase().contains("bed");
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player player)) return;
        if (!e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) return;

        if (!isBed(player.getInventory().getItemInMainHand().getType())) return;

        e.setDamage(3.5);
    }
}
