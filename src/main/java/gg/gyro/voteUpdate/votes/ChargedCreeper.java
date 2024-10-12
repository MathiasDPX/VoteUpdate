package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChargedCreeper extends Vote {
    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.CREEPER_HEAD);
    }

    @Override
    public String getName() {
        return Locales.getInstance().get("options.charged_creeper.name");
    }

    @Override
    public String getDescription() {
        return Locales.getInstance().get("options.charged_creeper.description");
    }

    @Override
    public void apply() {
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.ChargedCreeper());
    }
}
