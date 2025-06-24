package gg.gyro.voteUpdate.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DoubleOrHalf implements Listener {
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack result = e.getInventory().getResult();

        if (result == null) {
            return;
        }

        Random rand = new Random();
        int amount = result.getAmount();

        if (rand.nextBoolean()) {
            // Double the amount
            int newAmount = Math.min(amount * 2, result.getMaxStackSize());
            result.setAmount(newAmount);
        } else {
            // Halve the amount
            int newAmount = Math.max(1, amount / 2);
            result.setAmount(newAmount);
        }
    }
}
