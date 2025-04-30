package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TransparentPlayers extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BARRIER);
    }

    @Override
    public String getName() {
        return Locales.get("options.transparent_players.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.transparent_players.description");
    }

    @Override
    public void apply() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setInvisible(true);
        }
    }
}
