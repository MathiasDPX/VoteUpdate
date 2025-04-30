package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FishAnything extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.FISHING_ROD);
    }

    @Override
    public String getName() {
        return Locales.get("options.fish_anything.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.fish_anything.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.FishAnything());
    }
}
