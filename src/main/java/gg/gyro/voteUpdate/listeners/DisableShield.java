package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class DisableShield implements Listener {
    @EventHandler
    public void onShieldUse(PlayerInteractEvent event) {
        if (!event.getAction().isRightClick()) return;

        ItemStack item = event.getItem();
        if (item == null) return;
        if (item.getType() != Material.SHIELD) return;

        event.setCancelled(true);
    }
}
