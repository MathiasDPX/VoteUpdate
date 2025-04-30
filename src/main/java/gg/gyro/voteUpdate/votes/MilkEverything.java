package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MilkEverything extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.MILK_BUCKET);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.milkeverything.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.milkeverything.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.MilkEverything());
    }
}
