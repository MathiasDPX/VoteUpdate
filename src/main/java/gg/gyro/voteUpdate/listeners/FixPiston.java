package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;

public class FixPiston implements Listener {
    @EventHandler
    void onPiston(BlockPistonExtendEvent event) {
        Block piston = event.getBlock();

        piston.setType(Material.AIR);
        piston.getWorld().createExplosion(piston.getLocation(), 4, false, true);
    }
}
