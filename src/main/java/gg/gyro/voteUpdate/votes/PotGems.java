package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PotGems extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.DECORATED_POT);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.pot_gems.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.pot_gems.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.PotGems());
    }
}
