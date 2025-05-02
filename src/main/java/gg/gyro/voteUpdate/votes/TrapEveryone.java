package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TrapEveryone extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CHAIN);
    }

    @Override
    public String getName() {
        return Locales.get("options.trapall.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.trapall.description");
    }

    @Override
    public void apply() {
        for (Player player : VoteUpdate.getInstance().getServer().getOnlinePlayers()) {
            Location playerLoc = player.getLocation();
            World world = player.getWorld();

            for (int x = -1; x <= 1; x++) {
                for (int z = -1; z <= 1; z++) {
                    world.getBlockAt(playerLoc.getBlockX() + x, playerLoc.getBlockY() - 1, playerLoc.getBlockZ() + z).setType(Material.STONE);
                    world.getBlockAt(playerLoc.getBlockX() + x, playerLoc.getBlockY() + 2, playerLoc.getBlockZ() + z).setType(Material.STONE);
                }
            }

            for (int x = -1; x <= 1; x++) {
                for (int y = 0; y <= 1; y++) {
                    for (int z = -1; z <= 1; z++) {
                        if (x == -1 || x == 1 || z == -1 || z == 1) {
                            world.getBlockAt(playerLoc.getBlockX() + x, playerLoc.getBlockY() + y, playerLoc.getBlockZ() + z).setType(Material.IRON_BARS);
                        }
                    }
                }
            }
        }
    }
}
