package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class UnstableTNT implements Listener {
    @EventHandler
    void onPlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();
        if (!block.getType().equals(Material.TNT)) return;

        TNT blockdata = (TNT) block.getBlockData();
        blockdata.setUnstable(true);
    }
}
