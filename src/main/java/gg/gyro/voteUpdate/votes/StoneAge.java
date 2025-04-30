package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class StoneAge extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.STONE);
    }

    @Override
    public String getName() {
        return Locales.get("options.stone_age.name");
    }

    @Override
    public String getDescription() {
        String playername = Locales.get("options.stone_age.playername");
        return Locales.get("options.stone_age.description").replace("%playername%", playername);
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.StoneAge());
        Component playername = Component.text(Locales.get("options.stone_age.playername"));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.displayName(playername);
        }
    }
}
