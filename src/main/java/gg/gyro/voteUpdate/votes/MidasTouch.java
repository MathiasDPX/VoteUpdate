package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MidasTouch extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.GOLD_INGOT);
    }

    @Override
    public String getName() {
        return Locales.get("options.midas_touch.name");
    }

    @Override
    public String getDescription() {
        return Locales.get("options.midas_touch.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.MidasTouch());
    }
}
