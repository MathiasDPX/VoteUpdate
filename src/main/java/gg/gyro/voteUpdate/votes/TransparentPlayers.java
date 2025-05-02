package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TransparentPlayers extends Vote {
    @Override
    public String getId() {
        return "transparent_players";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.BARRIER);
    }

    @Override
    public String getName() {
        return Locales.get(getLocaleRoot()+"name");
    }

    @Override
    public String getDescription() {
        return Locales.get(getLocaleRoot()+"description");
    }

    @Override
    public void apply() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setInvisible(true);
        }
    }
}
