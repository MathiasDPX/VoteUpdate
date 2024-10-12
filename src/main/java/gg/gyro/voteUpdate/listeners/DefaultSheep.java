package gg.gyro.voteUpdate.listeners;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.Random;

public class DefaultSheep implements Listener {
    DyeColor color;

    public DefaultSheep() {
        this.color = DyeColor.values()[new Random().nextInt(DyeColor.values().length)];
        Bukkit.getServer().broadcast(Component.text("Sheep while now spawn "+color.name()));
    }

    @EventHandler
    public void onSheepSpawn(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Sheep sheep) {
            sheep.setColor(color);
        }
    }
}
