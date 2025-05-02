package gg.gyro.voteUpdate.listeners;

import gg.gyro.localeAPI.Locales;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MilkEverything implements Listener {
    @EventHandler
    public void onMilking(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
        if (item.getType() == Material.BUCKET) {
            event.setCancelled(true);

            int amount = item.getAmount();
            if (amount > 1) {
                item.setAmount(amount - 1);
            } else {
                event.getPlayer().getInventory().setItemInMainHand(null);
            }

            Entity entity = event.getRightClicked();
            ItemStack bucket = new ItemStack(Material.MILK_BUCKET);
            ItemMeta meta = bucket.getItemMeta();
            String bucketname = null;
            String rootLocale = new gg.gyro.voteUpdate.votes.MilkEverything().getLocaleRoot();
            if (entity instanceof Player player) {
                bucketname = Locales.get(rootLocale+"playermilk").replace("%player%", player.getName());
            } else {
                bucketname = Locales.get(rootLocale+"mobmilk").replace("%mob%", entity.getName());
            }

            meta.displayName(Component.text(bucketname).decoration(TextDecoration.ITALIC, false));
            bucket.setItemMeta(meta);
            event.getPlayer().getInventory().addItem(bucket);
        }
    }
}
