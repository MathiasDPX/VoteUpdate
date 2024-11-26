package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class UnstableTNT extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.TNT);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.unstable_tnt.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.unstable_tnt.name");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.UnstableTNT());
    }
}
