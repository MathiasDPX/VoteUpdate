package gg.gyro.voteUpdate.listeners;

import gg.gyro.localeAPI.Locales;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class EggFree implements Listener {
    Material material;

    public EggFree(Material material) {
        Bukkit.getServer().broadcast(Component.text(Locales.getInstance().get("options.egg_free.broadcast").replace("%material%", material.name().toLowerCase())));
        this.material = material;
    }

    @EventHandler
    public void onLayEgg(EntityDropItemEvent e) {
        if (!(e.getEntity() instanceof Chicken)) return;
        Item item = e.getItemDrop();

        item.setItemStack(new ItemStack(material, item.getItemStack().getAmount()));
    }
}
