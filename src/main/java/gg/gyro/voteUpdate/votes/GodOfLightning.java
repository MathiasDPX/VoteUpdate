package gg.gyro.voteUpdate.votes;

import gg.gyro.localeAPI.Locales;
import gg.gyro.voteUpdate.VoteUpdate;
import gg.gyro.voteUpdate.utils.Vote;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GodOfLightning extends Vote {
    @Override
    public String getId() {
        return "god_of_lightning";
    }

    @Override
    public ItemStack getIcon() {
        return new ItemStack(Material.LIGHTNING_ROD);
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
        VoteUpdate.registerEvents(new gg.gyro.voteUpdate.listeners.GodOfLightning());
    }
}
