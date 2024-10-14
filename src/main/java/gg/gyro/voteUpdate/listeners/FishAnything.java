package gg.gyro.voteUpdate.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class FishAnything implements Listener {
    private Material getRandomMaterial() {
        return Material.values()[new Random().nextInt(Material.values().length)];
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if (!event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) return;
        if (!(event.getCaught() instanceof Item item)) return;
        item.setItemStack(new ItemStack(getRandomMaterial(), item.getItemStack().getAmount()));
    }
}
