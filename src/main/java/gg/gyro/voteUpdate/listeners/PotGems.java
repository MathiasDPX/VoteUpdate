package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class PotGems implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (!block.getType().equals(Material.DECORATED_POT)) return;

        block.getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.EMERALD));
    }
}
