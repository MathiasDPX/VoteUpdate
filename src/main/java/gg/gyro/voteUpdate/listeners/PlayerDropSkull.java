package gg.gyro.voteUpdate.listeners;

import gg.gyro.voteUpdate.utils.Skull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class PlayerDropSkull implements Listener {
    public static Component generateDeathText(World world) {
        long timeTicks = world.getTime();
        long totalDays = world.getFullTime() / 24000;

        int hours = (int) ((timeTicks / 1000 + 6) % 24);
        int minutes = (int) ((timeTicks % 1000) * 60 / 1000);

        String timeFormatted = String.format("%02d:%02d", hours, minutes);

        return Component.text("Dropped at " + timeFormatted + " on day " + totalDays).color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false);
    }

    public static Component prettifyWorldName(World world) {
        Component component = Component.text(world.getName());;
        if (world.getName().equals("world")) {
            component = Component.text("Overworld").color(NamedTextColor.BLUE);
        } else if (world.getName().equals("world_nether")) {
            component = Component.text("Nether").color(NamedTextColor.RED);
        } else if (world.getName().equals("world_the_end")) {
            component = Component.text("End").color(NamedTextColor.LIGHT_PURPLE);
        }
        return component;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getPlayer();
        List<ItemStack> drops = event.getDrops();

        ItemStack head = Skull.getPlayerSkull(player.getName());
        ItemMeta meta = head.getItemMeta();

        World world = player.getWorld();
        meta.displayName(Component.text(player.getName() + "'s Skull").decoration(TextDecoration.ITALIC, false));
        meta.lore(List.of(
                generateDeathText(world),
                Component.text("Killed in the ").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false).append(prettifyWorldName(world))
        ));
        head.setItemMeta(meta);
        drops.add(head);
    }
}
