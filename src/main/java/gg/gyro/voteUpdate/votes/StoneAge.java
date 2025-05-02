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
    public String getId() {
        return "stone_age";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.STONE);
    }

    @Override
    public String getName() {
        return Locales.get(getLocaleRoot()+"name");
    }

    @Override
    public String getDescription() {
        String playername = Locales.get(getLocaleRoot()+"playername");
        return Locales.get(getLocaleRoot()+"description").replace("%playername%", playername);
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.StoneAge());
        Component playername = Component.text(Locales.get(getLocaleRoot()+"playername"));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.displayName(playername);
        }
    }
}
