package gg.gyro.voteUpdate.listeners;

import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

public class ChargedCreeper implements Listener {
    @EventHandler
    public void onCreeper(EntitySpawnEvent event) {
        if (!(event.getEntity() instanceof Creeper creeper)) return;
        creeper.setPowered(true);
    }
}
