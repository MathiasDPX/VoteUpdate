package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VotesManager;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class President extends Vote {
    @Override
    public String getId() {
        return "president";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.STONE_SHOVEL);
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
        ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
        Player president = players.get(new Random().nextInt(players.size()));

        president.sendMessage(Locales.get(getLocaleRoot()+"message").replace("%president%", president.getName()));
        VotesManager.setPresident(president.getUniqueId());
    }
}
